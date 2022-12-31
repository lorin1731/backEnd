/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.porfoliolorin.tjgl.Controller;

import com.porfoliolorin.tjgl.Dto.dtoeducacion;
import com.porfoliolorin.tjgl.Entity.Educacion;
import com.porfoliolorin.tjgl.Security.Controller.Mensaje;
import com.porfoliolorin.tjgl.Service.SEducacion;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins = "http://localhost:4200")
public class CEducacion {

    @Autowired
    SEducacion sEducacion;

    @GetMapping("/lista")
    public ResponseEntity<List<Educacion>> List() {
        List<Educacion> List = sEducacion.list();
        return new ResponseEntity(List, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
    if(!sEducacion.existsById(id)){
    return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
    }
    
    Educacion educacion = sEducacion.getOne(id).get();
    return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/id")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity(new Mensaje("Educacion eliminada"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoeducacion dtoeducacion) {
        if (StringUtils.isBlank(dtoeducacion.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (sEducacion.existByNombreE(dtoeducacion.getNombreE())) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(
                dtoeducacion.getNombreE(), dtoeducacion.getDescripcionE()
        );

        sEducacion.save(educacion);
        return new ResponseEntity(new Mensaje("Educacion creada"), HttpStatus.OK);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoeducacion dtoeducacion) {
        if (!sEducacion.existsById(id)) {
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        if (sEducacion.existByNombreE(dtoeducacion.getNombreE()) && sEducacion.getByNombreE(dtoeducacion.getNombreE()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(dtoeducacion.getNombreE())) {
            return new ResponseEntity(new Mensaje("el campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacio = sEducacion.getOne(id).get();

        dtoeducacion.setNombreE(dtoeducacion.getNombreE());
        dtoeducacion.setDescripcionE(dtoeducacion.getDescripcionE());

        sEducacion.save(educacio);

        return new ResponseEntity(new Mensaje("Eduacion actualizada"), HttpStatus.OK);
    }
}
