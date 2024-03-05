package edu.westga.comp4420.javafx_sample.model;

import java.util.HashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Note {

	private String text;
	private Set<String> topics;
	private int id;
	private static final String EMPTY_TOPIC = "Topic cannot be empty";
	private static final String EMPTY_TEXT = "Text cannot be empty";

	public Note() {
		this.id = -1;
		this.text = "";
		this.topics = new HashSet<String>();
	}

	public Note(String text) {
		if (text.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_TEXT);
		}

		this.id = -1;
		this.text = text;
		this.topics = new HashSet<String>();
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		if (text.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_TEXT);
		}

		this.text = text;
	}

	public ObservableList<String> getTopicsObservableList() {
		return FXCollections.observableArrayList(this.topics);
	}

	public boolean addTopic(String topic) {
		if (topic.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_TOPIC);
		}

		return this.topics.add(topic);
	}

	public boolean removeTopic(String topic) {
		if (topic.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_TOPIC);
		}

		return this.topics.remove(topic);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
