package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


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
	private long adUserId;//OK

	@Column(name="ad_client_id")
	private BigDecimal adClientId;//OK

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;//OK

	@Column(name="ad_orgtrx_id")
	private BigDecimal adOrgtrxId;//OK

	@Column(name="ad_user_uu")
	private String adUserUu;//OK

	private String answer;//OK

	private String birthcity;

	private Timestamp birthday;//OK

	@Column(name="bp_location_id")
	private BigDecimal bpLocationId;//OK

	private String bpname;//OK

	@Column(name="c_bpartner_id")
	private BigDecimal cBpartnerId;//OK

	@Column(name="c_bpartner_location_id")
	private BigDecimal cBpartnerLocationId;//OK

	@Column(name="c_campaign_id")
	private BigDecimal cCampaignId;//OK

	@Column(name="c_greeting_id")
	private BigDecimal cGreetingId;//OK

	@Column(name="c_job_id")
	private BigDecimal cJobId;//OK

	@Column(name="c_location_id")
	private BigDecimal cLocationId;//OK

	private String caldavpath;

	private String cardavpath;

	private String comments;//OK

	private String connectionprofile;//OK

	private Timestamp created;//OK

	private BigDecimal createdby;//OK

	private Timestamp dateaccountlocked;//OK

	private Timestamp datelastlogin;//OK

	private Timestamp datepasswordchanged;//OK

	private String description;//OK

	private String email;//OK

	private String emailuser;//OK

	private String emailuserpw;//OK

	private String emailverify;//OK

	private Timestamp emailverifydate;//OK

	private BigDecimal failedlogincount;//OK

	private String fax;//OK

	private String isactive;//OK

	private String isexpired;//OK

	private String isfullbpaccess;//OK

	private String isinpayroll;//OK

	private String islegaluser;

	private String islocked;//OK

	private String ismenuautoexpand;//OK

	private String isnopasswordreset;//OK

	private String issaleslead;//OK

	private Timestamp lastcontact;//OK

	private String lastresult;//OK

	private String ldapuser;//OK

	private String leadsource;//OK

	private String leadsourcedescription;//OK

	private String leadstatus;//OK

	private String leadstatusdescription;//OK

	private String name;//OK

	private String notificationtype;//OK

	private String password;//OK

	private String passwordxdav;

	private String phone;//OK

	private String phone2;//OK

	private String processing;//OK

	@Column(name="resource_product")
	private String resourceProduct;

	@Column(name="salesrep_id")
	private BigDecimal salesrepId;//OK

	private String salt;//OK

	private String securityquestion;//OK

	private String sex;

	@Column(name="supervisor_id")
	private BigDecimal supervisorId;//OK

	private String surname;

	private String title;//OK

	private Timestamp updated;//OK

	private BigDecimal updatedby;//OK

	private String usernamexdav;

	private String userpin;//OK

	private String value;//OK

	//bi-directional many-to-one association to AdCaldavparam
	@OneToMany(mappedBy="adUser")
	private List<AdCaldavparam> adCaldavparams;

	//bi-directional many-to-one association to RRequest
	@OneToMany(mappedBy="adUser")
	private List<RRequest> RRequests;

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

	public BigDecimal getCBpartnerId() {
		return this.cBpartnerId;
	}

	public void setCBpartnerId(BigDecimal cBpartnerId) {
		this.cBpartnerId = cBpartnerId;
	}

	public BigDecimal getCBpartnerLocationId() {
		return this.cBpartnerLocationId;
	}

	public void setCBpartnerLocationId(BigDecimal cBpartnerLocationId) {
		this.cBpartnerLocationId = cBpartnerLocationId;
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

	public BigDecimal getCLocationId() {
		return this.cLocationId;
	}

	public void setCLocationId(BigDecimal cLocationId) {
		this.cLocationId = cLocationId;
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

	public List<AdCaldavparam> getAdCaldavparams() {
		return this.adCaldavparams;
	}

	public void setAdCaldavparams(List<AdCaldavparam> adCaldavparams) {
		this.adCaldavparams = adCaldavparams;
	}
	
	public List<RRequest> getRRequests() {
		return this.RRequests;
	}

	public void setRRequests(List<RRequest> RRequests) {
		this.RRequests = RRequests;
	}
	
}
