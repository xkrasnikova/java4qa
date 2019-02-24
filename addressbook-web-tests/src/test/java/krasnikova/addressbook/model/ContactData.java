package krasnikova.addressbook.model;

import java.util.Objects;

public class ContactData {
  private final String id;
  private final String firstName;
  private final String lastName;
  private final String address;
  private final String phone;
  private final String email;

  @Override
  public String toString() {
    return "ContactData{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", address='" + address + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            ", group='" + group + '\'' +
            '}';
  }

  private String group;

  public ContactData(String id,String firstName, String lastName, String address, String phone, String email, String group) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }

  public ContactData(String firstName, String lastName, String address, String phone, String email, String group) {
    this.id = null;
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phone = phone;
    this.email = email;
    this.group = group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            address.equals(that.address) &&
            phone.equals(that.phone) &&
            email.equals(that.email) &&
            group.equals(that.group);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, address, phone, email, group);
  }

  public String getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getAddress() {
    return address;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
