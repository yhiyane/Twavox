
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour TransportStateTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TransportStateTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="READY"/&gt;
 *     &lt;enumeration value="SELECTING"/&gt;
 *     &lt;enumeration value="RECOVERING"/&gt;
 *     &lt;enumeration value="LATE"/&gt;
 *     &lt;enumeration value="PLAYING"/&gt;
 *     &lt;enumeration value="STOPPED"/&gt;
 *     &lt;enumeration value="PAUSED"/&gt;
 *     &lt;enumeration value="UNSELECTED"/&gt;
 *     &lt;enumeration value="SEEKING"/&gt;
 *     &lt;enumeration value="UNAVAILABLE"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "TransportStateTypeEnum")
@XmlEnum
public enum TransportStateTypeEnum {

    READY,
    SELECTING,
    RECOVERING,
    LATE,
    PLAYING,
    STOPPED,
    PAUSED,
    UNSELECTED,
    SEEKING,
    UNAVAILABLE,
    EXTENSION;

    public String value() {
        return name();
    }

    public static TransportStateTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
