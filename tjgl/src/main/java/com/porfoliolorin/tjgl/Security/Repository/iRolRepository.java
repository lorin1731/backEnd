
package com.porfoliolorin.tjgl.Security.Repository;

import com.porfoliolorin.tjgl.Security.Entity.Rol;
import com.porfoliolorin.tjgl.Security.Enums.RolNombre;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iRolRepository extends JpaRepository<Rol, Integer> {
  Optional<Rol> finfByRolNombtr(RolNombre rolNombre);  
}
