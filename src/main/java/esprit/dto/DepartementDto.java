package esprit.dto;

import java.util.List;



public class DepartementDto {
	
	private int idD;

	private String nameD;

	private List<Integer> employesD;

	public String getName() {
		return nameD;
	}

	public void setName(String name) {
		this.nameD = name;
	}

	public int getId() {
		return idD;
	}

	public void setId(int id) {
		this.idD = id;
	}

	public List<Integer> getEmployes() {
		return employesD;
	}

	public void setEmployes(List<Integer> employes) {
		this.employesD = employes;
	}

	public List<Integer> getMissions() {
		return missionsD;
	}

	public void setMissions(List<Integer> missions) {
		this.missionsD = missions;
	}

	public int getEntreprise() {
		return entrepriseD;
	}

	public void setEntreprise(int entreprise) {
		this.entrepriseD = entreprise;
	}

	private List<Integer> missionsD;

	private int entrepriseD;

	
	

}
