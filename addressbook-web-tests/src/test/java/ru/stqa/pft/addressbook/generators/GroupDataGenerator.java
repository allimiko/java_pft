package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class GroupDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static void main(String[] args) throws IOException {
        GroupDataGenerator generator = new GroupDataGenerator();
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
        List<GroupData> groups = generatGroups(count);
        if(format.equals("csv")) {
            saveAsCsv(groups, new File(file));
        }else if (format.equals("xml")){
            saveAsXml(groups, new File(file));
        }else if (format.equals("json")){
            saveAsGson(groups, new File(file));
        }else {
            System.out.println("Unrecognized format " + format);
        }
    }

    private void saveAsGson(List<GroupData> groups, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(groups);
        try ( Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private void saveAsXml(List<GroupData> groups, File file) throws IOException {
        XStream xStream = new XStream();
        xStream.processAnnotations(GroupData.class);
        String xml = xStream.toXML(groups);
        try ( Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private  void saveAsCsv(List<GroupData> groups, File file) throws IOException {
        try (Writer writer = new FileWriter(file)){
            for (GroupData group : groups){
                writer.write(String.format("%s;%s;%s\n",
                        group.getName(), group.getHeader(),group.getFooter()));
            }
        }
    }

    private  List<GroupData> generatGroups(int count) {
        List<GroupData> groups =  new ArrayList<GroupData>();
        for (int i = 0; i < count; i++){
            int j = 45;
            groups.add(new GroupData()
                    .withName(String.format("test %s", j))
                    .withHeader(String.format("header %s", j))
                    .withFooter(String.format("footer %s", j)));
        }
        return groups;
    }
}
