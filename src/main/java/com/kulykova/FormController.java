package com.kulykova;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/form")
public class FormController {
  private final FormService service;

  @Autowired
  public FormController(FormService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView getForm() {
    return new ModelAndView("form");
  }

  @RequestMapping(method = RequestMethod.POST)
  public ModelAndView fillForm(@RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("passport") String passport) {
    FormModel form = service.createForm(firstName, lastName, passport);
    PDDocument pdfDocument = PDFDocumentFactory.createPDFDocument(form);
    ModelAndView modelAndView = new ModelAndView("resultPDF");
    modelAndView.addObject("pdfDocument", "done!");

    return modelAndView;
  }
}
