package com.rudoy.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
//        String name = req.getParameter("name");
//        String surname = req.getParameter("surname");

        System.out.println("Hello " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodBuyPage() {
        return "first/goodbye";
    }


    @GetMapping("/calculator")
    public String calcPage(@RequestParam(value = "firstInt", required = false) int a,
                           @RequestParam(value = "secondInt", required = false) int b,
                           @RequestParam(value = "action", required = false) String action,
                           Model model) {

        double res = switch (action) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / (double) b;
            default -> 0;
        };

        model.addAttribute("res", res);

        return "first/calculator";
    }
}
