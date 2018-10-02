
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ContentStoreTypeEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ContentStoreTypeEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="MAINSTORE"/&gt;
 *     &lt;enumeration value="EXTERNALSTORE"/&gt;
 *     &lt;enumeration value="REMOVABLE"/&gt;
 *     &lt;enumeration value="DVD"/&gt;
 *     &lt;enumeration value="USB"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ContentStoreTypeEnum")
@XmlEnum
public enum ContentStoreTypeEnum {

    MAINSTORE,
    EXTERNALSTORE,
    REMOVABLE,
    DVD,
    USB,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static ContentStoreTypeEnum fromValue(String v) {
        return valueOf(v);
    }

}
