
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ContentTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ContentTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="PACKAGE"/&gt;
 *     &lt;enumeration value="CLIP"/&gt;
 *     &lt;enumeration value="SHOW"/&gt;
 *     &lt;enumeration value="LICENSE"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ContentTypeEnum")
@XmlEnum
public enum ContentTypeEnum {

    PACKAGE,
    CLIP,
    SHOW,
    LICENSE,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static ContentTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
