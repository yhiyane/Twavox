
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TransportState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TransportState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="transportStateType" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}TransportStateType"/&gt;
 *         &lt;element name="transportStateUnavailableReason" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}TransportStateUnavailableReason" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransportState", propOrder = {
    "transportStateType",
    "transportStateUnavailableReason"
})
public class TransportState {

    @XmlElement(required = true)
    protected TransportStateType transportStateType;
    protected List<TransportStateUnavailableReason> transportStateUnavailableReason;

    /**
     * Obtient la valeur de la propriété transportStateType.
     * 
     * @return
     *     possible object is
     *     {@link TransportStateType }
     *     
     */
    public TransportStateType getTransportStateType() {
        return transportStateType;
    }

    /**
     * Définit la valeur de la propriété transportStateType.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportStateType }
     *     
     */
    public void setTransportStateType(TransportStateType value) {
        this.transportStateType = value;
    }

    /**
     * Gets the value of the transportStateUnavailableReason property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transportStateUnavailableReason property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransportStateUnavailableReason().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransportStateUnavailableReason }
     * 
     * 
     */
    public List<TransportStateUnavailableReason> getTransportStateUnavailableReason() {
        if (transportStateUnavailableReason == null) {
            transportStateUnavailableReason = new ArrayList<TransportStateUnavailableReason>();
        }
        return this.transportStateUnavailableReason;
    }

}
