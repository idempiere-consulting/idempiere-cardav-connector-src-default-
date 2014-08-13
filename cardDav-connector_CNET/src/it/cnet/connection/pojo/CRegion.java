package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the c_region database table.
 * 
 */
@Entity
@Table(name="c_region")
public class CRegion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="c_region_id")
	private long cRegionId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	@Column(name="c_region_uu")
	private String cRegionUu;

	private Timestamp created;

	private BigDecimal createdby;

	private String description;

	private String isactive;

	private String isdefault;

	private String name;

	private Timestamp updated;

	private BigDecimal updatedby;

	//bi-directional many-to-one association to CLocation
	@OneToMany(mappedBy="CRegion")
	private List<CLocation> CLocations;

	//bi-directional many-to-one association to CCountry
    @ManyToOne
	@JoinColumn(name="c_country_id")
	private CCountry CCountry;
    
  //bi-directional many-to-one association to CCity
  	@OneToMany(mappedBy="CRegion")
  	private List<CCity> CCitys;

    public CRegion() {
    }

	public long getCRegionId() {
		return this.cRegionId;
	}

	public void setCRegionId(long cRegionId) {
		this.cRegionId = cRegionId;
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

	public String getCRegionUu() {
		return this.cRegionUu;
	}

	public void setCRegionUu(String cRegionUu) {
		this.cRegionUu = cRegionUu;
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

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getIsdefault() {
		return this.isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public CCountry getCCountry() {
		return this.CCountry;
	}

	public void setCCountry(CCountry CCountry) {
		this.CCountry = CCountry;
	}
	
	public List<CCity> getCCitys() {
		return this.CCitys;
	}

	public void setCCitys(List<CCity> CCitys) {
		this.CCitys = CCitys;
	}
	
}