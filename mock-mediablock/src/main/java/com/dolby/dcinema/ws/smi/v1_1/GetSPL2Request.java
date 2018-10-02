
package com.dolby.dcinema.ws.smi.v1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="showId" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
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
    "showId"
})
@XmlRootElement(name = "getSPL2Request")
public class GetSPL2Request {

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String showId;

    /**
     * Obtient la valeur de la propriété showId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShowId() {
        return showId;
    }

    /**
     * Définit la valeur de la propriété showId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShowId(String value) {
        this.showId = value;
    }

}
