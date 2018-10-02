
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ScheduleMode.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ScheduleMode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="FULL_AUTOMATIC"/&gt;
 *     &lt;enumeration value="SELECT_AUTOMATIC"/&gt;
 *     &lt;enumeration value="MANUAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ScheduleMode")
@XmlEnum
public enum ScheduleMode {

    FULL_AUTOMATIC,
    SELECT_AUTOMATIC,
    MANUAL;

    public String value() {
        return name();
    }

    public static ScheduleMode fromValue(String v) {
        return valueOf(v);
    }

}
