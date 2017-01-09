package ru.stqa.pft.addressbook.model;

public class GroupData {
  private final String name;
  private final String header;
  private final String head;
  private final String footer;
  private  int id;

  public GroupData(String name, String header, String head, String footer) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.header = header;
    this.head = head;
    this.footer = footer;
  }

  public void setId(int id) {
    this.id = id;
  }

  public GroupData(int  id, String name, String header, String head, String footer) {
    this.name = name;
    this.header = header;
    this.head = head;
    this.footer = footer;
    this.id = id;
  }

    public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getHead() {
    return head;
  }

  public String getFooter() {
    return footer;
  }

  public int  getId() {
    return id;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GroupData groupData = (GroupData) o;

    return name != null ? name.equals(groupData.name) : groupData.name == null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}
