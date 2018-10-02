
package com.dolby.dcinema.ws.smi.v1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.PurgeSuiteState;


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
 *         &lt;element name="purgeSuiteState" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}PurgeSuiteState"/&gt;
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
    "purgeSuiteState"
})
@XmlRootElement(name = "getPurgeSuiteStateResponse")
public class GetPurgeSuiteStateResponse {

    @XmlElement(required = true)
    protected PurgeSuiteState purgeSuiteState;

    /**
     * Obtient la valeur de la propriété purgeSuiteState.
     * 
     * @return
     *     possible object is
     *     {@link PurgeSuiteState }
     *     
     */
    public PurgeSuiteState getPurgeSuiteState() {
        return purgeSuiteState;
    }

    /**
     * Définit la valeur de la propriété purgeSuiteState.
     * 
     * @param value
     *     allowed object is
     *     {@link PurgeSuiteState }
     *     
     */
    public void setPurgeSuiteState(PurgeSuiteState value) {
        this.purgeSuiteState = value;
    }

}
