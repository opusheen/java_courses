package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

public class DbHelper {
    public final SessionFactory sessionFactory;
    public DbHelper(){



    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

}
public ContactData contact;
public GroupData group;

public Groups groups() {
    Session session = sessionFactory.openSession();
            session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    for (GroupData groups : result) {
        System.out.println(groups);
    }
            session.getTransaction().commit();
            session.close();
            return new Groups(result);
}
    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }





    public Contacts contacttoDelete() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery("from ContactInGrData where deprecated = '0000-00-00' and where ").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }


    public ContactData contacts(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String request = "from ContactData where id = " + id;
        List<ContactData> resultContact = session.createQuery(request).list();
        ContactData result =  resultContact.iterator().next();
        session.getTransaction().commit();
        session.close();
        return result;
    }
    public GroupData groups(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String request = "from GroupData where group_id = " + id;
        System.out.println(request);
        List<GroupData> resultGroup = session.createQuery(request).list();
        GroupData result =  resultGroup.iterator().next();
        session.getTransaction().commit();
        session.close();
        return result;
    }





    public GroupData grouptoAdd(ContactData contact) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<GroupData> groups = session.createQuery("from GroupData").list();
            int GroupId = 0;
            int ContactId = contact.getId();

        for (GroupData group : groups) {
                GroupId = group.getId();
                 List<ContactInGrData> contac = session.createQuery(" from ContactInGrData where id ='" + ContactId + "'"+ " and group_id ='" + GroupId + "'").list();
                if (contac == null) {
                    session.getTransaction().commit();
                    session.close();
                    return group;
                }
                    }
        session.getTransaction().commit();
        session.close();
        return null;
}



    public ContactData contactToAddToGroup() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> contacts = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
        int ContactId = 0;
        int GroupId = 0;
        for (ContactData contact : contacts) {
            List<GroupData> groups = session.createQuery("from GroupData ").list();
            for (GroupData group : groups) {
                ContactId = contact.getId();
                GroupId = group.getId();
                List<ContactInGrData> contac = session.createQuery(" from ContactInGrData where id ='" + ContactId + "'" + " and group_id ='" + GroupId + "'").list();
                if (contac == null) {
                    session.getTransaction().commit();
                    session.close();
                    return contact;
                }
            }
        }
        session.getTransaction().commit();
        session.close();
        return null;
    }
}