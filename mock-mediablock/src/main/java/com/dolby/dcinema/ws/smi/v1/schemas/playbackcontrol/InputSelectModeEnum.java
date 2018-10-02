
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour InputSelectModeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="InputSelectModeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="SERVER"/&gt;
 *     &lt;enumeration value="HDMI_1"/&gt;
 *     &lt;enumeration value="HDMI_2"/&gt;
 *     &lt;enumeration value="HD_SDI"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "InputSelectModeEnum")
@XmlEnum
public enum InputSelectModeEnum {

    SERVER,
    HDMI_1,
    HDMI_2,
    HD_SDI,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static InputSelectModeEnum fromValue(String v) {
        return valueOf(v);
    }

}
