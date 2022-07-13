package stdbtt.springcourse.FirstRestApp.util;

public class SensorAlreadyExistException extends RuntimeException{
    public SensorAlreadyExistException(String msg){
        super(msg);
    }
}
