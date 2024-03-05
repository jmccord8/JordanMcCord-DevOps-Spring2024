package edu.westga.comp4420.javafx_sample.test.model.note;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.comp4420.javafx_sample.model.Note;
import edu.westga.comp4420.javafx_sample.model.NoteManager;
import javafx.collections.ObservableList;

class TestNoteManager {

	@Test
	void testValidNoteManager() {
		NoteManager nm = new NoteManager();

		assertEquals(0, nm.getSize());
	}

	@Test
	void testAddNote() {
		NoteManager nm = new NoteManager();
		Note note = new Note();

		nm.addNote(note);

		assertEquals(1, nm.getSize());
	}

	@Test
	void testAddNoteNull() {
		NoteManager nm = new NoteManager();

		assertThrows(IllegalArgumentException.class, () -> {
			// ACT - unit being tested (generates exception)
			nm.addNote(null);
		});
	}

	@Test
	void testRemoveNote() {
		NoteManager nm = new NoteManager();
		Note note = new Note("Something");
		nm.addNote(note);
		assertEquals(1, nm.getSize());

		nm.removeNote(note);

		assertEquals(0, nm.getSize());
	}

	@Test
	void testRemoveNoteNull() {
		NoteManager nm = new NoteManager();

		assertThrows(IllegalArgumentException.class, () -> {
			// ACT - unit being tested (generates exception)
			nm.removeNote(null);
		});
	}

	@Test
	void testGetNoteTitles() {
		NoteManager nm = new NoteManager();
		Note note1 = new Note("Hello");
		Note note2 = new Note("World");
		Note note3 = new Note("Fake");

		nm.addNote(note1);
		nm.addNote(note2);
		nm.addNote(note3);

		ObservableList<String> titles = nm.getNoteTitles();

		assertEquals("Hello", titles.get(0));
	}

	@Test
	void testUniqueTopics() {
		NoteManager nm = new NoteManager();

		Note note1 = new Note("Hello");
		note1.addTopic("a");
		note1.addTopic("b");
		note1.addTopic("c");

		Note note2 = new Note("World");
		note2.addTopic("b");
		note2.addTopic("a");
		note2.addTopic("c");
		note2.addTopic("d");
		nm.addNote(note1);
		nm.addNote(note2);

		ObservableList<String> result = nm.getUniqueTopics();

		assertEquals(4, result.size());
		assertEquals("c", result.get(2));
	}
}
