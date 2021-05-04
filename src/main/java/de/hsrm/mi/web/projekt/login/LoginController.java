package de.hsrm.mi.web.projekt.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController{

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String login_get(){
        return "login";
    }

    @PostMapping("/login")
    public String login_post(@RequestParam String username, @RequestParam String password, Model m){
       
        if(username != ""){
        String passwortwert = username + username.length();
        boolean korrekt = passwortwert.equals(password)? true:false;
        if(korrekt == true){
            m.addAttribute("loggedinusername", username);
            m.addAttribute("passwortwert", passwortwert);
            return "redirect:sichtung/meine";
        }else{
            m.addAttribute("username", username);
            m.addAttribute("passwortwert", passwortwert);
            m.addAttribute("korrekt", korrekt);
            m.addAttribute("loggedinusername", "");
            return ("/login");
        }
        }else{
            return ("/login");
        }
        
    }
    
}