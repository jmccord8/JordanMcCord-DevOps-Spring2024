package edu.westga.comp4420.javafx_sample.test.model.note;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import edu.westga.comp4420.javafx_sample.model.Note;

class TestNote {

	@Test
	void testValidNote() {
		Note note = new Note();

		assertEquals("", note.getText());
		assertEquals(0, note.getTopicsObservableList().size());
	}

	@ParameterizedTest
	@ValueSource(strings = { "Hello World" })
	void testValidNote(String text) {
		Note note = new Note(text);

		assertEquals(text, note.getText());
		assertEquals(0, note.getTopicsObservableList().size());
	}

	@Test
	void testEmptyNoteText() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Note("");
		});
	}

	@Test
	void testSetEmptyNoteText() {
		Note note = new Note("Something");

		assertThrows(IllegalArgumentException.class, () -> {
			note.setText("");
		});
	}

	@Test
	void testSetNoteText() {
		Note note = new Note();

		note.setText("Test");

		assertEquals("Test", note.getText());
	}

	@Test
	void testAddValidTopic() {
		Note note = new Note();

		note.addTopic("Hello World");

		assertEquals(1, note.getTopicsObservableList().size());
		assertTrue(note.getTopicsObservableList().contains("Hello World"));
	}

	@Test
	void testAddSameTopic() {
		Note note = new Note();

		note.addTopic("Hello World");
		note.addTopic("Hello World");

		assertEquals(1, note.getTopicsObservableList().size());
		assertTrue(note.getTopicsObservableList().contains("Hello World"));
	}

	@Test
	void testAddEmptyTopic() {
		Note note = new Note("Something");

		assertThrows(IllegalArgumentException.class, () -> {
			// ACT - unit being tested (generates exception)
			note.addTopic("");
		});
	}

	@Test
	void testRemoveTopic() {
		Note note = new Note();
		note.addTopic("Hello World");
		note.addTopic("New Topic");
		note.addTopic("Fake Topic");
		note.addTopic("Weird Topic");
		assertEquals(4, note.getTopicsObservableList().size());

		note.removeTopic("Fake Topic");

		assertFalse(note.getTopicsObservableList().contains("Fake Topic"));
		assertEquals(3, note.getTopicsObservableList().size());
	}

	@Test
	void testRemoveEmptyTopic() {
		Note note = new Note("Something");

		assertThrows(IllegalArgumentException.class, () -> {
			note.removeTopic("");
		});
	}

	@Test
	void testToString() {
		Note note = new Note("Hello");

		assertEquals("Hello", note.toString());
	}
}
