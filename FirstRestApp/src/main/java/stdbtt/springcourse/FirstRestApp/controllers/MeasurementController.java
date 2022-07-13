package stdbtt.springcourse.FirstRestApp.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import stdbtt.springcourse.FirstRestApp.dto.MeasurementDTO;
import stdbtt.springcourse.FirstRestApp.models.Measurement;
import stdbtt.springcourse.FirstRestApp.services.MeasurementService;
import stdbtt.springcourse.FirstRestApp.util.InvalidMeasurementValuesException;
import stdbtt.springcourse.FirstRestApp.util.MeasurementErrorResponse;
import stdbtt.springcourse.FirstRestApp.util.MeasurementValidator;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    private final ModelMapper modelMapper;

    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        measurementValidator.validate(measurementDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message = new StringBuilder();
            bindingResult.getFieldErrors()
                    .forEach(e -> message.append("field: ")
                            .append(e.getField())
                            .append(e.getDefaultMessage())
                            .append(";"));
            throw new InvalidMeasurementValuesException(message.toString());
        }

        Measurement measurement = modelMapper.map(measurementDTO, Measurement.class);
        System.out.println("sensor after mapping: "+measurement.getSensor().getName());
        measurementService.add(measurement);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping
    public List<MeasurementDTO> findAll(){
        return measurementService.findAll().stream()
                .map(measurement -> modelMapper.map(measurement, MeasurementDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDaysCount(){
        return measurementService.getRainyDaysCount();
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(InvalidMeasurementValuesException e){
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                e.getMessage(), System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
