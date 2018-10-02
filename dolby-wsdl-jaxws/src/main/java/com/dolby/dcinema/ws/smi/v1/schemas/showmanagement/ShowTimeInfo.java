
package com.dolby.dcinema.ws.smi.v1.schemas.showmanagement;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import com.dolby.dcinema.ws.smi.v1.schemas.common.InfoPropertyType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.LicenseState;


/**
 * <p>Classe Java pour ShowTimeInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ShowTimeInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="showTimeId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="showId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="auditoriumNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="licensed" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}LicenseState"/&gt;
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}duration"/&gt;
 *         &lt;element name="playable" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="overlap" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
@XmlType(name = "ShowTimeInfo", propOrder = {
    "showTimeId",
    "showId",
    "auditoriumNumber",
    "startTime",
    "licensed",
    "duration",
    "playable",
    "overlap",
    "properties"
})
public class ShowTimeInfo {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String showTimeId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String showId;
    @XmlElement(required = true)
    protected String auditoriumNumber;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    @XmlElement(required = true)
    protected LicenseState licensed;
    @XmlElement(required = true)
    protected Duration duration;
    protected boolean playable;
    protected boolean overlap;
    protected List<InfoPropertyType> properties;

    /**
     * Obtient la valeur de la propriété showTimeId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowTimeId() {
        return showTimeId;
    }

    /**
     * Définit la valeur de la propriété showTimeId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowTimeId(String value) {
        this.showTimeId = value;
    }

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
     * Obtient la valeur de la propriété auditoriumNumber.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditoriumNumber() {
        return auditoriumNumber;
    }

    /**
     * Définit la valeur de la propriété auditoriumNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditoriumNumber(String value) {
        this.auditoriumNumber = value;
    }

    /**
     * Obtient la valeur de la propriété startTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Définit la valeur de la propriété startTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
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
     * Obtient la valeur de la propriété overlap.
     * 
     */
    public boolean isOverlap() {
        return overlap;
    }

    /**
     * Définit la valeur de la propriété overlap.
     * 
     */
    public void setOverlap(boolean value) {
        this.overlap = value;
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
