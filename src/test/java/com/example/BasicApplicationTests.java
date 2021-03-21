package com.example;

import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasicApplicationTests {
    
        @Autowired
        private BasicController controller;
        
	@Test
	public void contextLoads() {
	}
        
        @Test
        void edit_profile_test(){
            assertEquals(controller.get_html("edit"),(String)controller.edit_profile());
            
        }//expect the page to be returned
    
        @Test
        void create_profile_test(){
            assertEquals(controller.create_profile(),controller.get_html("create_profile"));
        }
}
