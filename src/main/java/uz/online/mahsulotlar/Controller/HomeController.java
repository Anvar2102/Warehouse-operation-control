package uz.online.mahsulotlar.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String getInformationAbout() {
        String information = "Hello everyone.You can use this links:" +
                "\n/register   --  'register user'" +
                "\n/login   --  'login to system'" +
                "\n/add   --  'add product own'" +
                "\n/send  --  'sending product to another company'" +
                "\n/income  --  'show income history for you'" +
                "\n/outcome  -- 'show outcome history for you'";
        return information;
    }
}
