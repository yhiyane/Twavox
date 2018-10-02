package com.cineapps.model.cpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <p>
 * Classe Java pour anonymous complex type.
 * 
 * <p>
 * Le fragment de schéma suivant indique le contenu attendu figurant dans cette
 * classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Issuer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Creator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentTitleText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContentKind" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RatingList" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReelList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Reel" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="AssetList">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="MainPicture">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="FrameRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="ScreenAspectRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="MainSound">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="MainSubtitle" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                                                 &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
 *                                                 &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "annotationText", "issueDate", "issuer", "creator",
        "contentTitleText", "contentKind", "ratingList", "reelList" })
@XmlRootElement(name = "CompositionPlaylist")
public class CompositionPlaylist {

	@XmlElement(name = "Id", required = true)
	protected String id;
	@XmlElement(name = "AnnotationText", required = true)
	protected String annotationText;
	@XmlElement(name = "IssueDate", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar issueDate;
	@XmlElement(name = "Issuer", required = true)
	protected String issuer;
	@XmlElement(name = "Creator", required = true)
	protected String creator;
	@XmlElement(name = "ContentTitleText", required = true)
	protected String contentTitleText;
	@XmlElement(name = "ContentKind", required = true)
	protected String contentKind;
	@XmlElement(name = "RatingList", required = true)
	protected String ratingList;
	@XmlElement(name = "ReelList", required = true)
	protected CompositionPlaylist.ReelList reelList;

	/**
	 * Obtient la valeur de la propriété id.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Définit la valeur de la propriété id.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setId(String value) {
		this.id = value;
	}

	/**
	 * Obtient la valeur de la propriété annotationText.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAnnotationText() {
		return annotationText;
	}

	/**
	 * Définit la valeur de la propriété annotationText.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAnnotationText(String value) {
		this.annotationText = value;
	}

	/**
	 * Obtient la valeur de la propriété issueDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getIssueDate() {
		return issueDate;
	}

	/**
	 * Définit la valeur de la propriété issueDate.
	 * 
	 * @param value
	 *            allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setIssueDate(XMLGregorianCalendar value) {
		this.issueDate = value;
	}

	/**
	 * Obtient la valeur de la propriété issuer.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIssuer() {
		return issuer;
	}

	/**
	 * Définit la valeur de la propriété issuer.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIssuer(String value) {
		this.issuer = value;
	}

	/**
	 * Obtient la valeur de la propriété creator.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * Définit la valeur de la propriété creator.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCreator(String value) {
		this.creator = value;
	}

	/**
	 * Obtient la valeur de la propriété contentTitleText.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContentTitleText() {
		return contentTitleText;
	}

	/**
	 * Définit la valeur de la propriété contentTitleText.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContentTitleText(String value) {
		this.contentTitleText = value;
	}

	/**
	 * Obtient la valeur de la propriété contentKind.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getContentKind() {
		return contentKind;
	}

	/**
	 * Définit la valeur de la propriété contentKind.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setContentKind(String value) {
		this.contentKind = value;
	}

	/**
	 * Obtient la valeur de la propriété ratingList.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRatingList() {
		return ratingList;
	}

	/**
	 * Définit la valeur de la propriété ratingList.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRatingList(String value) {
		this.ratingList = value;
	}

	/**
	 * Obtient la valeur de la propriété reelList.
	 * 
	 * @return possible object is {@link CompositionPlaylist.ReelList }
	 * 
	 */
	public CompositionPlaylist.ReelList getReelList() {
		return reelList;
	}

	/**
	 * Définit la valeur de la propriété reelList.
	 * 
	 * @param value
	 *            allowed object is {@link CompositionPlaylist.ReelList }
	 * 
	 */
	public void setReelList(CompositionPlaylist.ReelList value) {
		this.reelList = value;
	}

	/**
	 * <p>
	 * Classe Java pour anonymous complex type.
	 * 
	 * <p>
	 * Le fragment de schéma suivant indique le contenu attendu figurant dans
	 * cette classe.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="Reel" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="AssetList">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence>
	 *                             &lt;element name="MainPicture">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="FrameRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="ScreenAspectRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
	 *                                     &lt;/sequence>
	 *                                   &lt;/restriction>
	 *                                 &lt;/complexContent>
	 *                               &lt;/complexType>
	 *                             &lt;/element>
	 *                             &lt;element name="MainSound">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                     &lt;/sequence>
	 *                                   &lt;/restriction>
	 *                                 &lt;/complexContent>
	 *                               &lt;/complexType>
	 *                             &lt;/element>
	 *                             &lt;element name="MainSubtitle" maxOccurs="unbounded" minOccurs="0">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                                       &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
	 *                                       &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
	 *                                     &lt;/sequence>
	 *                                   &lt;/restriction>
	 *                                 &lt;/complexContent>
	 *                               &lt;/complexType>
	 *                             &lt;/element>
	 *                           &lt;/sequence>
	 *                         &lt;/restriction>
	 *                       &lt;/complexContent>
	 *                     &lt;/complexType>
	 *                   &lt;/element>
	 *                 &lt;/sequence>
	 *               &lt;/restriction>
	 *             &lt;/complexContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "reel" })
	public static class ReelList {

		@XmlElement(name = "Reel")
		protected List<CompositionPlaylist.ReelList.Reel> reel;

		/**
		 * Gets the value of the reel property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the reel property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getReel().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link CompositionPlaylist.ReelList.Reel }
		 * 
		 * 
		 */
		public List<CompositionPlaylist.ReelList.Reel> getReel() {
			if (reel == null) {
				reel = new ArrayList<CompositionPlaylist.ReelList.Reel>();
			}
			return this.reel;
		}

		/**
		 * <p>
		 * Classe Java pour anonymous complex type.
		 * 
		 * <p>
		 * Le fragment de schéma suivant indique le contenu attendu figurant
		 * dans cette classe.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;complexContent>
		 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *       &lt;sequence>
		 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *         &lt;element name="AssetList">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence>
		 *                   &lt;element name="MainPicture">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="FrameRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="ScreenAspectRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
		 *                           &lt;/sequence>
		 *                         &lt;/restriction>
		 *                       &lt;/complexContent>
		 *                     &lt;/complexType>
		 *                   &lt;/element>
		 *                   &lt;element name="MainSound">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                           &lt;/sequence>
		 *                         &lt;/restriction>
		 *                       &lt;/complexContent>
		 *                     &lt;/complexType>
		 *                   &lt;/element>
		 *                   &lt;element name="MainSubtitle" maxOccurs="unbounded" minOccurs="0">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *                             &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
		 *                             &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
		 *                           &lt;/sequence>
		 *                         &lt;/restriction>
		 *                       &lt;/complexContent>
		 *                     &lt;/complexType>
		 *                   &lt;/element>
		 *                 &lt;/sequence>
		 *               &lt;/restriction>
		 *             &lt;/complexContent>
		 *           &lt;/complexType>
		 *         &lt;/element>
		 *       &lt;/sequence>
		 *     &lt;/restriction>
		 *   &lt;/complexContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "id", "assetList" })
		public static class Reel {

			@XmlElement(name = "Id", required = true)
			protected String id;
			@XmlElement(name = "AssetList", required = true)
			protected CompositionPlaylist.ReelList.Reel.AssetList assetList;

			/**
			 * Obtient la valeur de la propriété id.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getId() {
				return id;
			}

			/**
			 * Définit la valeur de la propriété id.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setId(String value) {
				this.id = value;
			}

			/**
			 * Obtient la valeur de la propriété assetList.
			 * 
			 * @return possible object is
			 *         {@link CompositionPlaylist.ReelList.Reel.AssetList }
			 * 
			 */
			public CompositionPlaylist.ReelList.Reel.AssetList getAssetList() {
				return assetList;
			}

			/**
			 * Définit la valeur de la propriété assetList.
			 * 
			 * @param value
			 *            allowed object is
			 *            {@link CompositionPlaylist.ReelList.Reel.AssetList }
			 * 
			 */
			public void setAssetList(CompositionPlaylist.ReelList.Reel.AssetList value) {
				this.assetList = value;
			}

			/**
			 * <p>
			 * Classe Java pour anonymous complex type.
			 * 
			 * <p>
			 * Le fragment de schéma suivant indique le contenu attendu figurant
			 * dans cette classe.
			 * 
			 * <pre>
			 * &lt;complexType>
			 *   &lt;complexContent>
			 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *       &lt;sequence>
			 *         &lt;element name="MainPicture">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="FrameRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="ScreenAspectRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *         &lt;element name="MainSound">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *         &lt;element name="MainSubtitle" maxOccurs="unbounded" minOccurs="0">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                   &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
			 *                   &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
			 *                 &lt;/sequence>
			 *               &lt;/restriction>
			 *             &lt;/complexContent>
			 *           &lt;/complexType>
			 *         &lt;/element>
			 *       &lt;/sequence>
			 *     &lt;/restriction>
			 *   &lt;/complexContent>
			 * &lt;/complexType>
			 * </pre>
			 * 
			 * 
			 */
			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = { "mainPicture", "mainSound", "mainSubtitle" })
			public static class AssetList {

				@XmlElement(name = "MainPicture", required = true)
				protected CompositionPlaylist.ReelList.Reel.AssetList.MainPicture mainPicture;
				@XmlElement(name = "MainSound", required = true)
				protected CompositionPlaylist.ReelList.Reel.AssetList.MainSound mainSound;
				@XmlElement(name = "MainSubtitle")
				protected List<CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle> mainSubtitle;

				/**
				 * Obtient la valeur de la propriété mainPicture.
				 * 
				 * @return possible object is
				 *         {@link CompositionPlaylist.ReelList.Reel.AssetList.MainPicture }
				 * 
				 */
				public CompositionPlaylist.ReelList.Reel.AssetList.MainPicture getMainPicture() {
					return mainPicture;
				}

				/**
				 * Définit la valeur de la propriété mainPicture.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link CompositionPlaylist.ReelList.Reel.AssetList.MainPicture }
				 * 
				 */
				public void setMainPicture(
				        CompositionPlaylist.ReelList.Reel.AssetList.MainPicture value) {
					this.mainPicture = value;
				}

				/**
				 * Obtient la valeur de la propriété mainSound.
				 * 
				 * @return possible object is
				 *         {@link CompositionPlaylist.ReelList.Reel.AssetList.MainSound }
				 * 
				 */
				public CompositionPlaylist.ReelList.Reel.AssetList.MainSound getMainSound() {
					return mainSound;
				}

				/**
				 * Définit la valeur de la propriété mainSound.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link CompositionPlaylist.ReelList.Reel.AssetList.MainSound }
				 * 
				 */
				public void setMainSound(CompositionPlaylist.ReelList.Reel.AssetList.MainSound value) {
					this.mainSound = value;
				}

				/**
				 * Gets the value of the mainSubtitle property.
				 * 
				 * <p>
				 * This accessor method returns a reference to the live list,
				 * not a snapshot. Therefore any modification you make to the
				 * returned list will be present inside the JAXB object. This is
				 * why there is not a <CODE>set</CODE> method for the
				 * mainSubtitle property.
				 * 
				 * <p>
				 * For example, to add a new item, do as follows:
				 * 
				 * <pre>
				 * getMainSubtitle().add(newItem);
				 * </pre>
				 * 
				 * 
				 * <p>
				 * Objects of the following type(s) are allowed in the list
				 * {@link CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle }
				 * 
				 * 
				 */
				public List<CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle> getMainSubtitle() {
					if (mainSubtitle == null) {
						mainSubtitle = new ArrayList<CompositionPlaylist.ReelList.Reel.AssetList.MainSubtitle>();
					}
					return this.mainSubtitle;
				}

				/**
				 * <p>
				 * Classe Java pour anonymous complex type.
				 * 
				 * <p>
				 * Le fragment de schéma suivant indique le contenu attendu
				 * figurant dans cette classe.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="FrameRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="ScreenAspectRatio" type="{http://www.w3.org/2001/XMLSchema}float"/>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "id", "editRate", "intrinsicDuration",
				        "entryPoint", "duration", "keyId", "hash", "frameRate", "screenAspectRatio" })
				public static class MainPicture {

					@XmlElement(name = "Id", required = true)
					protected String id;
					@XmlElement(name = "EditRate", required = true)
					protected String editRate;
					@XmlElement(name = "IntrinsicDuration")
					protected int intrinsicDuration;
					@XmlElement(name = "EntryPoint")
					protected int entryPoint;
					@XmlElement(name = "Duration")
					protected int duration;
					@XmlElement(name = "KeyId", required = true)
					protected String keyId;
					@XmlElement(name = "Hash", required = true)
					protected String hash;
					@XmlElement(name = "FrameRate", required = true)
					protected String frameRate;
					@XmlElement(name = "ScreenAspectRatio")
					protected float screenAspectRatio;

					/**
					 * Obtient la valeur de la propriété id.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getId() {
						return id;
					}

					/**
					 * Définit la valeur de la propriété id.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setId(String value) {
						this.id = value;
					}

					/**
					 * Obtient la valeur de la propriété editRate.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getEditRate() {
						return editRate;
					}

					/**
					 * Définit la valeur de la propriété editRate.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setEditRate(String value) {
						this.editRate = value;
					}

					/**
					 * Obtient la valeur de la propriété intrinsicDuration.
					 * 
					 */
					public int getIntrinsicDuration() {
						return intrinsicDuration;
					}

					/**
					 * Définit la valeur de la propriété intrinsicDuration.
					 * 
					 */
					public void setIntrinsicDuration(short value) {
						this.intrinsicDuration = value;
					}

					/**
					 * Obtient la valeur de la propriété entryPoint.
					 * 
					 */
					public int getEntryPoint() {
						return entryPoint;
					}

					/**
					 * Définit la valeur de la propriété entryPoint.
					 * 
					 */
					public void setEntryPoint(short value) {
						this.entryPoint = value;
					}

					/**
					 * Obtient la valeur de la propriété duration.
					 * 
					 */
					public int getDuration() {
						return duration;
					}

					/**
					 * Définit la valeur de la propriété duration.
					 * 
					 */
					public void setDuration(short value) {
						this.duration = value;
					}

					/**
					 * Obtient la valeur de la propriété keyId.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getKeyId() {
						return keyId;
					}

					/**
					 * Définit la valeur de la propriété keyId.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setKeyId(String value) {
						this.keyId = value;
					}

					/**
					 * Obtient la valeur de la propriété hash.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getHash() {
						return hash;
					}

					/**
					 * Définit la valeur de la propriété hash.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setHash(String value) {
						this.hash = value;
					}

					/**
					 * Obtient la valeur de la propriété frameRate.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getFrameRate() {
						return frameRate;
					}

					/**
					 * Définit la valeur de la propriété frameRate.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setFrameRate(String value) {
						this.frameRate = value;
					}

					/**
					 * Obtient la valeur de la propriété screenAspectRatio.
					 * 
					 */
					public float getScreenAspectRatio() {
						return screenAspectRatio;
					}

					/**
					 * Définit la valeur de la propriété screenAspectRatio.
					 * 
					 */
					public void setScreenAspectRatio(float value) {
						this.screenAspectRatio = value;
					}

				}

				/**
				 * <p>
				 * Classe Java pour anonymous complex type.
				 * 
				 * <p>
				 * Le fragment de schéma suivant indique le contenu attendu
				 * figurant dans cette classe.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="KeyId" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "id", "editRate", "intrinsicDuration",
				        "entryPoint", "duration", "keyId", "hash" })
				public static class MainSound {

					@XmlElement(name = "Id", required = true)
					protected String id;
					@XmlElement(name = "EditRate", required = true)
					protected String editRate;
					@XmlElement(name = "IntrinsicDuration")
					protected int intrinsicDuration;
					@XmlElement(name = "EntryPoint")
					protected int entryPoint;
					@XmlElement(name = "Duration")
					protected int duration;
					@XmlElement(name = "KeyId", required = true)
					protected String keyId;
					@XmlElement(name = "Hash", required = true)
					protected String hash;

					/**
					 * Obtient la valeur de la propriété id.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getId() {
						return id;
					}

					/**
					 * Définit la valeur de la propriété id.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setId(String value) {
						this.id = value;
					}

					/**
					 * Obtient la valeur de la propriété editRate.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getEditRate() {
						return editRate;
					}

					/**
					 * Définit la valeur de la propriété editRate.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setEditRate(String value) {
						this.editRate = value;
					}

					/**
					 * Obtient la valeur de la propriété intrinsicDuration.
					 * 
					 */
					public int getIntrinsicDuration() {
						return intrinsicDuration;
					}

					/**
					 * Définit la valeur de la propriété intrinsicDuration.
					 * 
					 */
					public void setIntrinsicDuration(short value) {
						this.intrinsicDuration = value;
					}

					/**
					 * Obtient la valeur de la propriété entryPoint.
					 * 
					 */
					public int getEntryPoint() {
						return entryPoint;
					}

					/**
					 * Définit la valeur de la propriété entryPoint.
					 * 
					 */
					public void setEntryPoint(short value) {
						this.entryPoint = value;
					}

					/**
					 * Obtient la valeur de la propriété duration.
					 * 
					 */
					public int getDuration() {
						return duration;
					}

					/**
					 * Définit la valeur de la propriété duration.
					 * 
					 */
					public void setDuration(short value) {
						this.duration = value;
					}

					/**
					 * Obtient la valeur de la propriété keyId.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getKeyId() {
						return keyId;
					}

					/**
					 * Définit la valeur de la propriété keyId.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setKeyId(String value) {
						this.keyId = value;
					}

					/**
					 * Obtient la valeur de la propriété hash.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getHash() {
						return hash;
					}

					/**
					 * Définit la valeur de la propriété hash.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setHash(String value) {
						this.hash = value;
					}

				}

				/**
				 * <p>
				 * Classe Java pour anonymous complex type.
				 * 
				 * <p>
				 * Le fragment de schéma suivant indique le contenu attendu
				 * figurant dans cette classe.
				 * 
				 * <pre>
				 * &lt;complexType>
				 *   &lt;complexContent>
				 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *       &lt;sequence>
				 *         &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="EntryPoint" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}short"/>
				 *         &lt;element name="Hash" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Language" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
				 *       &lt;/sequence>
				 *     &lt;/restriction>
				 *   &lt;/complexContent>
				 * &lt;/complexType>
				 * </pre>
				 * 
				 * 
				 */
				@XmlAccessorType(XmlAccessType.FIELD)
				@XmlType(name = "", propOrder = { "id", "editRate", "intrinsicDuration",
				        "entryPoint", "duration", "hash", "language" })
				public static class MainSubtitle {

					@XmlElement(name = "Id", required = true)
					protected String id;
					@XmlElement(name = "EditRate", required = true)
					protected String editRate;
					@XmlElement(name = "IntrinsicDuration")
					protected int intrinsicDuration;
					@XmlElement(name = "EntryPoint")
					protected byte entryPoint;
					@XmlElement(name = "Duration")
					protected int duration;
					@XmlElement(name = "Hash", required = true)
					protected String hash;
					@XmlElement(name = "Language")
					protected String language;

					/**
					 * Obtient la valeur de la propriété id.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getId() {
						return id;
					}

					/**
					 * Définit la valeur de la propriété id.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setId(String value) {
						this.id = value;
					}

					/**
					 * Obtient la valeur de la propriété editRate.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getEditRate() {
						return editRate;
					}

					/**
					 * Définit la valeur de la propriété editRate.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setEditRate(String value) {
						this.editRate = value;
					}

					/**
					 * Obtient la valeur de la propriété intrinsicDuration.
					 * 
					 */
					public int getIntrinsicDuration() {
						return intrinsicDuration;
					}

					/**
					 * Définit la valeur de la propriété intrinsicDuration.
					 * 
					 */
					public void setIntrinsicDuration(short value) {
						this.intrinsicDuration = value;
					}

					/**
					 * Obtient la valeur de la propriété entryPoint.
					 * 
					 */
					public byte getEntryPoint() {
						return entryPoint;
					}

					/**
					 * Définit la valeur de la propriété entryPoint.
					 * 
					 */
					public void setEntryPoint(byte value) {
						this.entryPoint = value;
					}

					/**
					 * Obtient la valeur de la propriété duration.
					 * 
					 */
					public int getDuration() {
						return duration;
					}

					/**
					 * Définit la valeur de la propriété duration.
					 * 
					 */
					public void setDuration(short value) {
						this.duration = value;
					}

					/**
					 * Obtient la valeur de la propriété hash.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getHash() {
						return hash;
					}

					/**
					 * Définit la valeur de la propriété hash.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setHash(String value) {
						this.hash = value;
					}

					/**
					 * Obtient la valeur de la propriété language.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getLanguage() {
						return language;
					}

					/**
					 * Définit la valeur de la propriété language.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setLanguage(String value) {
						this.language = value;
					}

				}

			}

		}

	}

	// private static final Logger logger =
	// Logger.getLogger(CompositionPlaylist.class);

	private static void renameTag(Document doc, String oldTagName, String newTagName) {
		NodeList nodeList = doc.getElementsByTagName(oldTagName);
		for (int i = 0; i < nodeList.getLength(); i++) {
			doc.renameNode(nodeList.item(i), null, newTagName);
		}
	}

	public static CompositionPlaylist fromFile(String cplFilePath) {
		try {
			// Parse the xml file into Document
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(cplFilePath);
			doc.getDocumentElement().normalize();

			// Rename the tag name to unmarshall multiple CPL formats into one
			// object
			renameTag(doc, "tt-cpl:ClosedCaption", "MainSubtitle");

			// Unmarshall
			return (CompositionPlaylist) JAXBContext.newInstance(CompositionPlaylist.class)
			        .createUnmarshaller().unmarshal(doc.getChildNodes().item(0));
		} catch (DOMException | ParserConfigurationException | SAXException | IOException
		        | JAXBException e) {
			// logger.error(e);
		}

		return null;
	}

}
