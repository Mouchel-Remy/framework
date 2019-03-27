package s4.spring.TD2.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import s4.spring.TD2.entities.Organization;
import s4.spring.TD2.repository.OrgasRepository;

@Controller
@RequestMapping("/orgas/")
public class OrganizationController {
	@Autowired
	private OrgasRepository orgasRepo;
	
	@GetMapping({"","index"})
	public String index(Model model) {
		List<Organization> orgas = orgasRepo.findAll();
		model.addAttribute("orgas",orgas);
		return "index";
	}
	
	@PostMapping("submit/{id}")
	public RedirectView submit(@PathVariable int id,Organization postedorga) {
		Optional<Organization> opt = orgasRepo.findById(id);
		if(opt.isPresent()) {
			Organization orga = opt.get();
			copyFrom(postedorga,orga);
			orgasRepo.save(orga);
		}
		return new RedirectView("/orgas/");
	}
	@PostMapping("submit")
	public RedirectView submit_(Organization postedOrga) {
		if(postedOrga.getId() != 0) {
			int id = postedOrga.getId();
			Optional<Organization> opt = orgasRepo.findById(id);
			if(opt.isPresent()) {
				Organization orga = opt.get();
				copyFrom(postedOrga,orga);
				orgasRepo.save(orga);
			}
			else {
				return new RedirectView("/orgas/");
			}
		}else {
			
		}
		return null;
	}
	
	
	private void copyFrom(Organization source,Organization dest) {
		dest.setName(source.getName());
		dest.setDomain(source.getDomain());
		dest.setCity(source.getCity());
		dest.setAlisases(source.getAlisases());
	}
	
	@GetMapping("new")
	public String frmnew(Model model) {
		model.addAttribute("orga",new Organization());
		return "orgas/frm";
	}
	@GetMapping("edit/{id}")
	public String frmEdit(@PathVariable int id,Model model) {
		Optional<Organization> opt = orgasRepo.findById(id);
		if(opt.isPresent()) {
			model.addAttribute("orga",opt.get());
			return "orgas/frm";
		}
		return "/orgas/404";
	}

}
