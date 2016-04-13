package com.herokuapp.todomvc4tasj.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.herokuapp.todomvc4tasj.helpers.Filter;
import com.herokuapp.todomvc4tasj.helpers.Task;
import com.herokuapp.todomvc4tasj.helpers.TaskType;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static com.herokuapp.todomvc4tasj.helpers.Task.aTasks;
import static com.herokuapp.todomvc4tasj.helpers.Task.prepareScript;


public class TodoMVC {

    static ElementsCollection tasks = $$("#todo-list>li");

    public static void givenAtAll(Task... tasks) {
        givenAtFilter(Filter.ALL, tasks);
    }

    public static void givenAtAll(TaskType taskType, String... taskTexts) {
        givenAtFilter(Filter.ALL, taskType, taskTexts);
    }

    public static void givenAtCompleted(Task... tasks) {
        givenAtFilter(Filter.COMPLETED, tasks);
    }

    public static void givenAtCompleted(TaskType taskType, String... taskTexts) {
        givenAtFilter(Filter.COMPLETED, taskType, taskTexts);
    }

    public static void givenAtActive(Task... tasks) {
        givenAtFilter(Filter.ACTIVE, tasks);
    }

    public static void givenAtActive(TaskType taskType, String... taskTexts) {
        givenAtFilter(Filter.ACTIVE, taskType, taskTexts);
    }

    public static void givenAtFilter(Filter filter, Task... tasks) {
        ensurePageUrl(filter);
        executeJavaScript(prepareScript(tasks));
        executeJavaScript("location.reload()");
        $("#new-todo").shouldBe(enabled);
    }

    public static void givenAtFilter(Filter filter, TaskType taskType, String... taskTexts) {
        givenAtFilter(filter, aTasks(taskType, taskTexts));
    }

    @Step
    public static void add(String... taskTexts) {
        for (String text : taskTexts) {
            $("#new-todo").setValue(text).pressEnter();
        }
    }

    @Step
    public static SelenideElement startEdit(String oldTaskText, String newTaskText) {
        tasks.find(exactText(oldTaskText)).doubleClick();
        return tasks.find(cssClass("editing")).$(".edit").setValue(newTaskText);
    }

    @Step
    public static void toggle(String taskText) {
        tasks.find(exactText(taskText)).$(".toggle").click();
    }

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public static void delete(String taskText) {
        tasks.find(exactText(taskText)).hover().$(".destroy").click();
    }

    @Step
    public static void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public static void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public static void filterCompleted() {
        $(By.linkText("Completed")).click();
    }

    @Step
    public static void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public static void assertItemsLeft(int count) {
        $("#todo-count>strong").shouldHave(exactText(String.valueOf(count)));
    }

    @Step
    public static void assertVisibleTasks(String... taskTexts) {
        tasks.filter(visible).shouldHave(exactTexts(taskTexts));
    }

    @Step
    public static void assertTasks(String... taskTexts) {
        tasks.shouldHave(exactTexts(taskTexts));
    }

    @Step
    public static void assertNoTasks() {
        tasks.shouldBe(empty);
    }

    @Step
    public static void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    @Step
    public static void ensurePageUrl(Filter filterName) {
        if (!url().equals(filterName)) {
            open(filterName.getUrl());
        }
    }
}
