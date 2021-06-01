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
    int orgId;


    @GetMapping("")
    public String index(Model model, Principal principal){
        
        Optional<Account> optLoggedInAccount = accountRepository.findByEmail(principal.getName());
        Account loggedInAccount = optLoggedInAccount.get();
        orgId = optLoggedInAccount.get().getOrganisation().getId();
        model.addAttribute("orgId", orgId);
        model.addAttribute("logoPath", loggedInAccount.getOrganisation().getLogoPath());
        
        return "index";
    }

    @GetMapping("createSlide")
    public String createSlide(Model model){
        model.addAttribute("orgId", orgId);

        //System.out.println(orgId);
        return "createSlide";
    }

    @GetMapping("editSlide/{slideId}")
    public String editSlide(@PathVariable int slideId, Model model){
        model.addAttribute("slideId", slideId);

        return "editSlide";
    }

    @GetMapping("/slideshow/{orgId}")
    public String slideshow(@PathVariable int orgId, Model model){


        model.addAttribute("orgId", orgId);
    
        return "slideshow";
    }

    @GetMapping("createAccount")
    public String createAccount(Model model){

        model.addAttribute("orgId", orgId);

        return "createAccount";
    }

    @GetMapping("t")
    public String test(Model model){

        return "test";
    }



}
