package site.linyy.jdkstudy.jdk.applet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/applet")
public class AppletController {

    @GetMapping("/hello")
    public String helloWorld() {

        return "/applet/hello";
    }

}
