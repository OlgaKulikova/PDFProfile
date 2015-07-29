package com.kulykova.services;

import com.kulykova.model.FormModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ServicesTests {
  private PDFDocumentFactory factory;
  private FormService formService;
  private FormModel formModel;

  @Before
  public void setUp() {
    factory = new PDFDocumentFactory();
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
  public void getPDPageContentStream_ShouldReturnNotNull() throws Exception {
    ByteArrayOutputStream baos = factory.createPDFDocument(formModel);
    assertNotNull(baos);
  }
}
