//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.07.15 à 04:49:49 PM CEST 
//

package com.cineapps.doremi.show;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;

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
 *         &lt;element name="ShowTitleText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IssueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Issuer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Creator" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PackList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Pack">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="EventList">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="Event" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="ElementList">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="MainElement">
 *                                                             &lt;complexType>
 *                                                               &lt;complexContent>
 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                   &lt;sequence>
 *                                                                     &lt;element name="Composition">
 *                                                                       &lt;complexType>
 *                                                                         &lt;complexContent>
 *                                                                           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                                             &lt;sequence>
 *                                                                               &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                               &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                               &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                               &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                                                               &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                                             &lt;/sequence>
 *                                                                           &lt;/restriction>
 *                                                                         &lt;/complexContent>
 *                                                                       &lt;/complexType>
 *                                                                     &lt;/element>
 *                                                                   &lt;/sequence>
 *                                                                 &lt;/restriction>
 *                                                               &lt;/complexContent>
 *                                                             &lt;/complexType>
 *                                                           &lt;/element>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
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
 *         &lt;element name="EventList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Event" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="ElementList">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="MainElement">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Composition" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                                                           &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                                 &lt;element name="Pattern" minOccurs="0">
 *                                                   &lt;complexType>
 *                                                     &lt;complexContent>
 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                                         &lt;sequence>
 *                                                           &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                           &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                                                           &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                         &lt;/sequence>
 *                                                       &lt;/restriction>
 *                                                     &lt;/complexContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                       &lt;element name="AutomationCue" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="Action" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="Offset">
 *                                                   &lt;complexType>
 *                                                     &lt;simpleContent>
 *                                                       &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
 *                                                         &lt;attribute name="Kind" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                                       &lt;/extension>
 *                                                     &lt;/simpleContent>
 *                                                   &lt;/complexType>
 *                                                 &lt;/element>
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
@XmlType(name = "", propOrder = { "id", "showTitleText", "annotationText", "issueDate", "issuer",
        "creator", "packList", "eventList" })
@XmlRootElement(name = "ShowPlaylist")
public class ShowPlaylist {

	@XmlElement(name = "Id", required = true)
	protected String id;
	@XmlElement(name = "ShowTitleText", required = true)
	protected String showTitleText;
	@XmlElement(name = "AnnotationText", required = true)
	protected String annotationText;
	@XmlElement(name = "IssueDate", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar issueDate;
	@XmlElement(name = "Issuer", required = true)
	protected String issuer;
	@XmlElement(name = "Creator", required = true)
	protected String creator;
	@XmlElement(name = "PackList", required = true)
	protected ShowPlaylist.PackList packList;
	@XmlElement(name = "EventList", required = true)
	protected ShowPlaylist.EventList eventList;

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
	 * Obtient la valeur de la propriété showTitleText.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShowTitleText() {
		return showTitleText;
	}

	/**
	 * Définit la valeur de la propriété showTitleText.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setShowTitleText(String value) {
		this.showTitleText = value;
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
	 * Obtient la valeur de la propriété packList.
	 * 
	 * @return possible object is {@link ShowPlaylist.PackList }
	 * 
	 */
	public ShowPlaylist.PackList getPackList() {
		return packList;
	}

	/**
	 * Définit la valeur de la propriété packList.
	 * 
	 * @param value
	 *            allowed object is {@link ShowPlaylist.PackList }
	 * 
	 */
	public void setPackList(ShowPlaylist.PackList value) {
		this.packList = value;
	}

	/**
	 * Obtient la valeur de la propriété eventList.
	 * 
	 * @return possible object is {@link ShowPlaylist.EventList }
	 * 
	 */
	public ShowPlaylist.EventList getEventList() {
		return eventList;
	}

	/**
	 * Définit la valeur de la propriété eventList.
	 * 
	 * @param value
	 *            allowed object is {@link ShowPlaylist.EventList }
	 * 
	 */
	public void setEventList(ShowPlaylist.EventList value) {
		this.eventList = value;
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
	 *         &lt;element name="Event" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="ElementList">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence>
	 *                             &lt;element name="MainElement">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="Composition" minOccurs="0">
	 *                                         &lt;complexType>
	 *                                           &lt;complexContent>
	 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                               &lt;sequence>
	 *                                                 &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                                                 &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                               &lt;/sequence>
	 *                                             &lt;/restriction>
	 *                                           &lt;/complexContent>
	 *                                         &lt;/complexType>
	 *                                       &lt;/element>
	 *                                       &lt;element name="Pattern" minOccurs="0">
	 *                                         &lt;complexType>
	 *                                           &lt;complexContent>
	 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                               &lt;sequence>
	 *                                                 &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                 &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}byte"/>
	 *                                                 &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
	 *                             &lt;element name="AutomationCue" maxOccurs="unbounded" minOccurs="0">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="Action" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="Offset">
	 *                                         &lt;complexType>
	 *                                           &lt;simpleContent>
	 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
	 *                                               &lt;attribute name="Kind" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *                                             &lt;/extension>
	 *                                           &lt;/simpleContent>
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
	@XmlType(name = "", propOrder = { "event" })
	public static class EventList {

		@XmlElement(name = "Event")
		protected List<ShowPlaylist.EventList.Event> event;

		/**
		 * Gets the value of the event property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a
		 * snapshot. Therefore any modification you make to the returned list
		 * will be present inside the JAXB object. This is why there is not a
		 * <CODE>set</CODE> method for the event property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getEvent().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link ShowPlaylist.EventList.Event }
		 * 
		 * 
		 */
		public List<ShowPlaylist.EventList.Event> getEvent() {
			if (event == null) {
				event = new ArrayList<ShowPlaylist.EventList.Event>();
			}
			return this.event;
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
		 *         &lt;element name="ElementList">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence>
		 *                   &lt;element name="MainElement">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="Composition" minOccurs="0">
		 *                               &lt;complexType>
		 *                                 &lt;complexContent>
		 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                                     &lt;sequence>
		 *                                       &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *                                       &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                     &lt;/sequence>
		 *                                   &lt;/restriction>
		 *                                 &lt;/complexContent>
		 *                               &lt;/complexType>
		 *                             &lt;/element>
		 *                             &lt;element name="Pattern" minOccurs="0">
		 *                               &lt;complexType>
		 *                                 &lt;complexContent>
		 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                                     &lt;sequence>
		 *                                       &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                       &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}byte"/>
		 *                                       &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
		 *                   &lt;element name="AutomationCue" maxOccurs="unbounded" minOccurs="0">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="Action" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="Offset">
		 *                               &lt;complexType>
		 *                                 &lt;simpleContent>
		 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
		 *                                     &lt;attribute name="Kind" type="{http://www.w3.org/2001/XMLSchema}string" />
		 *                                   &lt;/extension>
		 *                                 &lt;/simpleContent>
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
		@XmlType(name = "", propOrder = { "id", "elementList" })
		public static class Event {

			@XmlElement(name = "Id", required = true)
			protected String id;
			@XmlElement(name = "ElementList", required = true)
			protected ShowPlaylist.EventList.Event.ElementList elementList;

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
			 * Obtient la valeur de la propriété elementList.
			 * 
			 * @return possible object is
			 *         {@link ShowPlaylist.EventList.Event.ElementList }
			 * 
			 */
			public ShowPlaylist.EventList.Event.ElementList getElementList() {
				return elementList;
			}

			/**
			 * Définit la valeur de la propriété elementList.
			 * 
			 * @param value
			 *            allowed object is
			 *            {@link ShowPlaylist.EventList.Event.ElementList }
			 * 
			 */
			public void setElementList(ShowPlaylist.EventList.Event.ElementList value) {
				this.elementList = value;
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
			 *         &lt;element name="MainElement">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="Composition" minOccurs="0">
			 *                     &lt;complexType>
			 *                       &lt;complexContent>
			 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                           &lt;sequence>
			 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
			 *                             &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                           &lt;/sequence>
			 *                         &lt;/restriction>
			 *                       &lt;/complexContent>
			 *                     &lt;/complexType>
			 *                   &lt;/element>
			 *                   &lt;element name="Pattern" minOccurs="0">
			 *                     &lt;complexType>
			 *                       &lt;complexContent>
			 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                           &lt;sequence>
			 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                             &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}byte"/>
			 *                             &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
			 *         &lt;element name="AutomationCue" maxOccurs="unbounded" minOccurs="0">
			 *           &lt;complexType>
			 *             &lt;complexContent>
			 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
			 *                 &lt;sequence>
			 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Action" type="{http://www.w3.org/2001/XMLSchema}string"/>
			 *                   &lt;element name="Offset">
			 *                     &lt;complexType>
			 *                       &lt;simpleContent>
			 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
			 *                           &lt;attribute name="Kind" type="{http://www.w3.org/2001/XMLSchema}string" />
			 *                         &lt;/extension>
			 *                       &lt;/simpleContent>
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
			@XmlType(name = "", propOrder = { "mainElement", "automationCue" })
			public static class ElementList {

				@XmlElement(name = "MainElement", required = true)
				protected ShowPlaylist.EventList.Event.ElementList.MainElement mainElement;
				@XmlElement(name = "AutomationCue")
				protected List<ShowPlaylist.EventList.Event.ElementList.AutomationCue> automationCue;

				/**
				 * Obtient la valeur de la propriété mainElement.
				 * 
				 * @return possible object is
				 *         {@link ShowPlaylist.EventList.Event.ElementList.MainElement }
				 * 
				 */
				public ShowPlaylist.EventList.Event.ElementList.MainElement getMainElement() {
					return mainElement;
				}

				/**
				 * Définit la valeur de la propriété mainElement.
				 * 
				 * @param value
				 *            allowed object is
				 *            {@link ShowPlaylist.EventList.Event.ElementList.MainElement }
				 * 
				 */
				public void setMainElement(
				        ShowPlaylist.EventList.Event.ElementList.MainElement value) {
					this.mainElement = value;
				}

				/**
				 * Gets the value of the automationCue property.
				 * 
				 * <p>
				 * This accessor method returns a reference to the live list,
				 * not a snapshot. Therefore any modification you make to the
				 * returned list will be present inside the JAXB object. This is
				 * why there is not a <CODE>set</CODE> method for the
				 * automationCue property.
				 * 
				 * <p>
				 * For example, to add a new item, do as follows:
				 * 
				 * <pre>
				 * getAutomationCue().add(newItem);
				 * </pre>
				 * 
				 * 
				 * <p>
				 * Objects of the following type(s) are allowed in the list
				 * {@link ShowPlaylist.EventList.Event.ElementList.AutomationCue }
				 * 
				 * 
				 */
				public List<ShowPlaylist.EventList.Event.ElementList.AutomationCue> getAutomationCue() {
					if (automationCue == null) {
						automationCue = new ArrayList<ShowPlaylist.EventList.Event.ElementList.AutomationCue>();
					}
					return this.automationCue;
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
				 *         &lt;element name="Action" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *         &lt;element name="Offset">
				 *           &lt;complexType>
				 *             &lt;simpleContent>
				 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
				 *                 &lt;attribute name="Kind" type="{http://www.w3.org/2001/XMLSchema}string" />
				 *               &lt;/extension>
				 *             &lt;/simpleContent>
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
				@XmlType(name = "", propOrder = { "id", "action", "offset" })
				public static class AutomationCue {

					@XmlElement(name = "Id", required = true)
					protected String id;
					@XmlElement(name = "Action", required = true)
					protected String action;
					@XmlElement(name = "Offset", required = true)
					protected ShowPlaylist.EventList.Event.ElementList.AutomationCue.Offset offset;

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
					 * Obtient la valeur de la propriété action.
					 * 
					 * @return possible object is {@link String }
					 * 
					 */
					public String getAction() {
						return action;
					}

					/**
					 * Définit la valeur de la propriété action.
					 * 
					 * @param value
					 *            allowed object is {@link String }
					 * 
					 */
					public void setAction(String value) {
						this.action = value;
					}

					/**
					 * Obtient la valeur de la propriété offset.
					 * 
					 * @return possible object is
					 *         {@link ShowPlaylist.EventList.Event.ElementList.AutomationCue.Offset }
					 * 
					 */
					public ShowPlaylist.EventList.Event.ElementList.AutomationCue.Offset getOffset() {
						return offset;
					}

					/**
					 * Définit la valeur de la propriété offset.
					 * 
					 * @param value
					 *            allowed object is
					 *            {@link ShowPlaylist.EventList.Event.ElementList.AutomationCue.Offset }
					 * 
					 */
					public void setOffset(
					        ShowPlaylist.EventList.Event.ElementList.AutomationCue.Offset value) {
						this.offset = value;
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
					 *   &lt;simpleContent>
					 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>int">
					 *       &lt;attribute name="Kind" type="{http://www.w3.org/2001/XMLSchema}string" />
					 *     &lt;/extension>
					 *   &lt;/simpleContent>
					 * &lt;/complexType>
					 * </pre>
					 * 
					 * 
					 */
					@XmlAccessorType(XmlAccessType.FIELD)
					@XmlType(name = "", propOrder = { "value" })
					public static class Offset {

						@XmlValue
						protected int value;
						@XmlAttribute(name = "Kind")
						protected String kind;

						/**
						 * Obtient la valeur de la propriété value.
						 * 
						 */
						public int getValue() {
							return value;
						}

						/**
						 * Définit la valeur de la propriété value.
						 * 
						 */
						public void setValue(int value) {
							this.value = value;
						}

						/**
						 * Obtient la valeur de la propriété kind.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getKind() {
							return kind;
						}

						/**
						 * Définit la valeur de la propriété kind.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setKind(String value) {
							this.kind = value;
						}

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
				 *         &lt;element name="Composition" minOccurs="0">
				 *           &lt;complexType>
				 *             &lt;complexContent>
				 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *                 &lt;sequence>
				 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
				 *                   &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                 &lt;/sequence>
				 *               &lt;/restriction>
				 *             &lt;/complexContent>
				 *           &lt;/complexType>
				 *         &lt;/element>
				 *         &lt;element name="Pattern" minOccurs="0">
				 *           &lt;complexType>
				 *             &lt;complexContent>
				 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
				 *                 &lt;sequence>
				 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
				 *                   &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}byte"/>
				 *                   &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
				@XmlType(name = "", propOrder = { "composition", "pattern" })
				public static class MainElement {

					@XmlElement(name = "Composition")
					protected ShowPlaylist.EventList.Event.ElementList.MainElement.Composition composition;
					@XmlElement(name = "Pattern")
					protected ShowPlaylist.EventList.Event.ElementList.MainElement.Pattern pattern;

					/**
					 * Obtient la valeur de la propriété composition.
					 * 
					 * @return possible object is
					 *         {@link ShowPlaylist.EventList.Event.ElementList.MainElement.Composition }
					 * 
					 */
					public ShowPlaylist.EventList.Event.ElementList.MainElement.Composition getComposition() {
						return composition;
					}

					/**
					 * Définit la valeur de la propriété composition.
					 * 
					 * @param value
					 *            allowed object is
					 *            {@link ShowPlaylist.EventList.Event.ElementList.MainElement.Composition }
					 * 
					 */
					public void setComposition(
					        ShowPlaylist.EventList.Event.ElementList.MainElement.Composition value) {
						this.composition = value;
					}

					/**
					 * Obtient la valeur de la propriété pattern.
					 * 
					 * @return possible object is
					 *         {@link ShowPlaylist.EventList.Event.ElementList.MainElement.Pattern }
					 * 
					 */
					public ShowPlaylist.EventList.Event.ElementList.MainElement.Pattern getPattern() {
						return pattern;
					}

					/**
					 * Définit la valeur de la propriété pattern.
					 * 
					 * @param value
					 *            allowed object is
					 *            {@link ShowPlaylist.EventList.Event.ElementList.MainElement.Pattern }
					 * 
					 */
					public void setPattern(
					        ShowPlaylist.EventList.Event.ElementList.MainElement.Pattern value) {
						this.pattern = value;
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
					 *         &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
					 *         &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *       &lt;/sequence>
					 *     &lt;/restriction>
					 *   &lt;/complexContent>
					 * &lt;/complexType>
					 * </pre>
					 * 
					 * 
					 */
					@XmlAccessorType(XmlAccessType.FIELD)
					@XmlType(name = "", propOrder = { "id", "compositionPlaylistId",
					        "annotationText", "intrinsicDuration", "editRate" })
					public static class Composition {

						@XmlElement(name = "Id", required = true)
						protected String id;
						@XmlElement(name = "CompositionPlaylistId", required = true)
						protected String compositionPlaylistId;
						@XmlElement(name = "AnnotationText", required = true)
						protected String annotationText;
						@XmlElement(name = "IntrinsicDuration")
						protected int intrinsicDuration;
						@XmlElement(name = "EditRate", required = true)
						protected String editRate;

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
						 * Obtient la valeur de la propriété
						 * compositionPlaylistId.
						 * 
						 * @return possible object is {@link String }
						 * 
						 */
						public String getCompositionPlaylistId() {
							return compositionPlaylistId;
						}

						/**
						 * Définit la valeur de la propriété
						 * compositionPlaylistId.
						 * 
						 * @param value
						 *            allowed object is {@link String }
						 * 
						 */
						public void setCompositionPlaylistId(String value) {
							this.compositionPlaylistId = value;
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
						public void setIntrinsicDuration(int value) {
							this.intrinsicDuration = value;
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
					 *         &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}byte"/>
					 *         &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
					 *       &lt;/sequence>
					 *     &lt;/restriction>
					 *   &lt;/complexContent>
					 * &lt;/complexType>
					 * </pre>
					 * 
					 * 
					 */
					@XmlAccessorType(XmlAccessType.FIELD)
					@XmlType(name = "", propOrder = { "id", "annotationText", "duration",
					        "editRate" })
					public static class Pattern {

						@XmlElement(name = "Id", required = true)
						protected String id;
						@XmlElement(name = "AnnotationText", required = true)
						protected String annotationText;
						@XmlElement(name = "Duration")
						protected byte duration;
						@XmlElement(name = "EditRate", required = true)
						protected String editRate;

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
						 * Obtient la valeur de la propriété duration.
						 * 
						 */
						public byte getDuration() {
							return duration;
						}

						/**
						 * Définit la valeur de la propriété duration.
						 * 
						 */
						public void setDuration(byte value) {
							this.duration = value;
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

					}

				}

			}

		}

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
	 *         &lt;element name="Pack">
	 *           &lt;complexType>
	 *             &lt;complexContent>
	 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                 &lt;sequence>
	 *                   &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                   &lt;element name="EventList">
	 *                     &lt;complexType>
	 *                       &lt;complexContent>
	 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                           &lt;sequence>
	 *                             &lt;element name="Event" maxOccurs="unbounded" minOccurs="0">
	 *                               &lt;complexType>
	 *                                 &lt;complexContent>
	 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                     &lt;sequence>
	 *                                       &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                       &lt;element name="ElementList">
	 *                                         &lt;complexType>
	 *                                           &lt;complexContent>
	 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                               &lt;sequence>
	 *                                                 &lt;element name="MainElement">
	 *                                                   &lt;complexType>
	 *                                                     &lt;complexContent>
	 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                                         &lt;sequence>
	 *                                                           &lt;element name="Composition">
	 *                                                             &lt;complexType>
	 *                                                               &lt;complexContent>
	 *                                                                 &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *                                                                   &lt;sequence>
	 *                                                                     &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                                     &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                                     &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                                     &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
	 *                                                                     &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *                                                                   &lt;/sequence>
	 *                                                                 &lt;/restriction>
	 *                                                               &lt;/complexContent>
	 *                                                             &lt;/complexType>
	 *                                                           &lt;/element>
	 *                                                         &lt;/sequence>
	 *                                                       &lt;/restriction>
	 *                                                     &lt;/complexContent>
	 *                                                   &lt;/complexType>
	 *                                                 &lt;/element>
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
	@XmlType(name = "", propOrder = { "pack" })
	public static class PackList {

		@XmlElement(name = "Pack", required = true)
		protected ShowPlaylist.PackList.Pack pack;

		/**
		 * Obtient la valeur de la propriété pack.
		 * 
		 * @return possible object is {@link ShowPlaylist.PackList.Pack }
		 * 
		 */
		public ShowPlaylist.PackList.Pack getPack() {
			return pack;
		}

		/**
		 * Définit la valeur de la propriété pack.
		 * 
		 * @param value
		 *            allowed object is {@link ShowPlaylist.PackList.Pack }
		 * 
		 */
		public void setPack(ShowPlaylist.PackList.Pack value) {
			this.pack = value;
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
		 *         &lt;element name="EventList">
		 *           &lt;complexType>
		 *             &lt;complexContent>
		 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                 &lt;sequence>
		 *                   &lt;element name="Event" maxOccurs="unbounded" minOccurs="0">
		 *                     &lt;complexType>
		 *                       &lt;complexContent>
		 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                           &lt;sequence>
		 *                             &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                             &lt;element name="ElementList">
		 *                               &lt;complexType>
		 *                                 &lt;complexContent>
		 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                                     &lt;sequence>
		 *                                       &lt;element name="MainElement">
		 *                                         &lt;complexType>
		 *                                           &lt;complexContent>
		 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                                               &lt;sequence>
		 *                                                 &lt;element name="Composition">
		 *                                                   &lt;complexType>
		 *                                                     &lt;complexContent>
		 *                                                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
		 *                                                         &lt;sequence>
		 *                                                           &lt;element name="Id" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                                           &lt;element name="CompositionPlaylistId" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                                           &lt;element name="AnnotationText" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                                           &lt;element name="IntrinsicDuration" type="{http://www.w3.org/2001/XMLSchema}int"/>
		 *                                                           &lt;element name="EditRate" type="{http://www.w3.org/2001/XMLSchema}string"/>
		 *                                                         &lt;/sequence>
		 *                                                       &lt;/restriction>
		 *                                                     &lt;/complexContent>
		 *                                                   &lt;/complexType>
		 *                                                 &lt;/element>
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
		@XmlType(name = "", propOrder = { "id", "eventList" })
		public static class Pack {

			@XmlElement(name = "Id", required = true)
			protected String id;
			@XmlElement(name = "EventList", required = true)
			protected ShowPlaylist.EventList eventList;

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
			 * Obtient la valeur de la propriété eventList.
			 * 
			 * @return possible object is
			 *         {@link ShowPlaylist.PackList.Pack.EventList }
			 * 
			 */
			public ShowPlaylist.EventList getEventList() {
				return eventList;
			}

			/**
			 * Définit la valeur de la propriété eventList.
			 * 
			 * @param value
			 *            allowed object is
			 *            {@link ShowPlaylist.PackList.Pack.EventList }
			 * 
			 */
			public void setEventList(ShowPlaylist.EventList value) {
				this.eventList = value;
			}

		}

	}

}
