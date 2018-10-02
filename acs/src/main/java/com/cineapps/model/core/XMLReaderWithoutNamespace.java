/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.core;

import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.util.StreamReaderDelegate;

public class XMLReaderWithoutNamespace extends StreamReaderDelegate {

	public XMLReaderWithoutNamespace(XMLStreamReader reader) {
		super(reader);
	}

	@Override
	public String getAttributeNamespace(int arg0) {
		return "";
	}

	@Override
	public String getNamespaceURI() {
		return "";
	}
}
