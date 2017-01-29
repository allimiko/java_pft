package ru.stqa.pft.mantis.mantis;

import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Администратор on 29.01.2017.
 */
public class SoapTests extends TestBase {
    @Test
    public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
       Set<Project> projects =  app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects){
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        Set<Project> projects =  app.soap().getProjects();
        Issue issue = new Issue().withSummary("Test issue").withDescription("Everything is bad")
                .withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(),created.getSummary());
    }

    @Test
    public void testSkip() throws RemoteException, ServiceException, MalformedURLException {
        skipIfNotFixed(0000003);
        Set<Project> projects =  app.soap().getProjects();
        assertEquals(projects.size(),4);
    }
}
