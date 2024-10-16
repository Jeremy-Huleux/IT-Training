package fr.equipefilrouge.filrougeSpring.controller;

import fr.equipefilrouge.filrougeSpring.dto.FormationDTO;
import fr.equipefilrouge.filrougeSpring.dto.SousThemeDTO;
import fr.equipefilrouge.filrougeSpring.entity.Formation;
import fr.equipefilrouge.filrougeSpring.entity.SousTheme;
import fr.equipefilrouge.filrougeSpring.entity.Theme;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import fr.equipefilrouge.filrougeSpring.services.impl.FormationServiceImpl;
import fr.equipefilrouge.filrougeSpring.services.impl.SousThemeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller pour une formation
 */
@RestController
@RequestMapping("/formations")
@CrossOrigin
public class FormationController {

    /**
     * Déclaration du service Formation
     */
    private final FormationServiceImpl formationServiceImpl;

    private final SousThemeServiceImpl sousThemeService;

    /**
     * Constructeur du controller Formation
     * @param formationServiceImpl le service de la formation
     */
    public FormationController(FormationServiceImpl formationServiceImpl, SousThemeServiceImpl sousThemeService) {
        this.formationServiceImpl = formationServiceImpl;
        this.sousThemeService = sousThemeService;
    }

    /**
     * Méthode pour créer une nouvelle formation
     * @param formationDTO La formation à créer
     * @return la formation nouvellement créée
     */
    @PostMapping("/create")
    public Formation createFormation(@RequestBody FormationDTO formationDTO) {
        // Récupère le sous-thème correspondant à l'ID
        SousTheme sousTheme = sousThemeService.findById(formationDTO.getSousThemeId());
        // Crée une nouvelle formation
        Formation formation = new Formation(formationDTO.getNom(), formationDTO.getPrix(),
                formationDTO.getDescription(), formationDTO.getImg(), sousTheme);
        // Enregistre la formation
        return formationServiceImpl.create(formation);
    }

    /**
     * Création d'une liste de formations
     * @param formations la liste des formations à créer
     * @return la liste des formations nouvellement créée
     */
    @PostMapping("/createFormations")
    public List<Formation> listFormations(@RequestBody List<Formation> formations) {
        return formationServiceImpl.createListe(formations);
    }


    /**
     * Méthode pour mettre à jour une formation
     * @param formation la formation à mettre à jour
     * @return la formation mis à jour
     */
    @PatchMapping("/update")
    public Formation updateFormation(@RequestBody Formation formation) {
        return formationServiceImpl.update(formation);
    }

    /**
     * Affiche toutes les formations
     * @return la liste des formations
     */
    @GetMapping("/all")
    public List<Formation> getAllFormations() {
        return formationServiceImpl.findAll();
    }

    /**
     * Méthode pour rechercher une session avec son identifiant
     * @param id l'identifiant de la session
     * @return la session recherchée
     */
    @GetMapping("/{id}")
    public Formation getFormationById(@PathVariable Long id) {
        return formationServiceImpl.findById(id);
    }

    /**
     * Supprimer une formation selon son id
     * @param id l'identifiant de la formation
     * @return la formation supprimée
     */
    @DeleteMapping("/delete/{id}")
    public Formation deleteFormationById(@PathVariable Long id) {
        return formationServiceImpl.deleteById(id);
    }

    /**
     * Renvoyer le nombre de formations en BDD
     * @return le nombre de formations
     */
    @GetMapping("/nbFormations")
    public Long nbFormations() {
        return formationServiceImpl.countFormations();
    }
    /**
     * Supprimer toutes les formations
     */
    @DeleteMapping("/delete/all")
    public void deleteAllFormation() {
        formationServiceImpl.deleteAll();
    }

}
