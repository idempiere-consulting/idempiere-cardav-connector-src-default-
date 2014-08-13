package it.cnet.connection.core;

import it.cnet.connection.pojo.AdCaldavparam;
import it.cnet.connection.pojo.AdSysconfig;
import it.cnet.connection.pojo.AdUser;
import it.cnet.connection.pojo.CBpartner;
import it.cnet.connection.pojo.CBpartnerLocation;
import it.cnet.connection.pojo.CCity;
import it.cnet.connection.pojo.CCountry;
import it.cnet.connection.pojo.CLocation;
import it.cnet.connection.pojo.CRegion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import net.fortuna.ical4j.model.property.Uid;
import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.VCardImpl;
import net.sourceforge.cardme.vcard.features.AddressFeature;
import net.sourceforge.cardme.vcard.features.EmailFeature;
import net.sourceforge.cardme.vcard.features.TelephoneFeature;
import net.sourceforge.cardme.vcard.types.AddressType;
import net.sourceforge.cardme.vcard.types.EmailType;
import net.sourceforge.cardme.vcard.types.FormattedNameType;
import net.sourceforge.cardme.vcard.types.NameType;
import net.sourceforge.cardme.vcard.types.OrganizationType;
import net.sourceforge.cardme.vcard.types.TelephoneType;
import net.sourceforge.cardme.vcard.types.UIDType;
import net.sourceforge.cardme.vcard.types.parameters.AddressParameterType;
import net.sourceforge.cardme.vcard.types.parameters.TelephoneParameterType;
import zswi.objects.dav.collections.AddressBookCollection;
import zswi.protocols.carddav.ServerVCard;
import zswi.protocols.communication.core.DavStore.DavStoreException;

public class CardDav_Connect_Sync {

	private String PERSISTENCE_UNIT_NAME = "cardDav-connector_CNET";
	private EntityManagerFactory factory;
	private EntityManager em;

	private CardDav_Cnet cDav = null;

	private AddressBookCollection principalAddBookColl = null;

	private int CALDAVTimeunit = 0;
	private int NRDAYSCALDAVBEFORE = 0;



	private static Logger logger=Logger.getLogger(CardDav_Connect_Sync.class.getCanonicalName());

	public CardDav_Connect_Sync() {

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
		CardDav_Connect_Sync connect_Sync = new CardDav_Connect_Sync();
		connect_Sync.syncCardav();

	}

	private void syncCardav(){


		logger.info("Initial sync...");

		syncCardav_iDempiereContact();

	}

	@SuppressWarnings("unchecked")
	private void syncCardav_iDempiereContact(){

		List<BigDecimal> listClientID = null;

		String calNameCardav = "";

		Calendar calToday = null;
		Date dtDayToCaldav = null;
		Date dtDayStartCaldav = null;

		Query qry = em.createQuery("select c from AdCaldavparam c where c.calendar like :strCardav");
		qry.setParameter("strCardav", "%CARDAV%");

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
						adUser.getCardavpath()!=null && !adUser.getCardavpath().equals("")){
					cDav = new CardDav_Cnet(adUser.getUsernamexdav(), adUser.getPasswordxdav(), adUser.getCardavpath());

					if(adUser.getName().contains("publicCard:")){
						calNameCardav = adUser.getName().substring("publicCard:".length());
						setPrincipalAddBook(calNameCardav, true);
					}
					else if(adUser.getName().contains("publicCard")){
						calNameCardav = "Contacts";
						setPrincipalAddBook(calNameCardav, true);
					}
					else{
						calNameCardav = "Contacts";
						setPrincipalAddBook(calNameCardav, false);
					}

					logger.info("AdUser = "+adUser.getName()+" --- cdav__User/Path: "+ adUser.getUsernamexdav()+" / "+adUser.getCardavpath());
					logger.info("cdav__PrincipalAddBook: "+ principalAddBookColl.getDisplayName());


					List<ServerVCard>listVcard = null;
					try {
						listVcard= cDav.getDav().getVCards(principalAddBookColl);
					} catch (DavStoreException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					VCard vCard = null;
					for (ServerVCard serverVCard : listVcard) {
						vCard = serverVCard.getVcard();
						String uidValue = vCard.getUID().getUID();

						CBpartner cbpart = null;
						CBpartnerLocation cbpLoc = null;
						CLocation cLoc = null;
						CCountry cCountr = null;
						CRegion cRegion = null;
						CCity cCity = null;

						AdUser user = null;

						boolean upFromWeb = false;

						if(uidValue.contains("@bp")){

							logger.info("UID_value: < "+uidValue+" >");
							uidValue = uidValue.substring(uidValue.indexOf("@bp")+3);
							uidValue = uidValue.substring(0, uidValue.indexOf("@"));
							long cbpart_Id = Long.parseLong(uidValue);

							cbpart = em.find(CBpartner.class, cbpart_Id);

							cbpLoc = em.find(CBpartnerLocation.class, cbpart.getCBpartnerLocations().get(0).getCBpartnerLocationId());

							Iterator<TelephoneFeature> tels =   vCard.getTelephoneNumbers();
							while (tels.hasNext()) {
								TelephoneFeature telphoneFeat = (TelephoneFeature) tels.next();
								if(telphoneFeat.containsTelephoneParameterType(TelephoneParameterType.VOICE)){
									upFromWeb = true;
									break;
								}

							}

							if(upFromWeb){
								logger.info("Contact update in webCalendar.....");
								em.getTransaction().begin();

								EmailFeature email = (!vCard.getEmails().hasNext())?null:vCard.getEmails().next();
								if(email!=null)
									cbpLoc.setEmail(email.getEmail());

								//Address location	
								if(cbpLoc.getCLocation()==null)
									cLoc = new CLocation();
								else
									cLoc = cbpLoc.getCLocation();

								AddressFeature address = (!vCard.getAddresses().hasNext())?null:vCard.getAddresses().next();

								if(address!=null){
									cLoc.setAddress1(address.getStreetAddress());
									cLoc.setCity(address.getLocality());
									cLoc.setPostal(address.getPostalCode());

									if(cLoc.getCCountry()==null)
										cCountr = new CCountry();
									else
										cCountr = cLoc.getCCountry();

									cCountr.setName(address.getCountryName());
									cLoc.setCCountry(cCountr);

									if(cLoc.getCRegion()==null)
										cRegion = new CRegion();
									else
										cRegion = cLoc.getCRegion();

									cRegion.setName(address.getRegion());
									cLoc.setCRegion(cRegion);

									cbpLoc.setCLocation(cLoc);
								}
								//	

								//Telephone Number
								tels = vCard.getTelephoneNumbers();
								while (tels.hasNext()) {
									TelephoneFeature telphoneFeat = (TelephoneFeature) tels.next();
									if(telphoneFeat.containsTelephoneParameterType(TelephoneParameterType.WORK)){
										cbpLoc.setPhone(telphoneFeat.getTelephone());
									}
									else if(telphoneFeat.containsTelephoneParameterType(TelephoneParameterType.FAX)){
										cbpLoc.setFax(telphoneFeat.getTelephone());
									}


								}
								//

								em.merge(cbpLoc);
								em.getTransaction().commit();

								createVCard_bPart(cDav, cbpLoc);

								upFromWeb = false;

							}else{
								createVCard_bPart(cDav, cbpLoc);
							}

						}
						else if(uidValue.contains("@us")){

							logger.info("UID_value: < "+uidValue+" >");
							uidValue = uidValue.substring(uidValue.indexOf("@us")+3);
							uidValue = uidValue.substring(0, uidValue.indexOf("@"));
							long adUser_Id = Long.parseLong(uidValue);

							user = em.find(AdUser.class, adUser_Id);


							Iterator<TelephoneFeature> tels =   vCard.getTelephoneNumbers();
							while (tels.hasNext()) {
								TelephoneFeature telphoneFeat = (TelephoneFeature) tels.next();
								if(telphoneFeat.containsTelephoneParameterType(TelephoneParameterType.VOICE)){
									upFromWeb = true;
									break;
								}

							}

							if(upFromWeb){
								logger.info("Contact update in webCalendar.....");
								em.getTransaction().begin();

								EmailFeature email = vCard.getEmails().next();
								if(email!=null)
									user.setEmail(email.getEmail());

								//Address location	
								if(user.getCLocation()==null)
									cLoc = new CLocation();
								else
									cLoc = user.getCLocation();

								AddressFeature address = vCard.getAddresses().next();

								if(address!=null){
									cLoc.setAddress1(address.getStreetAddress());
									cLoc.setCity(address.getLocality());
									cLoc.setPostal(address.getPostalCode());

									if(cLoc.getCCountry()==null)
										cCountr = new CCountry();
									else
										cCountr = cLoc.getCCountry();

									cCountr.setName(address.getCountryName());
									cLoc.setCCountry(cCountr);

									if(cLoc.getCRegion()==null)
										cRegion = new CRegion();
									else
										cRegion = cLoc.getCRegion();

									cRegion.setName(address.getRegion());
									cLoc.setCRegion(cRegion);

									user.setCLocation(cLoc);
								}
								//	

								//Telephone Number
								while (tels.hasNext()) {
									TelephoneFeature telphoneFeat = (TelephoneFeature) tels.next();
									if(telphoneFeat.containsTelephoneParameterType(TelephoneParameterType.WORK)){
										user.setPhone(telphoneFeat.getTelephone());
									}
									else if(telphoneFeat.containsTelephoneParameterType(TelephoneParameterType.FAX)){
										user.setFax(telphoneFeat.getTelephone());
									}


								}
								//

								em.merge(user);
								em.getTransaction().commit();

								createVCard_user(cDav, user);
								upFromWeb = false;

							}else	
								createVCard_user(cDav, user);

						}



					}
				}

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

		vCard.addTelephoneNumber(new TelephoneType(phoneNumber, TelephoneParameterType.WORK));
		vCard.addTelephoneNumber(new TelephoneType(faxNumber, TelephoneParameterType.FAX));

		//vCard.setEnd(new EndType());

		// generate unique identifier..
		StringBuffer id = new StringBuffer();
		id = id.append("@bp").append(cBpartnerLocation.getCBpartner().getCBpartnerId()).append("@").append(cBpartnerLocation.getCBpartnerLocationUu());
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

		vCard.addTelephoneNumber(new TelephoneType(phoneNumber, TelephoneParameterType.WORK));
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

}
