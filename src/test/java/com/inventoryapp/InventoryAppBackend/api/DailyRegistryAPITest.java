package com.inventoryapp.InventoryAppBackend.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author ale
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DailyRegistryAPITest {
    
    
    private DailyRegistry dailyRegistry;
    
    private List<DailyRegistry> dailyRegistries;
    
    @MockBean
    DailyRegistryRepo drRepo;
    
    
    @Autowired
    MockMvc mockmvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @BeforeEach
    void setup(){
        
        dailyRegistries = new ArrayList<>();
        
        dailyRegistry = new DailyRegistry();
        
        dailyRegistry.setDrDateTime(LocalDateTime.now());
        dailyRegistry.setDrUserId("randomUser_"+LocalDateTime.now());
        
        UnitDependecy unit = new UnitDependecy();
        unit.setuId(1);
        unit.setuCode("code_"+LocalDateTime.now());
        unit.setuImage("image_"+LocalDateTime.now());
        unit.setuName("name_"+LocalDateTime.now());
        
        dailyRegistry.setDrUnit(unit);
        
        DailyRegistry dailyRegistry2 = new DailyRegistry();
        
        dailyRegistry2.setDrDateTime(LocalDateTime.now());
        dailyRegistry2.setDrUserId("randomUser_"+LocalDateTime.now());
        
        dailyRegistry2.setDrUnit(unit);
        
        dailyRegistries.add(dailyRegistry);
        dailyRegistries.add(dailyRegistry2);
        
    }
    
    @Test
    void createDailyRegistryAPI() throws Exception{
        
        Mockito.when(drRepo.save(dailyRegistry)).thenReturn(dailyRegistry);
        
        mockmvc.perform(post("/dailyregistry/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dailyRegistry)))
                .andExpect(status().isCreated());
        
        DailyRegistry invalidDailyRegistry = new DailyRegistry();
        invalidDailyRegistry = dailyRegistry;
        
        invalidDailyRegistry.setDrUnit(null);
        
        mockmvc.perform(post("/dailyregistry/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDailyRegistry)))
                .andExpect(status().isNotAcceptable());
        
    }
    
    @Test
    void createDailyRegistryExceptionAPI() throws Exception{
        
        
        DailyRegistryServices drServices = Mockito.mock(DailyRegistryServices.class);
        
        Mockito.when(drServices.save(dailyRegistry))                
                .thenThrow(new Exception("Error database"));
        
        Mockito.when(drServices.update(dailyRegistry))
                .thenThrow(new Exception("Error database"));
        
        mockmvc.perform(post("/dailyregistry/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new DailyRegistry())))
                .andExpect(status().isInternalServerError());
        
        mockmvc.perform(put("/dailyregistry/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new DailyRegistry())))
                .andExpect(status().isNotAcceptable());
    }
    
    @Test
    void updateDailyRegistryAPI() throws Exception{
        Mockito.when(drRepo.save(dailyRegistry)).thenReturn(dailyRegistry);
        
        dailyRegistry.setDrId(1l);
        mockmvc.perform(put("/dailyregistry/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dailyRegistry)))
                .andExpect(status().isAccepted());
        
        DailyRegistry invalidDailyRegistry = new DailyRegistry();
        invalidDailyRegistry = dailyRegistry;
        
        invalidDailyRegistry.setDrUnit(null);
        
        mockmvc.perform(put("/dailyregistry/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDailyRegistry)))
                .andExpect(status().isNotAcceptable());
        
        invalidDailyRegistry = dailyRegistry;
        
        invalidDailyRegistry.setDrId(null);
        
        mockmvc.perform(put("/dailyregistry/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDailyRegistry)))
                .andExpect(status().isNotAcceptable());
    }  
    
    @Test
    void getDailyRegistryById()throws Exception{
        dailyRegistry.setDrId(1l);
        Mockito.when(drRepo.findById(1l)).thenReturn(Optional.of(dailyRegistry));
        
        MvcResult mvcResult = mockmvc.perform(MockMvcRequestBuilders.get("/dailyregistry/get?id=1"))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(dailyRegistry)))
                .andReturn();
        DailyRegistry dailyGetted = (DailyRegistry) new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), DailyRegistry.class);
        
        Assertions.assertEquals(dailyGetted.getDrId(), dailyRegistry.getDrId());        
        Assertions.assertEquals(dailyGetted.getDrUserId(), dailyRegistry.getDrUserId());
        Assertions.assertEquals(dailyGetted.getDrUnit().getuId(), dailyRegistry.getDrUnit().getuId());
    }
    
    @Test
    void getAllDailyRegistries() throws Exception{
        Mockito.when(drRepo.findAll()).thenReturn(dailyRegistries);
        
        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.get("/dailyregistry/getall"))
                .andExpect(status().isAccepted()).andReturn();
        
        List<DailyRegistry> returneData = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<DailyRegistry>>() {} );
        
        Assertions.assertEquals(returneData.size(), dailyRegistries.size());
        Assertions.assertEquals(returneData.get(0).getDrId(), dailyRegistries.get(0).getDrId());
        Assertions.assertEquals(returneData.get(0).getDrUnit().getuId(), dailyRegistries.get(0).getDrUnit().getuId());
        Assertions.assertEquals(returneData.get(0).getDrUserId(), dailyRegistries.get(0).getDrUserId());
        Assertions.assertEquals(returneData.get(1).getDrId(), dailyRegistries.get(1).getDrId());
        Assertions.assertEquals(returneData.get(1).getDrUnit().getuId(), dailyRegistries.get(1).getDrUnit().getuId());
        Assertions.assertEquals(returneData.get(1).getDrUserId(), dailyRegistries.get(1).getDrUserId());
                
    }
    
    
}
