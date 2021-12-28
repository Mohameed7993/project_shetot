package server;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import common.*;
import controllers.ServerConnectionController;
import javafx.scene.control.Label;

import ocsf.server.*;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */

public class EchoServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	// final public static int DEFAULT_PORT = 5555;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 * 
	 */

	public EchoServer(int port) {
		super(port);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 * @param
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		Message m = (Message) msg;
		String message[];
		try {
			switch (m.getMessageType()) {
			
			case connect:
				message = ((String) m.getObject()).split(" ");
				ServerConnectionController.clients_list.add(new Client(message[0], message[1], message[2]));
				client.sendToClient(new Message(null, null));
				break;
				
			case disconnect:
				message = ((String) m.getObject()).split(" ");
				ServerConnectionController.clients_list.add(new Client(message[0], message[1], message[2]));
				client.sendToClient(new Message(null, null));
				break;
				
			case login:
				message = ((String) m.getObject()).split(" ");
				Users user=mysqlConnection.checkUserLogIn(message[0], message[1]);
				client.sendToClient(new Message(MessageType.login, user));
				break;
				
			case w4cCard:
	            message = ((String) m.getObject()).split(" ");
	            W4C_Card w4c=mysqlConnection.getW4cInformationfromDB(message[0]);
	            client.sendToClient(new Message(MessageType.w4cCard, w4c));
				break;
				
			case scan:
				message = ((String) m.getObject()).split(" ");
				Account account=mysqlConnection.getAccountListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.scan, account));
				break;
				
			case ViewResturants:
				message = ((String) m.getObject()).split(" ");
				ArrayList<Resturants> resturant;
				resturant =mysqlConnection.getResturantsListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.ViewResturants, resturant));
				break;
				
			case ViewTybeMeallist:
				message = ((String) m.getObject()).split(" ");
				ArrayList<TybeMeal> tybemeal;
				tybemeal=mysqlConnection.getTybeMealListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.ViewTybeMeallist,tybemeal));	
				break;
				
			case ViewDishList:
				message=((String)m.getObject()).split(" ");
				ArrayList<Dish> Dish;
				Dish=mysqlConnection.getDishListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.ViewDishList, Dish));
				break;
				
			case ViewSelctionsList:
				message=((String)m.getObject()).split(" ");
				ArrayList<Selection> selection;	
			   	selection=mysqlConnection.getSelectionListFromDB(message[0]);
				client.sendToClient(new Message(MessageType.ViewSelctionsList, selection));
				break;
				
			case bussinessAccounts:
				message=((String)m.getObject()).split(" ");
				Business bussiness=mysqlConnection.getBussinessInformationfromDB(message[0]);
				client.sendToClient(new Message(MessageType.bussinessAccounts, bussiness));
				break;
			case OrdersListToDataBase:
				message=((String)m.getObject()).split(" ");
				mysqlConnection.setOrderinDB(message[0], message[1], message[2], message[3], message[4], message[5], message[6], message[7],message[8], message[9], message[10]);
				client.sendToClient(new Message(MessageType.OrdersListToDataBase, null));
				break;
			case itemsListtoDataBase:
				message=((String)m.getObject()).split(" ");
				mysqlConnection.SetItemsOfOrderinDB(message[0],message[1], message[2], Integer.valueOf(message[3]),Integer.valueOf(message[4]),message[5]);
				client.sendToClient(new Message(MessageType.itemsListtoDataBase, null));
				break;
			case OrderListBuild:
				ArrayList<OrdersList> Order_list;
				Order_list=mysqlConnection.BuildOrderTable();
				client.sendToClient(new Message(MessageType.OrderListBuild, Order_list));
				break;
			case ItemList:
				Integer message1;
				ArrayList<ItemList> Item_list;
				message1 =(Integer)m.getObject();
				Item_list=mysqlConnection.BuildItemList(message1);
				client.sendToClient(new Message(MessageType.ItemList, Item_list));
				break;
			case GetOrder:
				message=((String)m.getObject()).split(" ");
				OrdersList order;
				order =mysqlConnection.getOrder(message[0]);
				client.sendToClient(new Message(MessageType.GetOrder, order));
				break;
			case updateCelling:
				System.out.println(1111);
				message=((String)m.getObject()).split(" ");
				System.out.println(message[0]+" "+message[1]);
				mysqlConnection.updatecelling(message[0],message[1]);
				client.sendToClient(new Message(MessageType.updateCelling, null));
				break;
			case OrderListBuildEdit:
				Integer message2;
				message2 =(Integer)m.getObject();
				Order_list=mysqlConnection.EditBuildOrderTable(message2);
				client.sendToClient(new Message(MessageType.OrderListBuild, Order_list));
				break;
				
			default:
			
				break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		mysqlConnection.connectToDB();

	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}
}
//End of EchoServer class