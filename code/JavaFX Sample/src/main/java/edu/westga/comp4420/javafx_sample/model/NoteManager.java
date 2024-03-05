package edu.westga.comp4420.javafx_sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class NoteManager {
	private ObservableList<Note> notes;
	private static final String NULL_NOTE = "Note cannot be null";
	private static int id = 0;

	public NoteManager() {
		this.notes = FXCollections.observableArrayList();
	}

	public ObservableList<Note> getNotes() {
		return this.notes;
	}

	public ObservableList<String> getNoteTitles() {
		ObservableList<String> titles = FXCollections.observableArrayList();
		for (Note note : this.notes) {
			titles.add(note.getText());
		}
		return titles;
	}

	public ObservableList<String> getUniqueTopics() {
		ObservableList<String> uniqueTopics = FXCollections.observableArrayList();

		for (Note note : this.notes) {
			int size = note.getTopicsObservableList().size();
			for (int i = 0; i < size; i++) {
				String topic = note.getTopicsObservableList().get(i);
				if (!uniqueTopics.contains(topic)) {
					uniqueTopics.add(topic);
				}
			}
		}

		return uniqueTopics;
	}

	public void addNote(Note note) {
		if (note == null) {
			throw new IllegalArgumentException(NULL_NOTE);
		}

		note.setId(id);
		this.notes.add(note);
		id++;
	}

	public void removeNote(Note note) {
		if (note == null) {
			throw new IllegalArgumentException(NULL_NOTE);
		}
		this.notes.remove(note);
	}

	public int getSize() {
		return this.notes.size();
	}

}
