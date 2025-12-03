package com.xworkz.user.controller;

import com.xworkz.user.service.RegisterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ControllerTest {

    @Mock
    private RegisterService registerService;

    @InjectMocks
    private LoginController loginController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    void testGetLoginPage() throws Exception {

        when(registerService.getAllEmail()).thenReturn(
                Arrays.asList("anna@gmail.com", "test@gmail.com")
        );

        mockMvc.perform(get("/redirectToLogin"))
                .andExpect(status().isOk())
                .andExpect(view().name("Login"))
                .andExpect(model().attributeExists("emails"))
                .andExpect(model().attribute("emails",
                        Arrays.asList("anna@gmail.com", "test@gmail.com")));

        verify(registerService, times(1)).getAllEmail();
    }
}
