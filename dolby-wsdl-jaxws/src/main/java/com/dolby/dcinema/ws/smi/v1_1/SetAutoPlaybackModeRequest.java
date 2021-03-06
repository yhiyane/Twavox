
package com.dolby.dcinema.ws.smi.v1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.AutoPlaybackMode;


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
 *         &lt;element name="autoPlaybackMode" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}AutoPlaybackMode"/&gt;
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
    "autoPlaybackMode"
})
@XmlRootElement(name = "setAutoPlaybackModeRequest")
public class SetAutoPlaybackModeRequest {

    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    protected AutoPlaybackMode autoPlaybackMode;

    /**
     * Obtient la valeur de la propriété autoPlaybackMode.
     * 
     * @return
     *     possible object is
     *     {@link AutoPlaybackMode }
     *     
     */
    public AutoPlaybackMode getAutoPlaybackMode() {
        return autoPlaybackMode;
    }

    /**
     * Définit la valeur de la propriété autoPlaybackMode.
     * 
     * @param value
     *     allowed object is
     *     {@link AutoPlaybackMode }
     *     
     */
    public void setAutoPlaybackMode(AutoPlaybackMode value) {
        this.autoPlaybackMode = value;
    }

}
