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

import generated.Interpreten;
import generated.Interpreten.Interpret;
import webservice.XmlHelper;

@Path("users/")
public class InterpretenRes {

	public XmlHelper xmlHelp;

	public InterpretenRes() throws JAXBException {
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
	public Interpreten getUsers() throws JAXBException, SAXException{
		return this.xmlHelp.unmarshalInterpreten();
	}

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public Interpreten getInterpret(@PathParam("id") String id) throws JAXBException{
		Interpret interpret = new Interpret();

		Interpreten returnInterpret= new Interpreten();
		Interpreten interpreten = this.xmlHelp.unmarshalInterpreten();

		int paramId = Integer.parseInt(id);
		for (Interpreten.Interpret use: interpreten.getInterpret()){
			if(use.getId() == paramId){
				interpret=use;

				returnInterpret.getInterpret().add(interpret);
				return returnInterpret;
			}
		}

		return interpreten;
	}

	@PUT
	//@Path("put")
	@Consumes("application/xml")
	public void setInterpret(Interpreten str) throws JAXBException, FileNotFoundException, SAXException, DatatypeConfigurationException{
		Interpreten interpreten = this.xmlHelp.unmarshalInterpreten();
		int index = 0;
		boolean proof = false;

		int queryId = str.getInterpret().get(0).getId();

		for (Interpreten.Interpret use: interpreten.getInterpret()){
			if(use.getId() == queryId){
				index = interpreten.getInterpret().indexOf(use);
				proof=true;
			}
		}

		if(!proof){
			Interpreten userList = new Interpreten();

			str.getInterpret().get(0).setId(getNextId());

			userList.getInterpret().add(str.getInterpret().get(0));
			for (Interpreten.Interpret use: interpreten.getInterpret()){
				userList.getInterpret().add(use);
			}
			interpreten = userList;
		} else{
			interpreten.getInterpret().set(index, str.getInterpret().get(0));
		}

		this.xmlHelp.marshalInterpreten(interpreten);
	}


	@POST
	//@Path("post")
	@Consumes("application/xml")
	public void createInterpret(Interpreten str) throws JAXBException, FileNotFoundException, SAXException, DatatypeConfigurationException{
		Interpreten interpreten = this.xmlHelp.unmarshalInterpreten();
		Interpreten userList = new Interpreten();

		str.getInterpret().get(0).setId(getNextId());

		userList.getInterpret().add(str.getInterpret().get(0));
		for (Interpreten.Interpret use: interpreten.getInterpret()){
			userList.getInterpret().add(use);
		}
		this.xmlHelp.marshalInterpreten(userList);
	}

	@DELETE
	@Path("del/{id}")
	public void deleteNote(@PathParam("id") String id) throws JAXBException, FileNotFoundException, SAXException{
		int paramId = Integer.parseInt(id);
		Interpreten interpreten = this.xmlHelp.unmarshalInterpreten();
		for (Interpreten.Interpret use: interpreten.getInterpret()){
			if(use.getId() == paramId){
				interpreten.getInterpret().remove(use);
				break;
			}
		}
		this.xmlHelp.marshalInterpreten(interpreten);	
	}




	public int getNextId() throws JAXBException{
		int count = this.xmlHelp.unmarshalComments().getComment().get(0).getId();
		count++;

		return count;

	}


}