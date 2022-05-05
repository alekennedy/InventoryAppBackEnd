package com.inventoryapp.InventoryAppBackend.services;

import com.inventoryapp.InventoryAppBackend.models.DailyRegistry;
import com.inventoryapp.InventoryAppBackend.repo.DailyRegistryRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ale
 */
@Service
public class DailyRegistryServices {
    
    @Autowired
    private DailyRegistryRepo drRepo;
    
    public Boolean save(DailyRegistry dailyRegistry) throws Exception{
        Boolean isValid = false;
        if(dailyRegistry.getDrDateTime() ==null){
            return  false;
        }else if(dailyRegistry.getDrUnit() == null || dailyRegistry.getDrUnit().getuId() <=0){
            return false;
        }else if(dailyRegistry.getDrUserId()== null || dailyRegistry.getDrUserId().isBlank()){
            return false;
        }else{
            isValid = true;
        }
        
        if(isValid){
            try {
                drRepo.save(dailyRegistry);
                return isValid;
            } catch (Exception e) {
                throw new Exception("Error to save in database");
            }
        }else{
            return isValid;
        }
    }
    
    public Boolean update(DailyRegistry dailyRegistry) throws Exception{
        Boolean isValid = false;
        if(dailyRegistry.getDrId() == null  || dailyRegistry.getDrId() <= 0){
            return false;
        }else if(dailyRegistry.getDrDateTime() ==null){
            return  false;
        }else if(dailyRegistry.getDrUnit() == null || dailyRegistry.getDrUnit().getuId() <=0){
            return false;
        }else if(dailyRegistry.getDrUserId()== null || dailyRegistry.getDrUserId().isBlank()){
            return false;
        }else{
            isValid = true;
        }
        
        if(isValid){
            try {
                drRepo.save(dailyRegistry);
                return isValid;
            } catch (Exception e) {
                throw new Exception("Error to save in database");
            }
        }else{
            return isValid;
        }
    }
    
    public Optional<DailyRegistry> findById(Long id) throws Exception{        
        try {
            Optional<DailyRegistry> dailyRegistry = drRepo.findById(id);
            return dailyRegistry;
        } catch (Exception e) {
            throw new Exception("Error to get data from database");
        }
    }
    
    public List<DailyRegistry> findAll() throws Exception{
        try {
            List<DailyRegistry> dailyRestries = (List<DailyRegistry>) drRepo.findAll();
            return dailyRestries;            
        } catch (Exception e) {
            throw new Exception("Error to getall data from database");
        }
    }
}
