
package com.dolby.dcinema.ws.smi.v1.schemas.playbackcontrol;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PrepSuiteStatusEnum.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="PrepSuiteStatusEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *     &lt;enumeration value="SUCCESS"/&gt;
 *     &lt;enumeration value="NOT_VALIDATED"/&gt;
 *     &lt;enumeration value="BUSY"/&gt;
 *     &lt;enumeration value="NO_USAGE_RIGHTS"/&gt;
 *     &lt;enumeration value="LINK_ENCRYPTION_REQUIRED"/&gt;
 *     &lt;enumeration value="PROJECTOR_NOT_TRUSTED"/&gt;
 *     &lt;enumeration value="NO_LICENSES_FOUND"/&gt;
 *     &lt;enumeration value="INVALID_PARSE"/&gt;
 *     &lt;enumeration value="INVALID_DCI_PARSE"/&gt;
 *     &lt;enumeration value="CA_ERROR"/&gt;
 *     &lt;enumeration value="KEYTYPE_ERROR"/&gt;
 *     &lt;enumeration value="KEY_NOT_FOUND"/&gt;
 *     &lt;enumeration value="TRACKFILE_MISSING"/&gt;
 *     &lt;enumeration value="BAD_TRACKFILE_HASH"/&gt;
 *     &lt;enumeration value="SIGNER_ROLE_ERROR"/&gt;
 *     &lt;enumeration value="UNKNOWN_ERROR"/&gt;
 *     &lt;enumeration value="UNKNOWN"/&gt;
 *     &lt;enumeration value="EXTENSION"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "PrepSuiteStatusEnum")
@XmlEnum
public enum PrepSuiteStatusEnum {

    SUCCESS,
    NOT_VALIDATED,
    BUSY,
    NO_USAGE_RIGHTS,
    LINK_ENCRYPTION_REQUIRED,
    PROJECTOR_NOT_TRUSTED,
    NO_LICENSES_FOUND,
    INVALID_PARSE,
    INVALID_DCI_PARSE,
    CA_ERROR,
    KEYTYPE_ERROR,
    KEY_NOT_FOUND,
    TRACKFILE_MISSING,
    BAD_TRACKFILE_HASH,
    SIGNER_ROLE_ERROR,
    UNKNOWN_ERROR,
    UNKNOWN,
    EXTENSION;

    public String value() {
        return name();
    }

    public static PrepSuiteStatusEnum fromValue(String v) {
        return valueOf(v);
    }

}
