package gruppe10.inflowscreen.frontend;

import gruppe10.inflowscreen.backend.models.entities.Account;
import gruppe10.inflowscreen.backend.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;


@Controller
@CrossOrigin(value = "*")
public class FrontendController {
    
    @Autowired
    AccountRepository accountRepository;


    @GetMapping("")
    public String index(Model model, Principal principal){
        
        Optional<Account> optLoggedInAccount = accountRepository.findByEmail(principal.getName());
        
        Account loggedInAccount = optLoggedInAccount.get();
        
        model.addAttribute("logoPath", loggedInAccount.getOrganisation().getLogoPath());
    
        System.out.println(loggedInAccount.getOrganisation().getLogoPath());
        
        return "index";
    }

    @GetMapping("createSlide")
    public String createSlide(Model model){
     
        return "createSlide";
    }

    @GetMapping("editSlide/{id}")
    public String editSlide(@PathVariable String orgName, @PathVariable int id, Model model){
        model.addAttribute("orgName", orgName);
        model.addAttribute("slideId", id);

        return "editSlide";
    }

    @GetMapping("slideshow")
    public String slideshow(@PathVariable String orgName, Model model){
        model.addAttribute("orgName", orgName);

        return "slideshow";
    }

    @GetMapping("createAccount")
    public String createAccount(@PathVariable String orgName, Model model){
        model.addAttribute("orgName", orgName);

        return "createAccount";
    }

    @GetMapping("t")
    public String test(@PathVariable String orgName, Model model){
        model.addAttribute("orgName", orgName);

        return "test";
    }



}
