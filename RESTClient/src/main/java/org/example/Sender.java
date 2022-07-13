package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.dto.SensorDTO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class Sender {

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Autowired
    public Sender(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void registration(SensorDTO sensorDTO) throws JsonProcessingException {
        System.out.println("//registration...");
        String url = "http://localhost:8080/sensors/registration";

        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("name", sensorDTO.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<String>(jsonToSend.toString(), headers);
        String response = restTemplate.postForObject(url, request, String.class);
        if(response.equals("\"OK\""))
            System.out.println("Sensor ["+sensorDTO.getName()+"] was register successfully!");
    }

    public void sendData(double temperature, boolean raining, SensorDTO sensorDTO){
        String url = "http://localhost:8080/measurements/add";
        JSONObject jsonToSend = new JSONObject();
        jsonToSend.put("value", temperature);
        jsonToSend.put("raining", raining);
        jsonToSend.put("sensor", new JSONObject(sensorDTO));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<String>(jsonToSend.toString(), headers);
        String response = restTemplate.postForObject(url, request, String.class);
        if(response.equals("\"OK\""))
            System.out.println("Measurements was add successfully!");
    }

    public void getAllData(){
        String url = "http://localhost:8080/measurements";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }

    public void getRainyDayCount(){
        String url = "http://localhost:8080/measurements/rainyDaysCount";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);
    }
}
