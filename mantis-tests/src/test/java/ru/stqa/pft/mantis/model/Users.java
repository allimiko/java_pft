package ru.stqa.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Администратор on 28.01.2017.
 */
public class Users extends ForwardingSet<UserData> {
    private Set<UserData> delegate;

    public Users(Collection<UserData> groups) {
        this.delegate = new HashSet<UserData>(groups);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }
}
