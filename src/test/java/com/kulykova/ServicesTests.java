package com.kulykova;

import com.kulykova.model.FormModel;
import com.kulykova.services.FormService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServicesTests {
  private FormService formService;

  @Before
  public void setUp() {
    formService = new FormService();
  }

  @Test
  public void checkCreationOfForm() {
    FormModel expected = new FormModel();
    expected.setFirstName("Name");
    expected.setLastName("Surname");
    expected.setPassport("Passport");
    FormModel actual = formService.createForm("Name", "Surname", "Passport");

    assertEquals(expected, actual);
  }
}
