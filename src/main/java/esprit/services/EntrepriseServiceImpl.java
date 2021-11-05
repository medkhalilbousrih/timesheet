package esprit.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import esprit.entities.Departement;
import esprit.entities.Entreprise;
import esprit.repository.DepartementRepository;
import esprit.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	private static final Logger l = Logger.getLogger(IEntrepriseService.class);

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	public int ajouterEntreprise(Entreprise entreprise) {
		l.info("debut ajouter entreprise");
		entrepriseRepoistory.save(entreprise);
		l.debug("entreprise sauvegardé");
		l.info("fin ajouter entreprise");
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		l.info("debut ajouter departement");
		deptRepoistory.save(dep);
		l.debug("departement sauvegardé");
		l.info("fin ajouter departement");
		return dep.getId();
	}

	public Departement affecterDepartementAEntreprise(int depId, int entrepriseId) {
		l.info("debut affection departement");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);

		if (entrepriseManagedEntity != null && depManagedEntity != null) {
			depManagedEntity.setEntreprise(entrepriseManagedEntity);
			Departement d =  deptRepoistory.save(depManagedEntity);
			l.debug("departement bien affecter");
			return d;
		} else {
			l.debug("departement non affecter");
			return null;
		}
	}

	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		l.info("debut lister departements");
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		List<String> depNames = new ArrayList<>();
		if (entrepriseManagedEntity != null) {
			l.debug("liste des departements");
			for (Departement dep : entrepriseManagedEntity.getDepartements()) {
				depNames.add(dep.getName());
			}
		}
		l.info("fin lister departements");
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		l.info("debut suppression entreprise");
		try {
			entrepriseRepoistory.deleteById(entrepriseId);
			l.debug("entreprise supprimé");
		} catch (Exception e) {
			l.error("entreprise n'existe pas");
		}
		l.info("fin suppression entreprise");

	}

	@Transactional
	public void deleteDepartementById(int depId) {
		l.info("debut suppression departement");
		try {
			deptRepoistory.deleteById(depId);
			l.debug("departement supprimé");
		} catch (Exception e) {
			l.error("departement n'existe pas");
		}
		l.info("fin suppression departement");
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		l.info("debut suppression departement");
		Entreprise e = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		if(e != null) {
			l.debug("entreprise trouvé");
			return e;
		}
		else {
			l.debug("entreprise n'existe pas");
			return null;
		}
	}

}
