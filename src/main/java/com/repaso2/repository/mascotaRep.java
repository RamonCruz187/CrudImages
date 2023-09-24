
package com.repaso2.repository;

import com.repaso2.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface mascotaRep extends JpaRepository <Mascota, Long>{
    
}
