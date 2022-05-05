package com.inventoryapp.InventoryAppBackend.sevices;

import com.inventoryapp.InventoryAppBackend.models.DailyRegistry;
import com.inventoryapp.InventoryAppBackend.models.UnitDependecy;
import com.inventoryapp.InventoryAppBackend.repo.DailyRegistryRepo;
import com.inventoryapp.InventoryAppBackend.services.DailyRegistryServices;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class DailyRegistryServicesTest {
    
    private DailyRegistry dailyRegistry;
    
    private List<DailyRegistry> dailyRegistries;
    
    @Autowired
    private DailyRegistryServices drServices;
    
    @MockBean
    DailyRegistryRepo drRepo;
    
    @BeforeEach
    void setup(){
        dailyRegistries = new ArrayList<>();
        dailyRegistry = new DailyRegistry();
        dailyRegistry.setDrId(1l);
        dailyRegistry.setDrDateTime(LocalDateTime.now());
        dailyRegistry.setDrUserId("randomUser_"+LocalDateTime.now());
        
        UnitDependecy unit = new UnitDependecy();
        unit.setuId(1);
        unit.setuCode("code_"+LocalDateTime.now());
        unit.setuImage("image_"+LocalDateTime.now());
        unit.setuName("name_"+LocalDateTime.now());
        
        dailyRegistry.setDrUnit(unit);
        
        DailyRegistry daily2 = new DailyRegistry();
        
        daily2 = new DailyRegistry();
        daily2.setDrId(1l);
        daily2.setDrDateTime(LocalDateTime.now());
        daily2.setDrUserId("randomUser_"+LocalDateTime.now());
        daily2.setDrUnit(unit);
        
        dailyRegistries.add(dailyRegistry);
        dailyRegistries.add(daily2);
        
        Mockito.when(drRepo.save(dailyRegistry)).thenReturn(dailyRegistry);
    }
    
    @Test
    void createDailyRegistry() throws Exception{
        Boolean returnedValue = drServices.save(dailyRegistry);
        
        Assertions.assertTrue(returnedValue);
        
        DailyRegistry newDailyRegistry = new DailyRegistry();
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrUnit(null);
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrUserId("            ");
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
        
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrDateTime(null);
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrId(-1l);
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
    }
    
    @Test
    void updateDailyRegistry() throws Exception{
        Boolean returnedValue = drServices.update(dailyRegistry);
        
        Assertions.assertTrue(returnedValue);
        
        DailyRegistry newDailyRegistry = new DailyRegistry();
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrUnit(null);
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrUserId("            ");
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
        
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrDateTime(null);
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
        newDailyRegistry = dailyRegistry;
        newDailyRegistry.setDrId(-1l);
        
        returnedValue = drServices.save(newDailyRegistry);
        
        Assertions.assertFalse(returnedValue);
        
    }
    
    @Test
    void findById() throws Exception{
        DailyRegistry dailyReturned = new DailyRegistry();
        dailyReturned = dailyRegistry;
        dailyReturned.setDrId(1l);
        Mockito.when(drRepo.findById(1l)).thenReturn(Optional.of(dailyReturned));
        
        Optional<DailyRegistry> getDailyRegistry = drServices.findById(1l);
        
        Assertions.assertEquals(getDailyRegistry.get().getDrId(), dailyReturned.getDrId());
    }
    
    @Test
    void findAll() throws Exception{
        Mockito.when(drRepo.findAll()).thenReturn(dailyRegistries);
        
        List<DailyRegistry> dalies =  drServices.findAll();
        
        Assertions.assertEquals(dalies.size(), dailyRegistries.size());
        Assertions.assertEquals(dalies.get(0).getDrId(), dailyRegistries.get(0).getDrId());
        Assertions.assertEquals(dalies.get(0).getDrUnit().getuId(), dailyRegistries.get(0).getDrUnit().getuId());
        Assertions.assertEquals(dalies.get(1).getDrId(), dailyRegistries.get(1).getDrId());
        Assertions.assertEquals(dalies.get(1).getDrUnit().getuId(), dailyRegistries.get(1).getDrUnit().getuId());
        
        
    }
}
