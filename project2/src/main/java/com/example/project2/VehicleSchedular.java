package com.example.project2;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Component
public class VehicleSchedular {
    private RestTemplate restTemplate = new RestTemplate();
    private int id = 1;

    @Scheduled(fixedRate = 60000)
    public void addVehicle() {
        String post = "http://localhost:8080/addVehicle";
        Random r = new Random();

        Vehicle v = new Vehicle( "" + r.nextInt(), r.nextInt(40)+1986,r.nextInt(30000)+15000 );
        restTemplate.postForObject(post, v, Vehicle.class);
        id++;
    }

    @Scheduled(fixedRate = 120000)
    public void deleteVehicle() {
        String delete = "http://localhost:8080/deleteVehicle/";
        Random r = new Random();

        restTemplate.delete(delete+r.nextInt(id)%100);
    }

    @Scheduled(fixedRate = 30000)
    public void updateVehicle() {
        String post = "http://localhost:8080/updateVehicle";
        Random r = new Random();

        Vehicle v = new Vehicle(r.nextInt(id), "Van", 1986,15000 );
        restTemplate.put(post, v, Vehicle.class);

    }

    @Scheduled(cron = "0 0 * * * *")
    public void getLatestVehicles() {
        String post = "http://localhost:8080/getLatestVehicles";
        restTemplate.getForObject(post, List.class);

    }


}
