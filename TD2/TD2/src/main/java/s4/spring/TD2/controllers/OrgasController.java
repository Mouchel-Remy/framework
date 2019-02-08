package s4.spring.TD2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.TD2.entities.Organization;
import s4.spring.TD2.repository.OrgasRepository;

@Controller
@RequestMapping("/orgas/")
public class OrgasController {
	
	@Autowired
	private OrgasRepository orgasRepo;
	
	@RequestMapping("create")
	@ResponseBody // PAS BESOIN DE VUE JE RETOURNE DIRECTEMENT SUR LE NAVIGATEUR
	public String createOrgas() {
		Organization organisation = new Organization();
		organisation.setName("IUT Ifs");
		organisation.setDomain("unicaen.fr");
		organisation.setAlisases("iutc3.unicaen.fr");
		orgasRepo.save(organisation);
		return organisation+" ajoutée dans la BDD,";
	}
}
