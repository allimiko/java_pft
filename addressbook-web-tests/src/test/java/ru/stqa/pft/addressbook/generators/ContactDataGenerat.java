package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactDate;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Администратор on 17.01.2017.
 */
public class ContactDataGenerat {

    @Parameter(names = "-w", description = "Contact count")
    public int count;

    @Parameter(names = "-s", description = "Target file")
    public String file;

    @Parameter(names = "-a", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDataGenerat generator = new ContactDataGenerat();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        }catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactDate> coctacts = generatContacts(count);
        if(format.equals("csv")) {
            save(coctacts, new File(file));
        }else if (format.equals("xml")){
            saveAsXml(coctacts, new File(file));
        }else if (format.equals("json")){
            saveAsGson(coctacts, new File(file));
        }else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsGson(List<ContactDate> coctacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(coctacts);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }

    private void saveAsXml(List<ContactDate> coctacts, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(coctacts);
        Writer writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }


    private  void save(List<ContactDate> coctacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactDate contact :coctacts){
            writer.write(String.format("%s;%s;%s;%s;%s\n",
                    contact.getFirstname()
                   ,contact.getLastname()
                    ,contact.getAddress()
                    ,contact.getMail()
                    ,contact.getMobilePhone()));
        }
        writer.close();

    }

    private  List<ContactDate> generatContacts(int count) {
        List<ContactDate> coctacts =  new ArrayList<ContactDate>();
        for (int i = 0; i < count; i++){
            coctacts.add(new ContactDate()
                    .withFirstname(String.format("Test %s", i))
                    .withFirstname(String.format("Test %s", i))
                    .withLastname(String.format("Test %s", i))
                    .withAddress(String.format(" Test %s",i))
                    .withMail(String.format("Test %s",i))
                    .withMobilePhone(String.format("Test %s",i)));
        }
        return coctacts;
    }
}
