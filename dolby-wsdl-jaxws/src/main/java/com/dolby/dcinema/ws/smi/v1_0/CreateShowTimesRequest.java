
package com.dolby.dcinema.ws.smi.v1_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.showmanagement.ShowTimeRequest;


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
 *         &lt;element name="showTimeRequest" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/showmanagement}ShowTimeRequest" maxOccurs="unbounded"/&gt;
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
    "showTimeRequest"
})
@XmlRootElement(name = "createShowTimesRequest")
public class CreateShowTimesRequest {

    @XmlElement(required = true)
    protected List<ShowTimeRequest> showTimeRequest;

    /**
     * Gets the value of the showTimeRequest property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the showTimeRequest property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShowTimeRequest().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShowTimeRequest }
     * 
     * 
     */
    public List<ShowTimeRequest> getShowTimeRequest() {
        if (showTimeRequest == null) {
            showTimeRequest = new ArrayList<ShowTimeRequest>();
        }
        return this.showTimeRequest;
    }

}
