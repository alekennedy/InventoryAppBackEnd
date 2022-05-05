package com.inventoryapp.InventoryAppBackend.api;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.inventoryapp.InventoryAppBackend.models.CheckedItems;
import com.inventoryapp.InventoryAppBackend.services.CheckedItemsServices;
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
 * @author alejandro
 */
@RestController
@RequestMapping("checkeditems")
@CrossOrigin({"*"})
public class CheckedItemsAPI {
    
    @Autowired
    private CheckedItemsServices ciServices;
    
    @PostMapping("create")
    public ResponseEntity<?> createChecked(@RequestBody CheckedItems checkedItem){
        try {
            if(ciServices.save(checkedItem)){
                return new ResponseEntity<>(HttpStatus.CREATED);                
            }else{
                return new ResponseEntity<>(new JSONPObject("message", "INVALID DATA"),HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            Logger.getLogger(CheckedItemsAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("update")
    public ResponseEntity<?> updateChecked(@RequestBody CheckedItems checkedItem){
        try {
            if(ciServices.update(checkedItem)){
                return new ResponseEntity<>(HttpStatus.CREATED);                
            }else{
                return new ResponseEntity<>(new JSONPObject("message", "INVALID DATA"),HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception ex) {
            Logger.getLogger(CheckedItemsAPI.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new JSONPObject("message", ex.getMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("get")
    public ResponseEntity<?> findById(@PathParam("id") Long id){
        
        try {
            CheckedItems checkedItems = ciServices.findById(id);
            return new ResponseEntity<>(checkedItems, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new JSONPObject("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("getall")
    public ResponseEntity<?> findAll(){
        
        try {
            List<CheckedItems> checkedItems = ciServices.findAll();
            return new ResponseEntity<>(checkedItems, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(new JSONPObject("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
