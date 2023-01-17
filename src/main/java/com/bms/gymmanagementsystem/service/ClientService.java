package com.bms.gymmanagementsystem.service;

import com.bms.gymmanagementsystem.dto.ClientDto;
import com.bms.gymmanagementsystem.dto.converter.ClientDtoConverter;
import com.bms.gymmanagementsystem.exception.ClientAlreadyExistException;
import com.bms.gymmanagementsystem.exception.ClientNotFoundException;
import com.bms.gymmanagementsystem.model.Address;
import com.bms.gymmanagementsystem.model.Client;
import com.bms.gymmanagementsystem.repository.ClientRepository;
import com.bms.gymmanagementsystem.request.address.UpdateAddressRequest;
import com.bms.gymmanagementsystem.request.client.CreateClientRequest;
import com.bms.gymmanagementsystem.request.client.UpdateClientRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientDtoConverter converter;
    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    public ClientService(ClientRepository clientRepository, ClientDtoConverter converter) {
        this.clientRepository = clientRepository;
        this.converter = converter;
    }

    public void createClient(final CreateClientRequest request) {
        checkIfClientExists(request.getEmail());

        Client client = new Client(
                request.getFirstName(),
                request.getLastName(),
                request.getAge(),
                request.getGender(),
                new Address(
                        request.getAddress().getStreet(),
                        request.getAddress().getCity(),
                        request.getAddress().getState(),
                        request.getAddress().getZipCode()
                ),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getPassword()
        );

        clientRepository.save(client);
        logger.info("Client created successfully");
    }

    public void updateClient(final String id, final UpdateClientRequest request) {
        Client client = getClientById(id);

        if (!client.getEmail().equals(request.getEmail())) {
            checkIfClientExists(request.getEmail());
        }

        Client updatedClient = new Client(
                client.getId(),
                request.getFirstName(),
                request.getLastName(),
                request.getAge(),
                client.getGender(),
                client.getAddress(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getPassword(),
                client.getMemberships(),
                client.getPayments(),
                client.getSchedules(),
                client.getTransactions(),
                client.getReports()
        );

        clientRepository.save(updatedClient);
        logger.info("Client updated successfully with id: {}", id);
    }

    public void updateClientAddress(final String id, final UpdateAddressRequest request) {
        Client client = getClientById(id);

        Client updatedClient = new Client(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getAge(),
                client.getGender(),
                new Address(
                        client.getAddress().getId(),
                        request.getStreet(),
                        request.getCity(),
                        request.getState(),
                        request.getZipCode()
                ),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getPassword(),
                client.getMemberships(),
                client.getPayments(),
                client.getSchedules(),
                client.getTransactions(),
                client.getReports()
        );

        clientRepository.save(updatedClient);
        logger.info("Client address updated successfully with id: {}", id);
    }

    public void deleteClient(final String id) {
        clientRepository.delete(getClientById(id));
        logger.info("Client deleted successfully with id: {}", id);
    }

    public ClientDto searchClientByName(final String firstName, final String lastName) {
        Client client = clientRepository
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName).orElseThrow(() -> {
                            logger.error("Client not found with first name: {} and last name: {}", firstName, lastName);
                            throw new ClientNotFoundException("Client not found with first name: " + firstName
                                    + " and last name: " + lastName);
                        }
                );

        logger.info("Client found with first name: {} and last name: {}", firstName, lastName);
        return converter.convert(client);
    }

    public Set<ClientDto> findAllClients() {
        Set<Client> clients = new HashSet<>(clientRepository.findAll());

        if (clients.isEmpty()) {
            logger.error("Client list is empty");
            throw new ClientNotFoundException("Client list is empty");
        }

        logger.info("Client list retrieved successfully");
        return converter.convert(clients);
    }

    protected Client getClientById(final String id) {
        return clientRepository.findById(id).orElseThrow(() -> {
            logger.error("Client with id {} not found", id);
            return new RuntimeException("Client with id " + id + " not found");
        });
    }

    private void checkIfClientExists(final String email) {
        if (clientRepository.existsByEmail(email)) {
            logger.error("Client with email {} already exists", email);
            throw new ClientAlreadyExistException("Client with email " + email + " already exists");
        }
    }
}
