/**
 * Copyright (c) 2015 CineApps
 * This source code is the property of CineApps.
 */
package com.cineapps.model.property;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractProperty {

	/**
	 * Convention : the list values for the same key is defined by
	 * "key = val1,val2,val3" This method returns the list of values.
	 * 
	 * @param sectionName
	 * @param optionName
	 * @return
	 */
	protected List<String> getListFromValues(String listPropertyValue) {
		if (listPropertyValue != null) {
			String[] valuesArray = listPropertyValue.split(",");
			return Arrays.asList(valuesArray);
		}
		return null;
	}
}
