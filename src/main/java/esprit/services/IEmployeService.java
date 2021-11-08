package esprit.services;

import java.util.Date;
import java.util.List;

import esprit.dto.ContratDto;
import esprit.dto.EmployeDto;
import esprit.entities.Contrat;
import esprit.entities.Employe;
import esprit.entities.Entreprise;
import esprit.entities.Mission;
import esprit.entities.Timesheet;




public interface IEmployeService {
	
	public int ajouterEmploye(Employe employe);
	public int mettreAjourEmailByEmployeId(String email, int employeId);
	public int affecterEmployeADepartement(int employeId, int depId);
	public void desaffecterEmployeDuDepartement(int employeId, int depId);
	public int ajouterContrat(Contrat contrat);
	public void affecterContratAEmploye(int contratId, int employeId);
	public String getEmployePrenomById(int employeId);
	public int deleteEmployeById(int employeId);
	public void deleteContratById(int contratId);
	public int getNombreEmployeJPQL();
	public List<String> getAllEmployeNamesJPQL();
	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise);
	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId);
	public void deleteAllContratJPQL();
	public float getSalaireByEmployeIdJPQL(int employeId);
	public Double getSalaireMoyenByDepartementId(int departementId);
	public List<Employe> getAllEmployes();
	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, 
	Date dateDebut, Date dateFin);
	
	public Employe mapToEntity(EmployeDto employeDto);
	public EmployeDto mapToDto(Employe employe);

	public Contrat mapToEntityC(ContratDto contratDto);
	public ContratDto mapToDtoC(Contrat contrat);

	
	

	
}
