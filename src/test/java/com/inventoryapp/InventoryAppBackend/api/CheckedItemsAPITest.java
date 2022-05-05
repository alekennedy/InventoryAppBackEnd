package com.inventoryapp.InventoryAppBackend.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryapp.InventoryAppBackend.models.CheckedItems;
import com.inventoryapp.InventoryAppBackend.models.DailyRegistry;
import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import com.inventoryapp.InventoryAppBackend.repo.CheckedItemsRepo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author alejandro
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CheckedItemsAPITest {
    @MockBean
    CheckedItemsRepo ciRepo;
    
    @Autowired
    MockMvc mockmvc;
    
    CheckedItems checkedItem;
    DailyRegistry dailyRegistry;
    DefaultItem di;
    
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
    public void createCheckedItem() throws Exception{
        
        Mockito.when(ciRepo.save(checkedItem)).thenReturn(checkedItem);
        
        mockmvc.perform(MockMvcRequestBuilders.post("/checkeditems/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(checkedItem)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
    
    @Test
    public void updateCheckedItem() throws Exception{
        
        Mockito.when(ciRepo.save(checkedItem)).thenReturn(checkedItem);
        checkedItem.setCiId(1l);
        mockmvc.perform(MockMvcRequestBuilders.put("/checkeditems/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(checkedItem)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        
        checkedItem.setCiId(null);
        mockmvc.perform(MockMvcRequestBuilders.put("/checkeditems/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(checkedItem)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }
    
    @Test
    public void getById() throws Exception{
        Mockito.when(ciRepo.findById(1l)).thenReturn(Optional.of(checkedItem));
        
        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.get("/checkeditems/get?id=1")).
                andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
        CheckedItems resultCheckedItems = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(),
                CheckedItems.class);
        
        Assertions.assertEquals(checkedItem.getCiId(), resultCheckedItems.getCiId());
        Assertions.assertEquals(checkedItem.getCiDailyRegistry().getDrId(), resultCheckedItems.getCiDailyRegistry().getDrId());
        Assertions.assertEquals(checkedItem.getCiItem().getDiId(), resultCheckedItems.getCiItem().getDiId());
                
    }
    
    @Test
    public void findAll() throws Exception{
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
        
        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.get("/checkeditems/getall"))
                .andExpect(MockMvcResultMatchers.status().isAccepted()).andReturn();
        
        List<CheckedItems> returnedItems = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), new TypeReference<List<CheckedItems>>(){});
        
        Assertions.assertEquals(items.size(), returnedItems.size());
        
        Assertions.assertEquals(items.get(0).getCiId(), returnedItems.get(0).getCiId());
        Assertions.assertEquals(items.get(0).getCiItem().getDiId(), returnedItems.get(0).getCiItem().getDiId());
        Assertions.assertEquals(items.get(0).getCiDailyRegistry().getDrId(), returnedItems.get(0).getCiDailyRegistry().getDrId());
        
        Assertions.assertEquals(items.get(1).getCiId(), returnedItems.get(1).getCiId());
        Assertions.assertEquals(items.get(1).getCiItem().getDiId(), returnedItems.get(1).getCiItem().getDiId());
        Assertions.assertEquals(items.get(1).getCiDailyRegistry().getDrId(), returnedItems.get(1).getCiDailyRegistry().getDrId());
    }
}
