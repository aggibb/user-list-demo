package name.aggibb.userlist;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by agibb on 2016-10-01.
 */
@Controller
public class HomeController {
    private UserRepository repository;
    private FilterText filterText;

    public HomeController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(value = {"/", "/index.html"}, method = RequestMethod.GET)
    public String home(Model model) {
        model.addAttribute("filterText", new FilterText());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String search(@Valid @ModelAttribute FilterText filterText, Errors errors) {
        if (errors.hasErrors()) {
            return "index";
        }
        this.filterText = filterText;
        return "redirect:/search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("filterText", filterText);
        model.addAttribute("searchResults",
                repository.findUsersByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(
                        filterText.getNameText(), filterText.getNameText()
                ));
        return "search";
    }
}
