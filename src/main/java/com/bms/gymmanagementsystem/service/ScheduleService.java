package com.bms.gymmanagementsystem.service;

import com.bms.gymmanagementsystem.dto.ScheduleDto;
import com.bms.gymmanagementsystem.dto.converter.ScheduleDtoConverter;
import com.bms.gymmanagementsystem.exception.ScheduleNotFoundException;
import com.bms.gymmanagementsystem.helper.DateHelper;
import com.bms.gymmanagementsystem.model.Schedule;
import com.bms.gymmanagementsystem.repository.ScheduleRepository;
import com.bms.gymmanagementsystem.request.schedule.CreateScheduleRequest;
import com.bms.gymmanagementsystem.request.schedule.UpdateScheduleRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ClientService clientService;
    private final TrainerService trainerService;
    private final ScheduleDtoConverter converter;
    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    public ScheduleService(ScheduleRepository scheduleRepository,
                           ClientService clientService,
                           TrainerService trainerService,
                           ScheduleDtoConverter converter) {
        this.scheduleRepository = scheduleRepository;
        this.clientService = clientService;
        this.trainerService = trainerService;
        this.converter = converter;
    }

    public void createSchedule(final CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getSession(),
                request.getActivity(),
                DateHelper.getCurrentDate(),
                request.getStartTime(),
                request.getEndTime(),
                clientService.getClientById(request.getClientId()),
                trainerService.getTrainerById(request.getTrainerId())
        );

        scheduleRepository.save(schedule);
        logger.info("Schedule created successfully");
    }

    public void updateSchedule(final String id, final UpdateScheduleRequest request) {
        Schedule schedule = getScheduleById(id);

        Schedule updatedSchedule = new Schedule(
                schedule.getId(),
                request.getSession(),
                request.getActivity(),
                schedule.getDate(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getClient(),
                schedule.getTrainer()
        );

        scheduleRepository.save(updatedSchedule);
        logger.info("Schedule updated successfully with id: {}", id);
    }

    public void updateScheduleStartTime(final String id, final LocalDateTime startTime) {
        Schedule schedule = getScheduleById(id);

        Schedule updatedSchedule = new Schedule(
                schedule.getId(),
                schedule.getSession(),
                schedule.getActivity(),
                schedule.getDate(),
                startTime,
                schedule.getEndTime(),
                schedule.getClient(),
                schedule.getTrainer()
        );
        scheduleRepository.save(updatedSchedule);
        logger.info("Schedule start time updated successfully with id: {}", id);
    }

    public void updateScheduleEndTime(final String id, final LocalDateTime endTime) {
        Schedule schedule = getScheduleById(id);

        Schedule updatedSchedule = new Schedule(
                schedule.getId(),
                schedule.getSession(),
                schedule.getActivity(),
                schedule.getDate(),
                schedule.getStartTime(),
                endTime,
                schedule.getClient(),
                schedule.getTrainer()
        );
        scheduleRepository.save(updatedSchedule);
        logger.info("Schedule end time updated successfully with id: {}", id);
    }

    public void updateScheduleTrainer(final String id, final String trainerId) {
        Schedule schedule = getScheduleById(id);

        Schedule updatedSchedule = new Schedule(
                schedule.getId(),
                schedule.getSession(),
                schedule.getActivity(),
                schedule.getDate(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getClient(),
                trainerService.getTrainerById(trainerId)
        );
        scheduleRepository.save(updatedSchedule);
        logger.info("Schedule trainer updated successfully with id: {}", id);
    }

    public void deleteSchedule(final String id) {
        scheduleRepository.delete(getScheduleById(id));
        logger.info("Schedule deleted successfully with id: {}", id);
    }

    public Set<ScheduleDto> findAllSchedules() {
        Set<Schedule> schedules = new HashSet<>(scheduleRepository.findAll());

        if (schedules.isEmpty()) {
            logger.error("Schedule list is empty");
            throw new ScheduleNotFoundException("Schedule list is empty");
        }

        logger.info("Schedule list found successfully with size: {}", schedules.size());
        return converter.convert(schedules);
    }

    private Schedule getScheduleById(final String id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Schedule with id {} not found", id);
                    throw new ScheduleNotFoundException("Schedule with id " + id + " not found");
                });
    }
}
