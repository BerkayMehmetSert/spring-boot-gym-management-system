package com.bms.gymmanagementsystem.service;

import com.bms.gymmanagementsystem.dto.TrainerDto;
import com.bms.gymmanagementsystem.dto.converter.TrainerDtoConverter;
import com.bms.gymmanagementsystem.exception.TrainerNotFoundException;
import com.bms.gymmanagementsystem.model.Address;
import com.bms.gymmanagementsystem.model.Trainer;
import com.bms.gymmanagementsystem.repository.TrainerRepository;
import com.bms.gymmanagementsystem.request.address.UpdateAddressRequest;
import com.bms.gymmanagementsystem.request.trainer.CreateTrainerRequest;
import com.bms.gymmanagementsystem.request.trainer.UpdateTrainerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrainerService {
    private final TrainerRepository trainerRepository;
    private final TrainerDtoConverter converter;
    private static final Logger logger = LoggerFactory.getLogger(TrainerService.class);

    public TrainerService(TrainerRepository trainerRepository,
                          TrainerDtoConverter converter) {
        this.trainerRepository = trainerRepository;
        this.converter = converter;
    }

    public void createTrainer(final CreateTrainerRequest request) {
        checkIfTrainerExists(request.getFirstName(), request.getLastName());

        Trainer trainer = new Trainer(
                request.getFirstName(),
                request.getLastName(),
                request.getGender(),
                request.getSalary(),
                new Address(
                        request.getAddress().getStreet(),
                        request.getAddress().getCity(),
                        request.getAddress().getState(),
                        request.getAddress().getZipCode()
                )
        );

        trainerRepository.save(trainer);
        logger.info("Trainer created successfully");
    }

    public void updateTrainer(final String id, final UpdateTrainerRequest request) {
        Trainer trainer = getTrainerById(id);

        if (!trainer.getFirstName().equals(request.getFirstName()) || !trainer.getLastName().equals(request.getLastName())) {
            checkIfTrainerExists(request.getFirstName(), request.getLastName());
        }

        Trainer updatedTrainer = new Trainer(
                trainer.getId(),
                request.getFirstName(),
                request.getLastName(),
                trainer.getGender(),
                request.getSalary(),
                trainer.getAddress(),
                trainer.getSchedules()
        );

        trainerRepository.save(updatedTrainer);
        logger.info("Trainer updated successfully with id: {}", id);
    }

    public void updateTrainerAddress(final String id, final UpdateAddressRequest request) {
        Trainer trainer = getTrainerById(id);

        Trainer updatedTrainer = new Trainer(
                trainer.getId(),
                trainer.getFirstName(),
                trainer.getLastName(),
                trainer.getGender(),
                trainer.getSalary(),
                new Address(
                        trainer.getAddress().getId(),
                        request.getStreet(),
                        request.getCity(),
                        request.getState(),
                        request.getZipCode()
                ),
                trainer.getSchedules()
        );

        trainerRepository.save(updatedTrainer);
        logger.info("Trainer address updated successfully with id: {}", id);
    }

    public void updateTrainerSalary(final String id, final Double salary) {
        Trainer trainer = getTrainerById(id);

        Trainer updatedTrainer = new Trainer(
                trainer.getId(),
                trainer.getFirstName(),
                trainer.getLastName(),
                trainer.getGender(),
                salary,
                trainer.getAddress(),
                trainer.getSchedules()
        );

        trainerRepository.save(updatedTrainer);
        logger.info("Trainer salary updated successfully with id: {}", id);
    }

    public void deleteTrainer(final String id) {
        trainerRepository.delete(getTrainerById(id));
        logger.info("Trainer deleted successfully with id: {}", id);
    }

    public TrainerDto searchTrainerByName(final String firstName, final String lastName) {
        Trainer trainer = trainerRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> {
                    logger.error("Trainer not found with first name: {} and last name: {}", firstName, lastName);
                    throw new TrainerNotFoundException("Trainer not found with name: " + firstName + " " + lastName);
                });

        return converter.convert(trainer);
    }

    public Set<TrainerDto> findAllTrainers() {
        Set<Trainer> trainers = new HashSet<>(trainerRepository.findAll());

        if (trainers.isEmpty()) {
            logger.error("Trainer list is empty");
            throw new TrainerNotFoundException("Trainer list is empty");
        }

        logger.info("Found {} trainers", trainers.size());
        return converter.convert(trainers);
    }

    protected Trainer getTrainerById(final String id) {
        return trainerRepository.findById(id).orElseThrow(() -> {
            logger.error("Trainer with id {} not found", id);
            throw new TrainerNotFoundException("Trainer with id " + id + " not found");
        });
    }

    private void checkIfTrainerExists(final String firstName, final String lastName) {
        if (trainerRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            logger.error("Trainer with name {} {} already exists", firstName, lastName);
            throw new TrainerNotFoundException("Trainer with name " + firstName + " " + lastName + " already exists");
        }
    }
}
