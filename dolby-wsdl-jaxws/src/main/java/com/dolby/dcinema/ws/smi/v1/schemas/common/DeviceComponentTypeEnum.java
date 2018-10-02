
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DeviceComponentTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DeviceComponentTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="SERVER"/&gt;
 *     &lt;enumeration value="PLAYER"/&gt;
 *     &lt;enumeration value="DECODER"/&gt;
 *     &lt;enumeration value="PROJECTOR"/&gt;
 *     &lt;enumeration value="AUTOMATION"/&gt;
 *     &lt;enumeration value="AUDIO"/&gt;
 *     &lt;enumeration value="AUDIO_ADAPTER"/&gt;
 *     &lt;enumeration value="FILTER_CONTROLLER"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DeviceComponentTypeEnum")
@XmlEnum
public enum DeviceComponentTypeEnum {

    SERVER,
    PLAYER,
    DECODER,
    PROJECTOR,
    AUTOMATION,
    AUDIO,
    AUDIO_ADAPTER,
    FILTER_CONTROLLER,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static DeviceComponentTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
