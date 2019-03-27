package s4.spring.TD2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import s4.spring.TD2.entities.Organization;
import s4.spring.TD2.repository.OrgasRepository;

@Controller
@RequestMapping("/rest/")
public class RestOrgasController {
	
	@Autowired
	private OrgasRepository repos;
	
	@GetMapping("")
	@ResponseBody
	public List<Organization> get() {
		return repos.findAll();
	}
	
	@PostMapping("create")
	@ResponseBody
	public void post(@RequestBody Organization orga) {
		repos.saveAndFlush(orga);
	}
	
}
