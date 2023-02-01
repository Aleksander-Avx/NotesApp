package com.avx.note.controller;

import com.avx.note.entity.NotesEntity;
import com.avx.note.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/notes")
@Controller
public class NotesController {
    private final NotesService notesService;

    @GetMapping("/")
    public String redirect() {
        return "redirect:/notes";
    }

    @GetMapping
    public String noteMain(Model model) {
        Iterable<NotesEntity> notes = notesService.findAll();
        model.addAttribute("notes", notes);
        return "notes-main";
    }

    @GetMapping("/about")
    public String about() {
        return "notes-about";
    }


    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/add")
    public String noteAdd() {
        return "notes-add";
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/add")
    public String notePostAdd(NotesEntity notesEntity) {
        notesService.saveNotes(notesEntity);
        return "redirect:/notes";
    }

    @GetMapping("/{id}")
    public String noteDetails(@PathVariable Long id, Model model) {
        model.addAttribute("notes", notesService.findById(id));
        return "notes-details";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}/edit")
    public String editDetails(@PathVariable Long id, Model model) {
        NotesEntity notes = notesService.findById(id);
        notesService.saveNotes(notes);
        model.addAttribute("notes", notesService.findById(id));
        return "/notes-edit";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/notes-edit")
    public String noteEdit(@ModelAttribute NotesEntity notesEntity) {
        notesService.saveNotes(notesEntity);
        return "redirect:/notes";
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping("/{id}/remove")
    public String deleteNote(@PathVariable Long id) {
        notesService.deleteById(id);
        return "redirect:/notes";
    }
}
