package com.kulykova.controllers;

import com.kulykova.model.FormModel;
import com.kulykova.services.FormService;
import com.kulykova.services.PDFDocumentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
@RequestMapping("/form")
public class FormController {
  private FormService service;
  private PDFDocumentFactory factory;

  @Autowired
  public FormController(FormService service, PDFDocumentFactory factory) {
    this.service = service;
    this.factory = factory;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getForm() {
    return new ModelAndView("form");
  }

  @RequestMapping(method = RequestMethod.POST)
  public void fillForm(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("passport") String passport,
                               HttpServletResponse response) throws IOException {
    FormModel form = service.createForm(firstName, lastName, passport);
    ByteArrayOutputStream pdfDocument = factory.createPDFDocument(form);

    response.setContentType("application/pdf");
    response.getOutputStream().write(pdfDocument.toByteArray());
    response.getOutputStream().close();
  }
}
