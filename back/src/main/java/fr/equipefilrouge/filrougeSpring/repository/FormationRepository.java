package fr.equipefilrouge.filrougeSpring.repository;

import fr.equipefilrouge.filrougeSpring.entity.Formation;
import fr.equipefilrouge.filrougeSpring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FormationRepository extends JpaRepository<Formation, Long> {
    /**
     * Requête pour compter le nombre de formations
     * @return le nombre de formations trouvés
     */
    @Query("SELECT COUNT(DISTINCT f.id) FROM Formation f")
    Long countFormation();
}
