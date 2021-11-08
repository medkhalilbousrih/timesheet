package esprit.dto;

import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import esprit.entities.Departement;
import esprit.entities.Timesheet;

public class MissionDto {

	@ManyToOne
	private Departement departement;
	@OneToMany(mappedBy = "mission")
	private List<Timesheet> timesheets;

}