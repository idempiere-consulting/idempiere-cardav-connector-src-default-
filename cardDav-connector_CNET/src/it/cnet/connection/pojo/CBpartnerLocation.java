package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the c_bpartner_location database table.
 * 
 */
@Entity
@Table(name="c_bpartner_location")
public class CBpartnerLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="c_bpartner_location_id")
	private long cBpartnerLocationId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	@Column(name="c_bpartner_location_uu")
	private String cBpartnerLocationUu;

	@Column(name="c_salesregion_id")
	private BigDecimal cSalesregionId;

	private Timestamp created;

	private BigDecimal createdby;

	private String customeraddressid;

	private String email;

	private String fax;

	private String isactive;

	private String isbillto;

	private String isdn;

	private String ispayfrom;

	private String isremitto;

	private String isshipto;

	private String name;

	private String phone;

	private String phone2;

	private Timestamp updated;

	private BigDecimal updatedby;

	//bi-directional many-to-one association to CBpartner
    @ManyToOne
	@JoinColumn(name="c_bpartner_id")
	private CBpartner CBpartner;

	//bi-directional many-to-one association to CLocation
    @ManyToOne
	@JoinColumn(name="c_location_id")
	private CLocation CLocation;

	//bi-directional many-to-one association to AdUser
	@OneToMany(mappedBy="CBpartnerLocation")
	private List<AdUser> adUsers;

    public CBpartnerLocation() {
    }

	public long getCBpartnerLocationId() {
		return this.cBpartnerLocationId;
	}

	public void setCBpartnerLocationId(long cBpartnerLocationId) {
		this.cBpartnerLocationId = cBpartnerLocationId;
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

	public String getCBpartnerLocationUu() {
		return this.cBpartnerLocationUu;
	}

	public void setCBpartnerLocationUu(String cBpartnerLocationUu) {
		this.cBpartnerLocationUu = cBpartnerLocationUu;
	}

	public BigDecimal getCSalesregionId() {
		return this.cSalesregionId;
	}

	public void setCSalesregionId(BigDecimal cSalesregionId) {
		this.cSalesregionId = cSalesregionId;
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

	public String getCustomeraddressid() {
		return this.customeraddressid;
	}

	public void setCustomeraddressid(String customeraddressid) {
		this.customeraddressid = customeraddressid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getIsbillto() {
		return this.isbillto;
	}

	public void setIsbillto(String isbillto) {
		this.isbillto = isbillto;
	}

	public String getIsdn() {
		return this.isdn;
	}

	public void setIsdn(String isdn) {
		this.isdn = isdn;
	}

	public String getIspayfrom() {
		return this.ispayfrom;
	}

	public void setIspayfrom(String ispayfrom) {
		this.ispayfrom = ispayfrom;
	}

	public String getIsremitto() {
		return this.isremitto;
	}

	public void setIsremitto(String isremitto) {
		this.isremitto = isremitto;
	}

	public String getIsshipto() {
		return this.isshipto;
	}

	public void setIsshipto(String isshipto) {
		this.isshipto = isshipto;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public CBpartner getCBpartner() {
		return this.CBpartner;
	}

	public void setCBpartner(CBpartner CBpartner) {
		this.CBpartner = CBpartner;
	}
	
	public CLocation getCLocation() {
		return this.CLocation;
	}

	public void setCLocation(CLocation CLocation) {
		this.CLocation = CLocation;
	}
	
	public List<AdUser> getAdUsers() {
		return this.adUsers;
	}

	public void setAdUsers(List<AdUser> adUsers) {
		this.adUsers = adUsers;
	}
	
}