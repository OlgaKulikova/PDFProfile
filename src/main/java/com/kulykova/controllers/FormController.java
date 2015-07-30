package com.kulykova.controllers;

import com.kulykova.factory.PDFDocumentFactory;
import com.kulykova.model.FormModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/form")
public class FormController {
  private PDFDocumentFactory factory;

  @Autowired
  public FormController(PDFDocumentFactory factory) {
    this.factory = factory;
  }

  @RequestMapping(method = RequestMethod.GET)
  public String getForm() {
    return "form";
  }

  @RequestMapping(method = RequestMethod.POST)
  public void fillForm(@Valid FormModel formModel, BindingResult bindingResult,
                               HttpServletResponse response) throws IOException {
    if (bindingResult.hasErrors()) {
      throw new IllegalArgumentException("Invalid filled fields!");
    }
    ByteArrayOutputStream pdfDocument = factory.createPDFDocument(formModel);

    response.setContentType("application/pdf");
    response.getOutputStream().write(pdfDocument.toByteArray());
    response.getOutputStream().close();
  }
}
