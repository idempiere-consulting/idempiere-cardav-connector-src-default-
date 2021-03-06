package it.cnet.connection.core;

import it.cnet.connection.pojo.AdCaldavparam;
import it.cnet.connection.pojo.AdSysconfig;
import it.cnet.connection.pojo.AdUser;
import it.cnet.connection.pojo.RRequest;
import it.cnet.connection.pojo.RRequesttype;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.HasPropertyRule;
import net.fortuna.ical4j.filter.Rule;
import net.fortuna.ical4j.model.DateTime;
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
import zswi.protocols.communication.core.DavStore;
import zswi.protocols.communication.core.DavStore.DavStoreException;
import zswi.protocols.communication.core.DavStore.NotSupportedComponent;
import zswi.protocols.communication.core.DavStore.UidConflict;

/**
 * Creating new events on calendar web Kerio. Events created by new requests on iDempiere
 * @author andrea
 *
 */
public class CalDav_Connect {

	private String PERSISTENCE_UNIT_NAME = "cdav-connector_CNET";
	private EntityManagerFactory factory;
	private EntityManager em;

	private CDav_Cnet cDav = null;

	private CalendarCollection principalCalendarColl = null;

	private int CALDAVTimeunit = 0;
	private int NRDAYSCALDAVBEFORE = 0;



	private static Logger logger=Logger.getLogger(CalDav_Connect.class);

	public CalDav_Connect(){

		//**************
		//   Test 

		/*	
		String username = "checchia.andrea";
		String password = "pichosisto";
		String url = "https://posta.consul-net.it/";
		cDav = new CDav_Cnet(username, password, url);
		 */
		//
		//**************


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
		CalDav_Connect connect = new CalDav_Connect();
		connect.syncCaldav();

	}

	private void syncCaldav(){

		//**************
		//   Test 

		/*	
		String nameCalendar = "Calendar";

		List<CalendarCollection> listCalUser = cDav.getListCalUser();
		for (CalendarCollection calendarColl : listCalUser) {

			if(calendarColl.getDisplayName().equals(nameCalendar)){
				principalCalUser = calendarColl;
				break;
			}
		}

		createEventCalTest();
		 */	
		//
		//**************

		logger.info("Initial sync...");
		createEventCal();


	}
	@SuppressWarnings("unchecked")
	private void createEventCal(){

		AdUser user = null;
		RRequesttype reqType = null;
		String calendarName = "";
		String calNameCaldav = "Calendar";
		boolean isOk = false;


		Query qry = em.createQuery("select c from AdCaldavparam c where c.calendar like :strCaldav");
		qry.setParameter("strCaldav", "%calendar%");

		List<AdCaldavparam> listCalDparams = qry.getResultList();

		for (AdCaldavparam param : listCalDparams) {

			qry = em.createQuery("select t from RRequest t where t.rRequestSync = ?1 and t.adClientId = ?2");
			qry.setParameter(1, "N");
			qry.setParameter(2, param.getAdClientId());

			List<RRequest> reqList = qry.getResultList();

			logger.info("Requests to syncronize = "+ reqList.size());

			for (RRequest rRequest : reqList) {

				user = em.find(AdUser.class, rRequest.getSalesrepId().longValue());
				reqType = rRequest.getRRequesttype();

				calendarName = reqType.getCalendar();


				qry = em.createQuery("select c from AdCaldavparam c where c.adUser = :pUser and c.RRequesttype = :pReqType " +
						"and c.calendar = :pCalendar");
				qry.setParameter("pUser", user);
				qry.setParameter("pReqType", reqType);
				qry.setParameter("pCalendar", calendarName);

				logger.info("Query_ADCaldavParam: "+ qry.toString() +
						"\n**********************" +
						"\nParam: " +
						"\nuser = "+user.getAdUserId()+"_"+user.getName() +
						"\nreqType = "+reqType.getRRequesttypeId()+"_"+reqType.getName() + 
						"\ncalendarName = "+reqType.getCalendar() + 
						"\n**********************");
				logger.info("Query_ADCaldavParam_resultSize = "+ qry.getResultList().size());

				AdCaldavparam calDparam = null;

				if(qry.getResultList().size() <= 0){

					//Query for ALL user....ad_user_id = NULL
					qry = em.createQuery("select c from AdCaldavparam c where c.RRequesttype = :pReqType " +
							"and c.calendar = :pCalendar and c.namepubliccalendar = :pAll");

					qry.setParameter("pReqType", reqType);
					qry.setParameter("pCalendar", calendarName);
					qry.setParameter("pAll", "all");

					if(qry.getResultList().size() > 0)
						calDparam = (AdCaldavparam) qry.getSingleResult();
					else 
						continue;
				}
				else
					calDparam = (AdCaldavparam) qry.getSingleResult();

				String[] nameUserCaldav = calDparam.getNameusercaldav().split(";");
				logger.info("ADCaldavParam.getNameusercaldav = "+ nameUserCaldav.toString());

				for (String strUser : nameUserCaldav) {
					String userPublic = "";

					logger.info("userCaldav = "+ strUser);


					if(strUser.contains("@ad_user")){
						qry = em.createQuery("select us from AdUser us where us.adUserId = "+rRequest.getSalesrepId());
					}
					else if(strUser.contains("publicCal")){
						qry = em.createQuery("select us from AdUser us where us.name = :pXdav");
						userPublic = strUser;

						qry.setParameter("pXdav", strUser);
					}
					else{
						qry = em.createQuery("select us from AdUser us where us.usernamexdav = :pXdav");
						qry.setParameter("pXdav", strUser);
					}



					logger.info("Query_ADUser_forCaldav: "+ qry.toString());

					if(qry.getResultList().size() >0){
						user = (AdUser) qry.getResultList().get(0);
						
						if(user.getUsernamexdav() != null && !user.getUsernamexdav().equals("") && 
								user.getCaldavpath()!=null && !user.getCaldavpath().equals("")){
							cDav = new CDav_Cnet(user.getUsernamexdav(), user.getPasswordxdav(), user.getCaldavpath());

							if(!userPublic.equals("")&& user.getName().contains("publicCal")){
								calNameCaldav = userPublic.substring("publicCal:".length());
								setPrincipalCalendar(calNameCaldav, true);
							}
							else
								setPrincipalCalendar(calNameCaldav, false);

							logger.info("cdav__User/Path: "+ user.getUsernamexdav()+" / "+user.getCaldavpath());
							logger.info("cdav__PrincipalCalendar: "+ principalCalendarColl.getDisplayName());

							if(createVCalendar(cDav, rRequest))
								isOk = true;
							else
								isOk = false;

						}
					}
				}

				if(isOk){
					em.getTransaction().begin();

					rRequest.setRRequestSync("Y");

					em.merge(rRequest);
					em.getTransaction().commit();

					isOk = false;
				}

			}

		}
		em.close();
		factory.close();
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



	private void testConnect(){
		try {
			DavStore prova = cDav.getDav();
			//System.out.println("URI:  "+prova.principalCollection().getCalendarHomeSet().getUri());

			List<CalendarCollection> list = prova.principalCollection().getCalendarHomeSet().getCalendarCollections();
			//System.out.println("List.size = "+list.size());

			/*
			for (CalendarCollection calendarColl : list) {
				if(calendarColl.getDisplayName().equals("Calendar")){
					princ = calendarColl;
					break;
				}
			}
			 */

			List<ServerVCalendar>listVcal= prova.getVCalendars(principalCalendarColl);



			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();

			Query q = em.createQuery("select t from RRequest t");
			List<RRequest> reqList = q.getResultList();

			StringBuffer strId = new StringBuffer();
			int i = 0;
			for (RRequest req : reqList) {
				if(req.getStarttime()!=null && req.getEndtime()!= null){
					i++;
					strId = strId.append(req.getRRequestId()).append(req.getStarttime().getTime()).append(req.getEndtime().getTime());
					//strId = strId.append("1223444");
					Rule uidRuleMatch = new HasPropertyRule(new Uid(strId.toString()));
					Filter filter = new Filter(new Rule[] {uidRuleMatch}, Filter.MATCH_ALL);
					if(filter !=null){
						for (ServerVCalendar serverVCalendar : listVcal) {
							//System.out.println("vCalendar:::: "+serverVCalendar.getVCalendar().getComponents().toString());
							System.out.println("\n"+filter.filter(serverVCalendar.getVCalendar().getComponents()).toString());
						}
						System.out.println("Booooh:"+i+":: "+filter.toString());
					}
				}
			}

			// http://www.java2s.com/Open-Source/Java/Development/iCal4j-1.0/net/fortuna/ical4j/filter/FilterTest.java.htm
			//
			/*
			Rule organiserRuleMatch = new HasPropertyRule(new ));
			Filter filter = new Filter(new Rule[] {organiserRuleMatch, attendeeRuleMatch}, Filter.MATCH_ALL);
	        suite.addTest(new FilterTest("testFilteredIsNotEmpty", filter, calendar.getComponents()));
			 */






			//CalendarCollection claTest = new CalendarCollection("");
		} catch (DavStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void testDB (){

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		em = factory.createEntityManager();

		// read the existing entries and write to console
		Query q = em.createQuery("select t from RRequest t");
		List<RRequest> reqList = q.getResultList();
		for (RRequest req : reqList) {
			System.out.println(req.toString());
		}
		System.out.println("Size: " + reqList.size());

		em.close();
	}


}
