package it.cnet.connection.pojo;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the ad_caldavparam database table.
 * 
 */
@Entity
@Table(name="ad_caldavparam")
public class AdCaldavparam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ad_caldavparam_id")
	private long adCaldavparamId;

	@Column(name="ad_caldavparam_uu")
	private String adCaldavparamUu;

	@Column(name="ad_client_id")
	private java.math.BigDecimal adClientId;

	@Column(name="ad_org_id")
	private java.math.BigDecimal adOrgId;

	private String calendar;

	private Timestamp created;

	private java.math.BigDecimal createdby;

	private String isactive;

	private String namepubliccalendar;

	private String nameusercaldav;

	private Timestamp updated;

	private java.math.BigDecimal updatedby;

	//bi-directional many-to-one association to AdUser
    @ManyToOne
	@JoinColumn(name="ad_user_id")
	private AdUser adUser;

	//bi-directional many-to-one association to RRequesttype
    @ManyToOne
	@JoinColumn(name="r_requesttype_id")
	private RRequesttype RRequesttype;

    public AdCaldavparam() {
    }

	public long getAdCaldavparamId() {
		return this.adCaldavparamId;
	}

	public void setAdCaldavparamId(long adCaldavparamId) {
		this.adCaldavparamId = adCaldavparamId;
	}

	public String getAdCaldavparamUu() {
		return this.adCaldavparamUu;
	}

	public void setAdCaldavparamUu(String adCaldavparamUu) {
		this.adCaldavparamUu = adCaldavparamUu;
	}

	public java.math.BigDecimal getAdClientId() {
		return this.adClientId;
	}

	public void setAdClientId(java.math.BigDecimal adClientId) {
		this.adClientId = adClientId;
	}

	public java.math.BigDecimal getAdOrgId() {
		return this.adOrgId;
	}

	public void setAdOrgId(java.math.BigDecimal adOrgId) {
		this.adOrgId = adOrgId;
	}

	public String getCalendar() {
		return this.calendar;
	}

	public void setCalendar(String calendar) {
		this.calendar = calendar;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public java.math.BigDecimal getCreatedby() {
		return this.createdby;
	}

	public void setCreatedby(java.math.BigDecimal createdby) {
		this.createdby = createdby;
	}

	public String getIsactive() {
		return this.isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getNamepubliccalendar() {
		return this.namepubliccalendar;
	}

	public void setNamepubliccalendar(String namepubliccalendar) {
		this.namepubliccalendar = namepubliccalendar;
	}

	public String getNameusercaldav() {
		return this.nameusercaldav;
	}

	public void setNameusercaldav(String nameusercaldav) {
		this.nameusercaldav = nameusercaldav;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public java.math.BigDecimal getUpdatedby() {
		return this.updatedby;
	}

	public void setUpdatedby(java.math.BigDecimal updatedby) {
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