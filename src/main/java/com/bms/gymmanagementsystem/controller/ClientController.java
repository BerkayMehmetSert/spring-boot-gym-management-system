package com.bms.gymmanagementsystem.controller;

import com.bms.gymmanagementsystem.dto.ClientDto;
import com.bms.gymmanagementsystem.request.address.UpdateAddressRequest;
import com.bms.gymmanagementsystem.request.client.CreateClientRequest;
import com.bms.gymmanagementsystem.request.client.UpdateClientRequest;
import com.bms.gymmanagementsystem.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/client")
public class ClientController {
    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createClient(@RequestBody CreateClientRequest request) {
        service.createClient(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable String id, @RequestBody UpdateClientRequest request) {
        service.updateClient(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Void> updateClientAddress(@PathVariable String id,
                                                    @RequestBody UpdateAddressRequest request) {
        service.updateClientAddress(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable String id) {
        service.deleteClient(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<ClientDto> searchClientByName(@RequestParam String firstName,
                                                        @RequestParam String lastName) {
        return ResponseEntity.ok(service.searchClientByName(firstName, lastName));
    }

    @GetMapping
    public ResponseEntity<Set<ClientDto>> findAllClients() {
        return ResponseEntity.ok(service.findAllClients());
    }
}
