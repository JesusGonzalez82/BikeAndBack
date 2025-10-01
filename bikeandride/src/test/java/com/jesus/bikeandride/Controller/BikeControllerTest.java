package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.BikeDao;
import com.jesus.bikeandride.Model.Bike;
import com.jesus.bikeandride.Model.BikeDdbb;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests Unitarios BikeController")
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
    @DisplayName("Debe crear una nueva bicicleta")
    void createBike(){
        BikeDdbb newBike = createTestBike(1L, "Giant", "mtb");
        BikeDdbb createdBike = createTestBike(1L, "Giant", "mtb");

        when(bikeDao.createBike(newBike)).thenReturn(createdBike);

        BikeDdbb resultado = bikeController.createBike(newBike);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getIdBike());
        verify(bikeDao, times(1)).createBike(newBike);
    }

    @Test
    @DisplayName("Debe obtener el listado de todas las bicicletas")
    void getAllBikes(){
        List<BikeDdbb> resultado = bikeController.getAllBikes();

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
    }

    @Test
    @DisplayName("Debe obtener la lista de bicicletas por el id de usuario")
    void getListBikeByUserId(){
        Long userId = 1L;
        BikeDdbb bike1 = createTestBike(1L, "Specialized", "mtb");
        BikeDdbb bike2 = createTestBike(2L, "Orbea", "road");
        List<BikeDdbb> expectedBikes = Arrays.asList(bike1, bike2);

        when(bikeDao.getListBikeByUserId(userId)).thenReturn(expectedBikes);

        List<BikeDdbb> resultado = bikeController.getListBikeByUserId(userId);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Specialized", resultado.get(0).getBike_brand());
        assertEquals("Orbea", resultado.get(1).getBike_brand());
        assertEquals(1L, resultado.get(0).getIdBike());
        assertEquals(2L, resultado.get(1).getIdBike());

        verify(bikeDao, times(1)).getListBikeByUserId(userId);
    }

    @Test
    @DisplayName("Retornar lista vacia cuando un usuario no tiene bicicletas")
    void getListBikesByUserIdNothingBike(){
        Long userId = 666L;
        when(bikeDao.getListBikeByUserId(userId)).thenReturn(Arrays.asList());

        List<BikeDdbb> resultado = bikeController.getListBikeByUserId(userId);

        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(bikeDao, times(1)).getListBikeByUserId(userId);
    }

    @Test
    @DisplayName("Debe devolver una lista vacia cuando un usuario no tenga bicicletas")
    void getListBikeWhenUserNoBikes(){
        Long userId = 666L;
        when(bikeDao.getListBikeByUserId(userId)).thenReturn(Arrays.asList());
        // When
        List<BikeDdbb> resultado = bikeController.getListBikeByUserId(userId);
        //Then
        assertNotNull(resultado);
        assertTrue(resultado.isEmpty());
        verify(bikeDao, times(1)).getListBikeByUserId(userId);
    }

    @Test
    @DisplayName("Actualizar varios campos a la vez")
    void partialUpdateBike(){
        Long bikeId = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("tipo", "Gravel");
        updates.put("anio", 2024);
        updates.put("status", "en uso");

        BikeDdbb existingBike = createTestBike(bikeId, "Canyon", "road");
        BikeDdbb updateBike = createTestBike(bikeId, "Canyon", "mtb");
        updateBike.setBirthday(Year.of(2024));
        updateBike.setStatus("en mantenimiento");

        when(bikeDao.getBikeById(bikeId)).thenReturn(existingBike);
        when(bikeDao.updateBike(existingBike)).thenReturn(updateBike);

        BikeDdbb resultado = bikeController.partialUpdateBike(bikeId, updates);

        assertNotNull(resultado);
        assertEquals("mtb", resultado.getType());
        assertEquals(Year.of(2024), resultado.getBirthday());
        assertEquals("en mantenimiento", resultado.getStatus());

        verify(bikeDao, times(1)).getBikeById(bikeId);
        verify(bikeDao, times(1)).updateBike(existingBike);

    }

    @Test
    @DisplayName("Lanzar una excepción cuando la bicicleta no existe")
    void partialUpdateBikeWhitNotExists(){
        Long bikeId = 666L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("tipo", "mtb");

        when(bikeDao.getBikeById(bikeId)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () ->{
            bikeController.partialUpdateBike(bikeId, updates);
        });

        assertEquals("Error al actualizar la bicicleta: Bicicleta no encontrada", exception.getMessage());

        verify(bikeDao, times(1)).getBikeById(bikeId);
        verify(bikeDao, never()).updateBike(any(BikeDdbb.class));
    }

    @Test
    @DisplayName("Errores al insertar campos no reconocidos")
    void partialUpdateBikeNoRecognicedFields(){
        Long bikeId = 1L;
        Map<String, Object> updates = new HashMap<>();
        updates.put("tipo", "E-bike");
        updates.put("campoInexistente", "valor");
        updates.put("fabricacion", 101);

        BikeDdbb existingBike = createTestBike(bikeId, "Citroen", "road");
        BikeDdbb updateBike = createTestBike(bikeId, "citroen", "E-bike");

        when(bikeDao.getBikeById(bikeId)).thenReturn(existingBike);
        when(bikeDao.updateBike(existingBike)).thenReturn(updateBike);

        BikeDdbb resultado = bikeController.partialUpdateBike(bikeId, updates);

        assertNotNull(resultado);
        assertEquals("E-bike", resultado.getType());

        verify(bikeDao, times(1)).getBikeById(bikeId);
        verify(bikeDao, times(1)).updateBike(existingBike);
    }


    private BikeDdbb createTestBike(Long id, String marca, String tipo) {
        BikeDdbb bike = new BikeDdbb();
        bike.setIdBike(id);
        bike.setBike_brand(marca);
        bike.setType(tipo);
        bike.setModel("Modelo Test");
        bike.setBirthday(Year.of(2023));
        bike.setWeight(15);
        bike.setBike_material("aluminio");
        bike.setStatus("disponible");

        // Si necesitas UserDdbb, créalo también o usa null
        // UserDdbb mockUser = new UserDdbb();
        // bike.setUser(mockUser);

        return bike;
    }
}
