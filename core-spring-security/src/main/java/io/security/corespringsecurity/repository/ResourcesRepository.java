package io.security.corespringsecurity.repository;

import io.security.corespringsecurity.domain.Resources;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepository extends JpaRepository<Resources, Long> {

    @Query("SELECT r FROM Resources r JOIN FETCH r.roles WHERE r.type = 'url' ORDER BY r.orderNum DESC")
    List<Resources> findActiveUrlResources();

}
