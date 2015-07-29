package com.kulykova.controllers;

import com.kulykova.model.FormModel;
import com.kulykova.services.FormService;
import com.kulykova.services.PDFDocumentFactory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.ByteArrayOutputStream;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/PDFProfile-servlet.xml",
    "file:src/test/resorces/testContext.xml"})
@WebAppConfiguration
public class ControllersTests {
  private MockMvc mockMvc;
  private FormModel formModel;

  @Autowired
  private FormService formServiceMock;

  @Autowired
  private PDFDocumentFactory factoryMock;

  @Autowired
  private WebApplicationContext wac;

  @Before
  public void setUp() {
    reset(formServiceMock);
    reset(factoryMock);
    mockMvc = webAppContextSetup(wac).build();

    formModel = new FormModel();
    formModel.setFirstName("Name");
    formModel.setLastName("Surname");
    formModel.setPassport("Passport");
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

  @Test
  public void checkPDFDocumentCreationAndFilling() throws Exception {
    when(factoryMock.createPDFDocument(formModel))
        .thenReturn(new ByteArrayOutputStream());
  }

  @Test
  public void checkPDPageContentStream() throws Exception {
    when(factoryMock.getPdPageContentStream(formModel, PDType1Font.HELVETICA_BOLD))
        .thenReturn(new PDPageContentStream(new PDDocument(), new PDPage()));
  }

  @Test (expected = Exception.class)
  public void checkThrowingException() {
    when(factoryMock.createPDFDocument(formModel)).thenThrow(new Exception());
  }

}

