package kayzer.web.controllers;

import kayzer.domain.models.binding.UserRegisterBindingModel;
import kayzer.domain.models.service.UserServiceModel;
import kayzer.domain.models.view.AllUsersUserViewModel;
import kayzer.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users", consumes = "application/json")
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody UserRegisterBindingModel userRegisterBindingModel) throws URISyntaxException {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Error: Passwords do not match!");
        }

        boolean registerResult = this.userService.registerUser(
                this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class)
        );

        return ResponseEntity.created(new URI("/users/register")).body(registerResult);
    }

    @GetMapping("/all")
    public Set<AllUsersUserViewModel> allUsers() {
        return this.userService.getAllUsers()
                .stream()
                .map(userServiceModel -> {
                    AllUsersUserViewModel currentUserViewModel = this.modelMapper.map(userServiceModel, AllUsersUserViewModel.class);
                    currentUserViewModel.setRole(userServiceModel.extractAuthority());
                    return currentUserViewModel;
                })
                .collect(Collectors.toSet());
    }

    @PostMapping("/promote")
    public ResponseEntity promoteUser(@RequestParam(name = "id") String id) {

        boolean promoteResult = this.userService.promoteUser(id);

        if (promoteResult) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/demote")
    public ResponseEntity demoteUser(@RequestParam(name = "id") String id) {
        boolean demoteResult = this.userService.demoteUser(id);

        if (demoteResult) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
