package com.rudoy.app.controller;

import com.rudoy.app.dao.PersonDAO;
import com.rudoy.app.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/people")
public class ControllerPeople {

    private final PersonDAO personDAO;

    @Autowired
    public ControllerPeople(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String main(Model model) {
        List<Person> personList = personDAO.index();
        model.addAttribute("people", personList);
        return "people/main";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Person person = personDAO.show(id);
        model.addAttribute("person", person);
        return "people/show";
    }

//    @GetMapping("/new")
//    public String newPerson(Model model) {
//        model.addAttribute("person", new Person());
//        return "people/new";
//    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) {
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id) {
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
 }
