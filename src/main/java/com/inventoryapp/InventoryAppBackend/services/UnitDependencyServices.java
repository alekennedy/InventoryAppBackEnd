package com.inventoryapp.InventoryAppBackend.services;

import com.inventoryapp.InventoryAppBackend.models.UnitDependecy;
import com.inventoryapp.InventoryAppBackend.repo.UnitDependecyRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alejandro
 */
@Service
public class UnitDependencyServices {
    @Autowired
    private UnitDependecyRepo unitDepRepo;
    
    public Boolean save(UnitDependecy unit) throws Exception{        
        Boolean isValid = false;
        if(unit.getuCode() == null || unit.getuCode().isEmpty() ){
            isValid = false;
        }else if(unit.getuImage() == null || unit.getuImage().isEmpty()){
            isValid = false;
        }else if(unit.getuName() == null || unit.getuName().isEmpty()){
            isValid = false;
        }else {
            isValid = true;
        }
        
        if(isValid){
            try {
                unit = unitDepRepo.save(unit);                
            } catch (Exception e) {
                throw new Exception("Error to save data");
            }
            
        }
        
        return isValid;
    }
    
    public Boolean update(UnitDependecy unit) throws Exception{        
        Boolean isValid = false;
        if(unit.getuId() == null || unit.getuId() <= 0){
            isValid = false;
        }else if(unit.getuCode() == null || unit.getuCode().isEmpty() ){
            isValid = false;
        }else if(unit.getuImage() == null || unit.getuImage().isEmpty()){
            isValid = false;
        }else if(unit.getuName() == null || unit.getuName().isEmpty()){
            isValid = false;
        }else {
            isValid = true;
        }
        
        if(isValid){
            try {
                unit = unitDepRepo.save(unit);
            } catch (Exception e) {
                throw new Exception("Error to save data");
            }
            
        }
        
        return isValid;
    }
    
    public List<UnitDependecy> getAll() throws Exception{
        try {
            List<UnitDependecy> units = (List<UnitDependecy>) unitDepRepo.findAll();
            
            for (int i = 0; i < units.size(); i++) {
                for (int j = 0; j < units.get(i).getuBoxes().size(); j++) {
                    units.get(i).getuBoxes().get(j).setbUnit(null);
                }
            }
            
            return units;            
        } catch (Exception e) {
            throw new Exception("Error to get data from database");
        }
    }
    
    public Optional<UnitDependecy> getbyId(Integer id) throws Exception{
        try {
            Optional<UnitDependecy> unit = unitDepRepo.findById(id);   
            for (int i = 0; i < unit.get().getuBoxes().size(); i++) {
                unit.get().getuBoxes().get(i).setbUnit(null);
            }
            return unit;            
        } catch (Exception e) {
            throw  new Exception("Error to get data by id");
        }
    }
}
