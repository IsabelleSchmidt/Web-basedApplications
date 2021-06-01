package de.hsrm.mi.web.projekt.fotologin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes("loggedinusername")
public class FotoLoginController{

    Logger logger = LoggerFactory.getLogger(FotoLoginController.class);

    @GetMapping("/fotologin")
    public String login_get(){
        logger.info("Ist im Get-Mapping drin");
        return "/foto/login";
    }

    @PostMapping("/fotologin")
    public String login_post(@RequestParam String username, @RequestParam String password, Model m){
       
        logger.info("Ist im PostMapping /login drin");

        if(username != ""){
            String passwortwert = username + username.length();
            boolean korrekt = passwortwert.equals(password)? true:false;
           
            if(korrekt == true){
                logger.info("Passwort ist korrekt");

                m.addAttribute("passwortwert", passwortwert);
                m.addAttribute("username", username);
                m.addAttribute("korrekt", korrekt);
                m.addAttribute("loggedinusername", username);
                return "redirect:/foto";
            }else{
                logger.info("Passwort ist falsch");

                m.addAttribute("korrekt", korrekt);
                m.addAttribute("username", username);
                m.addAttribute("passwortwert", passwortwert);
                m.addAttribute("loggedinusername", "");
                return ("/foto/login");
            }
        }else{
            return ("/login");
        }
        
    }
    
}