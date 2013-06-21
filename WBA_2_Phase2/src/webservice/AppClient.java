package webservice;

import generated.Users;
import generated.Users.User;
import generated.Interpreten;
import com.sun.jersey.api.client.*;

import java.io.File;
import java.net.URI;
import java.util.List;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.GregorianCalendar;
//import java.util.Locale;
//import java.util.TimeZone;

import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.xml.bind.JAXBException;
//import javax.xml.datatype.DatatypeConfigurationException;
//import javax.xml.datatype.DatatypeFactory;
//import javax.xml.datatype.Duration;
//import javax.xml.datatype.XMLGregorianCalendar;
//import javax.xml.namespace.QName;

public class AppClient {

	private static String host = "http://localhost:4534";
	
	
	public static void main(String[] args) throws InterruptedException {
		
		testPostUser();
		Thread.sleep(500);
		testGetUser();
		Thread.sleep(500);
		testGetInterpret();
	}

	public static void testPostUser() {
		
		String url = host + "/users";
		System.out.println( "POST: " + url );
		WebResource wrs = Client.create().resource(url);
		
		wrs = Client.create().resource(url);
//		User u = new User();
//	    u.setName("Luigi");
//	    u.setDate("1990-01-01");
//	    u.setNick("Lui");
//
//	    ClientResponse r = wrs.type("application/xml").put(ClientResponse.class, u);
//	    System.out.println(r.toString());
//	
		

			ClientResponse response = wrs
					.accept( MediaType.APPLICATION_XML )
					.type( MediaType.APPLICATION_XML )
					.entity( new File( "Post/application/xml" ) )
					.post( ClientResponse.class );

			if ( response.getStatus() != 201 ) {
				System.err.println( "Failed: HTTP error code: " + response.getStatus() );
				return;
			}

			URI output = response.getLocation();
			System.out.println( "Output from Server..." );
			System.out.println( output.toString() );
		}
	    
	

	
	public static void testGetUser() {

		String url = host + "/users";

		WebResource wrs = Client.create().resource(url);

		Users returnUsers = new Users();
		returnUsers = (wrs.accept("application/xml").get(Users.class));

		for (int i = 0; i <= 1; i++) {
			System.out.println(returnUsers.getUser().get(i).getId());
			System.out.println(returnUsers.getUser().get(i).getNick());
			System.out.println(returnUsers.getUser().get(i).getName());
			System.out.println(returnUsers.getUser().get(i).getDate());
		}

	}

	public static void testGetInterpret() {

		String url = host + "/interpreten";

		WebResource wrs = Client.create().resource(url);

		Interpreten returnInterpreten = new Interpreten();
		returnInterpreten = (wrs.accept("application/xml")
				.get(Interpreten.class));

		for (int i = 0; i <= 1; i++) {
			System.out.println(returnInterpreten.getInterpret().get(i)
					.getName());
			System.out.println(returnInterpreten.getInterpret().get(i)
					.getDate());
			System.out.println(returnInterpreten.getInterpret().get(i)
					.getGenre());
			System.out.println(returnInterpreten.getInterpret().get(i)
					.getDescription());
		}
	}


}