
package com.dolby.dcinema.ws.smi.v1_0;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import com.dolby.dcinema.ws.smi.v1.schemas.showmanagement.ShowTimeInfo;


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
 *         &lt;element name="showTimeInfo" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/showmanagement}ShowTimeInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "showTimeInfo"
})
@XmlRootElement(name = "getShowTimeInfosResponse")
public class GetShowTimeInfosResponse {

    protected List<ShowTimeInfo> showTimeInfo;

    /**
     * Gets the value of the showTimeInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the showTimeInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShowTimeInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ShowTimeInfo }
     * 
     * 
     */
    public List<ShowTimeInfo> getShowTimeInfo() {
        if (showTimeInfo == null) {
            showTimeInfo = new ArrayList<ShowTimeInfo>();
        }
        return this.showTimeInfo;
    }

}
