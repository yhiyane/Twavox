
package com.dolby.dcinema.ws.smi.v1.schemas.fault;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour NotFound complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="NotFound"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/fault}Fault"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="uuid" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}UUID"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotFound", propOrder = {
    "uuid"
})
public class NotFound
    extends Fault
{

    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String uuid;

    /**
     * Obtient la valeur de la propriété uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Définit la valeur de la propriété uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

}
