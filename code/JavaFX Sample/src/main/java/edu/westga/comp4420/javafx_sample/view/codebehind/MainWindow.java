package edu.westga.comp4420.javafx_sample.view.codebehind;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
//import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;

import edu.westga.comp4420.javafx_sample.Main;
import edu.westga.comp4420.javafx_sample.model.Note;
import edu.westga.comp4420.javafx_sample.model.NoteManager;

/**
 * CodeBehind To Handle Processing for the MainWindow
 *
 * @author Comp 4420
 * @version Spring 2024
 */
public class MainWindow {

	@FXML
	private ListView<Note> notesListView;

	private static NoteManager noteManager;

	@FXML
	private ListView<String> topicsListView;

	@FXML
	private Button noteButton;

	@FXML
	private Button remoteNoteButton;

	@FXML
	void initialize() {
		assert this.noteButton != null
				: "fx:id=\"noteButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
		if (noteManager == null) {
			noteManager = new NoteManager();
		}
		this.refreshNoteListView();
	}

	private void refreshNoteListView() {
		this.notesListView.setItems(noteManager.getNotes());
		this.topicsListView.setItems(noteManager.getUniqueTopics());
	}

	@FXML
	void createNote(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(Main.NOTE_DIALOG));
			Parent parent = loader.load();
			NoteWindow controller = loader.getController();
			Note selectedNote = this.notesListView.getSelectionModel().getSelectedItem();
			if (selectedNote == null) {
				Note note = new Note();
				noteManager.addNote(note);
				controller.initialize(note);
			} else {
				controller.initialize(selectedNote);
			}
			Scene scene = new Scene(parent);
			Stage noteStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			noteStage.setTitle(Main.NOTE_TITLE);
			noteStage.setScene(scene);
			noteStage.show();
		} catch (IOException ex) {
			Alert alertWindow = new Alert(Alert.AlertType.ERROR);
			alertWindow.setContentText("Unable to launch Note Window");
			alertWindow.showAndWait();
		}
	}

	@FXML
	void removeNote() {
		try {
			Note selectedNote = this.notesListView.getSelectionModel().getSelectedItem();
			if (selectedNote == null) {
				throw new Exception();
			}
			noteManager.removeNote(selectedNote);
			this.refreshNoteListView();
		} catch (Exception ex) {
			this.showNoNoteSelectedError();
		}

	}

	private void showNoNoteSelectedError() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("No note was selected. Please select a note before clicking the remove button.");
		alert.showAndWait();
	}
}
