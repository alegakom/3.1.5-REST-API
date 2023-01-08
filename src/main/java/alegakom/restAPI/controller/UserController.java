package alegakom.restAPI.controller;

import alegakom.restAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String getPrincipal(ModelMap model){
        model.addAttribute("user", userService.getPrincipalUser()); // реализовал метод, где получаю авторизованного юзера из авторитихолдера

//        через Principal получаем данные авторизованного пользователя bp POST request'а,
//        создается токен, а через UserService получаем у объекта User его имя из базы по username.
        return "user";
    }

}
