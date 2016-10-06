package name.aggibb.userlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by agibb on 2016-10-06.
 */
@Controller
@RequestMapping(value = "/search")
public class SearchController {

    private UserRepository repository;
    private FilterText filterText;

    @Autowired
    public SearchController(UserRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String search(@ModelAttribute FilterText filterText, Errors errors) {
        if (errors.hasErrors()) {
            return "index";
        }
        this.filterText = filterText;
        return "redirect:/search";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("filterText", filterText);
        model.addAttribute("searchResults",
                repository.findUsersByFirstNameContainsIgnoreCaseOrLastNameContainsIgnoreCase(
                        filterText.getFilterText(), filterText.getFilterText()
                ));
        return "search";

    }

}
