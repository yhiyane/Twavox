
package com.dolby.dcinema.ws.smi.v1.schemas.showmanagement;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CueTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CueTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="LIGHTS"/&gt;
 *     &lt;enumeration value="VOLUME"/&gt;
 *     &lt;enumeration value="MUTE"/&gt;
 *     &lt;enumeration value="AUDIO_PRESET"/&gt;
 *     &lt;enumeration value="AUDIO_ADAPTER"/&gt;
 *     &lt;enumeration value="AUDIO"/&gt;
 *     &lt;enumeration value="PROJECTOR"/&gt;
 *     &lt;enumeration value="FILTER"/&gt;
 *     &lt;enumeration value="PLAYER"/&gt;
 *     &lt;enumeration value="OTHER"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CueTypeEnum")
@XmlEnum
public enum CueTypeEnum {

    LIGHTS,
    VOLUME,
    MUTE,
    AUDIO_PRESET,
    AUDIO_ADAPTER,
    AUDIO,
    PROJECTOR,
    FILTER,
    PLAYER,
    OTHER,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static CueTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
