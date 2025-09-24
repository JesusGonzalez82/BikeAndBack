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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/imagenes")
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
    
}
