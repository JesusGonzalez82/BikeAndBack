package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.BikeDao;
import com.jesus.bikeandride.Model.BikeDdbb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final List<BikeDdbb> bikes = new ArrayList<>();
    @Autowired
    private BikeDao bikedao;

    public BikeController(BikeDao bikedao) {
        this.bikedao = bikedao;
    }

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

    @PatchMapping("/update/{id}")
    public BikeDdbb partialUpdateBike(@PathVariable long id, @RequestBody Map<String, Object> updates){
        try{
        BikeDdbb existingBike = bikedao.getBikeById(id);
        if (existingBike == null){
            throw new RuntimeException("Bicicleta no encontrada");
        }

        updates.forEach((key, value) ->{
           switch (key){
               case "tipo":
                   existingBike.setType((String) value);
                   break;
               case "anio":
                   if (value instanceof Integer){
                       existingBike.setBirthday(Year.of((Integer) value));
                   } else if (value instanceof String){
                       existingBike.setBirthday(Year.parse((String) value));
                   }
                   break;
               case "status":
                   existingBike.setStatus((String) value);
                   break;
           }
        });
        return bikedao.updateBike(existingBike);
    }catch (Exception e){
        throw new RuntimeException("Error al actualizar la bicicleta: " + e.getMessage(), e);
        }
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
