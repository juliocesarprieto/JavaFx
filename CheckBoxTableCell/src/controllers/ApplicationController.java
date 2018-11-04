package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import models.Person;

public class ApplicationController implements Initializable {

	@FXML
	private TableView<Person> tbView;

	@FXML
	private TableColumn<Person, Boolean> ckBoxTableCol;

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

	CheckBox ckBoxAll;
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
			if(ckBoxAll.isSelected()) {
				tbView.getItems().clear();
				ckBoxAll.setSelected(false);
				btnRemove.setDisable(true);
				btnEdit.setDisable(true);
			}
			else {
				int total =tbView.getItems().size();
				int i =0;
				while(i < total) {
					Person person = tbView.getItems().get(i);

					if(person.getCheck()) {
						tbView.getItems().remove(i);
						total = tbView.getItems().size();
						if(i > 0)
							i--;
					}else
						i++;					

				}
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

		ckBoxTableCol.setCellValueFactory(new PropertyValueFactory<Person, Boolean>("check"));
		ckBoxTableCol.setCellFactory(new Callback<TableColumn<Person,Boolean>, TableCell<Person,Boolean>>() {

			@Override
			public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> arg0) {				

				return new CheckBoxTableColumn();
			}
		});		

		//ADD CHECKBOX HEAD COLUMN
		ckBoxAll = new CheckBox();
		ckBoxAll.setOnAction(e -> {

			for(Person person : tbView.getItems()) {
				person.setCheck(ckBoxAll.isSelected());
			}


			btnRemove.setDisable(!ckBoxAll.isSelected());

			tbView.refresh();
		});

		ckBoxTableCol.setGraphic(ckBoxAll);
		//END ADD CHECKBOX HEAD COLUMN

		ckBoxAll.disableProperty().bind(Bindings.isEmpty(tbView.getItems()));//VERIFY IF TABLE != NULL -> DISABLE FALSE


		tbColName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));		
		tbColLastName.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
		tbColAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));

		tbView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			personSelected = tbView.getSelectionModel().getSelectedItem(); //Item selected
			positionSelected = tbView.getSelectionModel().getSelectedIndex(); //Item position selected 

			btnEdit.setDisable(false);

		});
		tbView.setEditable(true);
	}

	//*-*-*-**-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
	class CheckBoxTableColumn extends CheckBoxTableCell<Person, Boolean> {
		final CheckBox checkBox = new CheckBox();

		public CheckBoxTableColumn() {
			checkBox.setOnAction(evt ->{

				tbView.getItems().get(getIndex()).setCheck(checkBox.isSelected());

				if(!checkBox.isSelected())
					ckBoxAll.setSelected(false);
				else {
					boolean allSelected = true;
					btnRemove.setDisable(false);

					for(Person person : tbView.getItems()) {
						if(!person.getCheck()) {
							allSelected =false;
							break;
						}
					}

					if(allSelected)
						ckBoxAll.setSelected(true);
				}
			});
		}

		@Override 
		public void updateItem(Boolean item, boolean empty) {
			super.updateItem(item, empty);
			if(!empty) {
				checkBox.setSelected(tbView.getItems().get(getIndex()).getCheck());

				if(!checkBox.isSelected())
					ckBoxAll.setSelected(false);
				else {
					boolean allSelected = false;

					for(Person person : tbView.getItems()) {
						if(!person.getCheck()) {
							allSelected =false;
							break;
						}
					}

					if(allSelected)
						ckBoxAll.setSelected(true);
				}

				setGraphic(checkBox);
			}
			else
				setGraphic(null);
		}
	}

}
