package com.bms.gymmanagementsystem.controller;

import com.bms.gymmanagementsystem.dto.MembershipDto;
import com.bms.gymmanagementsystem.request.membership.CreateMemberShipRequest;
import com.bms.gymmanagementsystem.service.MembershipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/membership")
public class MembershipController {
    private final MembershipService service;

    public MembershipController(MembershipService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createMembership(@RequestBody CreateMemberShipRequest request) {
        service.createMembership(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateMembershipStatus(@PathVariable String id,
                                                       @RequestParam String status) {
        service.updateMembershipStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembership(@PathVariable String id) {
        service.deleteMembership(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<Set<MembershipDto>> findMembershipByClientId(@PathVariable String clientId) {
        return ResponseEntity.ok(service.findMembershipByClientId(clientId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Set<MembershipDto>> findMembershipByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.findMembershipByStatus(status));
    }

    @GetMapping
    public ResponseEntity<Set<MembershipDto>> findAllMemberships() {
        return ResponseEntity.ok(service.findAllMemberships());
    }
}
