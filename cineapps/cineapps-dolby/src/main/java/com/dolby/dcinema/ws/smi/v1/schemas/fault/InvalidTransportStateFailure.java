
package com.dolby.dcinema.ws.smi.v1.schemas.fault;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.TransportState;


/**
 * <p>Classe Java pour InvalidTransportStateFailure complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InvalidTransportStateFailure"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/fault}Fault"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="transportState" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}TransportState"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvalidTransportStateFailure", propOrder = {
    "transportState"
})
public class InvalidTransportStateFailure
    extends Fault
{

    @XmlElement(required = true)
    protected TransportState transportState;

    /**
     * Obtient la valeur de la propriété transportState.
     * 
     * @return
     *     possible object is
     *     {@link TransportState }
     *     
     */
    public TransportState getTransportState() {
        return transportState;
    }

    /**
     * Définit la valeur de la propriété transportState.
     * 
     * @param value
     *     allowed object is
     *     {@link TransportState }
     *     
     */
    public void setTransportState(TransportState value) {
        this.transportState = value;
    }

}
