### Generate code from xml for parsing

	- Generate a xsd file from xml
	- xjc -p com.cineapps.show ../resources/dolby/schema/show.xsd

### Generate server side with Apache 

	- Go to Desktop

	- ./apache-cxf-3.1.1/bin/wsdl2java -all -wsdlLocation /dolby-smi/PlaybackControl.wsdl
	 	~/dev/java/dolby-wsdl/src/main/resources/dolby-smi/PlaybackControl.wsdl
	
	- ./apache-cxf-3.1.1/bin/wsdl2java -all -wsdlLocation /dolby-smi/ShowManagement.wsdl
	 	~/dev/java/dolby-wsdl/src/main/resources/dolby-smi/ShowManagement.wsdl
	 	
	- The 'com' directory is created. Import it into 'dolby-wsdl' and 'mock-mediablock' projects
	 
### Change Java version

	export JAVA_HOME=$(/usr/libexec/java_home -v 1.7)