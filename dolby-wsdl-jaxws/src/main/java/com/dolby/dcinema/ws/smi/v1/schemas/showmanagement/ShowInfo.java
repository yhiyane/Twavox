
package com.dolby.dcinema.ws.smi.v1.schemas.showmanagement;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import com.dolby.dcinema.ws.smi.v1.schemas.common.InfoPropertyType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.LicenseState;
import com.dolby.dcinema.ws.smi.v1.schemas.common.Rating;


/**
 * <p>Classe Java pour ShowInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ShowInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="showId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}duration"/&gt;
 *         &lt;element name="encrypted" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="transferring" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="missingData" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="corrupt" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="licensed" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}LicenseState"/&gt;
 *         &lt;element name="playable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="rating" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}Rating"/&gt;
 *         &lt;element name="properties" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}InfoPropertyType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShowInfo", propOrder = {
    "showId",
    "name",
    "duration",
    "encrypted",
    "transferring",
    "missingData",
    "corrupt",
    "licensed",
    "playable",
    "rating",
    "properties"
})
public class ShowInfo {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String showId;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Duration duration;
    protected boolean encrypted;
    protected boolean transferring;
    protected boolean missingData;
    protected boolean corrupt;
    @XmlElement(required = true)
    protected LicenseState licensed;
    protected boolean playable;
    @XmlElement(required = true)
    protected Rating rating;
    protected List<InfoPropertyType> properties;

    /**
     * Obtient la valeur de la propriété showId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowId() {
        return showId;
    }

    /**
     * Définit la valeur de la propriété showId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowId(String value) {
        this.showId = value;
    }

    /**
     * Obtient la valeur de la propriété name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Définit la valeur de la propriété name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propriété duration.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Définit la valeur de la propriété duration.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setDuration(Duration value) {
        this.duration = value;
    }

    /**
     * Obtient la valeur de la propriété encrypted.
     * 
     */
    public boolean isEncrypted() {
        return encrypted;
    }

    /**
     * Définit la valeur de la propriété encrypted.
     * 
     */
    public void setEncrypted(boolean value) {
        this.encrypted = value;
    }

    /**
     * Obtient la valeur de la propriété transferring.
     * 
     */
    public boolean isTransferring() {
        return transferring;
    }

    /**
     * Définit la valeur de la propriété transferring.
     * 
     */
    public void setTransferring(boolean value) {
        this.transferring = value;
    }

    /**
     * Obtient la valeur de la propriété missingData.
     * 
     */
    public boolean isMissingData() {
        return missingData;
    }

    /**
     * Définit la valeur de la propriété missingData.
     * 
     */
    public void setMissingData(boolean value) {
        this.missingData = value;
    }

    /**
     * Obtient la valeur de la propriété corrupt.
     * 
     */
    public boolean isCorrupt() {
        return corrupt;
    }

    /**
     * Définit la valeur de la propriété corrupt.
     * 
     */
    public void setCorrupt(boolean value) {
        this.corrupt = value;
    }

    /**
     * Obtient la valeur de la propriété licensed.
     * 
     * @return
     *     possible object is
     *     {@link LicenseState }
     *     
     */
    public LicenseState getLicensed() {
        return licensed;
    }

    /**
     * Définit la valeur de la propriété licensed.
     * 
     * @param value
     *     allowed object is
     *     {@link LicenseState }
     *     
     */
    public void setLicensed(LicenseState value) {
        this.licensed = value;
    }

    /**
     * Obtient la valeur de la propriété playable.
     * 
     */
    public boolean isPlayable() {
        return playable;
    }

    /**
     * Définit la valeur de la propriété playable.
     * 
     */
    public void setPlayable(boolean value) {
        this.playable = value;
    }

    /**
     * Obtient la valeur de la propriété rating.
     * 
     * @return
     *     possible object is
     *     {@link Rating }
     *     
     */
    public Rating getRating() {
        return rating;
    }

    /**
     * Définit la valeur de la propriété rating.
     * 
     * @param value
     *     allowed object is
     *     {@link Rating }
     *     
     */
    public void setRating(Rating value) {
        this.rating = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the properties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InfoPropertyType }
     * 
     * 
     */
    public List<InfoPropertyType> getProperties() {
        if (properties == null) {
            properties = new ArrayList<InfoPropertyType>();
        }
        return this.properties;
    }

}
