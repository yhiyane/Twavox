
##### Read license : 

java -jar acs-license-utils-version-min.jar
	Required : 
		-action read 
		-license path/to/license

##### Generate license : 

java -jar acs-license-utils-version-min.jar
	Required : 
		-action generate 
		-license path/to/license 
		-key path/to/key
		-validity yyyy-MM-dd or null
		
	Optional :
		-multist nbCoins
		-ac