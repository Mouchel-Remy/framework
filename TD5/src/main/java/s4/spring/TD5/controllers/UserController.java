package s4.spring.TD5.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.TD5.entities.Script;
import s4.spring.TD5.entities.User;
import s4.spring.TD5.repository.ScriptRepository;
import s4.spring.TD5.repository.UserRepository;

@Controller
@RequestMapping("")
public class UserController {
	@Autowired
	private ScriptRepository scriptRepo;

	@Autowired
	private UserRepository usersRepo;

	@PostMapping("login")
	public RedirectView login(Model model, User utilisateur, HttpSession session) {
		User user = usersRepo.findByLogin(utilisateur.getLogin());
		if (user != null && user.getPassword().equals(utilisateur.getPassword())) {
			session.setAttribute("userConnecter", user);
			return new RedirectView("/index");
		}
		return new RedirectView("/login");
	}
	

	@GetMapping("login")
	public String loginView(Model model) {
		model.addAttribute("utilisateur", new User());
		return "login";
	}

	@GetMapping("logout")
	public RedirectView logout(Model model, HttpSession session) {
		session.removeAttribute("userConnecter");
		return new RedirectView("/login");
	}
	
	@GetMapping("index")
	public String index(Model model, HttpSession session) {
		if(session.getAttribute("userConnecter") != null) {
			model.addAttribute("user",session.getAttribute("userConnecter"));
			model.addAttribute("scripts",scriptRepo.findAll());
			return "index";
		}else {
			return "logout";
		}
	}
	
	
	@GetMapping("script/new")
	public String scriptView(Model model, HttpSession session) {
		model.addAttribute("user",session.getAttribute("userConnecter"));
		model.addAttribute("script",new Script());
		model.addAttribute("scripts",scriptRepo.findAll());
		return "newscript";
	}
	

	@RequestMapping("newUser")
	@ResponseBody
	public String newUser() {
		User user = new User();
		user.setEmail("remy-mouchel@outlook.fr");
		user.setIdentity("Mouchel");
		user.setLogin("Mouchel");
		user.setPassword("mdp");
		usersRepo.save(user);
		return user.getIdentity()+" ajouté dans le BDD";
	}
	
	@RequestMapping("newScript")
	@ResponseBody
	public String newScript() {
		Script script = new Script();
		script.setTitle("Script1");
		script.setDescription("Ceci est une description");
		script.setContent("Ceci est du contenu");
		script.setCreationDate("Ceci est une date");
		scriptRepo.save(script);
		return script.getTitle()+" ajouté dans le BDD";
	}
	


}
