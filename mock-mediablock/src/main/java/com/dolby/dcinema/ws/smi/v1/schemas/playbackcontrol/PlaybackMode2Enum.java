
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PlaybackMode2Enum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PlaybackMode2Enum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="TWO_D"/&gt;
 *     &lt;enumeration value="THREE_D"/&gt;
 *     &lt;enumeration value="DOLBY_3D"/&gt;
 *     &lt;enumeration value="REAL_D"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PlaybackMode2Enum")
@XmlEnum
public enum PlaybackMode2Enum {

    TWO_D("TWO_D"),
    THREE_D("THREE_D"),
    @XmlEnumValue("DOLBY_3D")
    DOLBY_3_D("DOLBY_3D"),
    REAL_D("REAL_D"),
    UNKNOWN("UNKNOWN"),
    EXTENSION("EXTENSION");
    private final String value;

    PlaybackMode2Enum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PlaybackMode2Enum fromValue(String v) {
        for (PlaybackMode2Enum c: PlaybackMode2Enum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
