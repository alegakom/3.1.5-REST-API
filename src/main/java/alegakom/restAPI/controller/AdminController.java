package alegakom.restAPI.controller;

import alegakom.restAPI.model.Role;
import alegakom.restAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        List<Role> rolesList;
        rolesList = userService.getAllRoles();
        model.addAttribute("userRoles", rolesList);
        return "admin";
    }



    @GetMapping(value = "/index")
    public String welcomePage(){
        return "index";
    }

}
