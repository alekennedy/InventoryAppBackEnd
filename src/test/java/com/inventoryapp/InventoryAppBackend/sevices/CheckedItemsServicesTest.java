package com.inventoryapp.InventoryAppBackend.sevices;

import com.inventoryapp.InventoryAppBackend.models.CheckedItems;
import com.inventoryapp.InventoryAppBackend.models.DailyRegistry;
import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import com.inventoryapp.InventoryAppBackend.repo.CheckedItemsRepo;
import com.inventoryapp.InventoryAppBackend.services.CheckedItemsServices;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.aspectj.lang.annotation.Before;
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
public class CheckedItemsServicesTest {
    
    @MockBean
    private CheckedItemsRepo ciRepo;
    
    @Autowired
    private CheckedItemsServices checkedItemsServices;
    
    CheckedItems checkedItem;
    
    DefaultItem di;
    
    DailyRegistry dailyRegistry;
    
    @BeforeEach
    void setUp(){
        checkedItem = new CheckedItems();
        
        dailyRegistry = new DailyRegistry();
        dailyRegistry.setDrDateTime(LocalDateTime.now());
        dailyRegistry.setDrId(1l);
        
        di = new DefaultItem();
        di.setDiDefaultQty(BigDecimal.valueOf(10.0d));
        di.setDiId(1l);
        di.setDiImage("random_img_"+LocalDateTime.now());
        di.setDiName("random_name_"+LocalDateTime.now());
                
        checkedItem.setCiDailyRegistry(dailyRegistry);
        checkedItem.setCiItem(di);
        checkedItem.setCiExists(Boolean.TRUE);
        checkedItem.setCiQuantity(BigDecimal.valueOf(9.0d));
        checkedItem.setCiObs("random_obs_"+LocalDateTime.now());
    }
            
    @Test
    void createCheckedItems() throws Exception{
        
        Mockito.when(ciRepo.save(checkedItem)).thenReturn(checkedItem);
        
        Boolean result = checkedItemsServices.save(checkedItem);
        
        Assertions.assertTrue(result);

               
        checkedItem.setCiObs(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertTrue(result);
        
        checkedItem.setCiObs("random_obs_"+LocalDateTime.now());;
        checkedItem.setCiDailyRegistry(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertFalse(result);
                
        checkedItem.setCiDailyRegistry(dailyRegistry);
        checkedItem.setCiItem(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertFalse(result);
        
        checkedItem.setCiItem(di);
        checkedItem.setCiQuantity(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertFalse(result);
    }
    
    @Test
    void updateCheckedItems() throws Exception{
        
        
        
        checkedItem.setCiId(1l);
        
        Mockito.when(ciRepo.save(checkedItem)).thenReturn(checkedItem);
        
        Boolean result = checkedItemsServices.update(checkedItem);
        
        Assertions.assertTrue(result);

        checkedItem.setCiId(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertTrue(result);
        
        checkedItem.setCiId(1l);
        checkedItem.setCiObs(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertTrue(result);
        
        checkedItem.setCiObs("random_obs_"+LocalDateTime.now());;
        checkedItem.setCiDailyRegistry(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertFalse(result);
                
        checkedItem.setCiDailyRegistry(dailyRegistry);
        checkedItem.setCiItem(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertFalse(result);
        
        checkedItem.setCiItem(di);
        checkedItem.setCiQuantity(null);
        result = checkedItemsServices.save(checkedItem);
        Assertions.assertFalse(result);
    }
    
    @Test
    void getCheckedItem() throws Exception{
        Mockito.when(ciRepo.findById(1l)).thenReturn(Optional.of(checkedItem));
        
        CheckedItems getCheckedItem = checkedItemsServices.findById(1l);
        
        Assertions.assertEquals(checkedItem.getCiId(), getCheckedItem.getCiId());
        Assertions.assertEquals(checkedItem.getCiDailyRegistry().getDrId(), getCheckedItem.getCiDailyRegistry().getDrId());
        Assertions.assertEquals(checkedItem.getCiItem().getDiId(), getCheckedItem.getCiItem().getDiId());
        
    }
    
    @Test
    void findAll() throws Exception{
        CheckedItems checkedItem2 = new CheckedItems();
        checkedItem2.setCiId(2l);
        checkedItem2.setCiDailyRegistry(dailyRegistry);
        checkedItem2.setCiItem(di);
        checkedItem2.setCiExists(Boolean.TRUE);
        checkedItem2.setCiQuantity(BigDecimal.valueOf(9.0d));
        checkedItem2.setCiObs("random_obs_"+LocalDateTime.now());
        
        List<CheckedItems> items = new ArrayList<>();
        items.add(checkedItem);
        items.add(checkedItem2);
        
        Mockito.when(ciRepo.findAll()).thenReturn(items);
        
        List<CheckedItems> items2 = checkedItemsServices.findAll();
        
        Assertions.assertEquals(items.size(), items2.size());
        Assertions.assertEquals(items.get(0).getCiId(), items2.get(0).getCiId());
        Assertions.assertEquals(items.get(0).getCiItem().getDiId(), items2.get(0).getCiItem().getDiId());
        Assertions.assertEquals(items.get(0).getCiDailyRegistry().getDrId(), items2.get(0).getCiDailyRegistry().getDrId());
        
        Assertions.assertEquals(items.get(1).getCiId(), items2.get(1).getCiId());
        Assertions.assertEquals(items.get(1).getCiItem().getDiId(), items2.get(1).getCiItem().getDiId());
        Assertions.assertEquals(items.get(1).getCiDailyRegistry().getDrId(), items2.get(1).getCiDailyRegistry().getDrId());
    }
}
