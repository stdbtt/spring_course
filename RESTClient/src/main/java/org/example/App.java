package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.config.SpringConfig;
import org.example.dto.SensorDTO;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JsonProcessingException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Sender sender = context.getBean("sender", Sender.class);
        sender.getRainyDayCount();

    }
}
