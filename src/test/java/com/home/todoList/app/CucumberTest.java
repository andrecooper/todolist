package com.home.todoList.app;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by andrew on 03.04.16.
 */

//@RunWith(Cucumber.class)
public class CucumberTest {

    @Given("^taskName$")
    public void taskname() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Given code");
    }

    @When("^serching Task by  TaskName$")
    public void serching_Task_by_TaskName() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("When code");

    }

    @Then("^return list of tasks$")
    public void return_list_of_tasks() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("then code");
    }

}
