package s4.spring.TD2.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Organization {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	private String domain;
	private String alisases;
	private String city;
	
	@OneToMany(mappedBy="organisation",cascade=CascadeType.ALL)
	private List<Groupe> groupes;
	
	
	/*public Organization(String name, String domain, String alisases, String city) {
		this.name = name;
		this.domain = domain;
		this.alisases = alisases;
		this.city = city;
		groupes= new ArrayList<>();
	}*/
	
	public Organization(){
		groupes= new ArrayList<>();
	}

	public void addGroupe(Groupe groupe) {
		groupes.add(groupe);
		groupe.setOrganisation(this);
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getAlisases() {
		return alisases;
	}
	public void setAlisases(String alisases) {
		this.alisases = alisases;
	}
	public List<Groupe> getGroupes() {
		return groupes;
	}

	public void setGroupes(List<Groupe> groupes) {
		this.groupes = groupes;
	}

	
	@Override
	public String toString() {
		return name+" at "+city;
	}
}
