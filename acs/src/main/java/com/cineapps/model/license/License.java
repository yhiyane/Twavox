/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.license;

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

import org.apache.log4j.Logger;

import com.cineapps.util.EncryptDecryptUtils;
import com.cineapps.util.SystemUtils;

@XmlRootElement(name = "license")
@XmlAccessorType(XmlAccessType.FIELD)
public class License {

	private static final Logger logger = Logger.getLogger(License.class);
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
		// License is passed
		// Skip this test if the limit date is null
		if (limitDate != null && limitDate.before(new Date())) {
			return false;
		}
		try {
			// Wrong mac address
			if (!SystemUtils.isMacAddressValid(mac)) {
				return false;
			}
		} catch (SocketException e) {
			logger.error("Cannot get mac address");
			return false;
		}

		return true;
	}

	/**
	 * Returns the String representation of the instance.
	 * 
	 * @return
	 * @throws JAXBException
	 */
	public String marshal() throws JAXBException {
		// Marshall the license in xml string
		JAXBContext jc = JAXBContext.newInstance(License.class);
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWriter = new StringWriter();
		marshaller.marshal(this, stringWriter);
		return stringWriter.toString();
	}

	/**
	 * Reads the encrypted content and unmarshal it to return an instance of
	 * License.
	 * 
	 * @param licenseFileUrl
	 *            the path to the license file with an encrypted content.
	 * @param privateKey
	 *            the private key used to decrypt the content
	 * @return
	 */
	public static License fromFile(String licenseFileUrl, String privateKey) {
		try {
			String encryptedContent = new String(Files.readAllBytes(Paths.get(licenseFileUrl)),
			        "UTF-8");
			String content = EncryptDecryptUtils.decrypt(encryptedContent, privateKey);
			return (License) JAXBContext.newInstance(License.class).createUnmarshaller()
			        .unmarshal(new StringReader(content));
		} catch (Exception e) {
			logger.error("Cannot load the license file in " + licenseFileUrl);
			logger.error(e);
		}
		return null;
	}

}
