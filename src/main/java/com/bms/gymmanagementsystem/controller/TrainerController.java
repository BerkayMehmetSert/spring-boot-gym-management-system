package com.bms.gymmanagementsystem.controller;

import com.bms.gymmanagementsystem.dto.TrainerDto;
import com.bms.gymmanagementsystem.request.address.UpdateAddressRequest;
import com.bms.gymmanagementsystem.request.trainer.CreateTrainerRequest;
import com.bms.gymmanagementsystem.request.trainer.UpdateTrainerRequest;
import com.bms.gymmanagementsystem.service.TrainerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/v1/trainer")
public class TrainerController {
    private final TrainerService service;

    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createTrainer(@RequestBody CreateTrainerRequest request) {
        service.createTrainer(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrainer(@PathVariable String id,
                                              @RequestBody UpdateTrainerRequest request) {
        service.updateTrainer(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/address")
    public ResponseEntity<Void> updateTrainerAddress(@PathVariable String id,
                                                     @RequestBody UpdateAddressRequest request) {
        service.updateTrainerAddress(id, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/salary")
    public ResponseEntity<Void> updateTrainerSalary(@PathVariable String id,
                                                    @RequestBody Double salary) {
        service.updateTrainerSalary(id, salary);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainer(@PathVariable String id) {
        service.deleteTrainer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<TrainerDto> searchTrainerByName(@RequestParam String firstName,
                                                          @RequestParam String lastName) {
        return ResponseEntity.ok(service.searchTrainerByName(firstName, lastName));
    }

    @GetMapping
    public ResponseEntity<Set<TrainerDto>> findAllTrainers() {
        return ResponseEntity.ok(service.findAllTrainers());
    }
}
