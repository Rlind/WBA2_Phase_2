package webservice;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import generated.Comments;
import generated.Interpreten;
import generated.Users;
import generated.Songs;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;


public class XmlHelper {
	JAXBContext comments;
	JAXBContext interpreten;
	JAXBContext users;
	JAXBContext songs;
	/**
	 * Kontext fuer marshal und unmarshall
	 * @throws JAXBException
	 */
	public XmlHelper() throws JAXBException{
		this.comments = JAXBContext.newInstance(generated.Comments.class);
		this.interpreten = JAXBContext.newInstance(generated.Interpreten.class);
		this.users = JAXBContext.newInstance(generated.Users.class);
		this.songs = JAXBContext.newInstance(generated.Songs.class);
	}


	/**
	 * Methode zum Auslesen aus der Comments-XML-Datei
	 * @return (Buchungen) buchungen
	 * @throws JAXBException
	 */
	public Comments unmarshalComments() throws JAXBException{
		Unmarshaller unmarshal = comments.createUnmarshaller();
		Comments comments = (Comments) unmarshal.unmarshal(new File("src/xmlxsd/Comment.xml"));

		return comments;
	}

	/**
	 * Methode zum Schreiben in die Comments-XML-Datei
	 * @param commentsliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalComments(Comments commentsliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/xmlxsd/Comment.xsd"));
		Marshaller marshal = comments.createMarshaller();
		marshal.setSchema(schema);
        marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xmlxsd/Comment.xml");
		marshal.marshal(commentsliste, file);
	}

	/**
	 * Methode zum Auslesen aus der Interpreten-XML-Datei
	 * @return (Interpreten) interpreten
	 * @throws JAXBException
	 */
	public Interpreten unmarshalInterpreten() throws JAXBException{
		Unmarshaller unmarshal = interpreten.createUnmarshaller();
		Interpreten interpreten = (Interpreten) unmarshal.unmarshal(new File("src/xmlxsd/Interpreten.xml"));

		return interpreten;
	}

	/**
	 * Methode zum Schreiben in die Interpreten-XML-Datei
	 * @param interpretenliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalInterpreten(Interpreten interpretenliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/xmlxsd/Interpreten.xsd"));
		Marshaller marshal = interpreten.createMarshaller();
		marshal.setSchema(schema);
        marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xmlxsd/Interpreten.xml");
		marshal.marshal(interpretenliste, file);
	}

	/**
	 * Methode zum Auslesen aus der Songs-XML-Datei
	 * @return (Songs) songs
	 * @throws JAXBException
	 */
	public Songs unmarshalSongs() throws JAXBException{
		Unmarshaller unmarshal = songs.createUnmarshaller();
		Songs songs = (Songs) unmarshal.unmarshal(new File("src/xmlxsd/Songs.xml"));

		return songs;
	}

	/**
	 * Methode zum Schreiben in die Songs-XML-Datei
	 * @param songliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalSongs(Songs songsliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/xmlxsd/Songs.xsd"));
		Marshaller marshal = songs.createMarshaller();
		marshal.setSchema(schema);
		marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xmlxsd/Songs.xml");
		marshal.marshal(songsliste, file);
	}

	/**
	 * Methode zum Auslesen aus der Users-XML-Datei
	 * @return (Users) user
	 * @throws JAXBException
	 */
	public Users unmarshalUsers() throws JAXBException{
		Unmarshaller unmarshal = users.createUnmarshaller();
		Users users = (Users) unmarshal.unmarshal(new File("src/xmlxsd/User.xml"));

		return users;
	}

	/**
	 * Methode zum Schreiben in die Users-XML-Datei
	 * @param usersliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalUsers(Users usersliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/xmlxsd/User.xsd"));
		Marshaller marshal = users.createMarshaller();
		marshal.setSchema(schema);
		marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/xmlxsd/User.xml");
		marshal.marshal(usersliste, file);
	}

}