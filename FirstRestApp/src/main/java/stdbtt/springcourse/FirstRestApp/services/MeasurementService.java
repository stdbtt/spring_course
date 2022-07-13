package stdbtt.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.springcourse.FirstRestApp.models.Measurement;
import stdbtt.springcourse.FirstRestApp.models.Sensor;
import stdbtt.springcourse.FirstRestApp.repositories.MeasurementRepository;
import stdbtt.springcourse.FirstRestApp.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeasurementService {
    private final MeasurementRepository measurementRepository;

    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void add(Measurement measurement) {
        Sensor sensor = sensorRepository.findSensorByName(measurement.getSensor().getName());
        sensor.setMeasurements(Collections.singletonList(measurement));
        measurement.setSensor(sensor);
        measurement.setMadeAt(LocalDateTime.now());
        measurementRepository.save(measurement);
    }

    @Transactional(readOnly = true)
    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    @Transactional(readOnly = true)
    public long getRainyDaysCount(){
       /* return measurementRepository.findAllByRainingIsTrue().stream()
                .map(Measurement::getMadeAt)
                .map(d -> ""+ d.getDayOfMonth()+d.getMonth().getValue()+d.getYear())
                .distinct()
                .count();*/
        return measurementRepository.countMeasurementByRainingIsTrue();


    }
}
