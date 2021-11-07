package esprit.dto;

import java.util.List;

public class EntrepriseDto {

	private int idE;
	private String nameE;
	private String raisonSocialE;
	private List<Integer> departementsE;

	public int getId() {
		return idE;
	}

	public void setId(int id) {
		this.idE = id;
	}

	public String getName() {
		return nameE;
	}

	public void setName(String name) {
		this.nameE = name;
	}

	public String getRaisonSocial() {
		return raisonSocialE;
	}

	public void setRaisonSocial(String raisonSocial) {
		this.raisonSocialE = raisonSocial;
	}

	public List<Integer> getDepartements() {
		return departementsE;
	}

	public void setDepartements(List<Integer> departements) {
		this.departementsE = departements;
	}
	
	
	
}
