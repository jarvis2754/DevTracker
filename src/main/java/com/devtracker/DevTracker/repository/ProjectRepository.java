package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Organization;
import com.devtracker.DevTracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project , Integer> {
    List<Project> findByProjectNameContainingIgnoreCaseAndOrganization(String projectName, Organization org);
    Optional<Project> findTopByOrderByProjectIdAsc();
    List<Project> findByOrganizationOrgId(int orgId);
}
