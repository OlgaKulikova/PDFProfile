package com.kulykova.model;

import javax.validation.constraints.*;

public class FormModel {

  @NotNull
  @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters long.")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must be alphabetic with no spaces.")
  private String firstName;

  @NotNull
  @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters long.")
  @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must be alphabetic with no spaces.")
  private String lastName;

  @NotNull
  @Size(min = 8, max = 14, message = "Passport number must be between 8 and 14 characters long.")
  @Pattern(regexp = "^[A-Z0-9]+$", message = "Passport number must be alphanumeric with no spaces.")
  private String passport;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    FormModel formModel = (FormModel) o;

    if (firstName != null ? !firstName.equals(formModel.firstName) : formModel.firstName != null) return false;
    if (lastName != null ? !lastName.equals(formModel.lastName) : formModel.lastName != null) return false;
    return !(passport != null ? !passport.equals(formModel.passport) : formModel.passport != null);

  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    result = 31 * result + (passport != null ? passport.hashCode() : 0);
    return result;
  }
}
