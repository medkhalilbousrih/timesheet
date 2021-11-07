package esprit.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	private static final Logger l = Logger.getLogger(TimesheetServiceImpl.class);

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

	

	
	public int affecterMissionADepartement(int missionId, int depId) {
		try {
			l.debug("lancement de l'affectation d'une mission");
			Optional <Mission> mission = missionRepository.findById(missionId);
			Optional <Departement> dep = deptRepoistory.findById(depId);
			if (!mission.isPresent() || !dep.isPresent()) {
				return 0 ;
			}
		mission.get().setDepartement(dep.get());
		missionRepository.save(mission.get());
		l.info("affectation d'une mission terminé avec succés");
		return depId;
		}
		catch (Exception e){
			l.error("Erreur dans la méthode affecterMissisionADepartement():"+ e);
		return 1;
		}finally {
			l.info("Méthode ajouterMission() términé !!!!");
		}
		
	}
	 public int  ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin) {
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
			return 1;
		} catch (Exception e) {
			l.error("Erreur dans la methode ajouterTimesheet():" + e);
			return 0 ;
		} finally {
			l.info("Méthode ajouterMission() términé ");
		}
			
	}

public int validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId) {
		
		Optional <Employe> validateur = employeRepository.findById(validateurId);
		Optional <Mission> mission = missionRepository.findById(missionId);
		
		if (!validateur.isPresent() || !mission.isPresent()) {
			return 0;
		}
		
		if(!validateur.get().getRole().equals(Role.CHEF_DEPARTEMENT)){
			l.info("l'employe doit etre chef de departement pour valider une feuille de temps !");
			return 0;
		}
		
		boolean chefDeLaMission = false;
		for(Departement dep : validateur.get().getDepartements()){
			if(dep.getId() == mission.get().getDepartement().getId()){
				chefDeLaMission = true;
				break;
			}
		}
		if(!chefDeLaMission){
			l.info("l'employe doit etre chef de departement de la mission en question");
			return 0;
		}
		TimesheetPK timesheetPK = new TimesheetPK(missionId, employeId, dateDebut, dateFin);
		Timesheet timesheet =timesheetRepository.findBytimesheetPK(timesheetPK);
		timesheet.setValide(true);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		l.info("dateDebut : " + dateFormat.format(timesheet.getTimesheetPK().getDateDebut()));
		return 1;
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
		List<Employe> employes = new ArrayList<>();
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
