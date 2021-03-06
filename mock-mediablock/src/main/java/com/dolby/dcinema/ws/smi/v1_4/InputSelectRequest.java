
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
 *         &lt;element name="inputSelectMode" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}InputSelectMode"/&gt;
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
    "inputSelectMode"
})
@XmlRootElement(name = "inputSelectRequest")
public class InputSelectRequest {

    @XmlElement(required = true)
    protected InputSelectMode inputSelectMode;

    /**
     * Obtient la valeur de la propriété inputSelectMode.
     * 
     * @return
     *     possible object is
     *     {@link InputSelectMode }
     *     
     */
    public InputSelectMode getInputSelectMode() {
        return inputSelectMode;
    }

    /**
     * Définit la valeur de la propriété inputSelectMode.
     * 
     * @param value
     *     allowed object is
     *     {@link InputSelectMode }
     *     
     */
    public void setInputSelectMode(InputSelectMode value) {
        this.inputSelectMode = value;
    }

}
