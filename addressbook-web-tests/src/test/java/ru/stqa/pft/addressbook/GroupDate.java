package ru.stqa.pft.addressbook;

public class GroupDate {
  private final String name;
  private final String header;
  private final String head;
  private final String footer;

  public GroupDate(String name, String header, String head, String footer) {
    this.name = name;
    this.header = header;
    this.head = head;
    this.footer = footer;
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
}
