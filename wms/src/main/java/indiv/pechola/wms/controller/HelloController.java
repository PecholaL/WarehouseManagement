package indiv.pechola.wms.controller;

import indiv.pechola.wms.entity.User;
import indiv.pechola.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    // Test
    @GetMapping("/hello")
    public String hello(){
        return "Hola!";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> list() {
        return userService.listAll();
    }
}