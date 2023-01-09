/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.porfoliolorin.tjgl.Repository;

import com.porfoliolorin.tjgl.Entity.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lorin
 */
public interface IPersonaRepo extends JpaRepository<Persona, Long> {
    public List<Persona> findByEmailAndClave(String email, String clave);
}