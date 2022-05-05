
package com.inventoryapp.InventoryAppBackend.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventoryapp.InventoryAppBackend.models.Box;
import com.inventoryapp.InventoryAppBackend.models.DefaultItem;
import com.inventoryapp.InventoryAppBackend.repo.DefaultItemRepo;
import java.math.BigDecimal;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author ale
 */
@SpringBootTest
@AutoConfigureMockMvc
public class DefaultItemsAPITest {
    @Autowired
    MockMvc mockmvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    List<DefaultItem> defaultItems;
    
    @MockBean
    DefaultItemRepo diRepo;
    
    @BeforeEach
    public void before() throws Exception{
        Box box = new Box();
        box.setbId(15l);
        
        DefaultItem di3 = new DefaultItem();
        di3.setDiBox(box);
        di3.setDiId(1l);
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
        
        Mockito.when(diRepo.findById(di3.getDiId()))
        .thenReturn(Optional.of(di3));
        
        Mockito.when(diRepo.findAll())
                .thenReturn(defaultItems);
        
        Mockito.when(diRepo.findDefaultItemsByBox(box.getbId()))
                .thenReturn(defaultItems);
    }
    
    @Test
    void postToSaveDefaultItems() throws Exception{
        mockmvc.perform(post("/defaultitem/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(defaultItems)))
                .andExpect(status().isCreated());
        
        List<DefaultItem> incompleteDI = new ArrayList<>();
        incompleteDI = defaultItems;
        
        incompleteDI.get(0).setDiBox(null);
        
        mockmvc.perform(post("/defaultitem/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incompleteDI)))
                .andExpect(status().isNotAcceptable());
    }
    
    @Test
    void putToUpdateDefaultItem() throws Exception{
        mockmvc.perform(put("/defaultitem/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(defaultItems)))
                .andExpect(status().isAccepted());
        
        List<DefaultItem> incompleteDI = new ArrayList<>();
        incompleteDI = defaultItems;
        
        incompleteDI.get(0).setDiBox(null);
        
        mockmvc.perform(put("/defaultitem/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(incompleteDI)))
                .andExpect(status().isNotAcceptable());
    }
    
    @Test
    void getDefaultItem() throws Exception{
        mockmvc.perform(get("/defaultitem/get?id=1"))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.content().string(objectMapper.writeValueAsString(defaultItems.get(0))));
        
    }
    
    @Test
    void getDefaultItems() throws Exception{
        mockmvc.perform(get("/defaultitem/getall"))
                .andExpect(status().isAccepted())
                .andExpect(MockMvcResultMatchers.content()
                    .string(objectMapper.writeValueAsString(defaultItems)));
    }
    
    @Test
    void getDefaultItemsByBox() throws Exception{
        MvcResult result = mockmvc.perform(get("/defaultitem/getallbybox?id=15"))
            .andExpect(status().isAccepted()).andReturn();
        
        
        List<DefaultItem> defaultItemsReturn = 
            (List<DefaultItem>) new ObjectMapper().readValue(result.getResponse()
            .getContentAsString(),new TypeReference<List<DefaultItem>>(){});
        
        Assertions.assertEquals(defaultItems.size(), defaultItemsReturn.size());
    }
}
