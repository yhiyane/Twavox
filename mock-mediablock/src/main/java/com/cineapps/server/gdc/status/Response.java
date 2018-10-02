//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.07.17 à 04:41:19 PM CEST 
//

package com.cineapps.server.gdc.status;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

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
 *         &lt;element name="status">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="show_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="show_position">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="total_duration" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                           &lt;attribute name="played_duration" type="{http://www.w3.org/2001/XMLSchema}short" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="show_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="cpl_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="cpl_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="cpl_position">
 *                     &lt;complexType>
 *                       &lt;simpleContent>
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                           &lt;attribute name="cpl_index" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="total_duration" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="played_duration" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/extension>
 *                       &lt;/simpleContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *                 &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="error_description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="status2" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "status" })
@XmlRootElement(name = "response")
public class Response {

	@XmlElement(required = true)
	protected Response.Status status;
	@XmlAttribute(name = "version")
	protected Float version;
	@XmlAttribute(name = "status")
	protected String status2;

	/**
	 * Obtient la valeur de la propriété status.
	 * 
	 * @return possible object is {@link Response.Status }
	 * 
	 */
	public Response.Status getStatus() {
		return status;
	}

	/**
	 * Définit la valeur de la propriété status.
	 * 
	 * @param value
	 *            allowed object is {@link Response.Status }
	 * 
	 */
	public void setStatus(Response.Status value) {
		this.status = value;
	}

	/**
	 * Obtient la valeur de la propriété version.
	 * 
	 * @return possible object is {@link Float }
	 * 
	 */
	public Float getVersion() {
		return version;
	}

	/**
	 * Définit la valeur de la propriété version.
	 * 
	 * @param value
	 *            allowed object is {@link Float }
	 * 
	 */
	public void setVersion(Float value) {
		this.version = value;
	}

	/**
	 * Obtient la valeur de la propriété status2.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getStatus2() {
		return status2;
	}

	/**
	 * Définit la valeur de la propriété status2.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setStatus2(String value) {
		this.status2 = value;
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
	 *         &lt;element name="show_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="show_position">
	 *           &lt;complexType>
	 *             &lt;simpleContent>
	 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *                 &lt;attribute name="total_duration" type="{http://www.w3.org/2001/XMLSchema}short" />
	 *                 &lt;attribute name="played_duration" type="{http://www.w3.org/2001/XMLSchema}short" />
	 *               &lt;/extension>
	 *             &lt;/simpleContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *         &lt;element name="show_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="cpl_uuid" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="cpl_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
	 *         &lt;element name="cpl_position">
	 *           &lt;complexType>
	 *             &lt;simpleContent>
	 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *                 &lt;attribute name="cpl_index" type="{http://www.w3.org/2001/XMLSchema}byte" />
	 *                 &lt;attribute name="total_duration" type="{http://www.w3.org/2001/XMLSchema}byte" />
	 *                 &lt;attribute name="played_duration" type="{http://www.w3.org/2001/XMLSchema}byte" />
	 *               &lt;/extension>
	 *             &lt;/simpleContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *       &lt;attribute name="state" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *       &lt;attribute name="error_description" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "showUuid", "showPosition", "showName", "cplUuid", "cplName",
	        "cplPosition" })
	public static class Status {

		@XmlElement(name = "show_uuid", required = true)
		protected String showUuid;
		@XmlElement(name = "show_position", required = true)
		protected Response.Status.ShowPosition showPosition;
		@XmlElement(name = "show_name", required = true)
		protected String showName;
		@XmlElement(name = "cpl_uuid", required = true)
		protected String cplUuid;
		@XmlElement(name = "cpl_name", required = true)
		protected String cplName;
		@XmlElement(name = "cpl_position", required = true)
		protected Response.Status.CplPosition cplPosition;
		@XmlAttribute(name = "state")
		protected String state;
		@XmlAttribute(name = "error_description")
		protected String errorDescription;

		/**
		 * Obtient la valeur de la propriété showUuid.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShowUuid() {
			return showUuid;
		}

		/**
		 * Définit la valeur de la propriété showUuid.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShowUuid(String value) {
			this.showUuid = value;
		}

		/**
		 * Obtient la valeur de la propriété showPosition.
		 * 
		 * @return possible object is {@link Response.Status.ShowPosition }
		 * 
		 */
		public Response.Status.ShowPosition getShowPosition() {
			return showPosition;
		}

		/**
		 * Définit la valeur de la propriété showPosition.
		 * 
		 * @param value
		 *            allowed object is {@link Response.Status.ShowPosition }
		 * 
		 */
		public void setShowPosition(Response.Status.ShowPosition value) {
			this.showPosition = value;
		}

		/**
		 * Obtient la valeur de la propriété showName.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getShowName() {
			return showName;
		}

		/**
		 * Définit la valeur de la propriété showName.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setShowName(String value) {
			this.showName = value;
		}

		/**
		 * Obtient la valeur de la propriété cplUuid.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCplUuid() {
			return cplUuid;
		}

		/**
		 * Définit la valeur de la propriété cplUuid.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setCplUuid(String value) {
			this.cplUuid = value;
		}

		/**
		 * Obtient la valeur de la propriété cplName.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getCplName() {
			return cplName;
		}

		/**
		 * Définit la valeur de la propriété cplName.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setCplName(String value) {
			this.cplName = value;
		}

		/**
		 * Obtient la valeur de la propriété cplPosition.
		 * 
		 * @return possible object is {@link Response.Status.CplPosition }
		 * 
		 */
		public Response.Status.CplPosition getCplPosition() {
			return cplPosition;
		}

		/**
		 * Définit la valeur de la propriété cplPosition.
		 * 
		 * @param value
		 *            allowed object is {@link Response.Status.CplPosition }
		 * 
		 */
		public void setCplPosition(Response.Status.CplPosition value) {
			this.cplPosition = value;
		}

		/**
		 * Obtient la valeur de la propriété state.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getState() {
			return state;
		}

		/**
		 * Définit la valeur de la propriété state.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setState(String value) {
			this.state = value;
		}

		/**
		 * Obtient la valeur de la propriété errorDescription.
		 * 
		 * @return possible object is {@link String }
		 * 
		 */
		public String getErrorDescription() {
			return errorDescription;
		}

		/**
		 * Définit la valeur de la propriété errorDescription.
		 * 
		 * @param value
		 *            allowed object is {@link String }
		 * 
		 */
		public void setErrorDescription(String value) {
			this.errorDescription = value;
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
		 *   &lt;simpleContent>
		 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
		 *       &lt;attribute name="cpl_index" type="{http://www.w3.org/2001/XMLSchema}byte" />
		 *       &lt;attribute name="total_duration" type="{http://www.w3.org/2001/XMLSchema}byte" />
		 *       &lt;attribute name="played_duration" type="{http://www.w3.org/2001/XMLSchema}byte" />
		 *     &lt;/extension>
		 *   &lt;/simpleContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "value" })
		public static class CplPosition {

			@XmlValue
			protected String value;
			@XmlAttribute(name = "cpl_index")
			protected int cplIndex;
			@XmlAttribute(name = "total_duration")
			protected int totalDuration;
			@XmlAttribute(name = "played_duration")
			protected int playedDuration;

			/**
			 * Obtient la valeur de la propriété value.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getValue() {
				return value;
			}

			/**
			 * Définit la valeur de la propriété value.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setValue(String value) {
				this.value = value;
			}

			/**
			 * Obtient la valeur de la propriété cplIndex.
			 * 
			 * @return possible object is {@link Byte }
			 * 
			 */
			public int getCplIndex() {
				return cplIndex;
			}

			/**
			 * Définit la valeur de la propriété cplIndex.
			 * 
			 * @param value
			 *            allowed object is {@link Byte }
			 * 
			 */
			public void setCplIndex(int value) {
				this.cplIndex = value;
			}

			/**
			 * Obtient la valeur de la propriété totalDuration.
			 * 
			 * @return possible object is {@link Byte }
			 * 
			 */
			public int getTotalDuration() {
				return totalDuration;
			}

			/**
			 * Définit la valeur de la propriété totalDuration.
			 * 
			 * @param value
			 *            allowed object is {@link Byte }
			 * 
			 */
			public void setTotalDuration(int value) {
				this.totalDuration = value;
			}

			/**
			 * Obtient la valeur de la propriété playedDuration.
			 * 
			 * @return possible object is {@link Byte }
			 * 
			 */
			public int getPlayedDuration() {
				return playedDuration;
			}

			/**
			 * Définit la valeur de la propriété playedDuration.
			 * 
			 * @param value
			 *            allowed object is {@link Byte }
			 * 
			 */
			public void setPlayedDuration(int value) {
				this.playedDuration = value;
			}

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
		 *   &lt;simpleContent>
		 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
		 *       &lt;attribute name="total_duration" type="{http://www.w3.org/2001/XMLSchema}short" />
		 *       &lt;attribute name="played_duration" type="{http://www.w3.org/2001/XMLSchema}short" />
		 *     &lt;/extension>
		 *   &lt;/simpleContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "value" })
		public static class ShowPosition {

			@XmlValue
			protected String value;
			@XmlAttribute(name = "total_duration")
			protected int totalDuration;
			@XmlAttribute(name = "played_duration")
			protected int playedDuration;

			/**
			 * Obtient la valeur de la propriété value.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getValue() {
				return value;
			}

			/**
			 * Définit la valeur de la propriété value.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setValue(String value) {
				this.value = value;
			}

			/**
			 * Obtient la valeur de la propriété totalDuration.
			 * 
			 * @return possible object is {@link Short }
			 * 
			 */
			public int getTotalDuration() {
				return totalDuration;
			}

			/**
			 * Définit la valeur de la propriété totalDuration.
			 * 
			 * @param value
			 *            allowed object is {@link Short }
			 * 
			 */
			public void setTotalDuration(int value) {
				this.totalDuration = value;
			}

			/**
			 * Obtient la valeur de la propriété playedDuration.
			 * 
			 * @return possible object is {@link Short }
			 * 
			 */
			public int getPlayedDuration() {
				return playedDuration;
			}

			/**
			 * Définit la valeur de la propriété playedDuration.
			 * 
			 * @param value
			 *            allowed object is {@link Short }
			 * 
			 */
			public void setPlayedDuration(int value) {
				this.playedDuration = value;
			}

		}

	}

	/**
	 * Create a new default Response instance. Miss informations about the
	 * Status object.
	 * 
	 * @return
	 */
	public static Response defaultInstance() {
		Response response = new Response();
		response.setStatus2("OK");
		response.setVersion((float) 2.1);
		String defaultUuid = "urn:uuid:00000000-0000-0000-0000-000000000000";

		Status status = new Status();
		status.setCplUuid(defaultUuid);
		status.setShowUuid(defaultUuid);
		response.setStatus(status);

		return response;
	}

	public String marshall() throws JAXBException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(Response.class);
		StringWriter writer = new StringWriter();
		jaxbContext.createMarshaller().marshal(this, writer);

		return writer.toString();
	}
}
