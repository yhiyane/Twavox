
package com.dolby.dcinema.ws.smi.v1.schemas.common;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour LicenseStateEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="LicenseStateEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="NOT_NEEDED"/&gt;
 *     &lt;enumeration value="NOT_RELEVANT"/&gt;
 *     &lt;enumeration value="OK"/&gt;
 *     &lt;enumeration value="FUTURE"/&gt;
 *     &lt;enumeration value="ABOUT_TO_EXPIRE"/&gt;
 *     &lt;enumeration value="EXPIRED"/&gt;
 *     &lt;enumeration value="MISSING"/&gt;
 *     &lt;enumeration value="ERROR"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "LicenseStateEnum")
@XmlEnum
public enum LicenseStateEnum {

    NOT_NEEDED,
    NOT_RELEVANT,
    OK,
    FUTURE,
    ABOUT_TO_EXPIRE,
    EXPIRED,
    MISSING,
    ERROR,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static LicenseStateEnum fromValue(String v) {
        return valueOf(v);
    }

}
