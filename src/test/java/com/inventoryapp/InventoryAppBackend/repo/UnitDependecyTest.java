package com.inventoryapp.InventoryAppBackend.repo;

import com.inventoryapp.InventoryAppBackend.models.Box;
import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import com.inventoryapp.InventoryAppBackend.models.UnitDependecy;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author ale
 */
@SpringBootTest
public class UnitDependecyTest {
    @MockBean
    UnitDependecyRepo unitDependecyRepo;
    
    private UnitDependecy unitDependecy;
    
    @BeforeEach
    public void before(){
        
        unitDependecy = new UnitDependecy();        
        unitDependecy.setuCode("code"+LocalDateTime.now());
        unitDependecy.setuName("name_"+LocalDateTime.now());
        unitDependecy.setuImage("image_"+LocalDateTime.now());
        Box box1 = new Box();
        box1.setbImage("img_box_"+LocalDateTime.now());
        box1.setbName("name_box_"+LocalDateTime.now());
        box1.setbUnit(unitDependecy);
        
        DefaultItem di1 = new DefaultItem();
        di1.setDiBox(box1);
        di1.setDiImage("di_image_1"+Math.random());
        di1.setDiDefaultQty(BigDecimal.ZERO);
        di1.setDiName("DEFAULT_ITEM_1_"+Math.random());
        
        DefaultItem di2 = new DefaultItem();
        di1.setDiBox(box1);
        di1.setDiImage("di_image_2"+Math.random());
        di1.setDiDefaultQty(BigDecimal.ZERO);
        di1.setDiName("DEFAULT_ITEM_2_"+Math.random());
        
        box1.addDefaultItem(di1);
        box1.addDefaultItem(di2);
        
        Box box2 = new Box();
        box2.setbImage("img_box2_"+LocalDateTime.now());
        box2.setbName("name_box2_"+LocalDateTime.now());
        box2.setbUnit(unitDependecy);
        
        DefaultItem di3 = new DefaultItem();
        di3.setDiBox(box2);
        di3.setDiImage("di_image_3"+Math.random());
        di3.setDiDefaultQty(BigDecimal.ZERO);
        di3.setDiName("DEFAULT_ITEM_3_"+Math.random());
        
        DefaultItem di4 = new DefaultItem();
        di4.setDiBox(box2);
        di4.setDiImage("di_image_4"+Math.random());
        di4.setDiDefaultQty(BigDecimal.ZERO);
        di4.setDiName("DEFAULT_ITEM_4_"+Math.random());
        
        box2.addDefaultItem(di3);
        box2.addDefaultItem(di4);
        
        unitDependecy.addBox(box1);
        unitDependecy.addBox(box2);
        
        
        
        Optional<UnitDependecy> unitOptional;
        
        Mockito.when(unitDependecyRepo.save(unitDependecy))
        .thenReturn(unitDependecy);
        Mockito.when(unitDependecyRepo.findById(unitDependecy.getuId()))
        .thenReturn(Optional.of(unitDependecy));
    }
    
    @Test
    void crearUnitDependecy(){
        UnitDependecy unitDependecyRetrive = unitDependecyRepo.save(unitDependecy);
        
        Assertions.assertEquals(unitDependecy.getuName(), unitDependecyRetrive.getuName());
    }
}
