package stdbtt.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import stdbtt.springcourse.FirstRestApp.dto.SensorDTO;
import stdbtt.springcourse.FirstRestApp.models.Sensor;
import stdbtt.springcourse.FirstRestApp.services.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;
        if(sensorService.findSensorByName(sensor.getName())!=null)
            errors.rejectValue("name", "", "Sensor with this name["+ sensor.getName()+"] is already exist");
    }
}
