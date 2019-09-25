package residentevil.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import residentevil.domain.models.binding.UserLoginBindingModel;
import residentevil.domain.models.binding.UserRegisterBindingModel;
import residentevil.services.UserService;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login() {
        return this.view("users/login");
    }

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView loginConfirm(@ModelAttribute UserLoginBindingModel userLoginBindingModel) {
        UserDetails userFromDb = this.userService.loadUserByUsername(userLoginBindingModel.getUsername());

        if (userFromDb == null || !userFromDb.getPassword().equals(userLoginBindingModel.getPassword())) {
            return this.view("users/login");
        }

        return this.redirect("/home");
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register() {
        return this.view("users/register");
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return this.view("users/register");
        }

        this.userService.registerUser(userRegisterBindingModel);
        return this.redirect("/login");
    }
}
