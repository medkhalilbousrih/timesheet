package esprit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import esprit.entities.Mission;
import esprit.services.ITimesheetService;

@SpringBootTest
@RunWith(SpringRunner.class)
class TimesheetServiceTest {

	@Autowired
	ITimesheetService ts;

	@Test
	void testAjouterMission() {

		Mission mission = new Mission("formation", "Formation Angular");
		assertNotEquals(0, ts.ajouterMission(mission));
	}

	@Test
	void testAffectrMission() {

		
		assertNotEquals(0,ts.affecterMissionADepartement(1, 2));
	}

	@Test
	void testajouterTimesheet() {

		Date dateTime = new Date("08/07/2019");
		
		assertNotEquals(0,ts.ajouterTimesheet(1, 1, dateTime, dateTime));
	}

	
	
	@Test
	void testgetAllEmployeByMission() {

		
		assertEquals(0,ts.getAllEmployeByMission(10).size());
	}
}
