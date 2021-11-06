package esprit.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import esprit.entities.Contrat;
import esprit.entities.Departement;
import esprit.entities.Employe;
import esprit.entities.Entreprise;
import esprit.entities.Mission;
import esprit.entities.Timesheet;
import esprit.repository.ContratRepository;
import esprit.repository.DepartementRepository;
import esprit.repository.EmployeRepository;
import esprit.repository.TimesheetRepository;

@Service
public class EmployeServiceImpl implements IEmployeService {

	private static final Logger l = Logger.getLogger(IEmployeService.class);

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;

	public int ajouterEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}

	public void mettreAjourEmailByEmployeId(String email, int employeId) {

		try {
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);

			if (employeManagedEntity.isPresent()) {
				Employe employe = employeManagedEntity.get();
				employe.setEmail(email);
				employeRepository.save(employe);
				l.info("Employe Updated: " + employe);

			} else {

				l.warn("Employe don't exist");

			}

		} catch (Exception e) {

			l.error(e.toString());
		}
	}

	@Transactional
	public void affecterEmployeADepartement(int employeId, int depId) {
		try {

			Optional<Departement> depManagedEntity = deptRepoistory.findById(depId);
			Optional<Employe> employeManagedEntity = employeRepository.findById(employeId);

			if (depManagedEntity.isPresent() && employeManagedEntity.isPresent()) {

				Departement department = depManagedEntity.get();
				Employe employe = employeManagedEntity.get();

				if (department.getEmployes() == null) {

					List<Employe> employes = new ArrayList<>();
					employes.add(employe);
					department.setEmployes(employes);
				} else {

					department.getEmployes().add(employe);
				}

				deptRepoistory.save(department);

				l.info("departement affected " + department);
				l.info("employee affected " + employe);

			}

			else {
				l.warn("Cet employe ou ce departement n'existe pas");
			}

		} catch (Exception e) {
			l.error(e.toString());
		}
	}

	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId) {
		Departement dep = deptRepoistory.findById(depId).orElse(null);
		if (dep != null) {
			int employeNb = dep.getEmployes().size();
			for (int index = 0; index < employeNb; index++) {
				if (dep.getEmployes().get(index).getId() == employeId) {
					dep.getEmployes().remove(index);
					break;
				}
			}
		} else {
			l.warn("Dep don't exist");
		}
	}

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElse(null);
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);

		if ((contratManagedEntity != null) && (employeManagedEntity != null)) {
			contratManagedEntity.setEmploye(employeManagedEntity);
			contratRepoistory.save(contratManagedEntity);
			l.info("Contract affected");

		} else {
			l.warn("Employe or Contract not found");
		}

	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).orElse(null);
		if (employeManagedEntity != null) {
			return employeManagedEntity.getPrenom();

		} else {
			l.warn("Employe don't exist");
		}
		return null;
	}

	public void deleteEmployeById(int employeId) {
		Employe employe = employeRepository.findById(employeId).orElse(null);

	if(employe !=null) {	
		for (Departement dep : employe.getDepartements()) {
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
		l.info("employe deleted");
	}
	else {
		l.warn("Employe dosen't exist");
	}
	}

	public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).orElse(null);
		if (contratManagedEntity != null ) {
			contratRepoistory.delete(contratManagedEntity);
			l.info("Contract Deleted");
		}
		else {
			l.warn("Contract not found");
		}
		

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}

	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

}
