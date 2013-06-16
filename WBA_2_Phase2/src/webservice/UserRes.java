package webservice;

import java.io.FileNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;

import org.xml.sax.SAXException;

import generated.Users;
import generated.Users.User;
import webservice.XmlHelper;

@Path("/users")
public class UserRes {

	public XmlHelper xmlHelp;

	public UserRes() throws JAXBException {
		this.xmlHelp = new XmlHelper();
	}


	/**
	 * fordert angegebene Ressource vom Server an
	 * @param topic
	 * @return Ressource
	 * @throws SAXException 
	 * @throws JAXBException 
	 */
	@GET 
	@Produces("application/xml")
	public Users getUsers() throws JAXBException, SAXException{
		return this.xmlHelp.unmarshalUsers();
	}

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Users getUser(@PathParam("id") String id) throws JAXBException{
		User user = new User();

		Users returnUser = new Users();
		Users users = this.xmlHelp.unmarshalUsers();

		int paramId = Integer.parseInt(id);
		for (Users.User use: users.getUser()){
			if(use.getId() == paramId){
				user=use;

				returnUser.getUser().add(user);
				return returnUser;
			}
		}

		return users;
	}

	@PUT
	//@Path("put")
	@Consumes("application/xml")
	public void setUser(Users str) throws JAXBException, FileNotFoundException, SAXException, DatatypeConfigurationException{
		Users users = this.xmlHelp.unmarshalUsers();
		int index = 0;
		boolean proof = false;

		int queryId = str.getUser().get(0).getId();

		for (Users.User use: users.getUser()){
			if(use.getId() == queryId){
				index = users.getUser().indexOf(use);
				proof=true;
			}
		}

		if(!proof){
			Users userList = new Users();

			str.getUser().get(0).setId(getNextId());

			userList.getUser().add(str.getUser().get(0));
			for (Users.User use: users.getUser()){
				userList.getUser().add(use);
			}
			users = userList;
		} else{
			users.getUser().set(index, str.getUser().get(0));
		}

		this.xmlHelp.marshalUsers(users);
	}


	@POST
	//@Path("post")
	@Consumes("application/xml")
	public void createUser(Users str) throws JAXBException, FileNotFoundException, SAXException, DatatypeConfigurationException{
		Users users = this.xmlHelp.unmarshalUsers();
		Users userList = new Users();

		str.getUser().get(0).setId(getNextId());

		userList.getUser().add(str.getUser().get(0));
		for (Users.User use: users.getUser()){
			userList.getUser().add(use);
		}
		this.xmlHelp.marshalUsers(userList);
	}

	@DELETE
	@Path("del/{id}")
	public void deleteNote(@PathParam("id") String id) throws JAXBException, FileNotFoundException, SAXException{
		int paramId = Integer.parseInt(id);
		Users users = this.xmlHelp.unmarshalUsers();
		for (Users.User use: users.getUser()){
			if(use.getId() == paramId){
				users.getUser().remove(use);
				break;
			}
		}
		this.xmlHelp.marshalUsers(users);	
	}




	public int getNextId() throws JAXBException{
		int count = this.xmlHelp.unmarshalComments().getComment().get(0).getId();
		count++;

		return count;

	}


}