package esprit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import esprit.entities.Departement;
import esprit.entities.Entreprise;
import esprit.services.IEntrepriseService;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(OrderAnnotation.class)
class EntrepriseServiceTest {
	@Autowired
	IEntrepriseService es;

	@Test
	@Order(1)
	void testAjouterEntreprise() {
		Entreprise e = new Entreprise("Poulina", "anon");
		assertNotEquals(0, es.ajouterEntreprise(e));
	}

	@Test
	@Order(2)
	void testGetEntreprise() {
		assertNotNull(es.getEntrepriseById(1));
	}

	@Test
	@Order(3)
	void testAjouterDepartement() {
		Departement d = new Departement("Finance");
		Departement d1 = new Departement("Technique");
		assertNotEquals(0, es.ajouterDepartement(d));
		assertNotEquals(0, es.ajouterDepartement(d1));
	}

	@Test
	@Order(4)
	void testAffecterDepartementAEntreprise() {
		assertNotNull(es.affecterDepartementAEntreprise(1, 1));
		assertNotNull(es.affecterDepartementAEntreprise(2, 1));
	}

	@Test
	@Order(5)
	void testGetAlldepartementsParEntreprise() {
		assertNotEquals(0, es.getAllDepartementsNamesByEntreprise(1).size());
	}

	@Test
	@Order(6)
	void testAjouterEntreprise2() {
		Entreprise e = new Entreprise("Telnet", "anon");
		assertNotEquals(0, es.ajouterEntreprise(e));
	}

	@Test
	@Order(7)
	void testSuppressionEntreprise() {
		assertNotEquals(0, es.deleteEntrepriseById(2));
	}

}