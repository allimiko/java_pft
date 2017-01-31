package ru.stqa.pft.rest;

import appManager.ApplicationManager;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.RestAssured;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

/**
 * Created by Администратор on 31.01.2017.
 */
public class TestBase {
    protected static final ApplicationManager app = new ApplicationManager();

    @BeforeClass
    public  void init() throws IOException {
        app.init();
        RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
    }

    boolean isIssueOpen(int issueId){
        String json = RestAssured
                .get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId))
                .asString();
        JsonElement parsed = new JsonParser().parse(json);
       String issueState = parsed.getAsJsonObject().get("issues").getAsJsonArray()
              .get(0).getAsJsonObject().get("state_name").getAsString();

        if (issueState.equals("Resolved") || issueState.equals("Closed")){
                 return false;
               }
           return true;
    }

    public void skipIfNotFixed(int issueId) {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

}
