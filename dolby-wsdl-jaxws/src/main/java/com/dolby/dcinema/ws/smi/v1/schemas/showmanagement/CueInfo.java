
package com.dolby.dcinema.ws.smi.v1.schemas.showmanagement;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.DeviceComponentType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.InfoPropertyType;


/**
 * <p>Classe Java pour CueInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CueInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cueId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cueType" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/showmanagement}CueType"/&gt;
 *         &lt;element name="deviceComponentType" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}DeviceComponentType"/&gt;
 *         &lt;element name="auditoriumNumber" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="parameterized" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="isOutput" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
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
@XmlType(name = "CueInfo", propOrder = {
    "cueId",
    "name",
    "cueType",
    "deviceComponentType",
    "auditoriumNumber",
    "parameterized",
    "isOutput",
    "properties"
})
public class CueInfo {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String cueId;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected CueType cueType;
    @XmlElement(required = true)
    protected DeviceComponentType deviceComponentType;
    @XmlElement(required = true)
    protected String auditoriumNumber;
    protected boolean parameterized;
    protected boolean isOutput;
    protected List<InfoPropertyType> properties;

    /**
     * Obtient la valeur de la propriété cueId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCueId() {
        return cueId;
    }

    /**
     * Définit la valeur de la propriété cueId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCueId(String value) {
        this.cueId = value;
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
     * Obtient la valeur de la propriété cueType.
     * 
     * @return
     *     possible object is
     *     {@link CueType }
     *     
     */
    public CueType getCueType() {
        return cueType;
    }

    /**
     * Définit la valeur de la propriété cueType.
     * 
     * @param value
     *     allowed object is
     *     {@link CueType }
     *     
     */
    public void setCueType(CueType value) {
        this.cueType = value;
    }

    /**
     * Obtient la valeur de la propriété deviceComponentType.
     * 
     * @return
     *     possible object is
     *     {@link DeviceComponentType }
     *     
     */
    public DeviceComponentType getDeviceComponentType() {
        return deviceComponentType;
    }

    /**
     * Définit la valeur de la propriété deviceComponentType.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceComponentType }
     *     
     */
    public void setDeviceComponentType(DeviceComponentType value) {
        this.deviceComponentType = value;
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
     * Obtient la valeur de la propriété parameterized.
     * 
     */
    public boolean isParameterized() {
        return parameterized;
    }

    /**
     * Définit la valeur de la propriété parameterized.
     * 
     */
    public void setParameterized(boolean value) {
        this.parameterized = value;
    }

    /**
     * Obtient la valeur de la propriété isOutput.
     * 
     */
    public boolean isIsOutput() {
        return isOutput;
    }

    /**
     * Définit la valeur de la propriété isOutput.
     * 
     */
    public void setIsOutput(boolean value) {
        this.isOutput = value;
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
