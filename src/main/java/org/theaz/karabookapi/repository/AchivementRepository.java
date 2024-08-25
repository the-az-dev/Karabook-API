package org.theaz.karabookapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.theaz.karabookapi.entity.Achivement;

public interface AchivementRepository extends JpaRepository<Achivement, Long> {
    public Achivement findByAchivementId(Long Id);

    public void deleteByAchivementId(Long id);
}
