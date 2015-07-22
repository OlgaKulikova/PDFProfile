package com.kulykova;

import org.springframework.stereotype.Service;

@Service
public class FormService {
  public FormModel createForm(String firstName, String lastName, String passport) {
    FormModel form = new FormModel();
    form.setFirstName(firstName);
    form.setLastName(lastName);
    form.setPassport(passport);

    return form;
  }
}
