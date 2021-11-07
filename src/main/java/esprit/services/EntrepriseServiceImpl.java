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

	private static final Logger l = Logger.getLogger(EntrepriseServiceImpl.class);

	@Autowired
	EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;

	public int ajouterEntreprise(Entreprise entreprise) {
		try {
			l.info("debut ajouter entreprise");
			entrepriseRepoistory.save(entreprise);
			l.debug("entreprise bien sauvegardé");
			l.info("fin ajouter entreprise");
			return entreprise.getId();
		} catch (Exception e) {
			l.error(e);
			return 0;
		}
	}

	public int ajouterDepartement(Departement dep) {
		try {
			l.info("debut ajouter departement");
			deptRepoistory.save(dep);
			l.debug("departement sauvegardé");
			l.info("fin ajouter departement");
			return dep.getId();
		} catch (Exception e) {
			l.error(e);
			return 0;
		}
	}

	public Departement affecterDepartementAEntreprise(int depId, int entrepriseId) {
		try {
			l.info("debut affection departement");
			Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			Departement depManagedEntity = deptRepoistory.findById(depId).orElse(null);
			if (entrepriseManagedEntity != null && depManagedEntity != null) {
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				Departement d = deptRepoistory.save(depManagedEntity);
				l.debug("departement bien affecter");
				return d;
			} else {
				l.warn("departement non affecter");
				return null;
			}
		} catch (Exception e) {
			l.error(e);
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
	public int deleteEntrepriseById(int entrepriseId) {
		l.info("debut suppression entreprise");
		try {
			Entreprise e = entrepriseRepoistory.findById(entrepriseId).orElse(null);
			if (e != null) {
				entrepriseRepoistory.delete(e);
				l.debug("entreprise supprimé");
				return entrepriseId;
			} else {
				l.warn("entreprise n'existe pas");
				return 0;
			}
		} catch (Exception e) {
			l.error(e);
			return 0;
		}
	}

	@Transactional
	public int deleteDepartementById(int depId) {
		l.info("debut suppression departement");
		try {
			Departement d = deptRepoistory.findById(depId).orElse(null);
			if (d != null) {
				deptRepoistory.delete(d);
				l.debug("departement supprimé");
				return depId;
			} else {
				l.warn("departement n'existe pas");
				return 0;
			}
		} catch (Exception e) {
			l.error(e);
			return 0;
		}
	}

	public Entreprise getEntrepriseById(int entrepriseId) {
		l.info("debut get entreprise");
		Entreprise e = entrepriseRepoistory.findById(entrepriseId).orElse(null);
		if (e != null) {
			l.debug("entreprise trouvé");
			return e;
		} else {
			l.warn("entreprise n'existe pas");
			return null;
		}
	}
}
