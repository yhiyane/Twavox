package com.cineapps.manager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cineapps.parser.SubtitleParser;

public class DcpManager {
	
	private final int MOVIE_NAME_MAX_LENGTH = 15;
	private final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	public void manage(String srcPath, String destPath, SubtitleParser parser) throws IOException {
		// Generate dcp name
		String movieName = new File(srcPath).getName();
		String dcpName = generateDcpName(movieName);
		String dcpPath = destPath;
		dcpPath += destPath.endsWith("/") ? "" : "/";
		dcpPath += dcpName;
		
		// Create directory
		// Remove if exists
		File dcp = new File(dcpPath);
		if (dcp.exists()) {
			FileUtils.deleteDirectory(dcp);
		}
		dcp.mkdir();
		
		// Subtitle path
		String subtitleDir = UUID.randomUUID().toString();
	}
	
	private String generateDcpName(String movieName) {
		if (movieName.length() > MOVIE_NAME_MAX_LENGTH) {
			movieName = movieName.substring(0, MOVIE_NAME_MAX_LENGTH);
		}
		String date = format.format(new Date());
		return movieName + "_FTR-3_S_FR-FR_INT_51_2K_" + date;
	}
	
	private void generateAssetMap(String dcpPath) throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("AssetMap");
		Attr attr = doc.createAttribute("xmlns");
		attr.setValue("http://www.digicine.com/PROTO-ASDCP-AM-20040311#");
		doc.appendChild(rootElement);
		
	}
	
	/*
	<AssetMap xmlns="http://www.digicine.com/PROTO-ASDCP-AM-20040311#">
	  <Id>urn:uuid:42f59c4b-6638-4a36-b62f-32812f80ddea</Id>
	  <VolumeCount>1</VolumeCount>
	  <IssueDate>2014-02-07T11:12:46+01:00</IssueDate>
	  <Issuer>Doremi Labs, Inc.</Issuer>
	  <Creator>orca_wrapping 1.2.52</Creator>
	  <AssetList>
	    <Asset>
	      <Id>urn:uuid:be493e2c-caaa-4a7c-a5a9-47de8af0b029</Id>
	      <ChunkList>
	        <Chunk>
	          <Path>wav_BELLEBETE_AD_R1_24fps_enc_be493e2c-caaa-4a7c-a5a9-47de8af0b029_audio.mxf</Path>
	          <VolumeIndex>1</VolumeIndex>
	        </Chunk>
	      </ChunkList>
	    </Asset>
	    <Asset>
	      <Id>urn:uuid:72dfdd64-52d1-4beb-951d-f8b036aec2e5</Id>
	      <ChunkList>
	        <Chunk>
	          <Path>72dfdd64-52d1-4beb-951d-f8b036aec2e5/LaBelleEtLaBete_DCP_SME_24_R01.xml</Path>
	          <VolumeIndex>1</VolumeIndex>
	        </Chunk>
	      </ChunkList>
	    </Asset>
	  <Asset>
      <Id>urn:uuid:c2cdb9b1-660b-4e21-bf70-0178d408c275</Id>
      <AnnotationText>CPL: BelleEtLaBete_FTR_S_FR-OCAP_FR_51-VI_2K_20140207_TEF_VF</AnnotationText>
      <ChunkList>
        <Chunk>
          <Path>CPL_c2cdb9b1-660b-4e21-bf70-0178d408c275.xml</Path>
          <VolumeIndex>1</VolumeIndex>
        </Chunk>
      </ChunkList>
    </Asset>
    <Asset>
      <Id>urn:uuid:31f8c97c-d718-4f45-9ced-81e9f9653498</Id>
      <AnnotationText>PKL: BelleEtLaBete_FTR_S_FR-OCAP_FR_51-VI_2K_20140207_TEF_VF</AnnotationText>
      <PackingList>true</PackingList>
      <ChunkList>
        <Chunk>
          <Path>PKL_31f8c97c-d718-4f45-9ced-81e9f9653498.xml</Path>
          <VolumeIndex>1</VolumeIndex>
        </Chunk>
      </ChunkList>
    </Asset>
  </AssetList>
</AssetMap>
*/
	
	private void generateCpl(String dcpPath) {
		
	}
}
