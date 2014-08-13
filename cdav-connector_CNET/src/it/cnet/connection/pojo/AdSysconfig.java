package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;


/**
 * The persistent class for the ad_sysconfig database table.
 * 
 */
@Entity
@Table(name="ad_sysconfig")
public class AdSysconfig implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ad_sysconfig_id")
	private long adSysconfigId;

	@Column(name="ad_client_id")
	private BigDecimal adClientId;

	@Column(name="ad_org_id")
	private BigDecimal adOrgId;

	@Column(name="ad_sysconfig_uu")
	private String adSysconfigUu;

	private String configurationlevel;

	private Timestamp created;

	private BigDecimal createdby;

	private String description;

	private String entitytype;

	private String isactive;

	private String name;

	private Timestamp updated;

	private BigDecimal updatedby;

	private String value;

    public AdSysconfig() {
    }

	public long getAdSysconfigId() {
		return this.adSysconfigId;
	}

	public void setAdSysconfigId(long adSysconfigId) {
		this.adSysconfigId = adSysconfigId;
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

	public String getAdSysconfigUu() {
		return this.adSysconfigUu;
	}

	public void setAdSysconfigUu(String adSysconfigUu) {
		this.adSysconfigUu = adSysconfigUu;
	}

	public String getConfigurationlevel() {
		return this.configurationlevel;
	}

	public void setConfigurationlevel(String configurationlevel) {
		this.configurationlevel = configurationlevel;
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

	public String getEntitytype() {
		return this.entitytype;
	}

	public void setEntitytype(String entitytype) {
		this.entitytype = entitytype;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
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

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}