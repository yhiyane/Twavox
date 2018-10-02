
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PlayableContentType.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PlayableContentType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="CLIP"/&gt;
 *     &lt;enumeration value="SHOW"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PlayableContentType")
@XmlEnum
public enum PlayableContentType {

    CLIP,
    SHOW;

    public String value() {
        return name();
    }

    public static PlayableContentType fromValue(String v) {
        return valueOf(v);
    }

}
