package alegakom.restAPI.controller;

import alegakom.restAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String adminPage(ModelMap model){
        model.addAttribute("user", userService.getPrincipalUser());
        return "admin";
    }

    @GetMapping(value = "/index")
    public String welcomePage(){
        return "index";
    }

}
