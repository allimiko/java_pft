package ru.stqa.pft.addressbook.model;

public class ContactDate {
  private  int id = Integer.MAX_VALUE;;
  private  String firstname;
  private  String middlename;
  private  String lastname;
  private  String nickname;
  private  String company;
  private  String home;
  private String group;



  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getHome() {
    return home;
  }

  public String getGroup() {
    return group;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public ContactDate withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactDate withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactDate withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactDate withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactDate withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactDate withHome(String home) {
    this.home = home;
    return this;
  }

  public ContactDate withGroup(String group) {
    this.group = group;
    return this;
  }
 public ContactDate withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public String toString() {
    return "ContactDate{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", middlename='" + middlename + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactDate that = (ContactDate) o;

    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (middlename != null ? !middlename.equals(that.middlename) : that.middlename != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (middlename != null ? middlename.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
