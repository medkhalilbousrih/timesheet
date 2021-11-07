package esprit.services;

import java.util.Date;
import java.util.List;

import esprit.entities.Employe;
import esprit.entities.Mission;




public interface ITimesheetService {
	
	public int ajouterMission(Mission mission);
	public int affecterMissionADepartement(int missionId, int depId);
	public int ajouterTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin);
	public int validerTimesheet(int missionId, int employeId, Date dateDebut, Date dateFin, int validateurId);
	public List<Mission> findAllMissionByEmployeJPQL(int employeId);
	public List<Employe> getAllEmployeByMission(int missionId);
}
