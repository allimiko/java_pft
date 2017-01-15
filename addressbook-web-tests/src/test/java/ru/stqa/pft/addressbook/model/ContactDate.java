package ru.stqa.pft.addressbook.model;

public class ContactDate {
  private  int id = Integer.MAX_VALUE;
  private  String firstname;
  private  String middlename;
  private  String lastname;
  private  String nickname;
  private  String company;
  private  String homePhone;
  private  String mobilePhone;
  private  String workPhone;
  private  String allPhones;
  private String mail;
  private String mail2;
  private String mail3;
  private String allMail;
  private String address;
  private String group;

  public String getAddress() {
    return address;
  }

  public ContactDate withAddress(String address) {
    this.address = address;
    return this;
  }





  public String getAllMail() {
    return allMail;
  }

  public ContactDate withAllMail(String allMail) {
    this.allMail = allMail;
    return this;
  }
  public String getMail() {
    return mail;
  }

  public ContactDate withMail(String mail) {
    this.mail = mail;
    return  this;
  }

  public String getMail2() {
    return mail2;
  }

  public ContactDate withMail2(String mail2) {
    this.mail2 = mail2;
    return this;
  }

  public String getMail3() {
    return mail3;
  }

  public ContactDate withMail3(String mail3) {
    this.mail3 = mail3;
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactDate withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public ContactDate withMobilePhone(String mobile) {
    this.mobilePhone = mobile;
    return this;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public ContactDate withtWorkPhone(String work) {
    this.workPhone = work;
    return this;
  }

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

  public String getHomePhone() {
    return homePhone;
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

  public ContactDate withHomePhone(String home) {
    this.homePhone = home;
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

    if (id != that.id) return false;
    return firstname != null ? firstname.equals(that.firstname) : that.firstname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    return result;
  }
}
