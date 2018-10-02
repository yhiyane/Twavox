
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.Duration;
import com.dolby.dcinema.ws.smi.v1.schemas.common.ContentStoreType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.PlayableContentType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.Rating;


/**
 * <p>Classe Java pour ContentPlaybackState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ContentPlaybackState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contentId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="contentType" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}PlayableContentType"/&gt;
 *         &lt;element name="contentRating" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}Rating"/&gt;
 *         &lt;element name="contentStore" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}ContentStoreType"/&gt;
 *         &lt;element name="currentClipId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="currentClipIndex" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="clipPosition" type="{http://www.w3.org/2001/XMLSchema}duration"/&gt;
 *         &lt;element name="clipDuration" type="{http://www.w3.org/2001/XMLSchema}duration"/&gt;
 *         &lt;element name="showPosition" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *         &lt;element name="showDuration" type="{http://www.w3.org/2001/XMLSchema}duration" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentPlaybackState", propOrder = {
    "contentId",
    "contentType",
    "contentRating",
    "contentStore",
    "currentClipId",
    "currentClipIndex",
    "clipPosition",
    "clipDuration",
    "showPosition",
    "showDuration"
})
public class ContentPlaybackState {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String contentId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    protected PlayableContentType contentType;
    @XmlElement(required = true)
    protected Rating contentRating;
    @XmlElement(required = true)
    protected ContentStoreType contentStore;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String currentClipId;
    @XmlElement(required = true)
    protected BigInteger currentClipIndex;
    @XmlElement(required = true)
    protected Duration clipPosition;
    @XmlElement(required = true)
    protected Duration clipDuration;
    protected Duration showPosition;
    protected Duration showDuration;

    /**
     * Obtient la valeur de la propriété contentId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContentId() {
        return contentId;
    }

    /**
     * Définit la valeur de la propriété contentId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContentId(String value) {
        this.contentId = value;
    }

    /**
     * Obtient la valeur de la propriété contentType.
     * 
     * @return
     *     possible object is
     *     {@link PlayableContentType }
     *     
     */
    public PlayableContentType getContentType() {
        return contentType;
    }

    /**
     * Définit la valeur de la propriété contentType.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayableContentType }
     *     
     */
    public void setContentType(PlayableContentType value) {
        this.contentType = value;
    }

    /**
     * Obtient la valeur de la propriété contentRating.
     * 
     * @return
     *     possible object is
     *     {@link Rating }
     *     
     */
    public Rating getContentRating() {
        return contentRating;
    }

    /**
     * Définit la valeur de la propriété contentRating.
     * 
     * @param value
     *     allowed object is
     *     {@link Rating }
     *     
     */
    public void setContentRating(Rating value) {
        this.contentRating = value;
    }

    /**
     * Obtient la valeur de la propriété contentStore.
     * 
     * @return
     *     possible object is
     *     {@link ContentStoreType }
     *     
     */
    public ContentStoreType getContentStore() {
        return contentStore;
    }

    /**
     * Définit la valeur de la propriété contentStore.
     * 
     * @param value
     *     allowed object is
     *     {@link ContentStoreType }
     *     
     */
    public void setContentStore(ContentStoreType value) {
        this.contentStore = value;
    }

    /**
     * Obtient la valeur de la propriété currentClipId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentClipId() {
        return currentClipId;
    }

    /**
     * Définit la valeur de la propriété currentClipId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentClipId(String value) {
        this.currentClipId = value;
    }

    /**
     * Obtient la valeur de la propriété currentClipIndex.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCurrentClipIndex() {
        return currentClipIndex;
    }

    /**
     * Définit la valeur de la propriété currentClipIndex.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCurrentClipIndex(BigInteger value) {
        this.currentClipIndex = value;
    }

    /**
     * Obtient la valeur de la propriété clipPosition.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getClipPosition() {
        return clipPosition;
    }

    /**
     * Définit la valeur de la propriété clipPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setClipPosition(Duration value) {
        this.clipPosition = value;
    }

    /**
     * Obtient la valeur de la propriété clipDuration.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getClipDuration() {
        return clipDuration;
    }

    /**
     * Définit la valeur de la propriété clipDuration.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setClipDuration(Duration value) {
        this.clipDuration = value;
    }

    /**
     * Obtient la valeur de la propriété showPosition.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getShowPosition() {
        return showPosition;
    }

    /**
     * Définit la valeur de la propriété showPosition.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setShowPosition(Duration value) {
        this.showPosition = value;
    }

    /**
     * Obtient la valeur de la propriété showDuration.
     * 
     * @return
     *     possible object is
     *     {@link Duration }
     *     
     */
    public Duration getShowDuration() {
        return showDuration;
    }

    /**
     * Définit la valeur de la propriété showDuration.
     * 
     * @param value
     *     allowed object is
     *     {@link Duration }
     *     
     */
    public void setShowDuration(Duration value) {
        this.showDuration = value;
    }

}
