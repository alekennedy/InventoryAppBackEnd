package com.inventoryapp.InventoryAppBackend.api;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.inventoryapp.InventoryAppBackend.models.UnitDependecy;
import com.inventoryapp.InventoryAppBackend.services.UnitDependencyServices;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * @author alejandro
 */
@RestController
@CrossOrigin({"*"})
@RequestMapping("unitdependency")
public class UnitDependecyAPI {
    
    @Autowired
    private UnitDependencyServices unitDepServices;
    
    @PostMapping("save")
    public ResponseEntity<?> saveUnitDep(@RequestBody UnitDependecy unitDep){
        try {
            if(unitDepServices.save(unitDep)){
                return new ResponseEntity<UnitDependecy>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(new JSONPObject("message", "Datos no validadeos"),HttpStatus.NOT_ACCEPTABLE);
            }            
        } catch (Exception ex) {
            Logger.getLogger(UnitDependecyAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    
    @GetMapping("getall")
    public ResponseEntity<?> getall(){
        
        List<UnitDependecy> allUnits;
        try {
            allUnits = unitDepServices.getAll();
            return new ResponseEntity<>( allUnits,HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(UnitDependecyAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>( new JSONPObject("message", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("get")
    public ResponseEntity<?> getById(@PathParam("id") Integer id){
        try {
            Optional<UnitDependecy> unit = unitDepServices.getbyId(id);
            return new ResponseEntity<>(unit, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(UnitDependecyAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    @PutMapping("update")
    public ResponseEntity<?> updateUnitDep(@RequestBody UnitDependecy unitDep){
        
        try {
            if(unitDepServices.update(unitDep)){
                return new ResponseEntity<UnitDependecy>(unitDep, HttpStatus.ACCEPTED);
            }else {
                return new ResponseEntity<>(new JSONPObject("messge", "Invalid Data"), HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            Logger.getLogger(UnitDependecyAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("messge", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
