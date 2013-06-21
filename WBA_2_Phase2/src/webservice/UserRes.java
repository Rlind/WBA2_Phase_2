package webservice;

import java.io.FileNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.xml.sax.SAXException;

import Evidamman2.Logger;

import generated.Users;
import generated.Users.User;
import webservice.XmlHelper;

@Path("/users")
public class UserRes {

	public XmlHelper xmlHelp;

	// Konstruktor mit dem die Variable initalisiert werden kann
	public UserRes() throws JAXBException {
		this.xmlHelp = new XmlHelper();
	}

	/**
	 * fordert angegebene Ressource vom Server an
	 * 
	 * @param topic
	 * @return Ressource
	 * @throws SAXException
	 * @throws JAXBException
	 */

	@GET
	@Produces("application/xml")
	public Users getUsers() throws JAXBException, SAXException {
		return this.xmlHelp.unmarshalUsers();
	}

	@GET
	@Path("{userID}")
	@Produces("application/xml")
	public Users getUser(@QueryParam("nick") String Nick,
			@QueryParam("name") String Name) throws JAXBException {
		User user = new User();

		Users returnUser = new Users();
		Users users = this.xmlHelp.unmarshalUsers();
		// QueryParam Abfrage für Nick
		int paramNick = Integer.parseInt(Nick);
		for (Users.User use : users.getUser()) {
			if (use.getId() == paramNick) {
				user = use;
			}
		}

		int paramName = Integer.parseInt(Name);
		for (Users.User usee : users.getUser()) {
			if (usee.getId() == paramName) {
				user = usee;

				returnUser.getUser().add(user);
				return returnUser;
			}
		}

		return users;
	}

	@PUT
	// @Path("put")
	@Consumes("application/xml")
	public void setUser(Users str) throws JAXBException, FileNotFoundException,
			SAXException, DatatypeConfigurationException {
		Users users = this.xmlHelp.unmarshalUsers();
		int index = 0;
		boolean proof = false;

		int queryId = str.getUser().get(0).getId();

		for (Users.User use : users.getUser()) {
			if (use.getId() == queryId) {
				index = users.getUser().indexOf(use);
				proof = true;
			}
		}

		if (!proof) {
			Users userList = new Users();

			str.getUser().get(0).setId(getNextId());

			userList.getUser().add(str.getUser().get(0));
			for (Users.User use : users.getUser()) {
				userList.getUser().add(use);
			}
			users = userList;
		} else {
			users.getUser().set(index, str.getUser().get(0));
		}

		this.xmlHelp.marshalUsers(users);
	}

	@POST
	@Consumes("application/xml")
	public void createUser(Users str) throws JAXBException,
			FileNotFoundException, SAXException, DatatypeConfigurationException {
		Users users = this.xmlHelp.unmarshalUsers();
		Users userList = new Users();

		str.getUser().get(0).setId(getNextId());

		userList.getUser().add(str.getUser().get(0));
		for (Users.User use : users.getUser()) {// for each use do
												// users.getUser()
			userList.getUser().add(use);
		}
		this.xmlHelp.marshalUsers(userList);
	}

	@DELETE
	@Path("del/{userID}")
	public void deleteNote(@PathParam("id") String id) throws JAXBException,
			FileNotFoundException, SAXException {
		int paramId = Integer.parseInt(id);
		Users users = this.xmlHelp.unmarshalUsers();
		for (Users.User use : users.getUser()) {
			if (use.getId() == paramId) {
				users.getUser().remove(use);
				break;
			}
		}
		this.xmlHelp.marshalUsers(users);
	}

	public int getNextId() throws JAXBException {
		int count = this.xmlHelp.unmarshalComments().getComment().get(0)
				.getId();
		count++;

		return count;

	}

//	Die bekannte @Path-Annotation enthält nicht einfach nur einen statischen Pfad, sondern beliebig viele Platzhalter in geschweiften Klammern. Der Name des Platzhalters taucht in der Methode wieder auf, nämlich dann, wenn er mit @PathParam an einen Parameter gebunden wird.
	
	@Path("{userID}/song/{song}")
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public Users getUserofSong(@PathParam("userID") String userID,
			@PathParam("song") String song) throws JAXBException {
		User user = new User();

		Users returnUser = new Users();
		Users users = this.xmlHelp.unmarshalUsers();

		int paramUserID = Integer.parseInt(userID);
		for (Users.User use : users.getUser()) {
			if (use.getId() == paramUserID) {
				user = use;
			}
		}

		int paramSong = Integer.parseInt(song);
		for (Users.User usee : users.getUser()) {
			if (usee.getId() == paramSong) {
				user = usee;

				returnUser.getUser().add(user);
				return returnUser;
			}
		}

		return users;

	}
}