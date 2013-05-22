package ressourcen;
import generiert.Stundenplaene;
import generiert.Stundenplaene.Stundenplan;

import javax.ws.rs.*;
import javax.xml.bind.JAXBException;

import org.xml.sax.SAXException;

import help.*;

@Path("/stundenplaene")
public class StundenplaeneR {
	public XMLWorker xmlWork;

    public StundenplaeneR() throws JAXBException{
		this.xmlWork = new XMLWorker();
	}
    
    /**
	 * Stundenplanresource wird hiermit angefordert
	 * @return Resource
	 * @throws SAXException 
	 * @throws JAXBException 
	 */
	@GET 
	@Produces("application/xml")
	public Stundenplaene getStundenplaene() throws JAXBException, SAXException{
		return this.xmlWork.unmarshalStundenplaene();
	}

	@GET
	@Path("/stundenplan/{id}")
	@Produces("application/xml")
	public Stundenplaene getStundenplan(@PathParam("id") int S_id) throws JAXBException{
		Stundenplan stplan = new Stundenplan();

		Stundenplaene returnStundenplan = new Stundenplaene();
		Stundenplaene stplaene = this.xmlWork.unmarshalStundenplaene();

		int paramId = S_id;
		for (Stundenplaene.Stundenplan stp: stplaene.getStundenplan()){
			if(stp.getSId() == paramId){
				stplan=stp;

				returnStundenplan.getStundenplan().add(stplan);
				return returnStundenplan;
			}
		}

		return returnStundenplan;
	}
}