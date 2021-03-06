package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the ad_user database table.
 * 
 */
@Entity
@Table(name="ad_user")
public class AdUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ad_user_id")
	private long adUserId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	@Column(name="ad_orgtrx_id")
	private BigDecimal adOrgtrxId;

	@Column(name="ad_user_uu")
	private String adUserUu;

	private String answer;

	private String birthcity;

	private Timestamp birthday;

	@Column(name="bp_location_id")
	private BigDecimal bpLocationId;

	private String bpname;

	@Column(name="c_campaign_id")
	private BigDecimal cCampaignId;

	@Column(name="c_greeting_id")
	private BigDecimal cGreetingId;

	@Column(name="c_job_id")
	private BigDecimal cJobId;

	private String caldavpath;

	private String cardavpath;

	private BigDecimal cardavsync;

	private String comments;

	private String connectionprofile;

	private Timestamp created;

	private BigDecimal createdby;

	private Timestamp dateaccountlocked;

	private Timestamp datelastlogin;

	private Timestamp datepasswordchanged;

	private String description;

	private String email;

	private String emailuser;

	private String emailuserpw;

	private String emailverify;

	private Timestamp emailverifydate;

	private BigDecimal failedlogincount;

	private String fax;

	private String isactive;

	private String isexpired;

	private String isfullbpaccess;

	private String isinpayroll;

	private String islegaluser;

	private String islocked;

	private String ismenuautoexpand;

	private String isnopasswordreset;

	private String issaleslead;

	private Timestamp lastcontact;

	private String lastresult;

	private String ldapuser;

	private String leadsource;

	private String leadsourcedescription;

	private String leadstatus;

	private String leadstatusdescription;

	private String name;

	private String notificationtype;

	private String password;

	private String passwordxdav;

	private String phone;

	private String phone2;

	private String processing;

	@Column(name="resource_product")
	private String resourceProduct;

	@Column(name="salesrep_id")
	private BigDecimal salesrepId;

	private String salt;

	private String securityquestion;

	private String sex;

	@Column(name="supervisor_id")
	private BigDecimal supervisorId;

	private String surname;

	private String title;

	private Timestamp updated;

	private BigDecimal updatedby;

	private String usernamexdav;

	private String userpin;

	private String value;

	//bi-directional many-to-one association to CBpartner
    @ManyToOne
	@JoinColumn(name="c_bpartner_id")
	private CBpartner CBpartner;

	//bi-directional many-to-one association to CBpartnerLocation
    @ManyToOne
	@JoinColumn(name="c_bpartner_location_id")
	private CBpartnerLocation CBpartnerLocation;

	//bi-directional many-to-one association to CLocation
    @ManyToOne
	@JoinColumn(name="c_location_id")
	private CLocation CLocation;

    public AdUser() {
    }

	public long getAdUserId() {
		return this.adUserId;
	}

	public void setAdUserId(long adUserId) {
		this.adUserId = adUserId;
	}

	public BigDecimal getAdClientId() {
		return this.adClientId;
	}

	public void setAdClientId(BigDecimal adClientId) {
		this.adClientId = adClientId;
	}

	public BigDecimal getAdOrgId() {
		return this.adOrgId;
	}

	public void setAdOrgId(BigDecimal adOrgId) {
		this.adOrgId = adOrgId;
	}

	public BigDecimal getAdOrgtrxId() {
		return this.adOrgtrxId;
	}

	public void setAdOrgtrxId(BigDecimal adOrgtrxId) {
		this.adOrgtrxId = adOrgtrxId;
	}

	public String getAdUserUu() {
		return this.adUserUu;
	}

	public void setAdUserUu(String adUserUu) {
		this.adUserUu = adUserUu;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getBirthcity() {
		return this.birthcity;
	}

	public void setBirthcity(String birthcity) {
		this.birthcity = birthcity;
	}

	public Timestamp getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public BigDecimal getBpLocationId() {
		return this.bpLocationId;
	}

	public void setBpLocationId(BigDecimal bpLocationId) {
		this.bpLocationId = bpLocationId;
	}

	public String getBpname() {
		return this.bpname;
	}

	public void setBpname(String bpname) {
		this.bpname = bpname;
	}

	public BigDecimal getCCampaignId() {
		return this.cCampaignId;
	}

	public void setCCampaignId(BigDecimal cCampaignId) {
		this.cCampaignId = cCampaignId;
	}

	public BigDecimal getCGreetingId() {
		return this.cGreetingId;
	}

	public void setCGreetingId(BigDecimal cGreetingId) {
		this.cGreetingId = cGreetingId;
	}

	public BigDecimal getCJobId() {
		return this.cJobId;
	}

	public void setCJobId(BigDecimal cJobId) {
		this.cJobId = cJobId;
	}

	public String getCaldavpath() {
		return this.caldavpath;
	}

	public void setCaldavpath(String caldavpath) {
		this.caldavpath = caldavpath;
	}

	public String getCardavpath() {
		return this.cardavpath;
	}

	public void setCardavpath(String cardavpath) {
		this.cardavpath = cardavpath;
	}

	public BigDecimal getCardavsync() {
		return this.cardavsync;
	}

	public void setCardavsync(BigDecimal cardavsync) {
		this.cardavsync = cardavsync;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getConnectionprofile() {
		return this.connectionprofile;
	}

	public void setConnectionprofile(String connectionprofile) {
		this.connectionprofile = connectionprofile;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public BigDecimal getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(BigDecimal createdby) {
		this.createdby = createdby;
	}

	public Timestamp getDateaccountlocked() {
		return this.dateaccountlocked;
	}

	public void setDateaccountlocked(Timestamp dateaccountlocked) {
		this.dateaccountlocked = dateaccountlocked;
	}

	public Timestamp getDatelastlogin() {
		return this.datelastlogin;
	}

	public void setDatelastlogin(Timestamp datelastlogin) {
		this.datelastlogin = datelastlogin;
	}

	public Timestamp getDatepasswordchanged() {
		return this.datepasswordchanged;
	}

	public void setDatepasswordchanged(Timestamp datepasswordchanged) {
		this.datepasswordchanged = datepasswordchanged;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmailuser() {
		return this.emailuser;
	}

	public void setEmailuser(String emailuser) {
		this.emailuser = emailuser;
	}

	public String getEmailuserpw() {
		return this.emailuserpw;
	}

	public void setEmailuserpw(String emailuserpw) {
		this.emailuserpw = emailuserpw;
	}

	public String getEmailverify() {
		return this.emailverify;
	}

	public void setEmailverify(String emailverify) {
		this.emailverify = emailverify;
	}

	public Timestamp getEmailverifydate() {
		return this.emailverifydate;
	}

	public void setEmailverifydate(Timestamp emailverifydate) {
		this.emailverifydate = emailverifydate;
	}

	public BigDecimal getFailedlogincount() {
		return this.failedlogincount;
	}

	public void setFailedlogincount(BigDecimal failedlogincount) {
		this.failedlogincount = failedlogincount;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsexpired() {
		return this.isexpired;
	}

	public void setIsexpired(String isexpired) {
		this.isexpired = isexpired;
	}

	public String getIsfullbpaccess() {
		return this.isfullbpaccess;
	}

	public void setIsfullbpaccess(String isfullbpaccess) {
		this.isfullbpaccess = isfullbpaccess;
	}

	public String getIsinpayroll() {
		return this.isinpayroll;
	}

	public void setIsinpayroll(String isinpayroll) {
		this.isinpayroll = isinpayroll;
	}

	public String getIslegaluser() {
		return this.islegaluser;
	}

	public void setIslegaluser(String islegaluser) {
		this.islegaluser = islegaluser;
	}

	public String getIslocked() {
		return this.islocked;
	}

	public void setIslocked(String islocked) {
		this.islocked = islocked;
	}

	public String getIsmenuautoexpand() {
		return this.ismenuautoexpand;
	}

	public void setIsmenuautoexpand(String ismenuautoexpand) {
		this.ismenuautoexpand = ismenuautoexpand;
	}

	public String getIsnopasswordreset() {
		return this.isnopasswordreset;
	}

	public void setIsnopasswordreset(String isnopasswordreset) {
		this.isnopasswordreset = isnopasswordreset;
	}

	public String getIssaleslead() {
		return this.issaleslead;
	}

	public void setIssaleslead(String issaleslead) {
		this.issaleslead = issaleslead;
	}

	public Timestamp getLastcontact() {
		return this.lastcontact;
	}

	public void setLastcontact(Timestamp lastcontact) {
		this.lastcontact = lastcontact;
	}

	public String getLastresult() {
		return this.lastresult;
	}

	public void setLastresult(String lastresult) {
		this.lastresult = lastresult;
	}

	public String getLdapuser() {
		return this.ldapuser;
	}

	public void setLdapuser(String ldapuser) {
		this.ldapuser = ldapuser;
	}

	public String getLeadsource() {
		return this.leadsource;
	}

	public void setLeadsource(String leadsource) {
		this.leadsource = leadsource;
	}

	public String getLeadsourcedescription() {
		return this.leadsourcedescription;
	}

	public void setLeadsourcedescription(String leadsourcedescription) {
		this.leadsourcedescription = leadsourcedescription;
	}

	public String getLeadstatus() {
		return this.leadstatus;
	}

	public void setLeadstatus(String leadstatus) {
		this.leadstatus = leadstatus;
	}

	public String getLeadstatusdescription() {
		return this.leadstatusdescription;
	}

	public void setLeadstatusdescription(String leadstatusdescription) {
		this.leadstatusdescription = leadstatusdescription;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotificationtype() {
		return this.notificationtype;
	}

	public void setNotificationtype(String notificationtype) {
		this.notificationtype = notificationtype;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordxdav() {
		return this.passwordxdav;
	}

	public void setPasswordxdav(String passwordxdav) {
		this.passwordxdav = passwordxdav;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getProcessing() {
		return this.processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getResourceProduct() {
		return this.resourceProduct;
	}

	public void setResourceProduct(String resourceProduct) {
		this.resourceProduct = resourceProduct;
	}

	public BigDecimal getSalesrepId() {
		return this.salesrepId;
	}

	public void setSalesrepId(BigDecimal salesrepId) {
		this.salesrepId = salesrepId;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSecurityquestion() {
		return this.securityquestion;
	}

	public void setSecurityquestion(String securityquestion) {
		this.securityquestion = securityquestion;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BigDecimal getSupervisorId() {
		return this.supervisorId;
	}

	public void setSupervisorId(BigDecimal supervisorId) {
		this.supervisorId = supervisorId;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public BigDecimal getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(BigDecimal updatedby) {
		this.updatedby = updatedby;
	}

	public String getUsernamexdav() {
		return this.usernamexdav;
	}

	public void setUsernamexdav(String usernamexdav) {
		this.usernamexdav = usernamexdav;
	}

	public String getUserpin() {
		return this.userpin;
	}

	public void setUserpin(String userpin) {
		this.userpin = userpin;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CBpartner getCBpartner() {
		return this.CBpartner;
	}

	public void setCBpartner(CBpartner CBpartner) {
		this.CBpartner = CBpartner;
	}
	
	public CBpartnerLocation getCBpartnerLocation() {
		return this.CBpartnerLocation;
	}

	public void setCBpartnerLocation(CBpartnerLocation CBpartnerLocation) {
		this.CBpartnerLocation = CBpartnerLocation;
	}
	
	public CLocation getCLocation() {
		return this.CLocation;
	}

	public void setCLocation(CLocation CLocation) {
		this.CLocation = CLocation;
	}
	
}