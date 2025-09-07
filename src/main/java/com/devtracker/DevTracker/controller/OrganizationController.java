package com.devtracker.DevTracker.controller;

import com.devtracker.DevTracker.dto.organization.OrganizationCreateDTO;
import com.devtracker.DevTracker.dto.organization.OrganizationCreateResponseDTO;
import com.devtracker.DevTracker.dto.organization.OrganizationDTO;
import com.devtracker.DevTracker.dto.organization.OrganizationJoinDTO;
import com.devtracker.DevTracker.model.Organization;
import com.devtracker.DevTracker.services.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping("/create")
    public ResponseEntity<OrganizationCreateResponseDTO> createOrganization(@RequestBody OrganizationCreateDTO dto) {
        Organization org = organizationService.createOrganization(dto);
        return ResponseEntity.ok(new OrganizationCreateResponseDTO(
                org.getOrgId(), org.getName(), org.getDescription(),
                org.getCreatedAt(), org.getOwner().getUserId(), org.getJoinPasscode()
        ));
    }

    @GetMapping("/{orgId}/passcode/{ownerId}")
    public ResponseEntity<String> getPasscode(@PathVariable int orgId, @PathVariable int ownerId) {
        String passcode = organizationService.getPasscode(orgId, ownerId);
        return ResponseEntity.ok(passcode);
    }


    @PostMapping("/join")
    public ResponseEntity<String> joinOrganization(@RequestBody OrganizationJoinDTO dto) {
        return ResponseEntity.ok(organizationService.joinOrganization(dto));
    }

    @PostMapping("/{orgId}/regenerate/{ownerId}")
    public ResponseEntity<String> regeneratePasscode(@PathVariable int orgId, @PathVariable int ownerId) {
        String newCode = organizationService.regeneratePasscode(orgId, ownerId);
        return ResponseEntity.ok(newCode);
    }
}
