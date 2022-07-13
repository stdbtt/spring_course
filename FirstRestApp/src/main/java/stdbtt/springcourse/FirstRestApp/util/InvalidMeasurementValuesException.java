package stdbtt.springcourse.FirstRestApp.util;

public class InvalidMeasurementValuesException extends RuntimeException{
    public InvalidMeasurementValuesException(String msg){
        super(msg);
    }
}
