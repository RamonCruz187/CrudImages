
package com.repaso2.controller;

import com.repaso2.model.Mascota;
import com.repaso2.service.mascotaIService;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
public class controller {
    
    @Autowired
    private mascotaIService mserv;
    
    
    
    @PostMapping ("/nueva/mascota")
    @SuppressWarnings("UseSpecificCatch")
    public ResponseEntity <String> crearMascota(@RequestParam("id") Long id,
                                                @RequestParam("nombre") String nombre,
                                               @RequestParam("raza") String raza,
                                               @RequestParam("edad") Long edad,
                                               @RequestParam("imagen") MultipartFile imagen){
        try {
            // Guardar la imagen en una carpeta o en la base de datos, según tus necesidades
            // Aquí se muestra un ejemplo de guardar la imagen en una carpeta
            //String rutaImagen = "C:/java/" + imagen.getOriginalFilename();
            //imagen.transferTo(new File(rutaImagen));
            
            // Crear un nuevo usuario y guardar en la base de datos
            Mascota mascota = new Mascota();
            mascota.setId(id);
            mascota.setNombre(nombre);
            mascota.setRaza(raza);
            mascota.setEdad(edad);
            
            String fileName = StringUtils.cleanPath(imagen.getOriginalFilename());
            Path uploadPath = Paths.get("C://temp/uploads"); // Reemplaza con la ruta deseada
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            
            try (InputStream inputStream = imagen.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                mascota.setImagen(filePath.toString());
            }
 
            mserv.nuevaMascota(mascota);
            
            return ResponseEntity.ok("Usuario creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el usuario");
        }
    }
    
   
    
    @GetMapping("/ver/mascotas")
    public List <Mascota> verMascotas(){
        return mserv.verMascotas();
    }
    
    @DeleteMapping ("/borrar/mascota/{id}")
    public void eliminarMascota(@PathVariable Long id){
        mserv.eliminarMascota(id);
    }
    
    @GetMapping ("/buscar/mascota/{id}")
    public Mascota buscarMascota(@PathVariable Long id){
        return mserv.verMascota(id);
    }
    
    @PostMapping("/actualizar/mascota")
    public void actualizarMascota(@RequestBody Mascota mas){
        mserv.nuevaMascota(mas);
    }
   
}
