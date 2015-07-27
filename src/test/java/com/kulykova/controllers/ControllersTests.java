package com.kulykova.controllers;

import com.kulykova.services.FormService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/PDFProfile-servlet.xml")
@WebAppConfiguration
public class ControllersTests {
  private MockMvc mockMvc;

  @Autowired
  private FormService formServiceMock;

  @Autowired
  private WebApplicationContext wac;

  @Before
  public void setUp() {
    Mockito.reset(formServiceMock);
    mockMvc = webAppContextSetup(wac).build();
  }

  @Test
  public void shouldRenderFormView() throws Exception {
    mockMvc.perform(get("/form"))
        .andExpect(status().isOk())
        .andExpect(view().name("form"))
        .andExpect(forwardedUrl("/WEB-INF/pages/form.jsp"));
  }

  @Test
  public void whenOutputStreamIsNull_ShouldRenderErrorView() throws Exception {
    mockMvc.perform(post("/form"))
        .andExpect(view().name("error"))
        .andExpect(forwardedUrl("/WEB-INF/pages/error.jsp"));
  }


}
