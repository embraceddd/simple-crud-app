package com.example.whythisisntworking.controller;

import com.example.whythisisntworking.dao.PersonDao;
import com.example.whythisisntworking.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class PersonController {

    @Autowired
    private PersonDao personDao;
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("users", personDao.getAll());
        return "users";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personDao.getById(id));
        return "userPage";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("person", new Person());
        return "createUser";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("person") @Valid Person person,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "createUser";
        personDao.save(person);
        return "redirect:http://localhost:8080/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personDao.getById(id));
        return "edit";
    }

    @PatchMapping("/editUsers/{id}")
    public String editUser(@ModelAttribute("person") @Valid Person personAttribute,
                           BindingResult bindingResult,
                           @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return "edit";
        personDao.update(personAttribute, id);
        return "redirect:http://localhost:8080/users";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        personDao.delete(id);
        return "redirect:http://localhost:8080/users";
    }
}
