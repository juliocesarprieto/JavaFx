package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Person;

public class ApplicationController implements Initializable {

	@FXML
	private TableView<Person> tbView;

	@FXML
	private TableColumn<Person, String> tbColName;

	@FXML
	private TableColumn<Person, String> tbColLastName;

	@FXML
	private TableColumn<Person, Integer> tbColAge;

	@FXML
	private TextField txtName, txtLastName, txtAge;

	@FXML
	private Button btnAdd, btnEdit, btnRemove;	

	Person personSelected;
	int positionSelected;


	@FXML
	void actionBtnAdd(ActionEvent event) {
		if(personSelected ==null) {
			Person person = new Person(
					txtName.getText(),
					txtLastName.getText(),
					Integer.parseInt(txtAge.getText()));

			if(person !=null)
				tbView.getItems().add(person);	
		}else {
			personSelected.setName(txtName.getText());
			personSelected.setLastName(txtLastName.getText());
			personSelected.setAge(Integer.parseInt(txtAge.getText()));
			tbView.getItems().set(positionSelected, personSelected);

			personSelected = null;
			positionSelected = 0;

			tbView.refresh();
		}

		cleanText();
	}

	@FXML
	void actionBtnEdit(ActionEvent event) {
		if(personSelected != null) {
			txtName.setText(personSelected.getName());
			txtLastName.setText(personSelected.getLastName());
			txtAge.setText(String.valueOf(personSelected.getAge()));			
		}
	}

	@FXML
	void actionBtnRemove(ActionEvent event) {
		if(tbView.getItems() !=null) {
			tbView.getItems().remove(positionSelected);
			if(tbView.getItems().size() ==0) {
				btnRemove.setDisable(true); 
				btnEdit.setDisable(true);
			}
		}		
	}

	public void cleanText() {
		txtName.setText("");
		txtLastName.setText("");
		txtAge.setText("");
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		tbColName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));		
		tbColLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		tbColAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));

		tbView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			personSelected = tbView.getSelectionModel().getSelectedItem(); //Item selected
			positionSelected = tbView.getSelectionModel().getSelectedIndex(); //Item position selected 

			btnRemove.setDisable(false); 
			btnEdit.setDisable(false);

		});
	}	

}
