package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter (names = "-f", description = "Target file")
    public String file;
    @Parameter (names = "-d", description = "Data format")
    public String format;
///-c 1 -f src\test\resources\contacts.json -d json
//wdrir=addressbook web tests

    public static void main (String [] args) throws IOException {

        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException{
        List<ContactData> contacts = generateContacts (count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if(format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format" + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)){
        writer.write(json);
             }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)) {
        writer.write(xml);
    }
    }

    private  void saveAsCsv (List<ContactData> contacts, File file ) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact : contacts) {
            writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(),contact.getLastname(),contact.getAddress(), contact.getEmail(),
                    contact.getMobilephonenumber())) ;
        }
        writer.close();
    }


    private  List<ContactData> generateContacts (int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData().withFirstName(String.format("Ivan%s", i))
                    .withLastname(String.format("Ivanov%s", i))
                    .withAddress(String.format("street%s",i))
                    .withEmail(String.format("emailyahoo%s",i))
                    .withEmail2(String.format("emailyahoo%s",i))
                    .withEmail3(String.format("emailyahoo%s",i))
                    .withMobilephonenumber(String.format("435%s",i))
                    .withWorkphone(String.format("43353464365%s",i))
                    .withHomephone(String.format("00%s",i)));

        }
        return contacts;
    }

}

// -c 10 -f src\test\resources\contacts.json -d json
//wdir \java_c\java_courses\addressbook-web-tests