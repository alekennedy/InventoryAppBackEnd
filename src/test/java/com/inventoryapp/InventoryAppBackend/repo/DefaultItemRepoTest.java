package com.inventoryapp.InventoryAppBackend.repo;

import com.inventoryapp.InventoryAppBackend.models.Box;
import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
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
 * @author alejandro
 */
@SpringBootTest
public class DefaultItemRepoTest {
    @MockBean
    //@Autowired
    DefaultItemRepo diRepo;
    
    @Autowired
    BoxRep boxRepo;
    
    private List<DefaultItem> defaultItems;
    
    @BeforeEach
    public void before(){
        
        Box box = new Box();
        box.setbId(15l);
        box.setbImage("");
        box.setbName("test");
        box.setbUnit(null);
        
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
        
        Mockito.when(diRepo.findDefaultItemsByBox(15l))
        .thenReturn(defaultItems);
        
    }
    
    @Test
    void crearUnitDependecy(){
        List<DefaultItem> result = (List<DefaultItem>) diRepo.saveAll(defaultItems);
        
        Assertions.assertEquals(result.size(), defaultItems.size());
    }
    
    @Test
    void findDefaultItemsByBoxId(){
        List<DefaultItem> defaultItemsByBox = diRepo.findDefaultItemsByBox(15l);
        
        Assertions.assertEquals(defaultItemsByBox.size(), defaultItems.size());
    }
    
}
