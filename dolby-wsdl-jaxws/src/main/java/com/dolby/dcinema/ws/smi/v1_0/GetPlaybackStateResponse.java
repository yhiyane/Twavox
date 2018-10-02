
package com.dolby.dcinema.ws.smi.v1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.TransportState;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.ContentPlaybackState;
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
 *         &lt;element name="transportState" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}TransportState"/&gt;
 *         &lt;element name="contentPlaybackState" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}ContentPlaybackState" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="playbackMode" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}PlaybackMode" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "transportState",
    "contentPlaybackState"
})
@XmlRootElement(name = "getPlaybackStateResponse")
public class GetPlaybackStateResponse {

    @XmlElement(required = true)
    protected TransportState transportState;
    protected ContentPlaybackState contentPlaybackState;
    @XmlAttribute(name = "playbackMode")
    protected PlaybackMode playbackMode;

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

    /**
     * Obtient la valeur de la propriété contentPlaybackState.
     * 
     * @return
     *     possible object is
     *     {@link ContentPlaybackState }
     *     
     */
    public ContentPlaybackState getContentPlaybackState() {
        return contentPlaybackState;
    }

    /**
     * Définit la valeur de la propriété contentPlaybackState.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentPlaybackState }
     *     
     */
    public void setContentPlaybackState(ContentPlaybackState value) {
        this.contentPlaybackState = value;
    }

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
