package it.cnet.connection.core;

import it.cnet.connection.pojo.AdCaldavparam;
import it.cnet.connection.pojo.AdSysconfig;
import it.cnet.connection.pojo.AdUser;
import it.cnet.connection.pojo.RRequest;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.http.client.ClientProtocolException;
import org.apache.log4j.Logger;

import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.PeriodRule;
import net.fortuna.ical4j.filter.Rule;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.component.VTimeZone;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import zswi.objects.dav.collections.CalendarCollection;
import zswi.protocols.caldav.ServerVCalendar;
import zswi.protocols.communication.core.DavStore.DateNotUtc;
import zswi.protocols.communication.core.DavStore.DavStoreException;
import zswi.protocols.communication.core.DavStore.NotSupportedComponent;
import zswi.protocols.communication.core.DavStore.UidConflict;
/**
 * Synchronization of requests on the system iDempiere / Kerio
 * @author andrea
 *
 */
public class CalDav_Connect_Sync {

	private String PERSISTENCE_UNIT_NAME = "cdav-connector_CNET";
	private EntityManagerFactory factory;
	private EntityManager em;

	private CDav_Cnet cDav = null;

	private CalendarCollection principalCalendarColl = null;

	private int CALDAVTimeunit = 0;
	private int NRDAYSCALDAVBEFORE = 0;



	private static Logger logger=Logger.getLogger(CalDav_Connect_Sync.class);

	public CalDav_Connect_Sync(){

		//read request from DB iDempiere
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

		Query qry = em.createQuery("select ads from AdSysconfig ads where ads.name = ?1");
		qry.setParameter(1, "CALDAVTimeunit");
		if(qry.getResultList().size()>0)
			CALDAVTimeunit = Integer.parseInt(((AdSysconfig)qry.getResultList().get(0)).getValue());

		qry = em.createQuery("select ads from AdSysconfig ads where ads.name = ?1");
		qry.setParameter(1, "NRDAYSCALDAVBEFORE");
		if(qry.getResultList().size()>0)
			NRDAYSCALDAVBEFORE = Integer.parseInt(((AdSysconfig)qry.getResultList().get(0)).getValue());


	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CalDav_Connect_Sync connect = new CalDav_Connect_Sync();
		connect.syncCaldav();

	}

	private void syncCaldav(){


		logger.info("Initial sync...");

		syncCaldav_iDempiereCalendar();



	}


	@SuppressWarnings("unchecked")
	private void syncCaldav_iDempiereCalendar(){
		
		List<BigDecimal> listClientID = null;

		String calNameCaldav = "";

		Calendar calToday = null;
		Date dtDayToCaldav = null;
		Date dtDayStartCaldav = null;

		Query qry = em.createQuery("select c from AdCaldavparam c where c.calendar like :strCalendar");
		qry.setParameter("strCalendar", "%calendar%");

		logger.info("Query_ADCaldavParam_resultSize = "+ qry.getResultList().size());

		//AdCaldavparam calDparam = (AdCaldavparam) qry.getSingleResult();
		List<AdCaldavparam> listCalDparams = qry.getResultList();
		
		if(listCalDparams.size()>0)
			listClientID = new ArrayList<BigDecimal>();
		
		for (AdCaldavparam adCaldavparam : listCalDparams) {
			
			if(listClientID.contains(adCaldavparam.getAdClientId()))
				continue;
			else
				listClientID.add(adCaldavparam.getAdClientId());

			qry = em.createQuery("select us from AdUser us where us.adClientId = ?1");
			qry.setParameter(1, adCaldavparam.getAdClientId());
			List<AdUser> userList = qry.getResultList();

			for (AdUser adUser : userList) {
				if(adUser.getUsernamexdav() != null && !adUser.getUsernamexdav().equals("") && 
						adUser.getCaldavpath()!=null && !adUser.getCaldavpath().equals("")){
					cDav = new CDav_Cnet(adUser.getUsernamexdav(), adUser.getPasswordxdav(), adUser.getCaldavpath());

					if(adUser.getName().contains("publicCal")){
						calNameCaldav = adUser.getName().substring("publicCal:".length());
						setPrincipalCalendar(calNameCaldav, true);
					}
					else{
						calNameCaldav = "Calendar";
						setPrincipalCalendar(calNameCaldav, false);
					}

					logger.info("AdUser = "+adUser.getName()+" --- cdav__User/Path: "+ adUser.getUsernamexdav()+" / "+adUser.getCaldavpath());
					logger.info("cdav__PrincipalCalendar: "+ principalCalendarColl.getDisplayName());

					calToday = Calendar.getInstance();
					dtDayToCaldav = calToday.getTime();
					System.out.println("dtDayToCaldav_TODAY= "+dtDayToCaldav.toString());

					calToday.roll(Calendar.DAY_OF_YEAR, -NRDAYSCALDAVBEFORE);
					System.out.println("calToday-"+NRDAYSCALDAVBEFORE+" = "+calToday.getTime().toString());

					if(calToday.getTime().after(dtDayToCaldav))
						calToday.roll(Calendar.YEAR, -1);
					dtDayStartCaldav = calToday.getTime();
					System.out.println("dtDayStartCaldav= "+dtDayStartCaldav.toString());

					calToday.setTimeInMillis(dtDayToCaldav.getTime());
					calToday.roll(Calendar.DAY_OF_YEAR, NRDAYSCALDAVBEFORE);
					dtDayToCaldav = calToday.getTime();
					System.out.println("dtDayToCaldav(today+"+NRDAYSCALDAVBEFORE+" = "+dtDayToCaldav.toString());

					DateTime dtStart = new DateTime(dtDayStartCaldav);
					DateTime dtEnd = new DateTime(dtDayToCaldav);
					Period period = new Period(dtStart, dtEnd);
					logger.info("Period... : "+period.toString());

					Filter filter = new Filter(new Rule[] {new PeriodRule(period)}, Filter.MATCH_ANY);

					List<ServerVCalendar>listVcal = null;
					try {
						listVcal= cDav.getDav().getVCalendars(principalCalendarColl);
					} catch (DavStoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					for (ServerVCalendar serverVCalendar : listVcal) {
						//System.out.println("vCalendar:::: "+serverVCalendar.getVCalendar().getComponents().toString());
						//System.out.println("\n"+filter.filter(serverVCalendar.getVCalendar().getComponents(Component.VEVENT)).toString());

						for (Iterator<Component> i = filter.filter(serverVCalendar.getVCalendar().getComponents(/*Component.VEVENT*/)).iterator(); i.hasNext();) {
							Component c = (Component) i.next();

							VEvent eventCal = null;
							if (c instanceof VEvent) {
								eventCal = ((VEvent) c);
								Uid uid = eventCal.getUid();
								String uidValue = uid.getValue();


								RRequest request = null;

								if(uidValue.contains("@req")){

									logger.info("UID_value: < "+uidValue+" >");
									uidValue = uidValue.substring(uidValue.indexOf("@req")+4);
									uidValue = uidValue.substring(0, uidValue.indexOf("@"));
									long rRequestId = Long.parseLong(uidValue);

									request = em.find(RRequest.class, rRequestId);
									
									if(request == null){
										try {
											cDav.getDav().deleteVCalendar(serverVCalendar);
											
											continue;
										} catch (URISyntaxException
												| IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}

									if(eventCal.getPriority() != null){

										logger.info("Event update in webCalendar.....");
										em.getTransaction().begin();

										request.setSummary(eventCal.getSummary().getValue());
										request.setStarttime(new Timestamp(eventCal.getStartDate().getDate().getTime()));
										request.setDatestartplan(new Timestamp(eventCal.getStartDate().getDate().getTime()));
										request.setEndtime(new Timestamp(eventCal.getEndDate().getDate().getTime()));
										request.setDatecompleteplan(new Timestamp(eventCal.getEndDate().getDate().getTime()));

										em.merge(request);
										em.getTransaction().commit();

										createVCalendar(cDav, request);

									}
									else if(!request.getStarttime().equals(new Timestamp(eventCal.getStartDate().getDate().getTime())) || 
											!request.getEndtime().equals(new Timestamp(eventCal.getEndDate().getDate().getTime())) || 
											!request.getSummary().equals(eventCal.getSummary().getValue())) {

										logger.info("Event update in iDempiere.....");

										createVCalendar(cDav, request);

									}


								}
							}
						}
					}

				}
			}

		}
	}

	private void setPrincipalCalendar(String nameCaldavCalendar, boolean isPublic){
		List<CalendarCollection> listCalCollection = null;

		if(isPublic)
			listCalCollection = cDav.getCalHomePublic().getCalendarCollections();
		else
			listCalCollection = cDav.getListCalUser();

		for (CalendarCollection calendarColl : listCalCollection) {

			if(calendarColl.getDisplayName().equals(nameCaldavCalendar)){
				principalCalendarColl = calendarColl;
				break;
			}
		}
	}

	private boolean createVCalendar(CDav_Cnet cDav, RRequest req) {
		boolean isOk = true;

		// Create a TimeZone
		TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
		TimeZone timezone = registry.getTimeZone("Europe/Rome");
		VTimeZone tz = timezone.getVTimeZone();

		if(req.getStarttime()!=null && req.getEndtime()!= null){

			// Start Date
			java.util.Calendar startDate = new GregorianCalendar();
			startDate.setTimeZone(timezone);
			startDate.setTimeInMillis(req.getStarttime().getTime());

			// End Date
			java.util.Calendar endDate = new GregorianCalendar();
			endDate.setTimeZone(timezone);
			endDate.setTimeInMillis(req.getEndtime().getTime());

			// Create the event
			//String eventName = "Progress Meeting";
			String eventName = req.getSummary();
			DateTime start = new DateTime(startDate.getTime());
			DateTime end = new DateTime(endDate.getTime());
			VEvent meeting = new VEvent(start, end, eventName);

			// add timezone info..
			meeting.getProperties().add(tz.getTimeZoneId());

			// generate unique identifier..
			StringBuffer id = new StringBuffer();
			id = id.append("@req").append(req.getRRequestId()).append("@").append(req.getRRequestUu());
			Uid uid = new Uid(id.toString());
			meeting.getProperties().add(uid);

			// add attendees..
			/*
				Attendee dev1 = new Attendee(URI.create("mailto:dev1@mycompany.com"));
				dev1.getParameters().add(Role.REQ_PARTICIPANT);
				dev1.getParameters().add(new Cn("Developer 1"));
				meeting.getProperties().add(dev1);

				Attendee dev2 = new Attendee(URI.create("mailto:dev2@mycompany.com"));
				dev2.getParameters().add(Role.OPT_PARTICIPANT);
				dev2.getParameters().add(new Cn("Developer 2"));
				meeting.getProperties().add(dev2);
			 */

			// Create a calendar
			net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
			icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 1.0//EN"));
			icsCalendar.getProperties().add(Version.VERSION_2_0);
			icsCalendar.getProperties().add(CalScale.GREGORIAN);

			// Add the event and print
			icsCalendar.getComponents().add(meeting);
			//System.out.println(icsCalendar);

			logger.info("create VCalendar: \n"+ icsCalendar);

			try {
				cDav.getDav().addVCalendar(principalCalendarColl, icsCalendar);
				//dav.updateVCalendar(calendar);

			} catch (DavStoreException | UidConflict | NotSupportedComponent
					| ValidationException e) {
				//e.printStackTrace();

				//When go by in this catch, VEvent updated
				isOk = true;

			}

		}


		return isOk;
	}


}
