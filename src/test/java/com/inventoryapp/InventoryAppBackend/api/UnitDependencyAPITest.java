package com.inventoryapp.InventoryAppBackend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryapp.InventoryAppBackend.models.UnitDependecy;
import com.inventoryapp.InventoryAppBackend.repo.UnitDependecyRepo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 *
 * @author ale
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UnitDependencyAPITest {
    @Autowired
    MockMvc mockmvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    List<UnitDependecy> units = new ArrayList<>();
    UnitDependecy unitDependecy = new UnitDependecy();
    
    
    @MockBean
    UnitDependecyRepo unitDependecyRepo;
    
    
    @BeforeEach
    public void before() throws Exception{        
        unitDependecy.setuId(1);
        unitDependecy.setuCode("code"+LocalDateTime.now());
        unitDependecy.setuName("name_"+LocalDateTime.now());
        unitDependecy.setuImage("image_"+LocalDateTime.now());
        
        UnitDependecy unitDependecy2 = new UnitDependecy();
        unitDependecy2.setuId(1);
        unitDependecy2.setuCode("code"+LocalDateTime.now());
        unitDependecy2.setuName("name_"+LocalDateTime.now());
        unitDependecy2.setuImage("image_"+LocalDateTime.now());        
        units.add(unitDependecy);
        units.add(unitDependecy2);
        
        Optional<UnitDependecy> unitOptional;
        
        Mockito.when(unitDependecyRepo.save(unitDependecy))
        .thenReturn(unitDependecy);
        
        Mockito.when(unitDependecyRepo.findById(unitDependecy.getuId()))
        .thenReturn(Optional.of(unitDependecy));
        
        Mockito.when(unitDependecyRepo.findAll())
        .thenReturn(units);
        
        
        
    }
    
    @Test            
    void getall() throws Exception{
        mockmvc.perform(get("/unitdependency/getall"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(units)));
    }
    
    @Test            
    void getById() throws Exception{
        mockmvc.perform(get("/unitdependency/get?id=1"))
                .andExpect(status().isAccepted())
                .andExpect(content().string(objectMapper.writeValueAsString(unitDependecy)));
    }
    
    @Test
    void saveUnit() throws Exception{
        
        UnitDependecy unitDependecy = new UnitDependecy();
        unitDependecy.setuCode("code"+LocalDateTime.now());
        unitDependecy.setuName("name_"+LocalDateTime.now());
        unitDependecy.setuImage("image_"+LocalDateTime.now());
        
        mockmvc.perform(post("/unitdependency/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unitDependecy)))
                .andExpect(status().isCreated());
        
        unitDependecy.setuCode(null);
        unitDependecy.setuName("name_"+LocalDateTime.now());
        unitDependecy.setuImage("image_"+LocalDateTime.now());
        
        mockmvc.perform(post("/unitdependency/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unitDependecy)))
                .andExpect(status().isNotAcceptable());
        
        unitDependecy.setuCode("code"+LocalDateTime.now());
        unitDependecy.setuName(null);
        unitDependecy.setuImage("image_"+LocalDateTime.now());
        
        mockmvc.perform(post("/unitdependency/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unitDependecy)))
                .andExpect(status().isNotAcceptable());
        
        unitDependecy.setuCode("code"+LocalDateTime.now());
        unitDependecy.setuName("name_"+LocalDateTime.now());
        unitDependecy.setuImage(null);
        
        mockmvc.perform(post("/unitdependency/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unitDependecy)))
                .andExpect(status().isNotAcceptable());
        
    }
    
    @Test
    void updateUnit() throws Exception{
        UnitDependecy unitDependecy = new UnitDependecy();
        unitDependecy.setuId(1);
        unitDependecy.setuCode("code"+LocalDateTime.now()+"_updated");
        unitDependecy.setuName("name_"+LocalDateTime.now()+"_updated");
        unitDependecy.setuImage("image_"+LocalDateTime.now()+"_updated");
        mockmvc.perform(MockMvcRequestBuilders.put("/unitdependency/update")
                .content(objectMapper.writeValueAsString(unitDependecy))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }
    
}
