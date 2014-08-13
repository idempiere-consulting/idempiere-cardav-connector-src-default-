package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the c_location database table.
 * 
 */
@Entity
@Table(name="c_location")
public class CLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="c_location_id")
	private long cLocationId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	private String address1;

	private String address2;

	private String address3;

	private String address4;

	@Column(name="c_addressvalidation_id")
	private BigDecimal cAddressvalidationId;

	@Column(name="c_city_id")
	private BigDecimal cCityId;

	@Column(name="c_location_uu")
	private String cLocationUu;

	private String city;

	private Timestamp created;

	private BigDecimal createdby;

	private String isactive;

	private String isvalid;

	private String postal;

	@Column(name="postal_add")
	private String postalAdd;

	private String regionname;

	private String result;

	private Timestamp updated;

	private BigDecimal updatedby;

	private String validateaddress;

	//bi-directional many-to-one association to CBpartnerLocation
	@OneToMany(mappedBy="CLocation")
	private List<CBpartnerLocation> CBpartnerLocations;

	//bi-directional many-to-one association to CCountry
    @ManyToOne
	@JoinColumn(name="c_country_id")
	private CCountry CCountry;

	//bi-directional many-to-one association to CRegion
    @ManyToOne
	@JoinColumn(name="c_region_id")
	private CRegion CRegion;

	//bi-directional many-to-one association to AdUser
	@OneToMany(mappedBy="CLocation")
	private List<AdUser> adUsers;

    public CLocation() {
    }

	public long getCLocationId() {
		return this.cLocationId;
	}

	public void setCLocationId(long cLocationId) {
		this.cLocationId = cLocationId;
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

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return this.address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public BigDecimal getCAddressvalidationId() {
		return this.cAddressvalidationId;
	}

	public void setCAddressvalidationId(BigDecimal cAddressvalidationId) {
		this.cAddressvalidationId = cAddressvalidationId;
	}

	public BigDecimal getCCityId() {
		return this.cCityId;
	}

	public void setCCityId(BigDecimal cCityId) {
		this.cCityId = cCityId;
	}

	public String getCLocationUu() {
		return this.cLocationUu;
	}

	public void setCLocationUu(String cLocationUu) {
		this.cLocationUu = cLocationUu;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsvalid() {
		return this.isvalid;
	}

	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getPostalAdd() {
		return this.postalAdd;
	}

	public void setPostalAdd(String postalAdd) {
		this.postalAdd = postalAdd;
	}

	public String getRegionname() {
		return this.regionname;
	}

	public void setRegionname(String regionname) {
		this.regionname = regionname;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
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

	public String getValidateaddress() {
		return this.validateaddress;
	}

	public void setValidateaddress(String validateaddress) {
		this.validateaddress = validateaddress;
	}

	public List<CBpartnerLocation> getCBpartnerLocations() {
		return this.CBpartnerLocations;
	}

	public void setCBpartnerLocations(List<CBpartnerLocation> CBpartnerLocations) {
		this.CBpartnerLocations = CBpartnerLocations;
	}
	
	public CCountry getCCountry() {
		return this.CCountry;
	}

	public void setCCountry(CCountry CCountry) {
		this.CCountry = CCountry;
	}
	
	public CRegion getCRegion() {
		return this.CRegion;
	}

	public void setCRegion(CRegion CRegion) {
		this.CRegion = CRegion;
	}
	
	public List<AdUser> getAdUsers() {
		return this.adUsers;
	}

	public void setAdUsers(List<AdUser> adUsers) {
		this.adUsers = adUsers;
	}
	
}