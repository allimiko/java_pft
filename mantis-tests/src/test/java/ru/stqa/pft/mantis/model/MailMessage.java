package ru.stqa.pft.mantis.model;

/**
 * Created by Администратор on 26.01.2017.
 */
public class MailMessage {
    public String to;
    public String text;

    public MailMessage(String to, String text) {
        this.to = to;
        this.text = text;
    }
}
