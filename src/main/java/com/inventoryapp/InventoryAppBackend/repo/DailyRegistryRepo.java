/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inventoryapp.InventoryAppBackend.repo;

import com.inventoryapp.InventoryAppBackend.models.DailyRegistry;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ale
 */
public interface DailyRegistryRepo extends CrudRepository<DailyRegistry, Long>{
    
}
