package edu.westga.comp4420.javafx_sample.view.codebehind;

import java.io.IOException;
import java.util.Optional;

import edu.westga.comp4420.javafx_sample.Main;
import edu.westga.comp4420.javafx_sample.model.Note;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
//import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * CodeBehind To Handle Processing for the NoteWindow
 *
 * @author Comp 4420
 * @version Spring 2024
 */
public class NoteWindow {

	@FXML
	private TextArea noteTextArea;

	@FXML
	private ListView<String> topicListView;

	@FXML
	private Button homeButton;

	@FXML
	private Button addTopicButton;

	@FXML
	private Button removeTopicButton;

	@FXML
	private Button saveButton;

	private Note activeNote;

	@FXML
	void initialize(Note note) {
		try {
			assert this.homeButton != null
					: "fx:id=\"homeButton\" was not injected: check your FXML file 'NoteWindow.fxml'.";
			assert this.addTopicButton != null
					: "fx:id=\"addButton\" was not injected: check your FXML file 'NoteWindow.fxml'.";
			// Create MainWindow and send it back to the MainWindow
			this.activeNote = note;
			this.setNoteArea(this.activeNote.getText());
			this.refreshTopicList();
			this.homeButton.setOnAction((event) -> {
				try {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(Main.class.getResource(Main.GUI_RESOURCE));
					loader.load();
					Parent parent = loader.getRoot();
					Scene scene = new Scene(parent);
					Stage noteStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					noteStage.setTitle(Main.WINDOW_TITLE);
					noteStage.setScene(scene);
					noteStage.show();
				} catch (IOException ex) {
					Alert alertWindow = new Alert(Alert.AlertType.ERROR);
					alertWindow.setContentText("Unable to launch Note Window");
					alertWindow.showAndWait();
				}
			});

		} catch (Exception ex) {
			this.displayError(ex.getMessage());
		}
	}

	@FXML
	void addTopic() {
		try {
			TextInputDialog topicDialog = new TextInputDialog();
			topicDialog.setTitle("Add Topic");
			topicDialog.setHeaderText("Enter a topic");
			topicDialog.setContentText("Topic");
			Optional<String> result = topicDialog.showAndWait();
			if (result.isPresent()) {
				if (this.activeNote.addTopic(result.get())) {
					this.refreshTopicList();
				} else {
					this.displayError("You cannot add a duplicate topic.");
				}
			}
		} catch (IllegalArgumentException ex) {
			this.displayError(ex.getMessage());
		}
	}

	@FXML
	void removeTopic() {
		try {
			String selectedTopic = this.topicListView.getSelectionModel().getSelectedItem();
			if (selectedTopic != null) {
				this.activeNote.removeTopic(selectedTopic);
			} else {
				throw new Exception();
			}
			this.refreshTopicList();
		} catch (IllegalArgumentException ex) {
			this.displayError(ex.getMessage());
		} catch (Exception ex) {
			this.displayError("No topic was selected. Please select a topic before clicking the remove button.");
		}
	}

	@FXML
	void saveNote() {
		this.activeNote.setText(this.noteTextArea.getText());
	}

	public void setActiveNote(Note note) {
		this.activeNote = note;
	}

	public void setNoteArea(String text) {
		this.noteTextArea.setText(text);
	}

	private void refreshTopicList() {
		this.topicListView.setItems(this.activeNote.getTopicsObservableList());
	}

	private void displayError(String text) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.showAndWait();
	}
}
