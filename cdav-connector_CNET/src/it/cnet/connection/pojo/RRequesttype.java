package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the r_requesttype database table.
 * 
 */
@Entity
@Table(name="r_requesttype")
public class RRequesttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="r_requesttype_id")
	private long rRequesttypeId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	private BigDecimal autoduedatedays;

	private String calendar;

	private String confidentialtype;

	private String contentcolor;

	private Timestamp created;

	private BigDecimal createdby;

	private String description;

	private BigDecimal duedatetolerance;

	private Timestamp endtime;

	private String headercolor;

	private String isactive;

	private String isautochangerequest;

	private String isconfidentialinfo;

	private String isdefault;

	private String isemailwhendue;

	private String isemailwhenoverdue;

	private String isexcludesaturday;

	private String isexcludesunday;

	private String isindexed;

	private String isinvoiced;

	private String isnobusinessday;

	private String isselfservice;

	private BigDecimal maxtimeminute;

	private BigDecimal minuterounde;

	private String name;

	@Column(name="r_requesttype_uu")
	private String rRequesttypeUu;

	@Column(name="r_statuscategory_id")
	private BigDecimal rStatuscategoryId;

	private Timestamp starttime;

	private Timestamp updated;

	private BigDecimal updatedby;

	//bi-directional many-to-one association to AdCaldavparam
	@OneToMany(mappedBy="RRequesttype")
	private List<AdCaldavparam> adCaldavparams;

	//bi-directional many-to-one association to RRequest
	@OneToMany(mappedBy="RRequesttype")
	private List<RRequest> RRequests;

    public RRequesttype() {
    }

	public long getRRequesttypeId() {
		return this.rRequesttypeId;
	}

	public void setRRequesttypeId(long rRequesttypeId) {
		this.rRequesttypeId = rRequesttypeId;
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

	public BigDecimal getAutoduedatedays() {
		return this.autoduedatedays;
	}

	public void setAutoduedatedays(BigDecimal autoduedatedays) {
		this.autoduedatedays = autoduedatedays;
	}

	public String getCalendar() {
		return this.calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public String getConfidentialtype() {
		return this.confidentialtype;
	}

	public void setConfidentialtype(String confidentialtype) {
		this.confidentialtype = confidentialtype;
	}

	public String getContentcolor() {
		return this.contentcolor;
	}

	public void setContentcolor(String contentcolor) {
		this.contentcolor = contentcolor;
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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDuedatetolerance() {
		return this.duedatetolerance;
	}

	public void setDuedatetolerance(BigDecimal duedatetolerance) {
		this.duedatetolerance = duedatetolerance;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getHeadercolor() {
		return this.headercolor;
	}

	public void setHeadercolor(String headercolor) {
		this.headercolor = headercolor;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsautochangerequest() {
		return this.isautochangerequest;
	}

	public void setIsautochangerequest(String isautochangerequest) {
		this.isautochangerequest = isautochangerequest;
	}

	public String getIsconfidentialinfo() {
		return this.isconfidentialinfo;
	}

	public void setIsconfidentialinfo(String isconfidentialinfo) {
		this.isconfidentialinfo = isconfidentialinfo;
	}

	public String getIsdefault() {
		return this.isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getIsemailwhendue() {
		return this.isemailwhendue;
	}

	public void setIsemailwhendue(String isemailwhendue) {
		this.isemailwhendue = isemailwhendue;
	}

	public String getIsemailwhenoverdue() {
		return this.isemailwhenoverdue;
	}

	public void setIsemailwhenoverdue(String isemailwhenoverdue) {
		this.isemailwhenoverdue = isemailwhenoverdue;
	}

	public String getIsexcludesaturday() {
		return this.isexcludesaturday;
	}

	public void setIsexcludesaturday(String isexcludesaturday) {
		this.isexcludesaturday = isexcludesaturday;
	}

	public String getIsexcludesunday() {
		return this.isexcludesunday;
	}

	public void setIsexcludesunday(String isexcludesunday) {
		this.isexcludesunday = isexcludesunday;
	}

	public String getIsindexed() {
		return this.isindexed;
	}

	public void setIsindexed(String isindexed) {
		this.isindexed = isindexed;
	}

	public String getIsinvoiced() {
		return this.isinvoiced;
	}

	public void setIsinvoiced(String isinvoiced) {
		this.isinvoiced = isinvoiced;
	}

	public String getIsnobusinessday() {
		return this.isnobusinessday;
	}

	public void setIsnobusinessday(String isnobusinessday) {
		this.isnobusinessday = isnobusinessday;
	}

	public String getIsselfservice() {
		return this.isselfservice;
	}

	public void setIsselfservice(String isselfservice) {
		this.isselfservice = isselfservice;
	}

	public BigDecimal getMaxtimeminute() {
		return this.maxtimeminute;
	}

	public void setMaxtimeminute(BigDecimal maxtimeminute) {
		this.maxtimeminute = maxtimeminute;
	}

	public BigDecimal getMinuterounde() {
		return this.minuterounde;
	}

	public void setMinuterounde(BigDecimal minuterounde) {
		this.minuterounde = minuterounde;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRRequesttypeUu() {
		return this.rRequesttypeUu;
	}

	public void setRRequesttypeUu(String rRequesttypeUu) {
		this.rRequesttypeUu = rRequesttypeUu;
	}

	public BigDecimal getRStatuscategoryId() {
		return this.rStatuscategoryId;
	}

	public void setRStatuscategoryId(BigDecimal rStatuscategoryId) {
		this.rStatuscategoryId = rStatuscategoryId;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
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