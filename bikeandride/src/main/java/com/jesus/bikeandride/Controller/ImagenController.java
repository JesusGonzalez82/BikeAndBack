package com.jesus.bikeandride.Controller;

import com.jesus.bikeandride.Dao.IImagenDao;
import com.jesus.bikeandride.Model.ImagenDdbb;
import com.jesus.bikeandride.Model.TipoImagen;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@CrossOrigin(origins = "*")
public class ImagenController {

    @Autowired
    private IImagenDao ImagenDao;

    // Directorio donde guardamos las imágenes
    private static final String UPLOAD_DIR = "uploads/imagenes/";

    // ==== SUBIDA DE IMÁGENES ====

    @PostMapping("/create")
    @Transactional
    public ResponseEntity<?> createImage(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String typeStr,
            @RequestParam("entityId") Integer entityId) {

        try {
            // Validamos type
            TipoImagen type;
            try {
                type = TipoImagen.valueOf(typeStr.toUpperCase());
            } catch (IllegalArgumentException e){
                return ResponseEntity.badRequest().body("Tipo incorrecto de imagen");
            }

            // Validamos file
            if (file.isEmpty()){
                return ResponseEntity.badRequest().body("No existe el fichero");
            }

            // Validamos file type
            String contentType = file.getContentType();
            if (contentType == null || contentType.startsWith("image/")){
                return ResponseEntity.badRequest().body("Formatos permitidos: jpg, png, gif, etc.");
            }

            // Capacidad máxima 5MB
            if (file.getSize() > 5 * 1024 * 1026){
                return ResponseEntity.badRequest().body("Excedido el tamaño máximo. Máximo 5MB");
            }

            // Crear el directorio /Uploads si no existe
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            // Generamos un nombre de fichero único
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            // Guardamos el fichero físicamente
            Files.copy(file.getInputStream(), filePath);

            // Creamos la entidad en la Base de Datos
            ImagenDdbb image = new ImagenDdbb();
            image.setUrl("/uploads/imagenes" + fileName);
            image.setTipo(type);
            image.setFechaSubida(LocalDateTime.now());

            // Asignamos la correspondiente entity por tipo
            switch (type){
                case USUARIO:
                    image.setIdUsuario(entityId);
                    break;
                case BICICLETA:
                    image.setIdBici(entityId);
                    break;
                case RUTA:
                    image.setIdRuta(entityId);
                    break;
                case ACTIVIDAD:
                    image.setIdActividad(entityId);
                    break;
            }
            ImagenDdbb savedImage = ImagenDao.save(image);
            return ResponseEntity.ok(savedImage);
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar la imagen: " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inesperado " + e.getMessage());
        }
    }

    // ==== GET IMAGENES BY USER ====

    @GetMapping("/user/{userId}/avatar")
    public ImagenDdbb getUserAvatar(@PathVariable Integer userId){
        Optional<ImagenDdbb> avatar = ImagenDao.findByIdUsuarioAndTipo(userId, TipoImagen.USUARIO);
        if(avatar.isEmpty()){
            throw new RuntimeException("Avatar no encontrado para el usuario " + userId);
        }
        return avatar.get();
    }

    @GetMapping("/getListImageByUserId/{userId}")
    public List<ImagenDdbb> getListImageByUserId(@PathVariable Integer userId){
        return ImagenDao.findByIdUsuario(userId);
    }

    @GetMapping("/user/{userId}/count")
    public Long countUserImages(@PathVariable Integer userId){
        return ImagenDao.countByIdUsuario(userId);
        }

    // ==== GET IMAGENES BY BIKE ====

    @GetMapping("/getListImageByBikeId/{bikeId}")
    public List<ImagenDdbb> getListImageByBikeId(@PathVariable Integer bikeId){
        return ImagenDao.findByIdBici(bikeId);
    }

    @GetMapping("/bike/{bikeId}/principal")
    public ImagenDdbb getBikePrincipalImage(@PathVariable Integer bikeId){
        Optional<ImagenDdbb> image = ImagenDao.findFirstImagenByIdBici(bikeId);
        if (image.isEmpty()){
            throw new RuntimeException("Imagen principal no encontrada para la bicicleta " + bikeId);
        }
        return image.get();
    }

    // ==== GET IMAGENES BY ROUTE ====

    @GetMapping("/getListImageByRouteId/{routeId}")
    public List<ImagenDdbb> getListImageByRouteId(@PathVariable Integer routeId){
        return ImagenDao.findByIdRuta(routeId);
    }

    // ==== GET IMAGENES BY TYPE ====

    @GetMapping("/getListImageByType/{type}")
    public List<ImagenDdbb> getListImageByType(@PathVariable String type){
        try{
            TipoImagen tipoImagen = TipoImagen.valueOf(type.toUpperCase());
            return ImagenDao.findByTipo(tipoImagen);
        }catch (IllegalArgumentException e){
            throw new RuntimeException("Tipo de imagen no válido: " + type);
        }
    }

    // ==== GET IMAGENES ESPECIFICAS ====

    @GetMapping("/getImageById/{id}")
    public ImagenDdbb getImageById(@PathVariable Integer id){
        Optional<ImagenDdbb> image = ImagenDao.findById(id);
        if (image.isEmpty()){
            throw new RuntimeException("Imagen no encontrada " + id);
        }
        return image.get();
    }

    // ==== GET TODAS LAS IMAGENES ====

    @GetMapping("/ListaImagenes")
    public List<ImagenDdbb> getAllImages(){
        return ImagenDao.findAll();
    }

    @GetMapping("/count")
    public long countAllImages(){
        return ImagenDao.count();
    }

    // ==== DELETE IMAGEN ====

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ImagenDdbb deleteImage(@PathVariable Integer id){
        try {
            Optional<ImagenDdbb> image = ImagenDao.findById(id);
            if (image.isEmpty()){
                throw new RuntimeException("Imagen no encotrada: " + id);
            }

            // Borrado físico del archivo
            Path filePath = Paths.get("." + image.get().getUrl());
            Files.deleteIfExists(filePath);

            ImagenDao.deleteById(id);
            return image.get();

        }catch (IOException e){
            throw new RuntimeException("Error al eliminar el archivo: " + e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar la imagen: " + e.getMessage());
        }
    }

    // ==== DELETE ALL USER IMAGENES ====

    @DeleteMapping("/delete/user/{userId}")
    @Transactional
    public List<ImagenDdbb> deleteAllUserImages(@PathVariable Integer userId){
        try{
            // Lista de imagenes despues de borrar
            List<ImagenDdbb> images = ImagenDao.findByIdUsuario(userId);

            if (images.isEmpty()){
                throw new RuntimeException("No se encontraron imagenes para el usuario: " + userId);
            }

            // Borrado fisico del archivo
            for(ImagenDdbb image : images){
                Path filePath = Paths.get("." + image.getUrl());
                Files.deleteIfExists(filePath);
            }

            // Borrado de la Base de datos
            ImagenDao.deleteByIdUsuario(userId);
            return images;
        } catch (IOException e){
            throw new RuntimeException("Error al eliminar los archivos: " + e.getMessage());
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar las imagenes del usuario: " + e.getMessage());
        }
    }

    // ==== CHECK SI LA IMAGEN EXISTE ====

    @GetMapping("/exists/{id}")
    public boolean imageExists(@PathVariable Integer id){
        return ImagenDao.existsById(id);
    }

    @GetMapping("/bike/{bikeId}/hasImages/")
    public Boolean bikeHasImages(@PathVariable Integer bikeId){
        return ImagenDao.existsByIdBiciAndTipo(bikeId, TipoImagen.BICICLETA);
    }
}
