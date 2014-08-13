package it.cnet.connection.core;

import it.cnet.connection.pojo.AdCaldavparam;
import it.cnet.connection.pojo.AdSysconfig;
import it.cnet.connection.pojo.AdUser;
import it.cnet.connection.pojo.CBpartner;
import it.cnet.connection.pojo.CBpartnerLocation;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.property.Uid;
import net.sourceforge.cardme.db.MarkType;
import net.sourceforge.cardme.engine.ParameterType;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.features.TelephoneFeature;
import net.sourceforge.cardme.vcard.types.AddressType;
import net.sourceforge.cardme.vcard.types.BeginType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.EndType;
import net.sourceforge.cardme.vcard.types.FormattedNameType;
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.OrganizationType;
import net.sourceforge.cardme.vcard.types.RevisionType;
import net.sourceforge.cardme.vcard.types.TelephoneType;
import net.sourceforge.cardme.vcard.types.UIDType;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.ParameterTypeStyle;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
import zswi.objects.dav.collections.AddressBookCollection;
import zswi.protocols.communication.core.DavStore;
import zswi.protocols.communication.core.DavStore.DavStoreException;

/**
 * Creating new events on calendar web Kerio. Events created by new requests on iDempiere
 * @author andrea
 *
 */
public class CardDav_Connect {

	private String PERSISTENCE_UNIT_NAME = "cardDav-connector_CNET";
	private EntityManagerFactory factory;
	private EntityManager em;

	private CardDav_Cnet cDav = null;

	private AddressBookCollection principalAddBookColl = null;

	private int CALDAVTimeunit = 0;
	private int NRDAYSCALDAVBEFORE = 0;



	private static Logger logger=Logger.getLogger(CardDav_Connect.class.getCanonicalName());

	public CardDav_Connect(){

		//**************
		//   Test 

		/*
		String username = "checchia.andrea";
		String password = "pichosisto";
		String url = "https://posta.consul-net.it/";
		cDav = new CardDav_Cnet(username, password, url);
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
		CardDav_Connect connect = new CardDav_Connect();
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
		//testConnect();
		createContact();

	}

	@SuppressWarnings("unchecked")
	private void createContact(){

		String[] nameUserCardav;
		String cardavName = "CARDAV";

		boolean isOk = false;


		Query qry = em.createQuery("select c from AdCaldavparam c where c.calendar = :pCalendar");
		qry.setParameter("pCalendar", cardavName);

		logger.info("Query_ADCaldavParam: "+ qry.toString() +
				"\n**********************" +
				"\nParam: " +
				"\ncalendarName = "+cardavName + 
				"\n**********************");
		logger.info("Query_ADCaldavParam_resultSize = "+ qry.getResultList().size());

		//AdCaldavparam calDparam = (AdCaldavparam) qry.getSingleResult();
		List<AdCaldavparam> listCalDparams = qry.getResultList();

		for (AdCaldavparam calDparam : listCalDparams) {

			qry = em.createQuery("select bp from CBpartner bp where bp.cardavsync = ?1 and bp.adClientId = ?2");
			qry.setParameter(1, 1);
			qry.setParameter(2, calDparam.getAdClientId());
			List<CBpartner> bpartList = qry.getResultList();
			List<CBpartnerLocation> listBPLocations = null;

			qry = em.createQuery("select us from AdUser us where us.cardavsync = ?1 and us.adClientId = ?2");
			qry.setParameter(1, 1);
			qry.setParameter(2, calDparam.getAdClientId());
			List<AdUser>userList = qry.getResultList();



			//sync for BPartner
			for (CBpartner cBpartner : bpartList) {
				listBPLocations = cBpartner.getCBpartnerLocations();
				for (CBpartnerLocation cBpartnerLocation : listBPLocations) {

					if(calDparam.getNameusercaldav()!= null || !calDparam.getNameusercaldav().equals("")){

						nameUserCardav = calDparam.getNameusercaldav().split(";");
						logger.info("ADCaldavParam.getNameusercaldav = "+ nameUserCardav.toString());

						for (String strUser : nameUserCardav) {

							if(createConnectAddBook(strUser)){

								if(createVCard_bPart(cDav, cBpartnerLocation))
									isOk = true;
								else
									isOk = false;
							}

							cDav = null;
						}
					}
				}

				if(isOk){
					em.getTransaction().begin();

					cBpartner.setCardavsync(new BigDecimal(2));

					em.merge(cBpartner);
					em.getTransaction().commit();

					isOk = false;
				}
			}

			//sync for User
			for (AdUser adUser : userList) {
				if(calDparam.getNameusercaldav()!= null || !calDparam.getNameusercaldav().equals("")){

					nameUserCardav = calDparam.getNameusercaldav().split(";");
					logger.info("ADCaldavParam.getNameusercaldav = "+ nameUserCardav.toString());

					for (String strUser : nameUserCardav) {

						if(createConnectAddBook(strUser)){

							if(createVCard_user(cDav, adUser))
								isOk = true;
							else
								isOk = false;
						}

						cDav = null;
					}
				}

				if(isOk){
					em.getTransaction().begin();

					adUser.setCardavsync(new BigDecimal(2));

					em.merge(adUser);
					em.getTransaction().commit();

					isOk = false;
				}
			}
		}

		em.close();
		factory.close();
	}

	private boolean createConnectAddBook(String strUser){
		Query qry = null;
		AdUser user = null;
		boolean isConnect = false;

		String userPublic = "";
		String addBookNameCardav = "";

		logger.info("userCardav = "+ strUser);

		if(strUser.contains("publicCard")){
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
					user.getCardavpath()!=null && !user.getCardavpath().equals("")){
				cDav = new CardDav_Cnet(user.getUsernamexdav(), user.getPasswordxdav(), user.getCardavpath());

				if(!userPublic.equals("")&& user.getName().contains("publicCard:")){
					addBookNameCardav = userPublic.substring("publicCard:".length());
					setPrincipalAddBook(addBookNameCardav, true);
				}
				else if(!userPublic.equals("")&& user.getName().contains("publicCard")){
					addBookNameCardav = "Contacts";
					setPrincipalAddBook(addBookNameCardav, true);
				}
				else{
					addBookNameCardav = "Contacts";
					setPrincipalAddBook(addBookNameCardav, false);
				}

				logger.info("cdav__User/Path: "+ user.getUsernamexdav()+" / "+user.getCardavpath());
				logger.info("cdav__PrincipalCalendar: "+ principalAddBookColl.getDisplayName());

				isConnect = true;
			}
		}
		else
			isConnect = false;
		
		return isConnect;
	}

	private void setPrincipalAddBook(String nameCaldavCalendar, boolean isPublic){
		List<AddressBookCollection> listAddBookCollection = null;

		if(isPublic)
			listAddBookCollection = cDav.getAddBookHomePublic().getAddressbookCollections();
		else
			listAddBookCollection = cDav.getListAddBookUser();

		for (AddressBookCollection addBookColl : listAddBookCollection) {

			if(addBookColl.getDisplayName().equals(nameCaldavCalendar)){
				principalAddBookColl = addBookColl;
				break;
			}
		}
	}

	private boolean createVCard_bPart(CardDav_Cnet cDav, CBpartnerLocation cBpartnerLocation){
		boolean isOk = true;

		StringBuffer msg = new StringBuffer();
		String nameBPartener = "";
		String mailBPartner = "";
		String countryName = "";
		String streetAdd = "";
		String locality = "";
		String region = "";
		String postalCode = "";
		String phoneNumber = "";
		String faxNumber = "";


		nameBPartener = (cBpartnerLocation.getCBpartner().getName()==null)?"":cBpartnerLocation.getCBpartner().getName();
		mailBPartner = (cBpartnerLocation.getEmail()==null)?"":cBpartnerLocation.getEmail();

		if(cBpartnerLocation.getCLocation()!=null){
			countryName = (cBpartnerLocation.getCLocation().getCCountry().getName()==null)?"":cBpartnerLocation.getCLocation().getCCountry().getName();
			streetAdd = (cBpartnerLocation.getCLocation().getAddress1()==null)?"":cBpartnerLocation.getCLocation().getAddress1();
			locality = (cBpartnerLocation.getCLocation().getCity()==null)?"":cBpartnerLocation.getCLocation().getCity();
			region = (cBpartnerLocation.getCLocation().getCRegion().getName()==null)?"":cBpartnerLocation.getCLocation().getCRegion().getName();
			postalCode = (cBpartnerLocation.getCLocation().getPostal()==null)?"":cBpartnerLocation.getCLocation().getPostal();
		}

		phoneNumber = (cBpartnerLocation.getPhone()==null)?"":cBpartnerLocation.getPhone();
		faxNumber = (cBpartnerLocation.getFax()==null)?"":cBpartnerLocation.getFax();

		logger.info("CBpartnerLocation: \n"+ cBpartnerLocation.toString());


		VCard vCard = new VCardImpl();
		//vCard.setBegin(new BeginType());
		vCard.setFormattedName(new FormattedNameType(nameBPartener));
		vCard.setName(new NameType(nameBPartener));
		vCard.addEmail(new EmailType(mailBPartner));

		AddressType address = new AddressType();
		address.addAddressParameterType(AddressParameterType.WORK);
		address.setCountryName(countryName);
		address.setStreetAddress(streetAdd);
		address.setLocality(locality);
		address.setRegion(region);
		address.setPostalCode(postalCode);
		vCard.addAddress(address);

		TelephoneFeature ff = new TelephoneType(phoneNumber, TelephoneParameterType.WORK);
		ff.mark(MarkType.INSERT);
		vCard.addTelephoneNumber(ff);
		vCard.addTelephoneNumber(new TelephoneType(faxNumber, TelephoneParameterType.FAX));

		//vCard.setEnd(new EndType());

		// generate unique identifier..
		StringBuffer id = new StringBuffer();
		id = id.append("@bp").append(cBpartnerLocation.getCBpartner().getCBpartnerId()).append("@").append(cBpartnerLocation.getCBpartnerLocationUu());
		Uid uid = new Uid(id.toString());
		vCard.setUID(new UIDType(uid.getValue()));

		Calendar cal = Calendar.getInstance();
		vCard.setRevision(new RevisionType(cal));

		logger.info("create VCard: \n"+ vCard);

		try {
			cDav.getDav().addVCard(principalAddBookColl, vCard);
			//dav.updateVCalendar(calendar);

		} catch (DavStoreException e) {
			//e.printStackTrace();

			//When go by in this catch, VCard updated
			isOk = true;

		}

		return isOk;
	}


	private boolean createVCard_user(CardDav_Cnet cDav, AdUser adUser){
		boolean isOk = true;

		StringBuffer msg = new StringBuffer();
		String nameUser = "";
		String orgUser = "";
		String mailUser = "";
		String countryName = "";
		String streetAdd = "";
		String locality = "";
		String region = "";
		String postalCode = "";
		String phoneNumber = "";
		String faxNumber = "";

		nameUser = (adUser.getName()==null)?"":adUser.getName();

		if(adUser.getCBpartner()!=null)
			orgUser = (adUser.getCBpartner().getName()==null)?"":adUser.getCBpartner().getName();

		mailUser = (adUser.getEmail()==null)?"":adUser.getEmail();

		if(adUser.getCLocation()!= null){
			countryName = (adUser.getCLocation().getCCountry().getName()==null)?"":adUser.getCLocation().getCCountry().getName();
			streetAdd = (adUser.getCLocation().getAddress1()==null)?"":adUser.getCLocation().getAddress1();
			locality = (adUser.getCLocation().getCity()==null)?"":adUser.getCLocation().getCity();
			region = (adUser.getCLocation().getCRegion().getName()==null)?"":adUser.getCLocation().getCRegion().getName();
			postalCode = (adUser.getCLocation().getPostal()==null)?"":adUser.getCLocation().getPostal();
		}

		phoneNumber = (adUser.getPhone()==null)?"":adUser.getPhone();
		faxNumber = (adUser.getFax()==null)?"":adUser.getFax();

		logger.info("AdUser: \n"+ adUser.toString());

		VCard vCard = new VCardImpl();
		//vCard.setBegin(new BeginType());
		vCard.setFormattedName(new FormattedNameType(nameUser));
		vCard.setName(new NameType(nameUser));
		vCard.setOrganizations(new OrganizationType(orgUser));
		vCard.addEmail(new EmailType(mailUser));

		AddressType address = new AddressType();
		address.addAddressParameterType(AddressParameterType.WORK);
		address.setCountryName(countryName);
		address.setStreetAddress(streetAdd);
		address.setLocality(locality);
		address.setRegion(region);

		address.setPostalCode(postalCode);
		vCard.addAddress(address);

		TelephoneFeature ff = new TelephoneType(phoneNumber, TelephoneParameterType.WORK);
		ff.mark(MarkType.INSERT);
		vCard.addTelephoneNumber(ff);
		vCard.addTelephoneNumber(new TelephoneType(faxNumber, TelephoneParameterType.FAX));

		//vCard.setEnd(new EndType());

		// generate unique identifier..
		StringBuffer id = new StringBuffer();
		id = id.append("@us").append(adUser.getAdUserId()).append("@").append(adUser.getAdUserUu());
		Uid uid = new Uid(id.toString());
		vCard.setUID(new UIDType(uid.getValue()));

		logger.info("create VCard: \n"+ vCard);

		try {
			cDav.getDav().addVCard(principalAddBookColl, vCard);
			//dav.updateVCalendar(calendar);

		} catch (DavStoreException e) {
			//e.printStackTrace();

			//When go by in this catch, VCard updated
			isOk = true;

		}

		return isOk;
	}

	private void testConnect(){

		DavStore prova = cDav.getDav();
		//System.out.println("URI:  "+prova.principalCollection().getCalendarHomeSet().getUri());

		List<AddressBookCollection> list = prova.principalCollection().getAddressbookHomeSet().getAddressbookCollections();
		System.out.println("List.size = "+list.size());

		for (AddressBookCollection addressBookCollection : list) {
			System.out.println("Address_displayName: "+addressBookCollection.getDisplayName());
		}




	}




}
