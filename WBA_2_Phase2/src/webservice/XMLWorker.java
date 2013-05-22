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

/**
 * @author Jeremy Dennis Zervo
 *
 */
public class XMLWorker {
	JAXBContext comments;
	JAXBContext interpreten;
	JAXBContext users;
	JAXBContext songs;
	/**
	 * Kontext fuer marshal und unmarshall
	 * @throws JAXBException
	 */
	public XMLWorker() throws JAXBException{
		this.comments = JAXBContext.newInstance(generated.Comments.class);
		this.interpreten = JAXBContext.newInstance(generated.Interpreten.class);
		this.users = JAXBContext.newInstance(generated.Users.class);
		this.songs = JAXBContext.newInstance(generated.Songs.class);
	}


	/**
	 * Methode zum Auslesen aus der Buchungen-XML-Datei
	 * @return (Buchungen) buchungen
	 * @throws JAXBException
	 */
	public Comments unmarshalComments() throws JAXBException{
		Unmarshaller unmarshal = comments.createUnmarshaller();
		Comments comments = (Comments) unmarshal.unmarshal(new File("src/XMLs/Buchungen.xml"));

		return comments;
	}

	/**
	 * Methode zum Schreiben in die Buchungen-XML-Datei
	 * @param buchungsliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalComments(Comments commentsliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/Comments.xsd"));
		Marshaller marshal = comments.createMarshaller();
		marshal.setSchema(schema);
        marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/Comments.xml");
		marshal.marshal(commentsliste, file);
	}

	/**
	 * Methode zum Auslesen aus der Module-XML-Datei
	 * @return (Module) module
	 * @throws JAXBException
	 */
	public Interpreten unmarshalInterpreten() throws JAXBException{
		Unmarshaller unmarshal = interpreten.createUnmarshaller();
		Interpreten interpreten = (Interpreten) unmarshal.unmarshal(new File("src/Interpreten.xml"));

		return interpreten;
	}

	/**
	 * Methode zum Schreiben in die Module-XML-Datei
	 * @param modulliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalInterpreten(Interpreten interpretenliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/Interpreten.xsd"));
		Marshaller marshal = interpreten.createMarshaller();
		marshal.setSchema(schema);
        marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/Interpreten.xml");
		marshal.marshal(interpretenliste, file);
	}

	/**
	 * Methode zum Auslesen aus der Raeume-XML-Datei
	 * @return (Raeume) raeume
	 * @throws JAXBException
	 */
	public Songs unmarshalSongs() throws JAXBException{
		Unmarshaller unmarshal = songs.createUnmarshaller();
		Songs songs = (Songs) unmarshal.unmarshal(new File("src/Songs.xml"));

		return songs;
	}

	/**
	 * Methode zum Schreiben in die Raeume-XML-Datei
	 * @param raumliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalSongs(Songs songsliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/Songs.xsd"));
		Marshaller marshal = songs.createMarshaller();
		marshal.setSchema(schema);
		marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/Songs.xml");
		marshal.marshal(songsliste, file);
	}

	/**
	 * Methode zum Auslesen aus der Stundenplaene-XML-Datei
	 * @return (Stundenplaene) stundenplaene
	 * @throws JAXBException
	 */
	public Users unmarshalUsers() throws JAXBException{
		Unmarshaller unmarshal = users.createUnmarshaller();
		Users users = (Users) unmarshal.unmarshal(new File("src/Users.xml"));

		return users;
	}

	/**
	 * Methode zum Schreiben in die Stundenplaene-XML-Datei
	 * @param stundenplansliste
	 * @throws JAXBException
	 * @throws SAXException
	 * @throws FileNotFoundException
	 */
	public void marshalUsers(Users usersliste) throws JAXBException, SAXException, FileNotFoundException{
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File("src/Users.xsd"));
		Marshaller marshal = users.createMarshaller();
		marshal.setSchema(schema);
		marshal.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		marshal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream file = new FileOutputStream("src/Users.xml");
		marshal.marshal(usersliste, file);
	}

}