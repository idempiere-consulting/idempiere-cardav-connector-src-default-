package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the r_request database table.
 * 
 */
@Entity
@Table(name="r_request")
public class RRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="r_request_id")
	private long rRequestId;

	@Column(name="a_asset_id")
	private BigDecimal aAssetId;//OK

	@Column(name="ad_client_id")
	private BigDecimal adClientId;//OK

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;//OK

	@Column(name="ad_role_id")
	private BigDecimal adRoleId;//OK

	@Column(name="ad_table_id")
	private BigDecimal adTableId;//OK

	@Column(name="c_activity_id")
	private BigDecimal cActivityId;//OK

	@Column(name="c_bpartner_id")
	private BigDecimal cBpartnerId;//OK

	@Column(name="c_campaign_id")
	private BigDecimal cCampaignId;//OK

	@Column(name="c_invoice_id")
	private BigDecimal cInvoiceId;//OK

	@Column(name="c_invoicerequest_id")
	private BigDecimal cInvoicerequestId;//OK

	@Column(name="c_order_id")
	private BigDecimal cOrderId;//OK

	@Column(name="c_payment_id")
	private BigDecimal cPaymentId;//OK

	@Column(name="c_project_id")
	private BigDecimal cProjectId;//OK

	private Timestamp closedate;//OK

	private String confidentialtype;//OK

	private String confidentialtypeentry;//OK

	private Timestamp created;//OK

	private BigDecimal createdby;//OK

	private Timestamp datecompleteplan;//OK

	private Timestamp datelastaction;//OK

	private Timestamp datelastalert;//OK

	private Timestamp datenextaction;//OK

	private Timestamp datestartplan;//OK

	private String documentno;//OK

	private String duetype;//OK

	private Timestamp endtime;//OK

	private String isactive;//OK

	private String isescalated;//OK

	private String isinvoiced;//OK

	private String isselfservice;//OK

	private String lastresult;//OK

	@Column(name="m_changerequest_id")
	private BigDecimal mChangerequestId;//OK

	@Column(name="m_fixchangenotice_id")
	private BigDecimal mFixchangenoticeId;//OK

	@Column(name="m_inout_id")
	private BigDecimal mInoutId;//OK

	@Column(name="m_product_id")
	private BigDecimal mProductId;//OK

	@Column(name="m_productspent_id")
	private BigDecimal mProductspentId;//OK

	@Column(name="m_rma_id")
	private BigDecimal mRmaId;//OK

	private String nextaction;//OK

	private String priority;//OK

	private String priorityuser;//OK

	private String processed;//OK

	private BigDecimal qtyinvoiced;//OK

	private BigDecimal qtyplan;//OK

	private BigDecimal qtyspent;//OK

	@Column(name="r_category_id")
	private BigDecimal rCategoryId;//OK

	@Column(name="r_group_id")
	private BigDecimal rGroupId;//OK

	@Column(name="r_mailtext_id")
	private BigDecimal rMailtextId;//OK

	@Column(name="r_request_sync")
	private String rRequestSync;

	@Column(name="r_request_uu")
	private String rRequestUu;//OK

	@Column(name="r_requestrelated_id")
	private BigDecimal rRequestrelatedId;//OK

	@Column(name="r_resolution_id")
	private BigDecimal rResolutionId;//OK

	@Column(name="r_standardresponse_id")
	private BigDecimal rStandardresponseId;//OK

	@Column(name="r_status_id")
	private BigDecimal rStatusId;//OK

	@Column(name="record_id")
	private BigDecimal recordId;//OK

	private BigDecimal requestamt;//OK

	private String result;//OK

	@Column(name="salesrep_id")
	private BigDecimal salesrepId;//OK

	private Timestamp startdate;//OK

	private Timestamp starttime;//OK

	private String summary;//OK

	private String taskstatus;//OK

	private Timestamp updated;

	private BigDecimal updatedby;

	//bi-directional many-to-one association to AdUser
    @ManyToOne
	@JoinColumn(name="ad_user_id")
	private AdUser adUser;//OK

	//bi-directional many-to-one association to RRequesttype
    @ManyToOne
	@JoinColumn(name="r_requesttype_id")
	private RRequesttype RRequesttype;//OK

    public RRequest() {
    }

	public long getRRequestId() {
		return this.rRequestId;
	}

	public void setRRequestId(long rRequestId) {
		this.rRequestId = rRequestId;
	}

	public BigDecimal getAAssetId() {
		return this.aAssetId;
	}

	public void setAAssetId(BigDecimal aAssetId) {
		this.aAssetId = aAssetId;
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

	public BigDecimal getAdRoleId() {
		return this.adRoleId;
	}

	public void setAdRoleId(BigDecimal adRoleId) {
		this.adRoleId = adRoleId;
	}

	public BigDecimal getAdTableId() {
		return this.adTableId;
	}

	public void setAdTableId(BigDecimal adTableId) {
		this.adTableId = adTableId;
	}

	public BigDecimal getCActivityId() {
		return this.cActivityId;
	}

	public void setCActivityId(BigDecimal cActivityId) {
		this.cActivityId = cActivityId;
	}

	public BigDecimal getCBpartnerId() {
		return this.cBpartnerId;
	}

	public void setCBpartnerId(BigDecimal cBpartnerId) {
		this.cBpartnerId = cBpartnerId;
	}

	public BigDecimal getCCampaignId() {
		return this.cCampaignId;
	}

	public void setCCampaignId(BigDecimal cCampaignId) {
		this.cCampaignId = cCampaignId;
	}

	public BigDecimal getCInvoiceId() {
		return this.cInvoiceId;
	}

	public void setCInvoiceId(BigDecimal cInvoiceId) {
		this.cInvoiceId = cInvoiceId;
	}

	public BigDecimal getCInvoicerequestId() {
		return this.cInvoicerequestId;
	}

	public void setCInvoicerequestId(BigDecimal cInvoicerequestId) {
		this.cInvoicerequestId = cInvoicerequestId;
	}

	public BigDecimal getCOrderId() {
		return this.cOrderId;
	}

	public void setCOrderId(BigDecimal cOrderId) {
		this.cOrderId = cOrderId;
	}

	public BigDecimal getCPaymentId() {
		return this.cPaymentId;
	}

	public void setCPaymentId(BigDecimal cPaymentId) {
		this.cPaymentId = cPaymentId;
	}

	public BigDecimal getCProjectId() {
		return this.cProjectId;
	}

	public void setCProjectId(BigDecimal cProjectId) {
		this.cProjectId = cProjectId;
	}

	public Timestamp getClosedate() {
		return this.closedate;
	}

	public void setClosedate(Timestamp closedate) {
		this.closedate = closedate;
	}

	public String getConfidentialtype() {
		return this.confidentialtype;
	}

	public void setConfidentialtype(String confidentialtype) {
		this.confidentialtype = confidentialtype;
	}

	public String getConfidentialtypeentry() {
		return this.confidentialtypeentry;
	}

	public void setConfidentialtypeentry(String confidentialtypeentry) {
		this.confidentialtypeentry = confidentialtypeentry;
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

	public Timestamp getDatecompleteplan() {
		return this.datecompleteplan;
	}

	public void setDatecompleteplan(Timestamp datecompleteplan) {
		this.datecompleteplan = datecompleteplan;
	}

	public Timestamp getDatelastaction() {
		return this.datelastaction;
	}

	public void setDatelastaction(Timestamp datelastaction) {
		this.datelastaction = datelastaction;
	}

	public Timestamp getDatelastalert() {
		return this.datelastalert;
	}

	public void setDatelastalert(Timestamp datelastalert) {
		this.datelastalert = datelastalert;
	}

	public Timestamp getDatenextaction() {
		return this.datenextaction;
	}

	public void setDatenextaction(Timestamp datenextaction) {
		this.datenextaction = datenextaction;
	}

	public Timestamp getDatestartplan() {
		return this.datestartplan;
	}

	public void setDatestartplan(Timestamp datestartplan) {
		this.datestartplan = datestartplan;
	}

	public String getDocumentno() {
		return this.documentno;
	}

	public void setDocumentno(String documentno) {
		this.documentno = documentno;
	}

	public String getDuetype() {
		return this.duetype;
	}

	public void setDuetype(String duetype) {
		this.duetype = duetype;
	}

	public Timestamp getEndtime() {
		return this.endtime;
	}

	public void setEndtime(Timestamp endtime) {
		this.endtime = endtime;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsescalated() {
		return this.isescalated;
	}

	public void setIsescalated(String isescalated) {
		this.isescalated = isescalated;
	}

	public String getIsinvoiced() {
		return this.isinvoiced;
	}

	public void setIsinvoiced(String isinvoiced) {
		this.isinvoiced = isinvoiced;
	}

	public String getIsselfservice() {
		return this.isselfservice;
	}

	public void setIsselfservice(String isselfservice) {
		this.isselfservice = isselfservice;
	}

	public String getLastresult() {
		return this.lastresult;
	}

	public void setLastresult(String lastresult) {
		this.lastresult = lastresult;
	}

	public BigDecimal getMChangerequestId() {
		return this.mChangerequestId;
	}

	public void setMChangerequestId(BigDecimal mChangerequestId) {
		this.mChangerequestId = mChangerequestId;
	}

	public BigDecimal getMFixchangenoticeId() {
		return this.mFixchangenoticeId;
	}

	public void setMFixchangenoticeId(BigDecimal mFixchangenoticeId) {
		this.mFixchangenoticeId = mFixchangenoticeId;
	}

	public BigDecimal getMInoutId() {
		return this.mInoutId;
	}

	public void setMInoutId(BigDecimal mInoutId) {
		this.mInoutId = mInoutId;
	}

	public BigDecimal getMProductId() {
		return this.mProductId;
	}

	public void setMProductId(BigDecimal mProductId) {
		this.mProductId = mProductId;
	}

	public BigDecimal getMProductspentId() {
		return this.mProductspentId;
	}

	public void setMProductspentId(BigDecimal mProductspentId) {
		this.mProductspentId = mProductspentId;
	}

	public BigDecimal getMRmaId() {
		return this.mRmaId;
	}

	public void setMRmaId(BigDecimal mRmaId) {
		this.mRmaId = mRmaId;
	}

	public String getNextaction() {
		return this.nextaction;
	}

	public void setNextaction(String nextaction) {
		this.nextaction = nextaction;
	}

	public String getPriority() {
		return this.priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getPriorityuser() {
		return this.priorityuser;
	}

	public void setPriorityuser(String priorityuser) {
		this.priorityuser = priorityuser;
	}

	public String getProcessed() {
		return this.processed;
	}

	public void setProcessed(String processed) {
		this.processed = processed;
	}

	public BigDecimal getQtyinvoiced() {
		return this.qtyinvoiced;
	}

	public void setQtyinvoiced(BigDecimal qtyinvoiced) {
		this.qtyinvoiced = qtyinvoiced;
	}

	public BigDecimal getQtyplan() {
		return this.qtyplan;
	}

	public void setQtyplan(BigDecimal qtyplan) {
		this.qtyplan = qtyplan;
	}

	public BigDecimal getQtyspent() {
		return this.qtyspent;
	}

	public void setQtyspent(BigDecimal qtyspent) {
		this.qtyspent = qtyspent;
	}

	public BigDecimal getRCategoryId() {
		return this.rCategoryId;
	}

	public void setRCategoryId(BigDecimal rCategoryId) {
		this.rCategoryId = rCategoryId;
	}

	public BigDecimal getRGroupId() {
		return this.rGroupId;
	}

	public void setRGroupId(BigDecimal rGroupId) {
		this.rGroupId = rGroupId;
	}

	public BigDecimal getRMailtextId() {
		return this.rMailtextId;
	}

	public void setRMailtextId(BigDecimal rMailtextId) {
		this.rMailtextId = rMailtextId;
	}

	public String getRRequestSync() {
		return this.rRequestSync;
	}

	public void setRRequestSync(String rRequestSync) {
		this.rRequestSync = rRequestSync;
	}

	public String getRRequestUu() {
		return this.rRequestUu;
	}

	public void setRRequestUu(String rRequestUu) {
		this.rRequestUu = rRequestUu;
	}

	public BigDecimal getRRequestrelatedId() {
		return this.rRequestrelatedId;
	}

	public void setRRequestrelatedId(BigDecimal rRequestrelatedId) {
		this.rRequestrelatedId = rRequestrelatedId;
	}

	public BigDecimal getRResolutionId() {
		return this.rResolutionId;
	}

	public void setRResolutionId(BigDecimal rResolutionId) {
		this.rResolutionId = rResolutionId;
	}

	public BigDecimal getRStandardresponseId() {
		return this.rStandardresponseId;
	}

	public void setRStandardresponseId(BigDecimal rStandardresponseId) {
		this.rStandardresponseId = rStandardresponseId;
	}

	public BigDecimal getRStatusId() {
		return this.rStatusId;
	}

	public void setRStatusId(BigDecimal rStatusId) {
		this.rStatusId = rStatusId;
	}

	public BigDecimal getRecordId() {
		return this.recordId;
	}

	public void setRecordId(BigDecimal recordId) {
		this.recordId = recordId;
	}

	public BigDecimal getRequestamt() {
		return this.requestamt;
	}

	public void setRequestamt(BigDecimal requestamt) {
		this.requestamt = requestamt;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public BigDecimal getSalesrepId() {
		return this.salesrepId;
	}

	public void setSalesrepId(BigDecimal salesrepId) {
		this.salesrepId = salesrepId;
	}

	public Timestamp getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}

	public Timestamp getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Timestamp starttime) {
		this.starttime = starttime;
	}

	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTaskstatus() {
		return this.taskstatus;
	}

	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
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

	public AdUser getAdUser() {
		return this.adUser;
	}

	public void setAdUser(AdUser adUser) {
		this.adUser = adUser;
	}
	
	public RRequesttype getRRequesttype() {
		return this.RRequesttype;
	}

	public void setRRequesttype(RRequesttype RRequesttype) {
		this.RRequesttype = RRequesttype;
	}
	
}
