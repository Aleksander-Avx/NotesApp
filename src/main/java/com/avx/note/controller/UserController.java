package com.avx.note.controller;

import com.avx.note.entity.UserEntity;
import com.avx.note.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/user")
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;


    @GetMapping("/list")
    public String listUser(Long id, Model model) {
        UserEntity notes = userService.findById(id);
        model.addAttribute("usersId", notes);
        return "user-successes";
    }

    @GetMapping("/new")
    public String newUser() {

        return "user-new";
    }

    @PostMapping("/new")
    public String addNewUser(@RequestParam String email,
                             @RequestParam String password) {
        if (userService.addUser(email, password)) {
            return "redirect:/user/successes";
        } else return "redirect:/user/new";
    }

    @GetMapping("/successes")
    public String login(Model model, Long id) {
        model.addAttribute("userId", userService.findAll());
        return "user-successes";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam String email,
                               @RequestParam String password) {
        if (userService.registration(email, password)) {
            return "user-successes";
        } else {
            return "/registration";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        if (userService.logout(request)) {
            return "redirect:/notes/about";
        }
        return null;
    }

    @GetMapping("/confirmLogout")
    public String logout() {
        return "confirmLogout";
    }
}


