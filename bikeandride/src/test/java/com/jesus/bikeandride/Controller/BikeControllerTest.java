package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.BikeDao;
import com.jesus.bikeandride.Model.BikeDdbb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class BikeControllerTest {
    @InjectMocks
    private BikeController bikeController;

    @Mock
    private BikeDao bikeDao;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        bikeController = new BikeController(this.bikeDao);
    }

    @Test
    void getListBikeByUserId(){
        BikeDdbb bike = new BikeDdbb();
        bike.setIdBike(1L);
        List<BikeDdbb> expectedBikes = Arrays.asList(bike);
        when(bikeDao.getListBikeByUserId(1L)).thenReturn(expectedBikes);
        List<BikeDdbb> resultado = bikeController.getListBikeByUserId(1L);
        assertEquals(1, resultado.size());
    }
}
