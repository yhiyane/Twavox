
package com.dolby.dcinema.ws.smi.v1_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.showmanagement.ShowTimeResponse;


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
 *         &lt;element name="showTimeResponse" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/showmanagement}ShowTimeResponse" maxOccurs="unbounded"/&gt;
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
    "showTimeResponse"
})
@XmlRootElement(name = "createShowTimesResponse")
public class CreateShowTimesResponse {

    @XmlElement(required = true)
    protected List<ShowTimeResponse> showTimeResponse;

    /**
     * Gets the value of the showTimeResponse property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the showTimeResponse property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShowTimeResponse().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShowTimeResponse }
     * 
     * 
     */
    public List<ShowTimeResponse> getShowTimeResponse() {
        if (showTimeResponse == null) {
            showTimeResponse = new ArrayList<ShowTimeResponse>();
        }
        return this.showTimeResponse;
    }

}
