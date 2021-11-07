package esprit.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import esprit.dto.DepartementDto;
import esprit.dto.EntrepriseDto;
import esprit.entities.Departement;
import esprit.entities.Entreprise;
import esprit.services.IEmployeService;
import esprit.services.IEntrepriseService;
import esprit.services.ITimesheetService;

@RestController
public class RestControlEntreprise {

	@Autowired
	IEmployeService iemployeservice;
	@Autowired
	IEntrepriseService ientrepriseservice;
	@Autowired
	ITimesheetService itimesheetservice;
	
	ModelMapper modelMapper = new ModelMapper();


	@PostMapping("/ajouterEntreprise")
	@ResponseBody
	public int ajouterEntreprise(@RequestBody EntrepriseDto ssiiConsulting) {
		Entreprise entreprise = modelMapper.map(ssiiConsulting, Entreprise.class);
		return ientrepriseservice.ajouterEntreprise(entreprise);
	}

	@PutMapping(value = "/affecterDepartementAEntreprise/{iddept}/{identreprise}")
	public void affecterDepartementAEntreprise(@PathVariable("iddept") int depId,
			@PathVariable("identreprise") int entrepriseId) {
		ientrepriseservice.affecterDepartementAEntreprise(depId, entrepriseId);
	}

	@DeleteMapping("/deleteEntrepriseById/{identreprise}")
	@ResponseBody
	public void deleteEntrepriseById(@PathVariable("identreprise") int entrepriseId) {
		ientrepriseservice.deleteEntrepriseById(entrepriseId);
	}

	@GetMapping(value = "getEntrepriseById/{identreprise}")
	@ResponseBody
	public Entreprise getEntrepriseById(@PathVariable("identreprise") int entrepriseId) {

		return ientrepriseservice.getEntrepriseById(entrepriseId);
	}

	@PostMapping("/ajouterDepartement")
	@ResponseBody
	public int ajouterDepartement(@RequestBody DepartementDto dep) {
		Departement depart = modelMapper.map(dep, Departement.class);
		return ientrepriseservice.ajouterDepartement(depart);
	}

	@GetMapping(value = "getAllDepartementsNamesByEntreprise/{identreprise}")
	@ResponseBody
	public List<String> getAllDepartementsNamesByEntreprise(@PathVariable("identreprise") int entrepriseId) {
		return ientrepriseservice.getAllDepartementsNamesByEntreprise(entrepriseId);
	}

	@DeleteMapping("/deleteDepartementById/{iddept}")
	@ResponseBody
	public void deleteDepartementById(@PathVariable("iddept") int depId) {
		ientrepriseservice.deleteDepartementById(depId);

	}
}
