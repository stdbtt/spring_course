package stdbtt.springcourse.FirstRestApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import stdbtt.springcourse.FirstRestApp.dto.MeasurementDTO;
import stdbtt.springcourse.FirstRestApp.dto.SensorDTO;
import stdbtt.springcourse.FirstRestApp.services.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        if(sensorService.findSensorByName(measurementDTO.getSensor().getName())==null)
            errors.rejectValue("sensor", "", "Sensor with this name["+ measurementDTO.getSensor().getName()+"] is not register");
    }
}
