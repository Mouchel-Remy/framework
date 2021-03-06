package s4.spring.TD2.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.TD2.entities.Groupe;
import s4.spring.TD2.entities.Organization;
import s4.spring.TD2.repository.GroupeRepository;
import s4.spring.TD2.repository.OrgasRepository;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {
	
	@Autowired
	private OrgasRepository orgasRepo;
	@Autowired
	private GroupeRepository groupeRepo;
	
	
	
	@RequestMapping("create")
	@ResponseBody // PAS BESOIN DE VUE JE RETOURNE DIRECTEMENT SUR LE NAVIGATEUR
	public String createOrgas() {
		Organization organisation = new Organization();
		organisation.setName("IUT de Caen");
		organisation.setDomain("unicaen.fr");
		organisation.setAlisases("iutc3.unicaen.fr");
		organisation.setCity("Ifs");
		orgasRepo.save(organisation);
		return organisation+" ajoutée dans la BDD,";
	}
	
	
	@RequestMapping("groupes")	
	@ResponseBody
	public String createGroupe() {
		Groupe groupe = new Groupe();
		groupeRepo.save(groupe);
		return "Groupe crée";		
	}
	
	@RequestMapping("create/groupes/{id}")
	@ResponseBody // PAS BESOIN DE VUE JE RETOURNE DIRECTEMENT SUR LE NAVIGATEUR
	public String createOrgaswhithGroupes(@PathVariable int id) {
		Optional<Organization> optOrga = orgasRepo.findById(id);
		if(optOrga.isPresent()) {
			Organization organisation = optOrga.get();
			Groupe groupe = new Groupe();
			groupe.setName("Etudiants");
			organisation.addGroupe(groupe);
			
			orgasRepo.save(organisation);
			return organisation+" ajoutée dans la BDD,";	
		}
		return "Organisation inexistante";
	}
	
	@PostMapping("create")
	public Organization post(@RequestBody Organization orga) {
		return orgasRepo.saveAndFlush(orga);
	}
	@GetMapping("")
	public void update() {
	}
	
	@GetMapping("{id}")
	public void get() {
		
	}
	
	@GetMapping("delete")
	public void delete() {
		
	}
}
