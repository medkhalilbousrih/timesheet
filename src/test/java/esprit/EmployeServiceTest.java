package esprit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import esprit.entities.Departement;
import esprit.entities.Employe;
import esprit.entities.Role;
import esprit.services.EmployeServiceImpl;
import esprit.services.IEmployeService;
import esprit.services.IEntrepriseService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class EmployeServiceTest {

	@Autowired
	EmployeServiceImpl em;

	@Autowired
	IEmployeService employeService;
	
	@Autowired
	private IEmployeService iEmployeService;
	@Autowired
	IEntrepriseService ientrepriseservice;

	
	@Test
	@Order(1)
	void testAjouterEmploye() {
		Employe employe = new Employe("aze", "kh", "aalop", true, Role.ADMINISTRATEUR);
		Employe employe2 = new Employe("axxx", "ggg", "aalop", true, Role.ADMINISTRATEUR);
		Employe employe1 = new Employe("qqqq", "rrrr", "kwala", true, Role.CHEF_DEPARTEMENT);
		assertNotEquals(0, employeService.ajouterEmploye(employe));
		assertNotEquals(0, employeService.ajouterEmploye(employe1));
		assertNotEquals(0, employeService.ajouterEmploye(employe2));

	}

	@Test
	@Order(2)
	void testMettreAjourEmailByEmployeId() {
		assertNotEquals(0, employeService.mettreAjourEmailByEmployeId("test5@gmail.com", 2));
	}

	@Test
	@Order(3)
	void testGetAllEmployes() {
		assertNotEquals(0, employeService.getAllEmployes().size());

	}

	@Test
	@Order(5)
	void testDeleteEmployeById() {
		assertNotEquals(0, employeService.deleteEmployeById(1));

	}

	@Test
	@Order(4)
	void testgetEmployePrenomById() {
		assertNotNull(employeService.getEmployePrenomById(2));
	}

	

	/*@Test
	@Order(6)
	void testAffecterEmployeADepartement() {
		Departement department = new Departement("test");
		ientrepriseservice.ajouterDepartement(department);
		Employe employe = new Employe("hello", "world", "aalop", true, Role.ADMINISTRATEUR);
		iEmployeService.ajouterEmploye(employe);
		assertNotEquals(0,iEmployeService.affecterEmployeADepartement(3, 2));
	}*/
	
	/*
	@Test
	@Order(7)
	void testDesaffecterEmployeDuDepartement() {
		iEmployeService.desaffecterEmployeDuDepartement(3,2);

	}*/
}
