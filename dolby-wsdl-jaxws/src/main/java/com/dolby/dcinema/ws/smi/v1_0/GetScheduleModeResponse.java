
package com.dolby.dcinema.ws.smi.v1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ScheduleMode;


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
 *         &lt;element name="scheduleMode" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}ScheduleMode"/&gt;
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
    "scheduleMode"
})
@XmlRootElement(name = "getScheduleModeResponse")
public class GetScheduleModeResponse {

    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    protected ScheduleMode scheduleMode;

    /**
     * Obtient la valeur de la propriété scheduleMode.
     * 
     * @return
     *     possible object is
     *     {@link ScheduleMode }
     *     
     */
    public ScheduleMode getScheduleMode() {
        return scheduleMode;
    }

    /**
     * Définit la valeur de la propriété scheduleMode.
     * 
     * @param value
     *     allowed object is
     *     {@link ScheduleMode }
     *     
     */
    public void setScheduleMode(ScheduleMode value) {
        this.scheduleMode = value;
    }

}
