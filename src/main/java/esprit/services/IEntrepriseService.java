package esprit.services;

import java.util.List;

import esprit.entities.Departement;
import esprit.entities.Entreprise;



public interface IEntrepriseService {
	
	public int ajouterEntreprise(Entreprise entreprise);
	public int ajouterDepartement(Departement dep);
	Departement affecterDepartementAEntreprise(int depId, int entrepriseId);
	List<String> getAllDepartementsNamesByEntreprise(int entrepriseId);
	public int deleteEntrepriseById(int entrepriseId);
	public int deleteDepartementById(int depId);
	public Entreprise getEntrepriseById(int entrepriseId);
}
