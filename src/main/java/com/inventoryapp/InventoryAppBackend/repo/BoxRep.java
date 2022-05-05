/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inventoryapp.InventoryAppBackend.repo;

import com.inventoryapp.InventoryAppBackend.models.Box;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ale
 */
public interface BoxRep extends CrudRepository<Box, Long>{
    
}
