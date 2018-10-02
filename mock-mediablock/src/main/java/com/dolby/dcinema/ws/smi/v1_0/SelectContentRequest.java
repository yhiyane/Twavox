
package com.dolby.dcinema.ws.smi.v1_0;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.PlayableContentType;


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
 *         &lt;element name="contentId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *         &lt;element name="playableContentType" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}PlayableContentType" minOccurs="0"/&gt;
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
    "contentId",
    "playableContentType"
})
@XmlRootElement(name = "selectContentRequest")
public class SelectContentRequest {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String contentId;
    @XmlSchemaType(name = "token")
    protected PlayableContentType playableContentType;

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
     * Obtient la valeur de la propriété playableContentType.
     * 
     * @return
     *     possible object is
     *     {@link PlayableContentType }
     *     
     */
    public PlayableContentType getPlayableContentType() {
        return playableContentType;
    }

    /**
     * Définit la valeur de la propriété playableContentType.
     * 
     * @param value
     *     allowed object is
     *     {@link PlayableContentType }
     *     
     */
    public void setPlayableContentType(PlayableContentType value) {
        this.playableContentType = value;
    }

}
