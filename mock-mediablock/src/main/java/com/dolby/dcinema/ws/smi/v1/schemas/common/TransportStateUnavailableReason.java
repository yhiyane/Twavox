
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Classe Java pour TransportStateUnavailableReason complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TransportStateUnavailableReason"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.dolby.com/dcinema/ws/smi/v1/schemas/common&gt;TransportStateUnavailableReasonEnum"&gt;
 *       &lt;attribute name="extension" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransportStateUnavailableReason", propOrder = {
    "value"
})
public class TransportStateUnavailableReason {

    @XmlValue
    protected TransportStateUnavailableReasonEnum value;
    @XmlAttribute(name = "extension")
    protected String extension;

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link TransportStateUnavailableReasonEnum }
     *     
     */
    public TransportStateUnavailableReasonEnum getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportStateUnavailableReasonEnum }
     *     
     */
    public void setValue(TransportStateUnavailableReasonEnum value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété extension.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Définit la valeur de la propriété extension.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtension(String value) {
        this.extension = value;
    }

}
