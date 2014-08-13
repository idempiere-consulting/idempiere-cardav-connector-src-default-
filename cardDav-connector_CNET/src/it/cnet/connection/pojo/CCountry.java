package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the c_country database table.
 * 
 */
@Entity
@Table(name="c_country")
public class CCountry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="c_country_id")
	private long cCountryId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_language")
	private String adLanguage;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	private String allowcitiesoutoflist;

	@Column(name="c_country_uu")
	private String cCountryUu;

	@Column(name="c_currency_id")
	private BigDecimal cCurrencyId;

	private String capturesequence;

	private String countrycode;

	private Timestamp created;

	private BigDecimal createdby;

	private String description;

	private String displaysequence;

	private String displaysequencelocal;

	private String expressionbankaccountno;

	private String expressionbankroutingno;

	private String expressionphone;

	private String expressionpostal;

	@Column(name="expressionpostal_add")
	private String expressionpostalAdd;

	@Column(name="haspostal_add")
	private String haspostalAdd;

	private String hasregion;

	private String isactive;

	private String isaddresslineslocalreverse;

	private String isaddresslinesreverse;

	private String ispostcodelookup;

	private String lookupclassname;

	private String lookupclientid;

	private String lookuppassword;

	private String lookupurl;

	private String mediasize;

	private String name;

	private String regionname;

	private Timestamp updated;

	private BigDecimal updatedby;

	//bi-directional many-to-one association to CLocation
	@OneToMany(mappedBy="CCountry")
	private List<CLocation> CLocations;

	//bi-directional many-to-one association to CRegion
	@OneToMany(mappedBy="CCountry")
	private List<CRegion> CRegions;

    public CCountry() {
    }

	public long getCCountryId() {
		return this.cCountryId;
	}

	public void setCCountryId(long cCountryId) {
		this.cCountryId = cCountryId;
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

	public String getAllowcitiesoutoflist() {
		return this.allowcitiesoutoflist;
	}

	public void setAllowcitiesoutoflist(String allowcitiesoutoflist) {
		this.allowcitiesoutoflist = allowcitiesoutoflist;
	}

	public String getCCountryUu() {
		return this.cCountryUu;
	}

	public void setCCountryUu(String cCountryUu) {
		this.cCountryUu = cCountryUu;
	}

	public BigDecimal getCCurrencyId() {
		return this.cCurrencyId;
	}

	public void setCCurrencyId(BigDecimal cCurrencyId) {
		this.cCurrencyId = cCurrencyId;
	}

	public String getCapturesequence() {
		return this.capturesequence;
	}

	public void setCapturesequence(String capturesequence) {
		this.capturesequence = capturesequence;
	}

	public String getCountrycode() {
		return this.countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
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

	public String getDisplaysequence() {
		return this.displaysequence;
	}

	public void setDisplaysequence(String displaysequence) {
		this.displaysequence = displaysequence;
	}

	public String getDisplaysequencelocal() {
		return this.displaysequencelocal;
	}

	public void setDisplaysequencelocal(String displaysequencelocal) {
		this.displaysequencelocal = displaysequencelocal;
	}

	public String getExpressionbankaccountno() {
		return this.expressionbankaccountno;
	}

	public void setExpressionbankaccountno(String expressionbankaccountno) {
		this.expressionbankaccountno = expressionbankaccountno;
	}

	public String getExpressionbankroutingno() {
		return this.expressionbankroutingno;
	}

	public void setExpressionbankroutingno(String expressionbankroutingno) {
		this.expressionbankroutingno = expressionbankroutingno;
	}

	public String getExpressionphone() {
		return this.expressionphone;
	}

	public void setExpressionphone(String expressionphone) {
		this.expressionphone = expressionphone;
	}

	public String getExpressionpostal() {
		return this.expressionpostal;
	}

	public void setExpressionpostal(String expressionpostal) {
		this.expressionpostal = expressionpostal;
	}

	public String getExpressionpostalAdd() {
		return this.expressionpostalAdd;
	}

	public void setExpressionpostalAdd(String expressionpostalAdd) {
		this.expressionpostalAdd = expressionpostalAdd;
	}

	public String getHaspostalAdd() {
		return this.haspostalAdd;
	}

	public void setHaspostalAdd(String haspostalAdd) {
		this.haspostalAdd = haspostalAdd;
	}

	public String getHasregion() {
		return this.hasregion;
	}

	public void setHasregion(String hasregion) {
		this.hasregion = hasregion;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsaddresslineslocalreverse() {
		return this.isaddresslineslocalreverse;
	}

	public void setIsaddresslineslocalreverse(String isaddresslineslocalreverse) {
		this.isaddresslineslocalreverse = isaddresslineslocalreverse;
	}

	public String getIsaddresslinesreverse() {
		return this.isaddresslinesreverse;
	}

	public void setIsaddresslinesreverse(String isaddresslinesreverse) {
		this.isaddresslinesreverse = isaddresslinesreverse;
	}

	public String getIspostcodelookup() {
		return this.ispostcodelookup;
	}

	public void setIspostcodelookup(String ispostcodelookup) {
		this.ispostcodelookup = ispostcodelookup;
	}

	public String getLookupclassname() {
		return this.lookupclassname;
	}

	public void setLookupclassname(String lookupclassname) {
		this.lookupclassname = lookupclassname;
	}

	public String getLookupclientid() {
		return this.lookupclientid;
	}

	public void setLookupclientid(String lookupclientid) {
		this.lookupclientid = lookupclientid;
	}

	public String getLookuppassword() {
		return this.lookuppassword;
	}

	public void setLookuppassword(String lookuppassword) {
		this.lookuppassword = lookuppassword;
	}

	public String getLookupurl() {
		return this.lookupurl;
	}

	public void setLookupurl(String lookupurl) {
		this.lookupurl = lookupurl;
	}

	public String getMediasize() {
		return this.mediasize;
	}

	public void setMediasize(String mediasize) {
		this.mediasize = mediasize;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegionname() {
		return this.regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
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

	public List<CLocation> getCLocations() {
		return this.CLocations;
	}

	public void setCLocations(List<CLocation> CLocations) {
		this.CLocations = CLocations;
	}
	
	public List<CRegion> getCRegions() {
		return this.CRegions;
	}

	public void setCRegions(List<CRegion> CRegions) {
		this.CRegions = CRegions;
	}
	
}