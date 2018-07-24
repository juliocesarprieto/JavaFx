package application.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.models.Person;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DetailsController implements Initializable{
	
	@FXML
    private TableView<Person> tbView;

    @FXML
    private TableColumn<Person, String> tbColName;

    @FXML
    private TableColumn<Person, String> tbColLastName;

    @FXML
    private TableColumn<Person, Integer> tbColAge;

    @FXML
    private TableColumn<Person, String> tbColDescription;

    @FXML
    private Button btnAdd, btnUpdate;
    
    Person personUpdate;
    int positionRowUpdate;
    
    @FXML
    void actionBtnAdd(ActionEvent evt) {    	
    	openFormPerson(true);
    }
    
    @FXML
    void actionBtnUpdate(ActionEvent evt) {
    	
    	if(tbView.getItems().size() >0) {
    		personUpdate = tbView.getSelectionModel().getSelectedItem();
    		positionRowUpdate = tbView.getSelectionModel().getSelectedIndex();
    		openFormPerson(false);
    	}
    		
    	
    }
    
    /**
     * Method openFormPerson
     * @param newPerson if newPerson == true then open form clean, else open form with person load
     */
    void openFormPerson(boolean newPerson) {
    	Stage stage = new Stage();
    	FXMLLoader fxml = new FXMLLoader(getClass().getResource("../views/formView.fxml"));
    	if(newPerson)
    		fxml.setController(new FormController(this, null));//IMPORTANT TO COMUNICATION BETWEEN CONTROLLERS
    	else
    		fxml.setController(new FormController(this, personUpdate));//IMPORTANT TO COMUNICATION BETWEEN CONTROLLERS
    		
    	Parent root;
		try {
			root = fxml.load();
			Scene scene = new Scene(root,323,407);
			scene.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
			stage.setScene(scene);
			stage.setTitle("Form Person");
			stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    /**
     * Method add new person
     * 
     * @param person
     */
    public void newPerson(Person person) {    	
    	if(person !=null)
    		tbView.getItems().add(person);    	
    }
    
    
    /**
     * Method update person
     * 
     * @param person
     */
    public void updatePerson(Person person) {
    	
    	tbView.getItems().set(positionRowUpdate, person);
    	tbView.refresh();
    	
    }
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tbColName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		tbColLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		tbColAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
		tbColDescription.setCellValueFactory(new PropertyValueFactory<Person, String>("description"));		
		
	}
    
    

}
