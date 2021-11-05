package esprit;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import esprit.entities.Mission;
import esprit.services.ITimesheetService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TimesheetServiceTest {

	@Autowired
	ITimesheetService ts;

	@Test
	public void testAjouterMission() {

		Mission mission = new Mission("formation", "Formation Angular");
		ts.ajouterMission(mission);

	}

	@Test
	public void testAffectrMission() {

		ts.affecterMissionADepartement(1, 2);

	}

	@Test
	public void testajouterTimesheet() {

		Date dateTime = new Date("08/07/2019");
		ts.ajouterTimesheet(1, 1, dateTime, dateTime);

	}

	/*
	 * @Test }
	 */
	@Test
	public void testfindAllMissionByEmployeJPQL() {

		ts.findAllMissionByEmployeJPQL(50);

	}

	@Test
	public void testgetAllEmployeByMission() {

		ts.getAllEmployeByMission(10);

	}
}
