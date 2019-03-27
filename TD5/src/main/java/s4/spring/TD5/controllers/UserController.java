package s4.spring.TD5.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.TD5.entities.User;
import s4.spring.TD5.repository.UserRepository;

@Controller
@RequestMapping("/user/")
public class UserController {
	
	private User user;
	
	@Autowired
	private UserRepository usersRepo;
	
	@RequestMapping("index")
	public String index(Model model) {
		List<User> users = usersRepo.findAll();
		model.addAttribute("users",users);
		
		if(user == null) {
			return "index";
		}
		
		return "login";
	}
	
	@PostMapping("login")
	public RedirectView login(@RequestParam("mail") String mail,@RequestParam("motdepasse") String motdepasse) {
	User utilisateur = usersRepo.findByEmail(mail);
		if(utilisateur != null && utilisateur.getPassword().equals(motdepasse)) {
			user = utilisateur;
		}
		return new RedirectView("/user/index");
	}
	
	@PostMapping("logout")
	public RedirectView logout() {
		user = null;
		return new RedirectView("/user/index");
	}
	
	
	@RequestMapping("new")
	@ResponseBody
	public String newUser() {
		User user = new User();
		user.setEmail("remy-mouchel@outlook.fr");
		user.setIdentity("Mouchel");
		user.setLogin("Mouchel");
		user.setPassword("mdp");
		usersRepo.save(user);
		return user.getIdentity()+" Ajouter dans le BDD";
	}
	
}
