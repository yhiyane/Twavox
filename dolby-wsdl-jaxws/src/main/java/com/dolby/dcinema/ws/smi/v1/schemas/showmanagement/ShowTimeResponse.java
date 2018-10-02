
package com.dolby.dcinema.ws.smi.v1.schemas.showmanagement;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.fault.Fault;


/**
 * <p>Classe Java pour ShowTimeResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ShowTimeResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="showTimeId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="fault" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/fault}Fault"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ShowTimeResponse", propOrder = {
    "showTimeId",
    "fault"
})
public class ShowTimeResponse {

    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "anyURI")
    protected String showTimeId;
    @XmlElement(required = true, nillable = true)
    protected Fault fault;

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
     * Obtient la valeur de la propriété fault.
     * 
     * @return
     *     possible object is
     *     {@link Fault }
     *     
     */
    public Fault getFault() {
        return fault;
    }

    /**
     * Définit la valeur de la propriété fault.
     * 
     * @param value
     *     allowed object is
     *     {@link Fault }
     *     
     */
    public void setFault(Fault value) {
        this.fault = value;
    }

}
