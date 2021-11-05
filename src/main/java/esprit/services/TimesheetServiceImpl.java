package esprit.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import esprit.entities.Departement;
import esprit.entities.Employe;
import esprit.entities.Mission;
import esprit.entities.Role;
import esprit.entities.Timesheet;
import esprit.entities.TimesheetPK;
import esprit.repository.DepartementRepository;
import esprit.repository.EmployeRepository;
import esprit.repository.MissionRepository;
import esprit.repository.TimesheetRepository;

@Service
public class TimesheetServiceImpl implements ITimesheetService {
	private static final Logger l = Logger.getLogger(ITimesheetService.class);

	@Autowired
	MissionRepository missionRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	EmployeRepository employeRepository;

	public int ajouterMission(Mission mission) {
		l.info("debut ajouter mission");
		missionRepository.save(mission);
		l.debug("mission  sauvegardé");
		l.info("fin ajouter mission");
		return mission.getId();
	}

	public void affecterMissionADepartement(int missionId, int depId) {
		try {
			l.debug("lancement de l'affectation d'une mission");
			Mission mission = missionRepository.findById(missionId).get();
			Departement dep = deptRepoistory.findById(depId).get();
			mission.setDepartement(dep);
			missionRepository.save(mission);
			l.info("affectation d'une mission terminé avec succés");
		} catch (Exception e) {
			l.error("Erreur dans la méthode affecterMissisionADepartement():" + e);
		} finally {
			l.info("Méthode ajouterMission() términé !!!!");
		}

	}

	public void ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
		TimesheetPK timesheetPK = new TimesheetPK();
		timesheetPK.setDateDebut(dateDebut);
		timesheetPK.setDateFin(dateFin);
		timesheetPK.setIdEmploye(employeId);
		timesheetPK.setIdMission(missionId);

		Timesheet timesheet = new Timesheet();
		timesheet.setTimesheetPK(timesheetPK);
		timesheet.setValide(false);

		try {
			l.debug("lancement de l'ajout du timesheet");
			timesheetRepository.save(timesheet);
			l.info("ajout terminé avec succée");
		} catch (Exception e) {
			l.error("Erreur dans la methode ajouterTimesheet():" + e);
		} finally {
			l.info("Méthode ajouterMission() términé ");
		}

	}

	public void validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		System.out.println("In valider Timesheet");
		Employe validateur = employeRepository.findById(validateurId).get();
		Mission mission = missionRepository.findById(missionId).get();
		// verifier s'il est un chef de departement (interet des enum)
		if (!validateur.getRole().equals(Role.CHEF_DEPARTEMENT)) {
			l.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return;
		}
		// verifier s'il est le chef de departement de la mission en question
		boolean chefDeLaMission = false;
		for (Departement dep : validateur.getDepartements()) {
			if (dep.getId() == mission.getDepartement().getId()) {
				chefDeLaMission = true;
				break;
			}
		}
		if (!chefDeLaMission) {
			l.info("l'employe doit etre chef de departement de la mission en question");
			return;
		}
		//
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet = timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);

		// Comment Lire une date de la base de données
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		l.info("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));

	}

	public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
		List<Mission> missions = null;
		try {
			l.debug("lancement  de l'affichage de la liste des missions avec les employes ");
			missions = timesheetRepository.findAllMissionByEmployeJPQL(employeId);
			l.info("liste des missons avec les employes !!!");
		} catch (Exception e) {
			l.error("Erreur dans la méthode  findAllMissionByEmployeJPQL(): " + e);
		}
		return missions;
	}

	public List<Employe> getAllEmployeByMission(int missionId) {
		List<Employe> employes = null;
		try {
			l.debug("lancement de l'affichage de la liste des employes avec missions");
			employes = timesheetRepository.getAllEmployeByMission(missionId);
			l.info("liste des employes avec leurs missions !!!");
		} catch (Exception e) {
			l.error("Erreur dans la méthode  getAllEmployeByMission(): " + e);
		}
		return employes;
	}
}
