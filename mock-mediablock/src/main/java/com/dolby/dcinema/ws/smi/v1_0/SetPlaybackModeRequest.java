
package com.dolby.dcinema.ws.smi.v1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.PlaybackMode;


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
 *         &lt;element name="playbackMode" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}PlaybackMode"/&gt;
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
    "playbackMode"
})
@XmlRootElement(name = "setPlaybackModeRequest")
public class SetPlaybackModeRequest {

    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    protected PlaybackMode playbackMode;

    /**
     * Obtient la valeur de la propriété playbackMode.
     * 
     * @return
     *     possible object is
     *     {@link PlaybackMode }
     *     
     */
    public PlaybackMode getPlaybackMode() {
        return playbackMode;
    }

    /**
     * Définit la valeur de la propriété playbackMode.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaybackMode }
     *     
     */
    public void setPlaybackMode(PlaybackMode value) {
        this.playbackMode = value;
    }

}
