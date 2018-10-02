
package com.dolby.dcinema.ws.smi.v1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour anonymous complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="cueId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="parameterizedValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "cueId",
    "parameterizedValue"
})
@XmlRootElement(name = "fireCueRequest")
public class FireCueRequest {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String cueId;
    protected String parameterizedValue;

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
     * Obtient la valeur de la propriété parameterizedValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterizedValue() {
        return parameterizedValue;
    }

    /**
     * Définit la valeur de la propriété parameterizedValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterizedValue(String value) {
        this.parameterizedValue = value;
    }

}
