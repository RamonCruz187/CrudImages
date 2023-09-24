
package com.repaso2.service;

import com.repaso2.model.Mascota;
import java.util.List;



public interface mascotaIService {
 
    public void nuevaMascota (Mascota mas);
    public List <Mascota> verMascotas();
    public void eliminarMascota(Long id);
    public Mascota verMascota (Long id);
        
    
    
}
