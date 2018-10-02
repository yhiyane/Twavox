
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PurgeSuiteState complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PurgeSuiteState"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="projectorNumber" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="totalProjectors" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="projectorSeries" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="totalExtractedLogs" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="totalLogs" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="status" type="{http://www.dolby.com/dcinema/ws/smi/v1/schemas/playbackcontrol}ProjectorLogExtractionStatus"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurgeSuiteState", propOrder = {
    "projectorNumber",
    "totalProjectors",
    "projectorSeries",
    "totalExtractedLogs",
    "totalLogs",
    "status"
})
public class PurgeSuiteState {

    @XmlElement(required = true)
    protected BigInteger projectorNumber;
    @XmlElement(required = true)
    protected BigInteger totalProjectors;
    @XmlElement(required = true)
    protected String projectorSeries;
    @XmlElement(required = true)
    protected BigInteger totalExtractedLogs;
    @XmlElement(required = true)
    protected BigInteger totalLogs;
    @XmlElement(required = true)
    @XmlSchemaType(name = "token")
    protected ProjectorLogExtractionStatus status;

    /**
     * Obtient la valeur de la propriété projectorNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getProjectorNumber() {
        return projectorNumber;
    }

    /**
     * Définit la valeur de la propriété projectorNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setProjectorNumber(BigInteger value) {
        this.projectorNumber = value;
    }

    /**
     * Obtient la valeur de la propriété totalProjectors.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalProjectors() {
        return totalProjectors;
    }

    /**
     * Définit la valeur de la propriété totalProjectors.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalProjectors(BigInteger value) {
        this.totalProjectors = value;
    }

    /**
     * Obtient la valeur de la propriété projectorSeries.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectorSeries() {
        return projectorSeries;
    }

    /**
     * Définit la valeur de la propriété projectorSeries.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectorSeries(String value) {
        this.projectorSeries = value;
    }

    /**
     * Obtient la valeur de la propriété totalExtractedLogs.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalExtractedLogs() {
        return totalExtractedLogs;
    }

    /**
     * Définit la valeur de la propriété totalExtractedLogs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalExtractedLogs(BigInteger value) {
        this.totalExtractedLogs = value;
    }

    /**
     * Obtient la valeur de la propriété totalLogs.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalLogs() {
        return totalLogs;
    }

    /**
     * Définit la valeur de la propriété totalLogs.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalLogs(BigInteger value) {
        this.totalLogs = value;
    }

    /**
     * Obtient la valeur de la propriété status.
     * 
     * @return
     *     possible object is
     *     {@link ProjectorLogExtractionStatus }
     *     
     */
    public ProjectorLogExtractionStatus getStatus() {
        return status;
    }

    /**
     * Définit la valeur de la propriété status.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectorLogExtractionStatus }
     *     
     */
    public void setStatus(ProjectorLogExtractionStatus value) {
        this.status = value;
    }

}
