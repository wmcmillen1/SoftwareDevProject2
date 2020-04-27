package com.example.project2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class VehicleController {
    @Autowired
    private VehicleDao vehicleDao;
    @RequestMapping(value = "/testVehicle", method = RequestMethod.POST)
    public Vehicle testVehice() {
        Vehicle v = new Vehicle(1, "Van", 2000, 4000);
        return v;
    }

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Vehicle addVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        vehicleDao.createVehicle(newVehicle);
        return newVehicle;
    }

    @GetMapping(value = "/getVehicle/{id}")
    public Vehicle getVehicle(@PathVariable("id") int id) throws IOException {
        return vehicleDao.getById(id);
    }

    @RequestMapping(value = "/deleteVehicle/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVehicle(@PathVariable("id") int id) throws IOException {
        Vehicle v = vehicleDao.getById(id);
        if (v == null) {
            return new ResponseEntity<String>("ID not Found", HttpStatus.NOT_FOUND);
        }else {
            vehicleDao.deleteVehicle(id);
            return new ResponseEntity<String>("Entry Deleted", HttpStatus.FOUND);
        }
    }

    @RequestMapping(value = "/updateVehicle", method = RequestMethod.PUT)
    public Vehicle updateVehicle(@RequestBody Vehicle newVehicle) throws IOException {
        if (vehicleDao.getById(newVehicle.getId())== null) {
            return null;
        }
        vehicleDao.updateVehicle(newVehicle);
        return newVehicle;
    }


    @RequestMapping(value = "/getLatestVehicles", method = RequestMethod.GET)
    public List<Vehicle> getLatestVehicles() throws IOException {
        int highestId = vehicleDao.queryLatestVehicle();
        int lowestId = vehicleDao.queryEarliestVehicle();
        ArrayList<Vehicle> list = new ArrayList<>();
        for (int i = highestId; i >= lowestId; i--) {
            if (i == 0)
                break;

            if (list.size() >= 10)
                break;

            if (!(vehicleDao.getById(i) == null)) {
                list.add(vehicleDao.getById(i));
            }
        }
        return list;
    }



}
