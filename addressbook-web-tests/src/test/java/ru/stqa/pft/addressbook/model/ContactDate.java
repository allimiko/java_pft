package ru.stqa.pft.addressbook.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactDate {

  @Id
  @Column (name = "id")
  private  int id = Integer.MAX_VALUE;

  @Column (name = "firstname")
  private  String firstname;

  @Column (name = "middlename")
  private  String middlename;

  @Column (name = "lastname")
  private  String lastname;

  @Column (name = "nickname")
  private  String nickname;

  @Column (name = "company")
  private  String company;

  @Column (name = "home")
  @Type(type = "text")
  private  String homePhone;

  @Column (name = "mobile")
  @Type(type = "text")
  private  String mobilePhone;

  @Column (name = "work")
  @Type(type = "text")
  private  String workPhone;

  @Column (name = "email")
  @Type(type = "text")
  private String mail;

  @Column (name = "email2")
  @Type(type = "text")
  private String mail2;

  @Column (name = "email3")
  @Type(type = "text")
  private String mail3;

  @Column (name = "address")
  @Type(type = "text")
  private String address;

  @Transient
  private String content;

  @Transient
  @Column (name = "photo")
  private String photo;

  @Transient
  private String allPhones;

  @Transient
  private String allMail;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<>();

  public File getPhoto() {
    return new File(photo);
  }

  public ContactDate withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }


  public String getContent() {
    return content;
  }

  public ContactDate withContent(String content) {
    this.content = content;
    return this;
  }

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


  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public Groups getGroups() {
    return new Groups(groups);
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


 public ContactDate withId(int id) {
    this.id = id;
    return this;
  }

  public ContactDate inGroup(GroupData group) {
    groups.add(group);
    return this;
  }

  @Override
  public String toString() {
    return "ContactDate{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactDate that = (ContactDate) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }
}
