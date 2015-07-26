package com.kulykova.model;

public class FormModel {
  private String firstName;
  private String lastName;
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
