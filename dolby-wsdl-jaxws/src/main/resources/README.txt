
### Generate server side with WsImport

	- Go to resources
	 	
	- wsimport dolby-smi/PlaybackControl.wsdl -keep -wsdllocation /datafs/config/dolby-smi/PlaybackControl.wsdl
	- wsimport dolby-smi/ShowManagement.wsdl -keep -wsdllocation /datafs/config/dolby-smi/ShowManagement.wsdl
	 	
	- The 'src' directory is created. Import it into 'dolby-wsdl' and 'mock-mediablock' projects
	
### Generate code from xml

	- create xsd from xml
	- xjc -p com.cineapps.show ../resources/schema/show.xsd