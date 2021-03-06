package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import common.ItemInCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MyCartController implements Initializable {
		
	public static Stage stage1=new Stage();
	
	public static String yourOrderIsInResturant=null;
	
	public static boolean Flag;
	
	ObservableList<ItemInCart> ItemsList; 
	
	public static ItemInCart ItemSelected;
	
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
    private TableColumn<ItemInCart,String> Tybemealcol;

    @FXML
    private TableColumn<ItemInCart,String> dishescol;

    @FXML
    private TableColumn<ItemInCart,String> extrascol;

    @FXML
    private TableColumn<ItemInCart,Integer> itempricecol;
    
    @FXML
    private TableColumn<ItemInCart,Integer> QuantityCol;

    @FXML
    private ComboBox<String> Comb;

    @FXML
    void AddAction(ActionEvent event) {
    	MyCartController.Flag=true;
    	if(ItemDetailsController.itemList.size()!=0) {
    	Stage stage = new Stage() ;//((Node) event.getSource()).getScene().getWindow();// get stage
    	TybeMealController AFrame=new TybeMealController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
    	else {
    		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
        	ChooseResturantController AFrame=new ChooseResturantController();
    		try {
    			AFrame.start(stage);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
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
    	if(ItemDetailsController.itemList.size()!=0) {
    	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
    	PaymentMethodController AFrame=new PaymentMethodController();
		try {
			AFrame.start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    	else {
    		Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Error");
            a.setHeaderText("make you Items");
            a.showAndWait();
    	}
    }

    @FXML
    void deleteAction(ActionEvent event) {
    	if(CartList.getSelectionModel().getSelectedItem()!=null)
    	{  		
    		ItemSelected =CartList.getSelectionModel().getSelectedItem();
    		ItemDetailsController.itemList.remove(ItemSelected);
    		 ItemsList=FXCollections.observableArrayList(ItemDetailsController.itemList);
        	 CartList.setItems(ItemsList);
    		
    	}
    	else {
    		Alert a = new Alert(AlertType.ERROR);
            a.setContentText("Error");
            a.setHeaderText("should you Select The item that you want to delete");
            a.showAndWait();
    	}
    	
    	
    }
    
    @FXML
    void Edit(ActionEvent event) {
    	if(CartList.getSelectionModel().getSelectedItem()!=null)
    	{
	    	String EditChoose = (String)Comb.getValue();
	    	ItemSelected =CartList.getSelectionModel().getSelectedItem();
	    	Stage stage = new Stage() ;//((Node) event.getSource()).getScene().getWindow();// get stage
	    	if(EditChoose.equals("Optionals"))
	    		{
		        	MyCartController.Flag=false;
		    		OptionalSelectionController AFrame=new OptionalSelectionController();
		    		try {
		    			AFrame.start(stage);
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		    	}
    	else if(EditChoose.equals("Quantity"))
    	{
        	MyCartController.Flag=false;
    		ItemDetailsController AFrame=new ItemDetailsController();
    		try {
    			AFrame.start(stage);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    else 
    	{
   		Alert a = new Alert(AlertType.ERROR);
        a.setContentText("Error");
        a.setHeaderText("should you Select The item that you want to delete");
        a.showAndWait();
     	Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();// get stage
     	MyCartController AFrame=new MyCartController();
    		try {
    			AFrame.start(stage);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    }
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	ObservableList<String> List =FXCollections.observableArrayList("Optionals","Quantity");
    	Comb.setItems(List);
    	resturantnametxt.setText(ChooseResturantController.resturant.getResturantName());
    	Tybemealcol.setCellValueFactory(new PropertyValueFactory<ItemInCart,String>("TypeMeal"));
    	dishescol.setCellValueFactory(new PropertyValueFactory<ItemInCart,String>("Dishes"));
    	extrascol.setCellValueFactory(new PropertyValueFactory<ItemInCart,String>("Extras"));
    	itempricecol.setCellValueFactory(new PropertyValueFactory<ItemInCart,Integer>("TotalPrice"));
    	QuantityCol.setCellValueFactory(new PropertyValueFactory<ItemInCart,Integer>("quantity"));
    	ItemsList=FXCollections.observableArrayList(ItemDetailsController.itemList);
    	CartList.setItems(ItemsList); 
    	CartList.setOnMousePressed(new EventHandler<MouseEvent>()
    	{
    		@Override
    		public void handle(MouseEvent arg0)
    		{
    			if(CartList.getSelectionModel().getSelectedItem()!=null)
    				ItemSelected=CartList.getSelectionModel().getSelectedItem();
    		}
    	});
    	 
	}
	


	public void start(Stage stage)  throws Exception {
		// TODO Auto-generated method stub
		stage1=stage;
		Parent root = FXMLLoader.load(getClass().getResource("/View/MyCart.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("My Cart");
		stage.setScene(scene);

		stage.show();
	}
}