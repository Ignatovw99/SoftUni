package residentevil.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import residentevil.domain.models.binding.UserRoleEditBindingModel;
import residentevil.domain.models.view.UserViewModel;
import residentevil.services.UserRoleService;
import residentevil.services.UserService;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController extends BaseController {

    private final UserService userService;

    private final UserRoleService userRoleService;

    public AdminController(UserService userService, UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/admin/home")
    public ModelAndView adminHome(Authentication authentication, ModelAndView modelAndView) {
        modelAndView.addObject("username", authentication.getName());
        modelAndView.addObject("authorities", authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        return this.view("admin-home", modelAndView);
    }


    @GetMapping("/users/all")
    public ModelAndView showAllUsers(ModelAndView modelAndView) {
        modelAndView.addObject("users", this.userService.getAllUsers());
        return this.view("users/show-users", modelAndView);
    }

    @GetMapping("/users/edit/{id}")
    public ModelAndView editUserRole(@PathVariable("id") String id, ModelAndView modelAndView, Principal principal) {
        UserViewModel userView = this.userService.getUserById(id);
        if (userView.getUsername().equals(principal.getName()) || userView.getAuthorities().contains("ROOT")) {
            return this.view("error/unauthorized");
        }
        modelAndView.addObject("user", userView);
        modelAndView.addObject("roles", this.userRoleService.getAllUserRoles().stream().filter(role -> !role.equals("ROOT")).collect(Collectors.toList()));
        return this.view("users/edit-user", modelAndView);
    }

    @PostMapping("/users/edit/{id}")
    public ModelAndView editUserRoleConfirm(@PathVariable("id") String id,
                                            @ModelAttribute UserRoleEditBindingModel userRoleEditBindingModel,
                                            Principal principal) {
        this.userService.editUserRole(id, userRoleEditBindingModel, principal.getName());
        return this.redirect("/users/all");
    }

    @GetMapping(value = "/test", produces = "application/json")
    @ResponseBody
    public Map<String, Integer> test() {
        return new HashMap<String, Integer>() {{
            put("Pesho", 12);
            put("Pesds", 15);
            put("Pesh", 2213);
        }};
    }
}
