package com.kulykova.services;

import com.kulykova.model.FormModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/PDFProfile-servlet.xml")
@WebAppConfiguration
public class ServicesTests {
  private FormService formService;
  private FormModel formModel;

  @Autowired
  private PDFDocumentFactory factoryMock;

  @Before
  public void setUp() {
    Mockito.reset(factoryMock);
    formService = new FormService();
    formModel = new FormModel();
    formModel.setFirstName("Name");
    formModel.setLastName("Surname");
    formModel.setPassport("Passport");
  }

  @Test
  public void checkFormModelCreation() {
    FormModel actual = formService.createForm("Name", "Surname", "Passport");
    assertEquals(formModel, actual);
  }

  @Test
  public void checkPDFDocumentCreationAndFilling() {
    when(factoryMock.createPDFDocument(formModel))
        .thenReturn(new ByteArrayOutputStream());
  }

  @Test
  public void checkPDPageContentStream() throws Exception {
    when(factoryMock.getPdPageContentStream(formModel, PDType1Font.HELVETICA_BOLD))
        .thenReturn(new PDPageContentStream(new PDDocument(), new PDPage()));
  }
}
