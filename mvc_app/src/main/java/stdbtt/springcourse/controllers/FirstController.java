package stdbtt.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static stdbtt.springcourse.controllers.Action.addition;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "surname", required = false) String surname, Model model ){

            //System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message","Hello, " + name + " " + surname );
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage(){
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a") String parA, @RequestParam(value = "b") String parB, @RequestParam(value = "action") String action, Model model){
        int a = Integer.parseInt(parA);
        int b = Integer.parseInt(parB);
        int result=0;
        String operation="";
        Action compare = Action.valueOf(action);
        switch (compare) {
            case addition:
                result = a + b;
                operation = "+";
                break;
            case multiplication:
                result = a * b;
                operation = "*";
                break;
            case subtraction:
                result = a - b;
                operation = "-";
                break;
            case division:
                result = a / b;
                operation = "/";
        }
        model.addAttribute("result", String.valueOf(result));
        model.addAttribute("operation", operation);
        return "first/calculator";
    }
}
