package esprit.dto;

import java.util.List;

import esprit.entities.Contrat;
import esprit.entities.Departement;
import esprit.entities.Role;
import esprit.entities.Timesheet;

public class EmployeDto {

	private int idD;

	private String prenomD;

	private String nomD;

	private String emailD;

	private boolean actifD;

	private Role roleD;

	private List<Departement> departementsD;

	private Contrat contratD;

	private List<Timesheet> timesheetsD;

	public EmployeDto() {
		super();
	}

	public int getId() {
		return idD;
	}

	public void setId(int id) {
		this.idD = id;
	}

	public String getPrenom() {
		return prenomD;
	}

	public void setPrenom(String prenom) {
		this.prenomD = prenom;
	}

	public String getNom() {
		return nomD;
	}

	public void setNom(String nom) {
		this.nomD = nom;
	}

	public String getEmail() {
		return emailD;
	}

	public void setEmail(String email) {
		this.emailD = email;
	}

	public boolean isActif() {
		return actifD;
	}

	public void setActif(boolean actif) {
		this.actifD = actif;
	}

	public Role getRole() {
		return roleD;
	}

	public void setRole(Role role) {
		this.roleD = role;
	}

	public List<Departement> getDepartements() {
		return departementsD;
	}

	public void setDepartements(List<Departement> departement) {
		this.departementsD = departement;
	}

	public Contrat getContrat() {
		return contratD;
	}

	public void setContrat(Contrat contrat) {
		this.contratD = contrat;
	}

	public List<Timesheet> getTimesheets() {
		return timesheetsD;
	}

	public void setTimesheets(List<Timesheet> timesheets) {
		this.timesheetsD = timesheets;
	}

	@Override
	public String toString() {
		return "Employe [id=" + idD + ", prenom=" + prenomD + ", nom=" + nomD + ", email=" + emailD + ", actif=" + actifD
				+ ", role=" + roleD + "]";
	}

}
