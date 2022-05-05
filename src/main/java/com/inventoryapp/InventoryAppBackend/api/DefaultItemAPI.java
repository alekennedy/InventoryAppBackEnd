package com.inventoryapp.InventoryAppBackend.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import com.inventoryapp.InventoryAppBackend.services.DefaultItemsServices;
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
 * @author ale
 */
@RestController
@RequestMapping("defaultitem")
@CrossOrigin({"*"})
public class DefaultItemAPI {
    
    @Autowired
    private DefaultItemsServices diServices;
    
    @PostMapping("save")
    public ResponseEntity<?> createDefaultItem(@RequestBody List<DefaultItem> defaultItems){
        try {
            if(diServices.saveAll(defaultItems)){
                return new ResponseEntity<>(defaultItems,HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(new JSONPObject("message","invalid data"),HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            Logger.getLogger(DefaultItemAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("update")
    public ResponseEntity<?> updateDefaultItem(@RequestBody List<DefaultItem> defaultItems){
        try {
            if(diServices.saveAll(defaultItems)){
                return new ResponseEntity<>(defaultItems,HttpStatus.ACCEPTED);
            }else{
                return new ResponseEntity<>(new JSONPObject("message","invalid data"),HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            Logger.getLogger(DefaultItemAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("get")
    public ResponseEntity<?> getDefaultItem(@PathParam("id") Long id){
        try {
            Optional<DefaultItem> di = diServices.getbyId(id);
            return new ResponseEntity<>(di, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(DefaultItemAPI.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(new JSONPObject("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("getall")
    public ResponseEntity<?> getDefaultItems(){
        try {
            List<DefaultItem> defaultItems =  diServices.getAll();
            
            return new ResponseEntity<>(defaultItems, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(DefaultItemAPI.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(new JSONPObject("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("getallbybox")
    public ResponseEntity<?> getDefaultItemsByBox(@PathParam("id") Long id){
        try {
            List<DefaultItem> defaultItems =  diServices.getAllByBoxId(id);
            
            return new ResponseEntity<>(defaultItems, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            Logger.getLogger(DefaultItemAPI.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(new JSONPObject("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
