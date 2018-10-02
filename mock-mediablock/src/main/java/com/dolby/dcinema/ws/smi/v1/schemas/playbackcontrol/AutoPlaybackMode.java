
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour AutoPlaybackMode.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="AutoPlaybackMode"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="MANUAL"/&gt;
 *     &lt;enumeration value="THREE_D"/&gt;
 *     &lt;enumeration value="DOLBY_3D"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "AutoPlaybackMode")
@XmlEnum
public enum AutoPlaybackMode {

    MANUAL("MANUAL"),
    THREE_D("THREE_D"),
    @XmlEnumValue("DOLBY_3D")
    DOLBY_3_D("DOLBY_3D"),
    UNKNOWN("UNKNOWN");
    private final String value;

    AutoPlaybackMode(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AutoPlaybackMode fromValue(String v) {
        for (AutoPlaybackMode c: AutoPlaybackMode.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
