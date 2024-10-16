package com.umesh.jwtnotes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.umesh.jwtnotes.entity.Note;
import com.umesh.jwtnotes.repository.NoteRepository;

@RestController
@RequestMapping("/api/note")
public class NoteController {

	@Autowired
	private NoteRepository noteRepo;

	@GetMapping("/notes")
	public List<Note> getNotes() {
		// Retrieve email from the Security Context
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// Fetch and return user details
		return noteRepo.findByAuthor(email);
	}

	@PostMapping("/addnote")
	public Note addNote(@RequestBody String content) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Note newNote = new Note();
		newNote.setAuthor(email);
		newNote.setContent(content);

		return noteRepo.save(newNote);
	}

	@DeleteMapping("/delete-note/{id}")
	public ResponseEntity<String> deleteNote(@PathVariable Long id) {
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Find the note by its ID
		Note note = noteRepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));

		// Check if the note belongs to the authenticated user
		if (!note.getAuthor().equals(email)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this note.");
		}

		noteRepo.delete(note);
		return ResponseEntity.ok("Note deleted successfully.");
	}

	@PutMapping("/edit-note/{id}")
	public ResponseEntity<String> editNote(@PathVariable Long id, @RequestBody String newContent) {

		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// Find the note by its ID
		Note note = noteRepo.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));

		// Check if the note belongs to the authenticated user
		if (!note.getAuthor().equals(email)) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to edit this note.");
		}

		note.setContent(newContent);

		noteRepo.save(note);

		return ResponseEntity.ok("Note updated successfully.");
	}
}
