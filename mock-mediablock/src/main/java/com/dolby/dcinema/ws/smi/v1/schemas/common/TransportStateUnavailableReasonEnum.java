
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TransportStateUnavailableReasonEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TransportStateUnavailableReasonEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="NTP_ERROR"/&gt;
 *     &lt;enumeration value="EXTERNAL_REFERENCE_ERROR"/&gt;
 *     &lt;enumeration value="CONNECTION_ERROR"/&gt;
 *     &lt;enumeration value="CINELINK_ERROR"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TransportStateUnavailableReasonEnum")
@XmlEnum
public enum TransportStateUnavailableReasonEnum {

    NTP_ERROR,
    EXTERNAL_REFERENCE_ERROR,
    CONNECTION_ERROR,
    CINELINK_ERROR,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static TransportStateUnavailableReasonEnum fromValue(String v) {
        return valueOf(v);
    }

}
