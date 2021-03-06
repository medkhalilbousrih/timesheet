package esprit.controller;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import esprit.dto.MissionDto;
import esprit.entities.Employe;
import esprit.entities.Mission;
import esprit.services.IEmployeService;
import esprit.services.IEntrepriseService;
import esprit.services.ITimesheetService;

@RestController
public class RestControlTimesheet {

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;

	@PostMapping("/ajouterMission")
	@ResponseBody
	public int ajouterMission(@RequestBody MissionDto missionDto) {
		ModelMapper modelMapper = new ModelMapper();
		Mission mission = modelMapper.map(missionDto, Mission.class);
		itimesheetservice.ajouterMission(mission);
		return mission.getId();
	}

	@PutMapping(value = "/affecterMissionADepartement/{idmission}/{iddept}")
	public void affecterMissionADepartement(@PathVariable("idmission") int missionId,
			@PathVariable("iddept") int depId) {
		itimesheetservice.affecterMissionADepartement(missionId, depId);
	}

	@PostMapping("/ajouterTimesheet/idmission/idemp/dated/datef")
	@ResponseBody
	public void ajouterTimesheet(@PathVariable("idmission") int missionId, @PathVariable("idemp") int employeId,
			@PathVariable("dated") Date dateDebut, @PathVariable("datef") Date dateFin) {
		itimesheetservice.ajouterTimesheet(missionId, employeId, dateDebut, dateFin);
	}

	@PutMapping(value = "/validerTimesheet/{idmission}/{idemp}/{dated}/{datef}/{idval}")
	public void validerTimesheet(@PathVariable("idmission") int missionId, @PathVariable("idemp") int employeId,
			@PathVariable("dated") Date dateDebut, @PathVariable("datef") Date dateFin,
			@PathVariable("idval") int validateurId) {
		itimesheetservice.validerTimesheet(missionId, employeId, dateDebut, dateFin, validateurId);
	}

	@GetMapping(value = "findAllMissionByEmployeJPQL/{idemp}")
	@ResponseBody
	public List<Mission> findAllMissionByEmployeJPQL(@PathVariable("idemp") int employeId) {

		return itimesheetservice.findAllMissionByEmployeJPQL(employeId);
	}

	@GetMapping(value = "getAllEmployeByMission/{idmission}")
	@ResponseBody
	public List<Employe> getAllEmployeByMission(@PathVariable("idmission") int missionId) {

		return itimesheetservice.getAllEmployeByMission(missionId);
	}
}