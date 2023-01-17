package com.bms.gymmanagementsystem.service;

import com.bms.gymmanagementsystem.dto.MembershipDto;
import com.bms.gymmanagementsystem.dto.converter.MembershipDtoConverter;
import com.bms.gymmanagementsystem.exception.MembershipNotFoundException;
import com.bms.gymmanagementsystem.helper.DateHelper;
import com.bms.gymmanagementsystem.model.Membership;
import com.bms.gymmanagementsystem.repository.MembershipRepository;
import com.bms.gymmanagementsystem.request.membership.CreateMemberShipRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MembershipService {
    private final MembershipRepository membershipRepository;
    private final ClientService clientService;
    private final MembershipDtoConverter converter;
    private static final Logger logger = LoggerFactory.getLogger(MembershipService.class);

    public MembershipService(MembershipRepository membershipRepository,
                             ClientService clientService,
                             MembershipDtoConverter converter) {
        this.membershipRepository = membershipRepository;
        this.clientService = clientService;
        this.converter = converter;
    }

    public void createMembership(final CreateMemberShipRequest request) {
        Membership membership = new Membership(
                request.getStatus(),
                DateHelper.getCurrentDate(),
                clientService.getClientById(request.getClientId())
        );

        membershipRepository.save(membership);
        logger.info("Membership created successfully");
    }

    public void updateMembershipStatus(final String id, final String status) {
        Membership membership = getMembershipById(id);

        Membership updatedMembership = new Membership(
                membership.getId(),
                status,
                membership.getDate(),
                membership.getClient()
        );

        membershipRepository.save(updatedMembership);
        logger.info("Membership status updated successfully to {}. Id: {}", status, id);
    }

    public void deleteMembership(final String id) {
        membershipRepository.delete(getMembershipById(id));
        logger.info("Membership deleted successfully. Id: {}", id);
    }

    public Set<MembershipDto> findMembershipByClientId(final String clientId) {
        Set<Membership> memberships = membershipRepository.findByClient_Id(clientId);

        if (memberships.isEmpty()) {
            logger.error("Membership not found. Client id: {}", clientId);
            throw new MembershipNotFoundException("Membership not found for client with id: " + clientId);
        }
        logger.info("Found membership for client with id {}", clientId);
        return converter.convert(memberships);
    }

    public Set<MembershipDto> findMembershipByStatus(final String status) {
        Set<Membership> memberships = membershipRepository.findByStatus(status);

        if (memberships.isEmpty()) {
            logger.warn("No memberships found with status {}", status);
            throw new MembershipNotFoundException("No memberships found with status: " + status);
        }

        logger.info("Found {} memberships with status {}", memberships.size(), status);
        return converter.convert(memberships);
    }

    public Set<MembershipDto> findAllMemberships() {
        Set<Membership> memberships = new HashSet<>(membershipRepository.findAll());

        if (memberships.isEmpty()) {
            logger.warn("Membership list is empty");
            throw new MembershipNotFoundException("Membership list is empty");
        }

        logger.info("Found {} memberships", memberships.size());
        return converter.convert(memberships);
    }

    private Membership getMembershipById(final String id) {
        return membershipRepository.findById(id).orElseThrow(() -> {
            logger.error("Membership with id {} not found", id);
            throw new MembershipNotFoundException("Membership with id " + id + " not found");
        });
    }
}
