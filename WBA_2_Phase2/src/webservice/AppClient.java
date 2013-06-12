package webservice;

import com.sun.jersey.api.client.*;

public class AppClient {
	public static void main(String[] args) {
		String url = "http://localhost:4434";

	      url = url + "/musikseite";

	      WebResource wrs = Client.create().resource(url);

	      System.out.println( wrs.accept("application/xml").get(String.class));
	}
}