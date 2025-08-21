package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.BikeDao;
import com.jesus.bikeandride.Model.BikeDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final List<BikeDdbb> bikes = new ArrayList<>();
    @Autowired
    private BikeDao bikedao;

    @GetMapping("/listaBikes/")
    public List<BikeDdbb> getAllBikes() {
        return bikes;
    }

    @GetMapping("/getListBikeByUserId/{id}")
    public List<BikeDdbb> getListBikeByUserId(@PathVariable Long id) {
        return bikedao.getListBikeByUserId(id);
    }

    @PostMapping("/create")
    public BikeDdbb createBike(@RequestBody BikeDdbb newBike) {
        return bikedao.createBike(newBike);
    }
/*
    @PostMapping("/bikes/")
    public BikeDdbb addBike(@RequestBody BikeDdbb newBike){
        int newId = bikes.size() + 1;
        newBike.setIdBike(newId);
        bikes.add(newBike);
        return newBike;
    }

 */
}
