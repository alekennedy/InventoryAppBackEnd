/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.inventoryapp.InventoryAppBackend.repo;

import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ale
 */
public interface DefaultItemRepo extends CrudRepository<DefaultItem, Long>{
    
    @Query("select df from DefaultItem df where df.diBox.bId = :boxId")
    List<DefaultItem> findDefaultItemsByBox(Long boxId);
    
}
