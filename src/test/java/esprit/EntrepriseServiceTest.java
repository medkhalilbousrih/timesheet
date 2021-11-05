package esprit;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import esprit.entities.Departement;
import esprit.entities.Entreprise;
import esprit.services.IEntrepriseService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EntrepriseServiceTest {
	@Autowired
	IEntrepriseService es;

	@Test
	public void testAjouterEntreprise() {
		Entreprise e = new Entreprise("Poulina", "anon");
		assertNotEquals(0, es.ajouterEntreprise(e));
	}

	@Test
	public void testAjouterDepartement() {
		Departement d = new Departement("Finance");
		Departement d1 = new Departement("Technique");
		assertNotEquals(0, es.ajouterDepartement(d));
		assertNotEquals(0, es.ajouterDepartement(d1));
	}

	@Test
	public void testAffecterDepartementAEntreprise() {
		assertNotNull(es.affecterDepartementAEntreprise(1, 1));
		assertNotNull(es.affecterDepartementAEntreprise(2, 1));
	}
}