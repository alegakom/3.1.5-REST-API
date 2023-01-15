package alegakom.restAPI.controller;

import alegakom.restAPI.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class UserController {


    @GetMapping(value = "/user")
    public String getPrincipal(ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user); // реализовал метод, где получаю авторизованного юзера из авторитихолдера

//        через Principal получаем данные авторизованного пользователя bp POST request'а,
//        создается токен, а через UserService получаем у объекта User его имя из базы по username.
        return "user";
    }

}
