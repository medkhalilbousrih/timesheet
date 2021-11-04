package esprit.repository;

import org.springframework.data.repository.CrudRepository;

import esprit.entities.Entreprise;

public interface EntrepriseRepository extends CrudRepository<Entreprise, Integer>  {
	
	
}
