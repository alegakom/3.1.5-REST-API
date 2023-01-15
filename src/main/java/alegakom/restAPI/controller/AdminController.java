package alegakom.restAPI.controller;

import alegakom.restAPI.model.Role;
import alegakom.restAPI.model.User;
import alegakom.restAPI.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api")
public class AdminController {

    private final RoleService roleService;

    @Autowired
    public AdminController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin")
    public String adminPage(ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        List<Role> rolesList;
        rolesList = roleService.getAllRoles();
        model.addAttribute("userRoles", rolesList);
        return "admin";
    }



    @GetMapping(value = "/index")
    public String welcomePage(){
        return "index";
    }

}
