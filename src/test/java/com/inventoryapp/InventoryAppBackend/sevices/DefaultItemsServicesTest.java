/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.inventoryapp.InventoryAppBackend.sevices;

import com.inventoryapp.InventoryAppBackend.models.Box;
import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import com.inventoryapp.InventoryAppBackend.repo.DefaultItemRepo;
import com.inventoryapp.InventoryAppBackend.services.DefaultItemsServices;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author ale
 */
@SpringBootTest
public class DefaultItemsServicesTest {
    
    @MockBean
    DefaultItemRepo diRepo;
    
    @Autowired
    DefaultItemsServices diServices;
    
    private List<DefaultItem> defaultItems;
    
    Box box = new Box();
    
    @BeforeEach
    public void before(){
        
        
        box.setbId(15l);
        
        DefaultItem di3 = new DefaultItem();
        di3.setDiBox(box);
        di3.setDiImage("di_image_3"+Math.random());
        di3.setDiDefaultQty(BigDecimal.ZERO);
        di3.setDiName("DEFAULT_ITEM_3_"+Math.random());
        
        DefaultItem di4 = new DefaultItem();
        di4.setDiBox(box);
        di4.setDiImage("di_image_4"+Math.random());
        di4.setDiDefaultQty(BigDecimal.ZERO);
        di4.setDiName("DEFAULT_ITEM_4_"+Math.random());
        
        box.addDefaultItem(di3);
        box.addDefaultItem(di4);       
        
        
        defaultItems = box.getDefaultItems();
        
        
        Mockito.when(diRepo.saveAll(defaultItems))
        .thenReturn(defaultItems);
        
    }
    
    @Test
    void validateCreateDefaultItems() throws Exception{
        
        Boolean isValid = diServices.saveAll(defaultItems); 
        
        Assertions.assertTrue(isValid);
        
        defaultItems.get(0).setDiBox(null);
        isValid = diServices.saveAll(defaultItems); 
        Assertions.assertFalse(isValid);
        
        defaultItems.get(0).setDiBox(box);
        
        defaultItems.get(0).setDiName("        ");
        isValid = diServices.saveAll(defaultItems); 
        Assertions.assertFalse(isValid);
        
        defaultItems.get(0).getDiBox().setbId(0l);
        isValid = diServices.saveAll(defaultItems); 
        Assertions.assertFalse(isValid);
    }
    
    
}
