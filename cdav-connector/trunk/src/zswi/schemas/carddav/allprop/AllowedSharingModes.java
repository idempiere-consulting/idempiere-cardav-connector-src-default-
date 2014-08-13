
package zswi.schemas.carddav.allprop;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://calendarserver.org/ns/}can-be-shared" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "canBeShared"
})
@XmlRootElement(name = "allowed-sharing-modes", namespace = "http://calendarserver.org/ns/")
public class AllowedSharingModes {

    @XmlElement(name = "can-be-shared", namespace = "http://calendarserver.org/ns/")
    protected CanBeShared canBeShared;

    /**
     * Gets the value of the canBeShared property.
     * 
     * @return
     *     possible object is
     *     {@link CanBeShared }
     *     
     */
    public CanBeShared getCanBeShared() {
        return canBeShared;
    }

    /**
     * Sets the value of the canBeShared property.
     * 
     * @param value
     *     allowed object is
     *     {@link CanBeShared }
     *     
     */
    public void setCanBeShared(CanBeShared value) {
        this.canBeShared = value;
    }

}
