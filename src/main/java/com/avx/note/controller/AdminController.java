package com.avx.note.controller;

import com.avx.note.entity.UserEntity;
import com.avx.note.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    public String showAllUsersData(Model model) {
        model.addAttribute("allUsers", userService.findAll());
        return "/all-users-data";
    }

    @GetMapping("/users/edit/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.findById(id));
        return "/user-update";
    }

    @PostMapping("/users/user-update")
    public String saveUser(UserEntity user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping(value = "/users/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }
}
