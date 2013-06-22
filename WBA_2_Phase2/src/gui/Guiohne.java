package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import org.jivesoftware.smack.XMPPException;



public class Guiohne extends JFrame {
	
	private static Xmpp smack = new Xmpp();
	private static final long serialVersionUID = 1L;
	private static JFrame window;
	private static JList newsList;
	private static DefaultComboBoxModel newsListItems;
	private static DefaultComboBoxModel subscribedListItems;
	private static DefaultComboBoxModel subscribedListItems2;
	private static DefaultComboBoxModel subscribedListItems3;
	private static DefaultComboBoxModel subscribedListItems4;
	private static JList subscribedList;
	private static JList subscribedList2;
	private static JList subscribedList3;
	private static JList subscribedList4;

	private static JButton btn_subscribe;
	private static JButton btn_subscribe2;
	private static JButton btn_subscribe3;
	private static JButton btn_subscribe4;
	private static JButton btn_unsubscribe;
	private static JButton btn_unsubscribe2;
	private static JButton btn_unsubscribe3;
	private static JButton btn_unsubscribe4;
	private static JTextArea ta_messages;
	private static JComboBox cb_allTopics;
	private static JTextArea ta_publishMessage;
	private static JButton btn_publish;
	private static JComboBox cb_allTopics2;
	private static JTextField tf_nodeName;
	private static JButton btn_createnode;
	private static JTextField tf_server;
	private static JTextField tf_port;
	private static JTextField tf_user;
	private static JTextField tf_suche;
	private static JPasswordField tf_password;
	private static JButton btn_login;

	private static JRadioButton btn_rest;
	private static JRadioButton btn_rest11;
	private static JRadioButton btn_rest111;
	private static JRadioButton btn_rest1111;
	private static JRadioButton btn_rest2;
	private static JRadioButton btn_rest22;
	private static JRadioButton btn_rest222;
	private static JRadioButton btn_rest2222;
	private static JRadioButton btn_rest3;
	private static JRadioButton btn_rest33;
	private static JRadioButton btn_rest333;
	private static JRadioButton btn_rest3333;
	private static JRadioButton btn_rest4;
	private static JRadioButton btn_rest44;
	private static JRadioButton btn_rest444;
	private static JRadioButton btn_rest4444;
	
	private static JTextField tf_rest;
	private static JTextField tf_rest2;
	private static JTextField tf_rest3;
	private static JTextField tf_rest4;
	
	public static void main(String[] args){
		startGUI();
	}
	
	public static void startGUI() {

		window = new JFrame(); 

		// Dimensionen analysieren
		Toolkit t = Toolkit.getDefaultToolkit();
		Dimension d = t.getScreenSize();
		int width = (int) d.getWidth();
		int height = (int) d.getHeight();

		// Eigenschaften Festlegen
		window.setBounds((width - 1000) / 2, (height - 750) / 2, 1000, 700);
		window.setTitle("MusicBoX");
		window.setResizable(false);
		window.getContentPane().setLayout(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initComponents(window);

		// Fenster anzeigen
		window.setVisible(true);
	}
	
	
	public static void initComponents(final JFrame window) {
	
		// Titel für Listboxen
			JLabel lbl_allTopics = new JLabel("News deiner abonnierten Kanäle:");
			lbl_allTopics.setLocation(200, 0);
			lbl_allTopics.setSize(300, 50);
			window.add(lbl_allTopics);
			JLabel lbl_subscribedTopics = new JLabel("Abonnierte User:");
			lbl_subscribedTopics.setLocation(200, 200);
			lbl_subscribedTopics.setSize(150, 50);
			window.add(lbl_subscribedTopics);
			JLabel lbl_subscribedInterpreten = new JLabel("Abonnierte Interpreten:");
			lbl_subscribedInterpreten.setLocation(400, 200);
			lbl_subscribedInterpreten.setSize(150, 50);
			window.add(lbl_subscribedInterpreten);
			JLabel lbl_subscribedSongs = new JLabel("Abonnierte Songs:");
			lbl_subscribedSongs.setLocation(600, 200);
			lbl_subscribedSongs.setSize(150, 50);
			window.add(lbl_subscribedSongs);
			JLabel lbl_subscribedGenres = new JLabel("Abonnierte Genres:");
			lbl_subscribedGenres.setLocation(800, 200);
			lbl_subscribedGenres.setSize(150, 50);
			window.add(lbl_subscribedGenres);
	

			// Listbox News
			newsListItems = new DefaultComboBoxModel();
			newsList = new JList(newsListItems);
			newsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			newsList.setEnabled(false);

			JScrollPane scr_newsList = new JScrollPane(newsList);
			scr_newsList.setPreferredSize(new Dimension(150, 200));
			scr_newsList.setBounds(200, 40, 550, 150);
			window.add(scr_newsList);

			// Listbox abonnierter User
			subscribedListItems = new DefaultComboBoxModel();
			subscribedList = new JList(subscribedListItems);
			subscribedList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			subscribedList.setEnabled(false);

			JScrollPane scr_subscribedList1 = new JScrollPane(subscribedList);
			scr_subscribedList1.setPreferredSize(new Dimension(150, 200));
			scr_subscribedList1.setBounds(200, 240, 150, 160);
			window.add(scr_subscribedList1);

			// Listbox abonnierter Interpreten
			subscribedListItems2 = new DefaultComboBoxModel();
			subscribedList2 = new JList(subscribedListItems2);
			subscribedList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			subscribedList2.setEnabled(false);

			JScrollPane scr_subscribedList2 = new JScrollPane(subscribedList2);
			scr_subscribedList2.setPreferredSize(new Dimension(150, 200));
			scr_subscribedList2.setBounds(400, 240, 150, 160);
			window.add(scr_subscribedList2);

			// Listbox abonnierter Songs
			subscribedListItems3 = new DefaultComboBoxModel();
			subscribedList3 = new JList(subscribedListItems2);
			subscribedList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			subscribedList3.setEnabled(false);

			JScrollPane scr_subscribedList3 = new JScrollPane(subscribedList3);
			scr_subscribedList3.setPreferredSize(new Dimension(150, 200));
			scr_subscribedList3.setBounds(600, 240, 150, 160);
			window.add(scr_subscribedList3);

			// Listbox abonnierter Genres
			subscribedListItems4 = new DefaultComboBoxModel();
			subscribedList4 = new JList(subscribedListItems4);
			subscribedList4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			subscribedList4.setEnabled(false);

			JScrollPane scr_subscribedList4 = new JScrollPane(subscribedList4);
			scr_subscribedList4.setPreferredSize(new Dimension(150, 200));
			scr_subscribedList4.setBounds(800, 240, 150, 160);
			window.add(scr_subscribedList4);

			ta_messages = new JTextArea();
			ta_messages.setEditable(false);
			ta_messages.insert("Bitte einloggen ...", 0);

			JScrollPane sp_messages = new JScrollPane(ta_messages);
			sp_messages.setPreferredSize(new Dimension(550, 150));
			sp_messages.setBounds(200, 40, 550, 150);
			window.add(sp_messages);

			JLabel lbl_chooseNode = new JLabel("Suche nach:");
			lbl_chooseNode.setBounds(800, 20, 150, 20);
			window.add(lbl_chooseNode);

			cb_allTopics = new JComboBox(newsListItems);
			cb_allTopics.setBounds(800, 40, 150, 20);
			cb_allTopics.setEnabled(false);
			window.add(cb_allTopics);
			
			tf_suche = new JTextField(255);
			tf_suche.setBounds(800, 70, 150, 20);
			tf_suche.setText("Suchen nach:");
			window.add(tf_suche);
			
			JLabel lbl_radio = new JLabel("User:");
			lbl_radio.setBounds(200, 470, 200, 20);
			lbl_radio.setFont(lbl_radio.getFont().deriveFont(Font.BOLD));
			window.add(lbl_radio);

			tf_rest = new JTextField(255);
			tf_rest.setBounds(200, 490, 120, 20);
			tf_rest.setText("");
			window.add(tf_rest);

			btn_rest = new JRadioButton("GET");
			btn_rest.setBounds(200, 520, 150, 20);
			btn_rest.setSelected(true);
			btn_rest11 = new JRadioButton("PUT", true);
			btn_rest11.setBounds(200, 550, 150, 20);
			btn_rest11.setSelected(true);
			btn_rest111 = new JRadioButton("POST");
			btn_rest111.setBounds(200, 580, 150, 20);
			btn_rest111.setSelected(true);
			btn_rest1111 = new JRadioButton("DELETE");
			btn_rest1111.setBounds(200, 610, 150, 20);
			btn_rest1111.setSelected(true);

			ButtonGroup g = new ButtonGroup();
			g.add(btn_rest);
			g.add(btn_rest11);
			g.add(btn_rest111);
			g.add(btn_rest1111);

			// Rest Interpret
			JLabel lbl_radio2 = new JLabel("Interpret:");
			lbl_radio2.setBounds(400, 470, 200, 20);
			lbl_radio2.setFont(lbl_radio2.getFont().deriveFont(Font.BOLD));
			window.add(lbl_radio2);

			tf_rest2 = new JTextField(255);
			tf_rest2.setBounds(400, 490, 120, 20);
			tf_rest2.setText("");
			window.add(tf_rest2);

			btn_rest2 = new JRadioButton("GET");
			btn_rest2.setBounds(400, 520, 150, 20);
			btn_rest2.setSelected(true);
			btn_rest22 = new JRadioButton("PUT", true);
			btn_rest22.setBounds(400, 550, 150, 20);
			btn_rest22.setSelected(true);
			btn_rest222 = new JRadioButton("POST");
			btn_rest222.setBounds(400, 580, 150, 20);
			btn_rest222.setSelected(true);
			btn_rest2222 = new JRadioButton("DELETE");
			btn_rest2222.setBounds(400, 610, 150, 20);
			btn_rest2222.setSelected(true);

			ButtonGroup g2 = new ButtonGroup();
			g2.add(btn_rest2);
			g2.add(btn_rest22);
			g2.add(btn_rest222);
			g2.add(btn_rest2222);

			// Rest Song
			JLabel lbl_radio3 = new JLabel("Song:");
			lbl_radio3.setBounds(600, 470, 200, 20);
			lbl_radio3.setFont(lbl_radio3.getFont().deriveFont(Font.BOLD));
			window.add(lbl_radio3);

			tf_rest3 = new JTextField(255);
			tf_rest3.setBounds(600, 490, 120, 20);
			tf_rest3.setText("");
			window.add(tf_rest3);

			btn_rest3 = new JRadioButton("GET");
			btn_rest3.setBounds(600, 520, 150, 20);
			btn_rest3.setSelected(true);
			btn_rest33 = new JRadioButton("PUT", true);
			btn_rest33.setBounds(600, 550, 150, 20);
			btn_rest33.setSelected(true);
			btn_rest333 = new JRadioButton("POST");
			btn_rest333.setBounds(600, 580, 150, 20);
			btn_rest333.setSelected(true);
			btn_rest3333 = new JRadioButton("DELETE");
			btn_rest3333.setBounds(600, 610, 150, 20);
			btn_rest3333.setSelected(true);

			ButtonGroup g3 = new ButtonGroup();
			g3.add(btn_rest3);
			g3.add(btn_rest33);
			g3.add(btn_rest333);
			g3.add(btn_rest3333);
			
			//Rest Genre
			JLabel lbl_radio4 = new JLabel("Genre:");
			lbl_radio4.setBounds(800, 470, 200, 20);
			lbl_radio4.setFont(lbl_radio4.getFont().deriveFont(Font.BOLD));
			window.add(lbl_radio4);
			
			tf_rest4 = new JTextField(255);
			tf_rest4.setBounds(800, 490, 120, 20);
			tf_rest4.setText("");
			window.add(tf_rest4);

			btn_rest4 = new JRadioButton("GET");
			btn_rest4.setBounds(800, 520, 150, 20);
			btn_rest4.setSelected(true);
			btn_rest44 = new JRadioButton("PUT", true);
			btn_rest44.setBounds(800, 550, 150, 20);
			btn_rest44.setSelected(true);
			btn_rest444 = new JRadioButton("POST");
			btn_rest444.setBounds(800, 580, 150, 20);
			btn_rest444.setSelected(true);
			btn_rest4444 = new JRadioButton("DELETE");
			btn_rest4444.setBounds(800, 610, 150, 20);
			btn_rest4444.setSelected(true);

			ButtonGroup g4 = new ButtonGroup();
			g4.add(btn_rest4);
			g4.add(btn_rest44);
			g4.add(btn_rest444);
			g4.add(btn_rest4444);

			window.add(btn_rest);
			window.add(btn_rest11);
			window.add(btn_rest111);
			window.add(btn_rest1111);
			window.add(btn_rest2);
			window.add(btn_rest22);
			window.add(btn_rest222);
			window.add(btn_rest2222);
			window.add(btn_rest3);
			window.add(btn_rest33);
			window.add(btn_rest333);
			window.add(btn_rest3333);
			window.add(btn_rest4);
			window.add(btn_rest44);
			window.add(btn_rest444);
			window.add(btn_rest4444);

			// Login
			JLabel lbl_server = new JLabel("Server:");
			lbl_server.setBounds(20, 20, 200, 20);
			lbl_server.setFont(lbl_server.getFont().deriveFont(Font.BOLD));
			window.add(lbl_server);

			tf_server = new JTextField(255);
			tf_server.setBounds(20, 40, 120, 20);
			tf_server.setText("localhost");
			window.add(tf_server);

			JLabel lbl_port = new JLabel("Port:");
			lbl_port.setBounds(20, 60, 100, 20);
			lbl_port.setFont(lbl_port.getFont().deriveFont(Font.BOLD));
			window.add(lbl_port);

			tf_port = new JTextField(4);
			tf_port.setBounds(20, 80, 120, 20);
			tf_port.setText("5222");
			window.add(tf_port);

			JLabel lbl_user = new JLabel("Username:");
			lbl_user.setBounds(20, 160, 100, 20);
			lbl_user.setFont(lbl_user.getFont().deriveFont(Font.BOLD));
			window.add(lbl_user);

			tf_user = new JTextField(255);
			tf_user.setBounds(20, 180, 120, 20);
			tf_user.setText("username");
			window.add(tf_user);

			JLabel lbl_password = new JLabel("Passwort:");
			lbl_password.setBounds(20, 200, 250, 20);
			lbl_password.setFont(lbl_password.getFont().deriveFont(Font.BOLD));
			window.add(lbl_password);

			tf_password = new JPasswordField(255);
			tf_password.setBounds(20, 220, 120, 20);
			tf_password.setText("username");
			window.add(tf_password);
			
			btn_subscribe = new JButton("Subscribe");
			btn_subscribe.setBounds(800, 110, 150, 20);
			btn_subscribe.setEnabled(false);
			
			window.add(btn_subscribe);
			
			btn_subscribe2 = new JButton("Publish");
			btn_subscribe2.setBounds(800, 150, 150, 20);
			btn_subscribe2.setEnabled(false);
			
			window.add(btn_subscribe2);
			
			btn_login = new JButton("LogIn");
			btn_login.setBounds(40, 250, 80, 20);
			btn_login.addActionListener(new ActionListener() 
			{
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean errorFound = false;

					if (tf_server.getText().length() == 0) {
						errorFound = true;
						throwError(window, "Fehler: Bitte Server angegeben!");
					}
					if (tf_port.getText().length() < 2 || tf_port.getText().length() > 5) {
						errorFound = true;
						throwError(window, "Fehler:  Falscher Port!");
					}
					try {
						Integer.parseInt(tf_port.getText());
					} 
					catch (Exception error) {
						errorFound = true;
						throwError(window, "Fehler: Port darf nur numerisch sein!");
					}
					if (tf_user.getText().length() == 0) {
						errorFound = true;
						throwError(window, "Fehler: Bitte Username angegeben!");
					}
					if (tf_password.getText().length() == 0) {
						errorFound = true;
						throwError(window, "Fehler: Bitte Passwort angegeben!");
					}

					if(errorFound == false) {

						if(btn_login.getText() == "LogOut"){
							smack.disconnect();
							doLogout();
						} else {
							smack.setUsername(tf_user.getText());
							smack.setPassword(tf_password.getText());
							smack.setServer(tf_server.getText());
							smack.setPort((Integer.parseInt(tf_port.getText())));
							smack.makeConn();

							if(!smack.doConnect()) errorFound=true;

							if(errorFound == false) {
								try {
									doLogin();
								} catch (XMPPException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								throwError(window,"Login fehlgeschlagen!");
							}
						}


					}
				}
			});
			window.add(btn_login);
			
			
	}

	public static void throwError(JFrame window, String message) {
		JOptionPane.showMessageDialog(window, message, "Fehler!",
				JOptionPane.ERROR_MESSAGE);
	}
	
	private static void doLogin() throws XMPPException {
		btn_login.setText("LogOut");
		btn_subscribe.setEnabled(true);
		btn_unsubscribe.setEnabled(true);
		btn_publish.setEnabled(true);
		btn_createnode.setEnabled(true);

		newsList.setEnabled(true);
		subscribedList.setEnabled(true);

		cb_allTopics.setEnabled(true);
		ta_publishMessage.setEnabled(true);

		tf_nodeName.setEnabled(true);
		cb_allTopics2.setEnabled(true);

		ta_messages.setText("Nachrichten werden geladen ...");}
	
	private static void doLogout() {
		btn_login.setText("LogIn");
		btn_subscribe.setEnabled(false);
		btn_unsubscribe.setEnabled(false);
		btn_publish.setEnabled(false);
		btn_createnode.setEnabled(false);

		newsList.setEnabled(true);
		subscribedList.setEnabled(true);

		cb_allTopics.setEnabled(true);
		ta_publishMessage.setEnabled(true);

		tf_nodeName.setEnabled(true);
		cb_allTopics2.setEnabled(true);

		ta_messages.setText("");

		newsListItems.removeAllElements();

		subscribedListItems.removeAllElements();
	}
}