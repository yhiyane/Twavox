
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ProjectorLogExtractionStatus.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ProjectorLogExtractionStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="DONE"/&gt;
 *     &lt;enumeration value="BUSY"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ProjectorLogExtractionStatus")
@XmlEnum
public enum ProjectorLogExtractionStatus {

    DONE,
    BUSY,
    ERROR,
    UNKNOWN;

    public String value() {
        return name();
    }

    public static ProjectorLogExtractionStatus fromValue(String v) {
        return valueOf(v);
    }

}
