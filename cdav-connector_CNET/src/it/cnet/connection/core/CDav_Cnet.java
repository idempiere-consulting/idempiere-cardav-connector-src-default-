package it.cnet.connection.core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.bind.JAXBException;

import net.fortuna.ical4j.data.ParserException;

import org.apache.http.client.ClientProtocolException;

import zswi.objects.dav.collections.CalendarCollection;
import zswi.objects.dav.collections.CalendarHomeSet;
import zswi.objects.dav.collections.PrincipalCollection;
import zswi.protocols.communication.core.DavStore;
import zswi.protocols.communication.core.DavStore.DavStoreException;

public class CDav_Cnet {


	private DavStore dav = null;
	private PrincipalCollection principalCollection = null;
	private CalendarCollection princ = null;
	private List<CalendarCollection> listCalUser = null;
	private CalendarHomeSet calHomePublic = null;

	private String pUsername;
	private String pPassword;
	private String pUrl;


	public CDav_Cnet(String username, String password, String url){
		pUsername = username;
		pPassword = password;
		pUrl = url;

		try {
			dav = new DavStore(pUsername, pPassword, pUrl);
			principalCollection = dav.principalCollection();

			//list of calendars user
			listCalUser = principalCollection.getCalendarHomeSet().getCalendarCollections();
			
			//set calendars public from path Kerio '/caldav/users/consul-net.it/.public/'
			setCalHomePublic();
		} catch (DavStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public DavStore getDav() {
		return dav;
	}

	public PrincipalCollection getPrincipalCollection() {
		return principalCollection;
	}

	public CalendarHomeSet getCalHomePublic() {
		return calHomePublic;
	}

	public List<CalendarCollection> getListCalUser() {
		return listCalUser;
	}

	private void setCalHomePublic(){
		List<PrincipalCollection> listPrincCalProxy = principalCollection.getCalendarProxyWriteFor();
		for (PrincipalCollection princCollection : listPrincCalProxy) {
			if(princCollection.getDisplayName().contains("Public")){
				
				try {
					calHomePublic = new CalendarHomeSet(dav.httpClient(), princCollection, dav.initUri(princCollection.getCalendarHomeSetUrl().getPath()));
				} catch (ClientProtocolException e) {
					
					e.printStackTrace();
				} catch (JAXBException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (URISyntaxException e) {
					
					e.printStackTrace();
				} catch (ParserException e) {
					
					e.printStackTrace();
				}
	            dav.fetchFeatures(calHomePublic);
				
				break;
			}
			//System.out.println("PrincipalColl_calendar:  "+principalCollection.getCalendarHomeSetUrl().getPath());
		}
	}


}
