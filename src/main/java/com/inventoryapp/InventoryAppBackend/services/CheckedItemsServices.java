package com.inventoryapp.InventoryAppBackend.services;

import com.inventoryapp.InventoryAppBackend.models.CheckedItems;
import com.inventoryapp.InventoryAppBackend.repo.CheckedItemsRepo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alejandro
 */
@Service
public class CheckedItemsServices {
    
    @Autowired
    private CheckedItemsRepo checkedItemsRepo;
    
    public Boolean save(CheckedItems checkedItem) throws Exception{
        Boolean isValid = Boolean.FALSE;
        
        if(checkedItem.getCiDailyRegistry()==null || checkedItem.getCiDailyRegistry().getDrId() == null){
            return false;
        }else if(checkedItem.getCiItem()==null || checkedItem.getCiItem().getDiId() == null){
            return false;
        }else if(checkedItem.getCiQuantity() ==null || checkedItem.getCiQuantity().doubleValue() < 0){
            return false;
        }else if(checkedItem.getCiExists() == null){
            return false;
        }else if(checkedItem.getCiObs() == null){
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Null Obs is fix to empty");
            checkedItem.setCiObs("");
            isValid = Boolean.TRUE;  
        }else{
            isValid = Boolean.TRUE;            
        }
        if(isValid){
            try {
                checkedItemsRepo.save(checkedItem);
                return isValid;                
            } catch (Exception e) {
                throw new Exception("Error to save in database");
            }
        }else{
            return isValid;
        }
    }
    
    public Boolean update(CheckedItems checkedItem) throws Exception{
        Boolean isValid = Boolean.FALSE;
        
        if(checkedItem.getCiId() == null){
            return false;
        }else if(checkedItem.getCiDailyRegistry()==null || checkedItem.getCiDailyRegistry().getDrId() == null){
            return false;
        }else if(checkedItem.getCiItem()==null || checkedItem.getCiItem().getDiId() == null){
            return false;
        }else if(checkedItem.getCiQuantity() ==null || checkedItem.getCiQuantity().doubleValue() < 0){
            return false;
        }else if(checkedItem.getCiExists() == null){
            return false;
        }else if(checkedItem.getCiObs() == null){
            Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Null Obs is fix to empty");
            checkedItem.setCiObs("");
            isValid = Boolean.TRUE;  
        }else{
            isValid = Boolean.TRUE;            
        }
        if(isValid){
            try {
                checkedItemsRepo.save(checkedItem);
                return isValid;                
            } catch (Exception e) {
                throw new Exception("Error to save in database");
            }
        }else{
            return isValid;
        }
    }
    
    public CheckedItems findById(Long id) throws Exception{
        try {
            CheckedItems checkedItem = checkedItemsRepo.findById(id).get();
            return checkedItem;
        } catch (Exception e) {
            throw new Exception("Error at get data from database");
        }
    }
    
    public List<CheckedItems> findAll() throws Exception{
        try {
            List<CheckedItems> items = (List<CheckedItems>) checkedItemsRepo.findAll();
            return  items;
        } catch (Exception e) {
            throw new Exception("Error at getall data from database");
        }
    }
}
