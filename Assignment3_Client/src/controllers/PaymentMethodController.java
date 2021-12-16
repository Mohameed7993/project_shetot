package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import common.Address;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentMethodController implements Initializable{
	
	public static int OrderDate;
	
	public static String DeleiveryType=null;
	
	public static Address address;
	
    @FXML
    private ImageView image1; 

    @FXML
    private Button BackButton;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private Text deleiveryservicetxt;

    @FXML
    private Text enterAdresstxt;

    @FXML
    private TextArea CityField;

    @FXML
    private Text streettxt;

    @FXML
    private Text housenumbertxt;

    @FXML
    private Text citytxt;

    @FXML
    private TextArea streetField;

    @FXML
    private TextArea houseNumberField;

    @FXML
    private Text paymenttxt;

    @FXML
    private Button payprivatebutton;

    @FXML
    private Button SharedDelButton;

    @FXML
    private Button takeawaybutton;

    @FXML
    private Button deleiveryButton;

    @FXML
    private Button paybussinessbutton;

    @FXML
    private Button robotbutton;

    @FXML
    private Text requesteddatetxt;

    @FXML
    private TextArea orderdateField;

    @FXML
    void BackButtonAction(ActionEvent event) {
    	
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	MyCartController AFrame=new MyCartController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void SharedDelButtonAction(ActionEvent event) {
    	DeleiveryType=("Shared-deleivery");
    	enterAdresstxt.setVisible(true);
		citytxt.setVisible(true);
		streettxt.setVisible(true);
		housenumbertxt.setVisible(true);
		CityField.setVisible(true);
		streetField.setVisible(true);
		houseNumberField.setVisible(true);
    }

    @FXML
    void deleiveryButtonAction(ActionEvent event) {
    	DeleiveryType=("Deleivery");
    	enterAdresstxt.setVisible(true);
		citytxt.setVisible(true);
		streettxt.setVisible(true);
		housenumbertxt.setVisible(true);
		CityField.setVisible(true);
		streetField.setVisible(true);
		houseNumberField.setVisible(true);
	
    }

    @FXML
    void paybussinessbuttonAction(ActionEvent event) {
    	if(orderdateField.getText().equals("")||CityField.getText().equals("")||streetField.getText().equals("")||
    			houseNumberField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")||
    			CityField.getText().matches("[a-zA-Z_]+")||streetField.getText().matches("[a-zA-Z_]+")||
    			houseNumberField.getText().matches("[a-zA-Z_]+")) {
       
    	    Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Error");
            a.setHeaderText("your details that you insert is Wrong!");
            a.showAndWait();
    		}
    		}
 
    	

    @FXML
    void payprivatebuttonAction(ActionEvent event) {
    	if(orderdateField.getText().equals("")||orderdateField.getText().matches("[a-zA-Z_]+")) {
    		 Alert a = new Alert(AlertType.ERROR);
             a.setContentText("Error");
             a.setHeaderText("your details that you insert is Wrong!");
             a.showAndWait();
    	}
    }

    @FXML
    void robotbuttonAction(ActionEvent event) {
    	DeleiveryType=("Robot");
    	enterAdresstxt.setVisible(false);
		citytxt.setVisible(false);
		streettxt.setVisible(false);
		housenumbertxt.setVisible(false);
		CityField.setVisible(false);
		streetField.setVisible(false);
		houseNumberField.setVisible(false);
    }

    @FXML
    void takeawaybuttonAction(ActionEvent event) {
    	DeleiveryType=("Take-Away");
    	enterAdresstxt.setVisible(false);
		citytxt.setVisible(false);
		streettxt.setVisible(false);
		housenumbertxt.setVisible(false);
		CityField.setVisible(false);
		streetField.setVisible(false);
		houseNumberField.setVisible(false);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(ChatClient.w4ccard.getAccountType().equals("private")) {
			SharedDelButton.setVisible(false);
			paybussinessbutton.setVisible(false);
		}
		enterAdresstxt.setVisible(false);
		citytxt.setVisible(false);
		streettxt.setVisible(false);
		housenumbertxt.setVisible(false);
		CityField.setVisible(false);
		streetField.setVisible(false);
		houseNumberField.setVisible(false);
		
		OrderDate=Integer.valueOf(orderdateField.getText());
		address.setCity(CityField.getText());
		address.setStreet(streetField.getText());
		address.setHouseNumber(houseNumberField.getText());
		
		
	}

	public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/PaymentMethod.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Payment");
		stage.setScene(scene);

		stage.show();
	}

}
