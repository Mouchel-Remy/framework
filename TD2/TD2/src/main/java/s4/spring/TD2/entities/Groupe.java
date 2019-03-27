package s4.spring.TD2.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
public class Groupe {
	@Id // CLE PRIMAIRE TOUT LE TEMPS Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; // CLE PRIMAIRE PEUT CHANGER DE NOM
	private String name;
	private String email;
	private String aliases;
	
	/*public Groupe(String name, String email, String aliases, Organization organisation) {
		this.name = name;
		this.email = email;
		this.aliases = aliases;
		this.organisation = organisation;
	}*/

	@ManyToOne  	// TO ONE PARCE QUE UNE SEULE ORGA
	private Organization organisation;
	
	@ManyToMany(mappedBy="groupes")
	private List<User> users;
	
	public Groupe() {
		users = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public Organization getOrganisation() {
		return organisation;
	}

	public void setOrganisation(Organization organisation) {
		this.organisation = organisation;
	}
}
