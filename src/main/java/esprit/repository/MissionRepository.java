package esprit.repository;

import org.springframework.data.repository.CrudRepository;

import esprit.entities.Mission;



public interface MissionRepository extends CrudRepository<Mission, Integer> {

}
