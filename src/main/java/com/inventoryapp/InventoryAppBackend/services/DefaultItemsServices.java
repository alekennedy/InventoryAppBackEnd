/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventoryapp.InventoryAppBackend.services;

import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import com.inventoryapp.InventoryAppBackend.repo.DefaultItemRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ale
 */
@Service
public class DefaultItemsServices {
    
    @Autowired
    private DefaultItemRepo diRepo;
    
    public Boolean saveAll(List<DefaultItem> items) throws Exception{
        
        Boolean isValidate = false;
        
        for (DefaultItem item : items) {
            if(item.getDiBox() == null){
                return false;
            }else if(item.getDiBox().getbId() == null || item.getDiBox().getbId() <= 0){
                return false;
            }else if(item.getDiName().isBlank() || item.getDiName() == null){
                return false;
            }else{
                isValidate = true;
            }
        }
        
        
        if(isValidate){
            try {
                diRepo.saveAll(items);
                return isValidate;
            } catch (Exception e) {
                throw new Exception("Error to save data");
            }
        }else{
            return false;
        }
    }
    
    public Optional<DefaultItem> getbyId(Long id) throws Exception{
        try {
            Optional<DefaultItem> defaultItem = diRepo.findById(id);
            defaultItem.get().getDiBox().setDefaultItems(null);
            defaultItem.get().getDiBox().getbUnit().setuBoxes(null);
            return defaultItem;            
        } catch (Exception e) {
            throw  new Exception("Error to get data by id");
        }
    }
    
    public List<DefaultItem> getAll() throws Exception{
        try {
            List<DefaultItem> defaultItems = (List<DefaultItem>) diRepo.findAll();    
            for (int i = 0; i < defaultItems.size(); i++) {
                defaultItems.get(i).getDiBox().setDefaultItems(null);
                defaultItems.get(i).getDiBox().getbUnit().setuBoxes(null);
            }
            return defaultItems;            
        } catch (Exception e) {
            throw  new Exception("Error to getALL data");
        }
    }
    
    public List<DefaultItem> getAllByBoxId(Long boxId) throws Exception{
        try {
            List<DefaultItem> defaultItems = diRepo.findDefaultItemsByBox(boxId);    
            for (int i = 0; i < defaultItems.size(); i++) {
                defaultItems.get(i).getDiBox().setDefaultItems(null);
                defaultItems.get(i).getDiBox().getbUnit().setuBoxes(null);
            }
            return defaultItems;            
        } catch (Exception e) {
            throw  new Exception("Error to getall data by box id");
        }
    }
    
}
