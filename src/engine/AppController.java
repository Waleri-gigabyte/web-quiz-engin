package engine;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @PostMapping("/actuator/shutdown")
    public void shutdown (){
        shutdown();
    }
}
