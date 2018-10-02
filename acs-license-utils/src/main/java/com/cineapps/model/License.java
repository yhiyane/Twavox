/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model;

import java.io.StringReader;
import java.io.StringWriter;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.cineapps.utils.EncryptDecryptUtils;
import com.cineapps.utils.SystemUtils;

@XmlRootElement(name = "license")
@XmlAccessorType(XmlAccessType.FIELD)
public class License {

	private String mac;
	private Date limitDate; // Can be null
	private int multiStNbCoins = -1;
	private boolean auxContent = false;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Date getLimitDate() {
		if (limitDate != null) {
			return new Date(limitDate.getTime());
		}
		return null;
	}

	public void setLimitDate(Date limitDate) {
		if (limitDate != null) {
			this.limitDate = new Date(limitDate.getTime());
		}
	}

	public boolean isAuxContent() {
		return auxContent;
	}

	public void setAuxContent(boolean auxContent) {
		this.auxContent = auxContent;
	}

	public int getMultiStNbCoins() {
		return multiStNbCoins;
	}

	public void setMultiStNbCoins(int multiStNbCoins) {
		this.multiStNbCoins = multiStNbCoins;
	}

	/**
	 * Returns true if this license is valid, false otherwise.
	 * 
	 * @return
	 */
	public boolean isValid() {
		// The limit date is null means the license is a definitive license.
		if (limitDate != null && limitDate.before(new Date())) {
			return false;
		}
		try {
			if (!SystemUtils.isMacAddressValid(mac)) {
				return false;
			}
		} catch (SocketException e) {
			return false;
		}

		return true;
	}

	public String toXml() throws JAXBException {
		// Marshall the license in xml string
		JAXBContext jc = JAXBContext.newInstance(License.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(this, stringWriter);
		return stringWriter.toString();
	}

	public static License fromFile(String licenseFileUrl, String privateKey) {
		try {
			String encryptedContent = new String(Files.readAllBytes(Paths.get(licenseFileUrl)),
			        "UTF-8");
			String content = EncryptDecryptUtils.decrypt(encryptedContent, privateKey);
			return (License) JAXBContext.newInstance(License.class).createUnmarshaller()
			        .unmarshal(new StringReader(content));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (auxContent ? 1231 : 1237);
		result = prime * result + ((limitDate == null) ? 0 : limitDate.hashCode());
		result = prime * result + ((mac == null) ? 0 : mac.hashCode());
		result = prime * result + multiStNbCoins;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		License other = (License) obj;
		if (auxContent != other.auxContent)
			return false;
		if (limitDate == null) {
			if (other.limitDate != null)
				return false;
		} else if (!limitDate.equals(other.limitDate))
			return false;
		if (mac == null) {
			if (other.mac != null)
				return false;
		} else if (!mac.equals(other.mac))
			return false;
		if (multiStNbCoins != other.multiStNbCoins)
			return false;
		return true;
	}

}
