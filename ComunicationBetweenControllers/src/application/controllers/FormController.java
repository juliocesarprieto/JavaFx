package application.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FormController implements Initializable {
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
    private TextField txtName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtAge;

    @FXML
    private TextArea txtDescription;

    @FXML
    private Button btnSave;
    
    private DetailsController detailsController;
    private Person person;
    boolean updatePerson;
    
    
    public FormController(DetailsController detailsController, Person person) {
		
    	this.detailsController = detailsController;
		this.person = person;
	}

    @FXML
    void actionBtnSave(ActionEvent event) {
    	//Create new Person
    	if(!updatePerson) {
    		Person newPerson = new Person();
        	newPerson.setName(txtName.getText());
        	newPerson.setLastName(txtLastName.getText());
        	newPerson.setAge(Integer.parseInt(txtAge.getText()));
        	newPerson.setDescription(txtDescription.getText());
        	
        	//Call newPerson(person) from DetailsController class
        	detailsController.newPerson(newPerson);//
    	}
    	else {// update person
    		person.setName(txtName.getText());
    		person.setLastName(txtLastName.getText());
    		person.setAge(Integer.parseInt(txtAge.getText()));
    		person.setDescription(txtDescription.getText());
    		
    		//Call method updatePerson from DetailsController class
    		detailsController.updatePerson(person);
    	}
    	
    	
    	//close window Form
    	Stage stage = (Stage) anchorPane.getScene().getWindow();
    	stage.close();
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				
		if(person !=null) {// load person when person != null
			
			txtName.setText(person.getName());
			txtLastName.setText(person.getLastName());
			txtAge.setText(String.valueOf(person.getAge()));
			txtDescription.setText(person.getDescription());
			updatePerson = true;//to know the action button Save
		}
		
		
	}


}
