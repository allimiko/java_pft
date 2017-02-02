package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Администратор on 14.01.2017.
 */
public class Contacts extends ForwardingSet<ContactDate> {

    private Set<ContactDate> delegate;

    public Contacts(Contacts contacts){
        this.delegate = new HashSet<ContactDate>(contacts.delegate);
    }

    public Contacts() {
        this.delegate = new HashSet<ContactDate>();
   }

    public Contacts(Collection<ContactDate> contactDates) {
        this.delegate = new HashSet<ContactDate>(contactDates);
    }

    @Override
    protected Set<ContactDate> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactDate date){
        Contacts contacts = new Contacts(this);
        contacts.add(date);
        return contacts;
    }

    public Contacts withOut (ContactDate contactDate){
        Contacts contacts = new Contacts(this);
        contacts.remove(contactDate);
        return contacts;
    }

}
