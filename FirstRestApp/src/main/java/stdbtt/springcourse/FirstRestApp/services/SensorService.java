package stdbtt.springcourse.FirstRestApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stdbtt.springcourse.FirstRestApp.dto.SensorDTO;
import stdbtt.springcourse.FirstRestApp.models.Sensor;
import stdbtt.springcourse.FirstRestApp.repositories.SensorRepository;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    @Transactional(readOnly = true)
    public Sensor findSensorByName(String name){
        return sensorRepository.findSensorByName(name);
    }
}
