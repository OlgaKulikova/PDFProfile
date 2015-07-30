package com.kulykova.factory;

import com.kulykova.model.FormModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PDFDocumentFactoryTest {
  private PDFDocumentFactory factory;
  private FormModel formModel;

  @Before
  public void setUp() {
    factory = new PDFDocumentFactory();
    formModel = new FormModel();
    formModel.setFirstName("Name");
    formModel.setLastName("Surname");
    formModel.setPassport("Passport");
  }

  @Test
  public void getPDPageContentStream_ShouldReturnNotNull() throws Exception {
    ByteArrayOutputStream baos = factory.createPDFDocument(formModel);
    assertNotNull(baos);
  }
}
