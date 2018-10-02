
package com.dolby.dcinema.ws.smi.v1_2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.PrepSuiteStatus;


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
 *         &lt;element name="prepSuiteStatus" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}PrepSuiteStatus"/&gt;
 *         &lt;element name="clipId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID" minOccurs="0"/&gt;
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
    "prepSuiteStatus",
    "clipId"
})
@XmlRootElement(name = "getPrepSuiteStateResponse")
public class GetPrepSuiteStateResponse {

    @XmlElement(required = true)
    protected PrepSuiteStatus prepSuiteStatus;
    @XmlSchemaType(name = "anyURI")
    protected String clipId;

    /**
     * Obtient la valeur de la propriété prepSuiteStatus.
     * 
     * @return
     *     possible object is
     *     {@link PrepSuiteStatus }
     *     
     */
    public PrepSuiteStatus getPrepSuiteStatus() {
        return prepSuiteStatus;
    }

    /**
     * Définit la valeur de la propriété prepSuiteStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link PrepSuiteStatus }
     *     
     */
    public void setPrepSuiteStatus(PrepSuiteStatus value) {
        this.prepSuiteStatus = value;
    }

    /**
     * Obtient la valeur de la propriété clipId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClipId() {
        return clipId;
    }

    /**
     * Définit la valeur de la propriété clipId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClipId(String value) {
        this.clipId = value;
    }

}
