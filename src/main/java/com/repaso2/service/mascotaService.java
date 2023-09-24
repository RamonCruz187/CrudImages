
package com.repaso2.service;

import com.repaso2.model.Mascota;
import com.repaso2.repository.mascotaRep;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class mascotaService implements mascotaIService{
    
    @Autowired
    public mascotaRep mrepo;

    @Override
    public void nuevaMascota(Mascota mas) {
        mrepo.save(mas);
    }

    @Override
    public List<Mascota> verMascotas() {
        return mrepo.findAll();
    }

    @Override
    public void eliminarMascota(Long id) {
        mrepo.deleteById(id);
    }

    @Override
    public Mascota verMascota(Long id) {
        return mrepo.findById(id).orElse(null);
    }
    
}
