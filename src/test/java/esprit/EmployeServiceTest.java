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

import esprit.entities.Employe;
import esprit.entities.Role;
import esprit.services.EmployeServiceImpl;
import esprit.services.IEmployeService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class EmployeServiceTest {

	@Autowired
	EmployeServiceImpl em;

	@Autowired
	IEmployeService employeService;

	@Test
	@Order(1)
	void testAjouterEmploye() {
		Employe employe = new Employe("aze", "kh", "aalop", true, Role.ADMINISTRATEUR);
		Employe employe1 = new Employe("qqqq", "rrrr", "kwala", true, Role.CHEF_DEPARTEMENT);
		assertNotEquals(0, employeService.ajouterEmploye(employe));
		assertNotEquals(0, employeService.ajouterEmploye(employe1));
		

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
    	assertNotEquals(0,employeService.deleteEmployeById(1));
    	
    }
    
    @Test
    @Order(4)
    void testgetEmployePrenomById() {
    	assertNotNull(employeService.getEmployePrenomById(2));
    }
}
