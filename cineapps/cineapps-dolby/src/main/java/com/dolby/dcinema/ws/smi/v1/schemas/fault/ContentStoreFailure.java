
package com.dolby.dcinema.ws.smi.v1.schemas.fault;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.common.ContentStoreType;


/**
 * <p>Classe Java pour ContentStoreFailure complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ContentStoreFailure"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/fault}Fault"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="contentStore" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/common}ContentStoreType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContentStoreFailure", propOrder = {
    "contentStore"
})
public class ContentStoreFailure
    extends Fault
{

    @XmlElement(required = true)
    protected ContentStoreType contentStore;

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

}
