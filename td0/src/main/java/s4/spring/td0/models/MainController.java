package s4.spring.td0.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@SessionAttributes("elements")
public class MainController {
	 @ModelAttribute("elements") 
	    public List<Element> getItems(){
		 Element elm = new Element();
		 List<Element> elms = new ArrayList<>();
	      return elms;
	    }
		@GetMapping("items")
		public String index(ModelMap model) {
			model.addAttribute("TEST1","test1");
			return "index";
		}
		@GetMapping("items/new")
		public String newItem() {
			return "newItem";
		}
		@PostMapping("items/addNew")
		public RedirectView addView(@RequestParam("nom") String nom,@ModelAttribute("elements") List<Element> elements) {
			 Element elm = new Element();
			 elm.setNom(nom);
			 elements.add(elm);
		     return new RedirectView("/items");
		}
}

