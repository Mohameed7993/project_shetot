package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import common.ItemInCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyCartController implements Initializable {

	ObservableList<ItemInCart> ItemsList; 
	
	public static Integer numberitem=0;
    @FXML
    private ImageView image1;

    @FXML
    private Button BackButton;

    @FXML
    private ImageView image2;

    @FXML
    private ImageView image3;

    @FXML
    private Button paymentButton;

    @FXML
    private Text resturantnametxt;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button Addbutton;

    @FXML
    private TableView<ItemInCart> CartList;

    @FXML
    private TableColumn<ItemInCart,Integer> ItemNumbercol;

    @FXML
    private TableColumn<ItemInCart,String> Tybemealcol;

    @FXML
    private TableColumn<ItemInCart,String> dishescol;

    @FXML
    private TableColumn<ItemInCart,String> extrascol;

    @FXML
    private TableColumn<ItemInCart,Integer> itempricecol;

    @FXML
    void AddAction(ActionEvent event) {

    }

    @FXML
    void BackButtonAction(ActionEvent event) {
    	
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	CustomerHomeController AFrame=new CustomerHomeController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    void PaymentAction(ActionEvent event) {

    }

    @FXML
    void deleteAction(ActionEvent event) {
    	numberitem--;
    }
    

    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
    	resturantnametxt.setText(ChooseResturantController.resturant.getResturantName());
    	ItemNumbercol.setCellValueFactory(new PropertyValueFactory<ItemInCart,Integer>("itemNumber"));
    	Tybemealcol.setCellValueFactory(new PropertyValueFactory<ItemInCart,String>("TypeMeal"));
    	dishescol.setCellValueFactory(new PropertyValueFactory<ItemInCart,String>("Dishes"));
    	extrascol.setCellValueFactory(new PropertyValueFactory<ItemInCart,String>("Extras"));
    	 itempricecol.setCellValueFactory(new PropertyValueFactory<ItemInCart,Integer>("TotalPrice"));
    	 
    	 ItemsList=FXCollections.observableArrayList(ItemDetailsController.itemList);
    	 CartList.setItems(ItemsList);
	}
	


	public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		
		Parent root = FXMLLoader.load(getClass().getResource("/View/MyCart.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("My Cart");
		stage.setScene(scene);

		stage.show();
	}

	

}