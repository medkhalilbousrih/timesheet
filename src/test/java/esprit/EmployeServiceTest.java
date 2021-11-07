package esprit;

import org.junit.jupiter.api.Test;
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
public class EmployeServiceTest {

	@Autowired
	EmployeServiceImpl em;

	@Autowired
	IEmployeService employeService;

	@Test
	public void testAjouterEmploye() {
		Employe employe = new Employe("aze", "kh", "aalop", true, Role.ADMINISTRATEUR);
		employeService.ajouterEmploye(employe);

	}

	@Test
	public void testMettreAjourEmailByEmployeId() {
		employeService.mettreAjourEmailByEmployeId("test5@gmail.com", 3);
	}

	@Test
	public void testGetAllEmployes() {
		employeService.getAllEmployes();

	}
	
    @Test
    public void testDeleteEmployeById() {
    	employeService.deleteEmployeById(2);
    }
    
    @Test
    public void testgetEmployePrenomById() {
    	employeService.getEmployePrenomById(1);
    }
}
