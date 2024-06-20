package indiv.pechola.wms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // Test
    @GetMapping
    public String hello(){
        return "hola";
    }
}
