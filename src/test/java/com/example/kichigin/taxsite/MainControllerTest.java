package com.example.kichigin.taxsite;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginPageStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testMainPageStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAboutPageStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testStoragePageStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/storage"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}