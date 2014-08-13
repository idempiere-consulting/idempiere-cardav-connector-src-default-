package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the c_bpartner database table.
 * 
 */
@Entity
@Table(name="c_bpartner")
public class CBpartner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="c_bpartner_id")
	private long cBpartnerId;

	private BigDecimal acqusitioncost;

	private BigDecimal actuallifetimevalue;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_language")
	private String adLanguage;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	@Column(name="ad_orgbp_id")
	private BigDecimal adOrgbpId;

	@Column(name="bpartner_parent_id")
	private BigDecimal bpartnerParentId;

	@Column(name="c_bp_group_id")
	private BigDecimal cBpGroupId;

	@Column(name="c_bpartner_uu")
	private String cBpartnerUu;

	@Column(name="c_dunning_id")
	private BigDecimal cDunningId;

	@Column(name="c_greeting_id")
	private BigDecimal cGreetingId;

	@Column(name="c_invoiceschedule_id")
	private BigDecimal cInvoicescheduleId;

	@Column(name="c_paymentterm_id")
	private BigDecimal cPaymenttermId;

	@Column(name="c_taxgroup_id")
	private BigDecimal cTaxgroupId;

	private BigDecimal cardavsync;

	private Timestamp created;

	private BigDecimal createdby;

	private String customerprofileid;

	@Column(name="default1099box_id")
	private BigDecimal default1099boxId;

	private String deliveryrule;

	private String deliveryviarule;

	private String description;

	private BigDecimal documentcopies;

    @Temporal( TemporalType.DATE)
	private Date dunninggrace;

	private String duns;

	private Timestamp firstsale;

	private BigDecimal flatdiscount;

	private String freightcostrule;

	@Column(name="invoice_printformat_id")
	private BigDecimal invoicePrintformatId;

	private String invoicerule;

	private String is1099vendor;

	private String isactive;

	private String iscustomer;

	private String isdiscountprinted;

	private String isemployee;

	private String ismanufacturer;

	private String isonetime;

	private String ispotaxexempt;

	private String isprospect;

	private String issalesrep;

	private String issummary;

	private String istaxexempt;

	private String isvendor;

	@Column(name="logo_id")
	private BigDecimal logoId;

	@Column(name="m_discountschema_id")
	private BigDecimal mDiscountschemaId;

	@Column(name="m_pricelist_id")
	private BigDecimal mPricelistId;

	private String naics;

	private String name;

	private String name2;

	private String nationalidnumber;

	private String nraams;

	private BigDecimal numberemployees;

	private String paymentrule;

	private String paymentrulepo;

	@Column(name="po_discountschema_id")
	private BigDecimal poDiscountschemaId;

	@Column(name="po_paymentterm_id")
	private BigDecimal poPaymenttermId;

	@Column(name="po_pricelist_id")
	private BigDecimal poPricelistId;

	private String poreference;

	private BigDecimal potentiallifetimevalue;

	private String rating;

	private String referenceno;

	@Column(name="salesrep_id")
	private BigDecimal salesrepId;

	private BigDecimal salesvolume;

	private String sendemail;

	private BigDecimal shareofcustomer;

	private BigDecimal shelflifeminpct;

	@Column(name="so_creditlimit")
	private BigDecimal soCreditlimit;

	@Column(name="so_creditused")
	private BigDecimal soCreditused;

	@Column(name="so_description")
	private String soDescription;

	private String socreditstatus;

	private String taxid;

	@Column(name="taxtypebppartner_id")
	private String taxtypebppartnerId;

	private BigDecimal totalopenbalance;

	private Timestamp updated;

	private BigDecimal updatedby;

	private String url;

	private String value;

	//bi-directional many-to-one association to AdUser
	@OneToMany(mappedBy="CBpartner")
	private List<AdUser> adUsers;

	//bi-directional many-to-one association to CBpartnerLocation
	@OneToMany(mappedBy="CBpartner")
	private List<CBpartnerLocation> CBpartnerLocations;

    public CBpartner() {
    }

	public long getCBpartnerId() {
		return this.cBpartnerId;
	}

	public void setCBpartnerId(long cBpartnerId) {
		this.cBpartnerId = cBpartnerId;
	}

	public BigDecimal getAcqusitioncost() {
		return this.acqusitioncost;
	}

	public void setAcqusitioncost(BigDecimal acqusitioncost) {
		this.acqusitioncost = acqusitioncost;
	}

	public BigDecimal getActuallifetimevalue() {
		return this.actuallifetimevalue;
	}

	public void setActuallifetimevalue(BigDecimal actuallifetimevalue) {
		this.actuallifetimevalue = actuallifetimevalue;
	}

	public BigDecimal getAdClientId() {
		return this.adClientId;
	}

	public void setAdClientId(BigDecimal adClientId) {
		this.adClientId = adClientId;
	}

	public String getAdLanguage() {
		return this.adLanguage;
	}

	public void setAdLanguage(String adLanguage) {
		this.adLanguage = adLanguage;
	}

	public BigDecimal getAdOrgId() {
		return this.adOrgId;
	}

	public void setAdOrgId(BigDecimal adOrgId) {
		this.adOrgId = adOrgId;
	}

	public BigDecimal getAdOrgbpId() {
		return this.adOrgbpId;
	}

	public void setAdOrgbpId(BigDecimal adOrgbpId) {
		this.adOrgbpId = adOrgbpId;
	}

	public BigDecimal getBpartnerParentId() {
		return this.bpartnerParentId;
	}

	public void setBpartnerParentId(BigDecimal bpartnerParentId) {
		this.bpartnerParentId = bpartnerParentId;
	}

	public BigDecimal getCBpGroupId() {
		return this.cBpGroupId;
	}

	public void setCBpGroupId(BigDecimal cBpGroupId) {
		this.cBpGroupId = cBpGroupId;
	}

	public String getCBpartnerUu() {
		return this.cBpartnerUu;
	}

	public void setCBpartnerUu(String cBpartnerUu) {
		this.cBpartnerUu = cBpartnerUu;
	}

	public BigDecimal getCDunningId() {
		return this.cDunningId;
	}

	public void setCDunningId(BigDecimal cDunningId) {
		this.cDunningId = cDunningId;
	}

	public BigDecimal getCGreetingId() {
		return this.cGreetingId;
	}

	public void setCGreetingId(BigDecimal cGreetingId) {
		this.cGreetingId = cGreetingId;
	}

	public BigDecimal getCInvoicescheduleId() {
		return this.cInvoicescheduleId;
	}

	public void setCInvoicescheduleId(BigDecimal cInvoicescheduleId) {
		this.cInvoicescheduleId = cInvoicescheduleId;
	}

	public BigDecimal getCPaymenttermId() {
		return this.cPaymenttermId;
	}

	public void setCPaymenttermId(BigDecimal cPaymenttermId) {
		this.cPaymenttermId = cPaymenttermId;
	}

	public BigDecimal getCTaxgroupId() {
		return this.cTaxgroupId;
	}

	public void setCTaxgroupId(BigDecimal cTaxgroupId) {
		this.cTaxgroupId = cTaxgroupId;
	}

	public BigDecimal getCardavsync() {
		return this.cardavsync;
	}

	public void setCardavsync(BigDecimal cardavsync) {
		this.cardavsync = cardavsync;
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

	public String getCustomerprofileid() {
		return this.customerprofileid;
	}

	public void setCustomerprofileid(String customerprofileid) {
		this.customerprofileid = customerprofileid;
	}

	public BigDecimal getDefault1099boxId() {
		return this.default1099boxId;
	}

	public void setDefault1099boxId(BigDecimal default1099boxId) {
		this.default1099boxId = default1099boxId;
	}

	public String getDeliveryrule() {
		return this.deliveryrule;
	}

	public void setDeliveryrule(String deliveryrule) {
		this.deliveryrule = deliveryrule;
	}

	public String getDeliveryviarule() {
		return this.deliveryviarule;
	}

	public void setDeliveryviarule(String deliveryviarule) {
		this.deliveryviarule = deliveryviarule;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDocumentcopies() {
		return this.documentcopies;
	}

	public void setDocumentcopies(BigDecimal documentcopies) {
		this.documentcopies = documentcopies;
	}

	public Date getDunninggrace() {
		return this.dunninggrace;
	}

	public void setDunninggrace(Date dunninggrace) {
		this.dunninggrace = dunninggrace;
	}

	public String getDuns() {
		return this.duns;
	}

	public void setDuns(String duns) {
		this.duns = duns;
	}

	public Timestamp getFirstsale() {
		return this.firstsale;
	}

	public void setFirstsale(Timestamp firstsale) {
		this.firstsale = firstsale;
	}

	public BigDecimal getFlatdiscount() {
		return this.flatdiscount;
	}

	public void setFlatdiscount(BigDecimal flatdiscount) {
		this.flatdiscount = flatdiscount;
	}

	public String getFreightcostrule() {
		return this.freightcostrule;
	}

	public void setFreightcostrule(String freightcostrule) {
		this.freightcostrule = freightcostrule;
	}

	public BigDecimal getInvoicePrintformatId() {
		return this.invoicePrintformatId;
	}

	public void setInvoicePrintformatId(BigDecimal invoicePrintformatId) {
		this.invoicePrintformatId = invoicePrintformatId;
	}

	public String getInvoicerule() {
		return this.invoicerule;
	}

	public void setInvoicerule(String invoicerule) {
		this.invoicerule = invoicerule;
	}

	public String getIs1099vendor() {
		return this.is1099vendor;
	}

	public void setIs1099vendor(String is1099vendor) {
		this.is1099vendor = is1099vendor;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIscustomer() {
		return this.iscustomer;
	}

	public void setIscustomer(String iscustomer) {
		this.iscustomer = iscustomer;
	}

	public String getIsdiscountprinted() {
		return this.isdiscountprinted;
	}

	public void setIsdiscountprinted(String isdiscountprinted) {
		this.isdiscountprinted = isdiscountprinted;
	}

	public String getIsemployee() {
		return this.isemployee;
	}

	public void setIsemployee(String isemployee) {
		this.isemployee = isemployee;
	}

	public String getIsmanufacturer() {
		return this.ismanufacturer;
	}

	public void setIsmanufacturer(String ismanufacturer) {
		this.ismanufacturer = ismanufacturer;
	}

	public String getIsonetime() {
		return this.isonetime;
	}

	public void setIsonetime(String isonetime) {
		this.isonetime = isonetime;
	}

	public String getIspotaxexempt() {
		return this.ispotaxexempt;
	}

	public void setIspotaxexempt(String ispotaxexempt) {
		this.ispotaxexempt = ispotaxexempt;
	}

	public String getIsprospect() {
		return this.isprospect;
	}

	public void setIsprospect(String isprospect) {
		this.isprospect = isprospect;
	}

	public String getIssalesrep() {
		return this.issalesrep;
	}

	public void setIssalesrep(String issalesrep) {
		this.issalesrep = issalesrep;
	}

	public String getIssummary() {
		return this.issummary;
	}

	public void setIssummary(String issummary) {
		this.issummary = issummary;
	}

	public String getIstaxexempt() {
		return this.istaxexempt;
	}

	public void setIstaxexempt(String istaxexempt) {
		this.istaxexempt = istaxexempt;
	}

	public String getIsvendor() {
		return this.isvendor;
	}

	public void setIsvendor(String isvendor) {
		this.isvendor = isvendor;
	}

	public BigDecimal getLogoId() {
		return this.logoId;
	}

	public void setLogoId(BigDecimal logoId) {
		this.logoId = logoId;
	}

	public BigDecimal getMDiscountschemaId() {
		return this.mDiscountschemaId;
	}

	public void setMDiscountschemaId(BigDecimal mDiscountschemaId) {
		this.mDiscountschemaId = mDiscountschemaId;
	}

	public BigDecimal getMPricelistId() {
		return this.mPricelistId;
	}

	public void setMPricelistId(BigDecimal mPricelistId) {
		this.mPricelistId = mPricelistId;
	}

	public String getNaics() {
		return this.naics;
	}

	public void setNaics(String naics) {
		this.naics = naics;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName2() {
		return this.name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getNationalidnumber() {
		return this.nationalidnumber;
	}

	public void setNationalidnumber(String nationalidnumber) {
		this.nationalidnumber = nationalidnumber;
	}

	public String getNraams() {
		return this.nraams;
	}

	public void setNraams(String nraams) {
		this.nraams = nraams;
	}

	public BigDecimal getNumberemployees() {
		return this.numberemployees;
	}

	public void setNumberemployees(BigDecimal numberemployees) {
		this.numberemployees = numberemployees;
	}

	public String getPaymentrule() {
		return this.paymentrule;
	}

	public void setPaymentrule(String paymentrule) {
		this.paymentrule = paymentrule;
	}

	public String getPaymentrulepo() {
		return this.paymentrulepo;
	}

	public void setPaymentrulepo(String paymentrulepo) {
		this.paymentrulepo = paymentrulepo;
	}

	public BigDecimal getPoDiscountschemaId() {
		return this.poDiscountschemaId;
	}

	public void setPoDiscountschemaId(BigDecimal poDiscountschemaId) {
		this.poDiscountschemaId = poDiscountschemaId;
	}

	public BigDecimal getPoPaymenttermId() {
		return this.poPaymenttermId;
	}

	public void setPoPaymenttermId(BigDecimal poPaymenttermId) {
		this.poPaymenttermId = poPaymenttermId;
	}

	public BigDecimal getPoPricelistId() {
		return this.poPricelistId;
	}

	public void setPoPricelistId(BigDecimal poPricelistId) {
		this.poPricelistId = poPricelistId;
	}

	public String getPoreference() {
		return this.poreference;
	}

	public void setPoreference(String poreference) {
		this.poreference = poreference;
	}

	public BigDecimal getPotentiallifetimevalue() {
		return this.potentiallifetimevalue;
	}

	public void setPotentiallifetimevalue(BigDecimal potentiallifetimevalue) {
		this.potentiallifetimevalue = potentiallifetimevalue;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getReferenceno() {
		return this.referenceno;
	}

	public void setReferenceno(String referenceno) {
		this.referenceno = referenceno;
	}

	public BigDecimal getSalesrepId() {
		return this.salesrepId;
	}

	public void setSalesrepId(BigDecimal salesrepId) {
		this.salesrepId = salesrepId;
	}

	public BigDecimal getSalesvolume() {
		return this.salesvolume;
	}

	public void setSalesvolume(BigDecimal salesvolume) {
		this.salesvolume = salesvolume;
	}

	public String getSendemail() {
		return this.sendemail;
	}

	public void setSendemail(String sendemail) {
		this.sendemail = sendemail;
	}

	public BigDecimal getShareofcustomer() {
		return this.shareofcustomer;
	}

	public void setShareofcustomer(BigDecimal shareofcustomer) {
		this.shareofcustomer = shareofcustomer;
	}

	public BigDecimal getShelflifeminpct() {
		return this.shelflifeminpct;
	}

	public void setShelflifeminpct(BigDecimal shelflifeminpct) {
		this.shelflifeminpct = shelflifeminpct;
	}

	public BigDecimal getSoCreditlimit() {
		return this.soCreditlimit;
	}

	public void setSoCreditlimit(BigDecimal soCreditlimit) {
		this.soCreditlimit = soCreditlimit;
	}

	public BigDecimal getSoCreditused() {
		return this.soCreditused;
	}

	public void setSoCreditused(BigDecimal soCreditused) {
		this.soCreditused = soCreditused;
	}

	public String getSoDescription() {
		return this.soDescription;
	}

	public void setSoDescription(String soDescription) {
		this.soDescription = soDescription;
	}

	public String getSocreditstatus() {
		return this.socreditstatus;
	}

	public void setSocreditstatus(String socreditstatus) {
		this.socreditstatus = socreditstatus;
	}

	public String getTaxid() {
		return this.taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public String getTaxtypebppartnerId() {
		return this.taxtypebppartnerId;
	}

	public void setTaxtypebppartnerId(String taxtypebppartnerId) {
		this.taxtypebppartnerId = taxtypebppartnerId;
	}

	public BigDecimal getTotalopenbalance() {
		return this.totalopenbalance;
	}

	public void setTotalopenbalance(BigDecimal totalopenbalance) {
		this.totalopenbalance = totalopenbalance;
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

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<AdUser> getAdUsers() {
		return this.adUsers;
	}

	public void setAdUsers(List<AdUser> adUsers) {
		this.adUsers = adUsers;
	}
	
	public List<CBpartnerLocation> getCBpartnerLocations() {
		return this.CBpartnerLocations;
	}

	public void setCBpartnerLocations(List<CBpartnerLocation> CBpartnerLocations) {
		this.CBpartnerLocations = CBpartnerLocations;
	}
	
}