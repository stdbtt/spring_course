package stdbtt.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stdbtt.springcourse.FirstRestApp.dto.SensorDTO;
import stdbtt.springcourse.FirstRestApp.models.Sensor;
import stdbtt.springcourse.FirstRestApp.services.SensorService;
import stdbtt.springcourse.FirstRestApp.util.SensorAlreadyExistException;
import stdbtt.springcourse.FirstRestApp.util.SensorErrorResponse;
import stdbtt.springcourse.FirstRestApp.util.SensorValidator;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    private final SensorValidator sensorValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult){

        sensorValidator.validate(sensorDTO, bindingResult);
        if(bindingResult.hasErrors()){
            throw new SensorAlreadyExistException(bindingResult.getFieldError("name").getDefaultMessage());
        }
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorAlreadyExistException e){
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);//404 status
    }

}
