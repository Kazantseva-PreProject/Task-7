package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.entity.User;
import web.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public String showALLUsers(Model model) {

        List<User> allUsers = userService.getAllUser();
        model.addAttribute("index", allUsers);

        return "index";
    }

    @RequestMapping("/add-new-user")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "user-info";

    }

    @RequestMapping(value = "/save-user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping("/update-info")
    public String updateUser(@RequestParam("id") int id, Model model) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @RequestMapping("/delete-user")
    public String deleteUser(@RequestParam("deleteId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}