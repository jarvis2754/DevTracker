package com.devtracker.DevTracker.repository;

import com.devtracker.DevTracker.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Integer> {
    Optional<Organization> findByOrgId(int orgId);
    Optional<Organization> findByJoinPasscode(String passcode);
}
