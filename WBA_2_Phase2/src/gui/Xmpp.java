package gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverItems;
import org.jivesoftware.smackx.pubsub.AccessModel;
import org.jivesoftware.smackx.pubsub.ConfigureForm;
import org.jivesoftware.smackx.pubsub.FormType;
import org.jivesoftware.smackx.pubsub.Item;
import org.jivesoftware.smackx.pubsub.LeafNode;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.PubSubManager;
import org.jivesoftware.smackx.pubsub.PublishModel;
import org.jivesoftware.smackx.pubsub.SimplePayload;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import gui.XmppData;

public class Xmpp {
	
	private String host;
	private String user;
	private String passw;
	private String server;
	private Integer port;
	private Connection con;
	private PubSubManager psm;
	private ServiceDiscoveryManager sdm;

		public void makeConn() {

			System.out.println(server);
			ConnectionConfiguration config = new ConnectionConfiguration(server,port);
			// pass some connection options
			config.setSASLAuthenticationEnabled(true);
			SASLAuthentication.supportSASLMechanism("PLAIN", 0);

			con = new XMPPConnection(config);
		}

		public boolean doConnect() {
			// let's connect
			try {
				con.connect();
				con.login(user, passw);
				// Create a pubsub manager using an existing Connection
				psm = new PubSubManager(con, "pubsub." + con.getHost());
				System.out.println("LoggedIn");
				return true;
			} catch (XMPPException e) {
				System.err.println("Login failed!");
				return false;
			}
		}
		
		public void discoverServices() throws XMPPException {
			System.out.println("Discovering Services...");
			sdm = ServiceDiscoveryManager.getInstanceFor(con);
			DiscoverItems items = sdm.discoverItems(XmppData.host);
			Iterator<DiscoverItems.Item> iter = items.getItems();
			while (iter.hasNext()) {
				DiscoverItems.Item i = iter.next();
				System.out.println(i.toXML());
			}
		}

		public void discoverNodes() {
			System.out.println("Discovering Nodes...");
			sdm = ServiceDiscoveryManager.getInstanceFor(con);
			DiscoverItems items = null;
			try {
				items = sdm.discoverItems("pubsub." + XmppData.host);
			} catch (XMPPException e) {
				e.printStackTrace();
				System.out.println("Discover failed");
			}
			Iterator<DiscoverItems.Item> iter = items.getItems();
			while (iter.hasNext()) {
				DiscoverItems.Item i = iter.next();
				System.out.println(i.getEntityID());
				System.out.println(i.getNode());
				System.out.println(i.getName());
			}
		}

		public List<String> getNodes() {
			// Create a manager using an existing Connection
			ServiceDiscoveryManager mgr = ServiceDiscoveryManager
					.getInstanceFor(con);
			DiscoverItems items = null;
			try {
				items = mgr.discoverItems("pubsub." + XmppData.host);
			} catch (XMPPException e) {
				e.printStackTrace();
				System.out.println("Discover failed");
			}
			List<String> nodeList = new ArrayList<String>();
			Iterator<DiscoverItems.Item> iter = items.getItems();
			while (iter.hasNext()) {
				DiscoverItems.Item i = iter.next();
				nodeList.add(i.getNode());
			}
			return nodeList;
		}

		public void createNode(String nodeID) {
			// Knoten erstellen und konfigurieren
			LeafNode leaf = getNode(nodeID);
			ConfigureForm form = new ConfigureForm(FormType.submit);
			form.setAccessModel(AccessModel.open);
			form.setDeliverPayloads(true);
			form.setNotifyRetract(true);
			form.setPersistentItems(true);
			form.setPublishModel(PublishModel.open);
			try {
				leaf.sendConfigurationForm(form);
			} catch (XMPPException e) {
				e.printStackTrace();
				System.out.println("sendConfigurationForm failed");
			}
		}

		public void pubItemInNode(String nodeID, String payload) {
			// Knoten "besorgen" und Item hinzufügen
			LeafNode node = getNode(nodeID);
			SimplePayload simplePayload = new SimplePayload(nodeID, "pubsub:"+nodeID, payload);
			PayloadItem<SimplePayload> item = new PayloadItem<SimplePayload>("expiration" + System.currentTimeMillis(), simplePayload);
			node.publish(item);
			try {
				System.out.println("Neues Item im Knoten: "+nodeID+" veröffentlicht. Count: "+node.getItems().size());
			} catch (XMPPException e) {
				e.printStackTrace();
				System.out.println("Fehler beim publishen");
			}
		}

		public void subToNode(String nodeID, ItemEventListener<Item> listener) {
			// Knoten "besorgen" und abonnieren
			LeafNode node = getNode(nodeID);
			node.addItemEventListener(listener);
			try {
				node.subscribe(user+ "@" +XmppData.host);
			} catch (XMPPException e) {
				e.printStackTrace();
				System.out.println("subToNode failed");
			}
		}

		public LeafNode getNode(String nodeID) {
			try {
				return psm.getNode(nodeID);
			} catch (XMPPException e) {
				e.printStackTrace();
				System.out.println("Knoten: "+nodeID+" konnte nicht geladen werden");
				return null;
			}
		}

		public void disconnect() {
			con.disconnect();
		}
		
		/**
		 * @return the username
		 */
		public String getUsername() {
			return user;
		}


		/**
		 * @param username the username to set
		 */
		public void setUsername(String username) {
			this.user = username;
		}


		/**
		 * @return the password
		 */
		public String getPassword() {
			return passw;
		}


		/**
		 * @param password the password to set
		 */
		public void setPassword(String password) {
			this.passw = password;
		}


		/**
		 * @return the server
		 */
		public String getServer() {
			return server;
		}


		/**
		 * @param server the server to set
		 */
		public void setServer(String server) {
			this.server = server;
		}


		/**
		 * @return the port
		 */
		public int getPort() {
			return port;
		}


		/**
		 * @param port the port to set
		 */
		public void setPort(int port) {
			this.port = port;
		}
	}
	