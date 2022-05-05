package com.inventoryapp.InventoryAppBackend.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.inventoryapp.InventoryAppBackend.models.DailyRegistry;
import com.inventoryapp.InventoryAppBackend.services.DailyRegistryServices;
import java.util.List;
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
 * @author ale
 */

@RestController
@RequestMapping("dailyregistry")
@CrossOrigin({"*"})
public class DailyRegistryAPI {
    @Autowired
    private DailyRegistryServices drServices;
    
    @PostMapping("save")
    public ResponseEntity<?> createDailyRegistry(@RequestBody DailyRegistry dailyRegistry){
        try {
            if(drServices.save(dailyRegistry)){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(new JSONPObject("message", "invalid data content"), HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            Logger.getLogger(DailyRegistryAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    
    @PutMapping("update")
    public ResponseEntity<?> updateDailyRegistry(@RequestBody DailyRegistry dailyRegistry){
        try {
            if(drServices.update(dailyRegistry)){
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(new JSONPObject("message", "invalid data content"), HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            Logger.getLogger(DailyRegistryAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    
    @GetMapping("get")
    public ResponseEntity<?> findbyId(@PathParam("id") Long id){
        try {
            DailyRegistry dailyRegistry = drServices.findById(id).get();
            return new ResponseEntity<>(dailyRegistry,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(DailyRegistryAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()),HttpStatus.ACCEPTED);
        }
    }
    
    @GetMapping("getall")
    public ResponseEntity<?> findAll(){
        try {
            List<DailyRegistry> dailyRegistries = drServices.findAll();
            return new ResponseEntity<>(dailyRegistries,HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            Logger.getLogger(DailyRegistryAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()),HttpStatus.ACCEPTED);
        }
    }
    
}
