
package com.dolby.dcinema.ws.smi.v1_4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol.InputSelectMode;


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
 *         &lt;element name="autoInputSelectMode" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}InputSelectMode"/&gt;
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
    "autoInputSelectMode"
})
@XmlRootElement(name = "getAutoInputSelectResponse")
public class GetAutoInputSelectResponse {

    @XmlElement(required = true)
    protected InputSelectMode autoInputSelectMode;

    /**
     * Obtient la valeur de la propriété autoInputSelectMode.
     * 
     * @return
     *     possible object is
     *     {@link InputSelectMode }
     *     
     */
    public InputSelectMode getAutoInputSelectMode() {
        return autoInputSelectMode;
    }

    /**
     * Définit la valeur de la propriété autoInputSelectMode.
     * 
     * @param value
     *     allowed object is
     *     {@link InputSelectMode }
     *     
     */
    public void setAutoInputSelectMode(InputSelectMode value) {
        this.autoInputSelectMode = value;
    }

}
