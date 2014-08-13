package it.cnet.connection.core;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.http.client.ClientProtocolException;

import zswi.objects.dav.collections.AddressBookCollection;
import zswi.objects.dav.collections.AddressBookHomeSet;
import zswi.objects.dav.collections.CalendarCollection;
import zswi.objects.dav.collections.PrincipalCollection;
import zswi.protocols.communication.core.DavStore;
import zswi.protocols.communication.core.DavStore.DavStoreException;

public class CardDav_Cnet {


	private DavStore dav = null;
	private PrincipalCollection principalCollection = null;
	private CalendarCollection princ = null;
	private List<AddressBookCollection> listAddBookUser = null;
	private AddressBookHomeSet addBookHomePublic = null;

	private String pUsername;
	private String pPassword;
	private String pUrl;


	public CardDav_Cnet(String username, String password, String url){
		pUsername = username;
		pPassword = password;
		pUrl = url;

		try {
			dav = new DavStore(pUsername, pPassword, pUrl);
			principalCollection = dav.principalCollection();

			//list of addressBook user
			listAddBookUser = principalCollection.getAddressbookHomeSet().getAddressbookCollections();
			
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

	public AddressBookHomeSet getAddBookHomePublic() {
		return addBookHomePublic;
	}

	public List<AddressBookCollection> getListAddBookUser() {
		return listAddBookUser;
	}

	private void setCalHomePublic(){
		List<PrincipalCollection> listPrincCalProxy = principalCollection.getCalendarProxyWriteFor();
		for (PrincipalCollection princCollection : listPrincCalProxy) {
			if(princCollection.getDisplayName().contains("Public")){
				
				try {
					addBookHomePublic = new AddressBookHomeSet(dav.httpClient(), princCollection, dav.initUri(princCollection.getAddressbookHomeSetUrl().getPath()));
				} catch (ClientProtocolException e) {
					
					e.printStackTrace();
				} catch (JAXBException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (URISyntaxException e) {
					
					e.printStackTrace();
				}
	            dav.fetchFeatures(addBookHomePublic);
				
				break;
			}
			//System.out.println("PrincipalColl_calendar:  "+principalCollection.getCalendarHomeSetUrl().getPath());
		}
	}


}
