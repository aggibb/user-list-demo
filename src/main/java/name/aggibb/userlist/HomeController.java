package name.aggibb.userlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by agibb on 2016-10-01.
 */
@Controller
public class HomeController {
    @RequestMapping(value = {"/", "/index.html"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("filterText", new FilterText());
        return "index";
    }
}
