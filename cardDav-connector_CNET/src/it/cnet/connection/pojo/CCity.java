package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the c_city database table.
 * 
 */
@Entity
@Table(name="c_city")
public class CCity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="c_city_id")
	private long cCityId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	private String areacode;

	@Column(name="c_city_uu")
	private String cCityUu;

	@Column(name="c_country_id")
	private BigDecimal cCountryId;

	private String coordinates;

	private Timestamp created;

	private BigDecimal createdby;

	private String isactive;

	private String locode;

	private String name;

	private String postal;

	private Timestamp updated;

	private BigDecimal updatedby;

	//bi-directional many-to-one association to CRegion
    @ManyToOne
	@JoinColumn(name="c_region_id")
	private CRegion CRegion;

    public CCity() {
    }

	public long getCCityId() {
		return this.cCityId;
	}

	public void setCCityId(long cCityId) {
		this.cCityId = cCityId;
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

	public String getAreacode() {
		return this.areacode;
	}

	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	public String getCCityUu() {
		return this.cCityUu;
	}

	public void setCCityUu(String cCityUu) {
		this.cCityUu = cCityUu;
	}

	public BigDecimal getCCountryId() {
		return this.cCountryId;
	}

	public void setCCountryId(BigDecimal cCountryId) {
		this.cCountryId = cCountryId;
	}

	public String getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
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

	public String getLocode() {
		return this.locode;
	}

	public void setLocode(String locode) {
		this.locode = locode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostal() {
		return this.postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
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

	public CRegion getCRegion() {
		return this.CRegion;
	}

	public void setCRegion(CRegion CRegion) {
		this.CRegion = CRegion;
	}
	
}