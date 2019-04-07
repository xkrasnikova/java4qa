package krasnikova.addressbook.appmanager;

import krasnikova.addressbook.model.ContactData;
import krasnikova.addressbook.model.Contacts;
import krasnikova.addressbook.model.GroupData;
import krasnikova.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper(){

      // A SessionFactory is set up once for an application!
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery( "from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

  public ContactData contact(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    Object result = session.createQuery("from ContactData where id = " + id).uniqueResult();
    session.getTransaction().commit();
    session.close();
    return ((ContactData) result);
  }

  public Contacts contactsWithGroup(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00' and from ").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }
}
