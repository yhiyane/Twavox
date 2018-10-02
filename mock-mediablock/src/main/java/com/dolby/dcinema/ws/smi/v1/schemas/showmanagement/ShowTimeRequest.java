
package com.dolby.dcinema.ws.smi.v1.schemas.showmanagement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour ShowTimeRequest complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ShowTimeRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="showTimeId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID" minOccurs="0"/&gt;
 *         &lt;element name="showId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="auditoriumNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShowTimeRequest", propOrder = {
    "showTimeId",
    "showId",
    "startTime",
    "auditoriumNumber"
})
public class ShowTimeRequest {

    @XmlSchemaType(name = "anyURI")
    protected String showTimeId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String showId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    protected String auditoriumNumber;

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

}
