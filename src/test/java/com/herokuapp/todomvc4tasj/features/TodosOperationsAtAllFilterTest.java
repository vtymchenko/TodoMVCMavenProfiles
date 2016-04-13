package com.herokuapp.todomvc4tasj.features;

import com.herokuapp.todomvc4tasj.categories.Buggy;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.herokuapp.todomvc4tasj.helpers.Task.aTask;
import static com.herokuapp.todomvc4tasj.helpers.TaskType.ACTIVE;
import static com.herokuapp.todomvc4tasj.helpers.TaskType.COMPLETED;
import static com.herokuapp.todomvc4tasj.pages.TodoMVC.*;

/**
 * Created by Valentyn on 11.04.2016.
 */
public class TodosOperationsAtAllFilterTest extends BaseTest {

    @Test
    @Category(Buggy.class)
    public void testCompleteOnAll() {
        givenAtAll(ACTIVE, "a", "b");

        toggle("a");
        assertTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testReopenOnAll() {
        givenAtAll(aTask(ACTIVE, "a"), aTask(COMPLETED, "b"));

        toggle("b");
        assertTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testReopenAllOnAll() {
        givenAtAll(COMPLETED, "a", "b");

        toggleAll();
        assertTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testClearCompletedOnAll() {
        givenAtAll(aTask(ACTIVE, "a"), aTask(COMPLETED, "b"), aTask(COMPLETED, "c"));

        clearCompleted();
        assertTasks("a");
    }

    @Test
    public void testCancelEditByEscapeOnAll() {
        givenAtAll(aTask(COMPLETED, "a"), aTask(ACTIVE, "b"), aTask(COMPLETED, "c"), aTask(ACTIVE, "d"));

        startEdit("d", "d edit").pressEscape();
        assertTasks("a", "b", "c", "d");
        assertItemsLeft(2);
    }

    @Test
    public void testEditByPressTabOnAll() {
        givenAtAll(aTask(COMPLETED, "a"), aTask(ACTIVE, "b"));

        startEdit("b", "b edited").pressTab();
        assertTasks("a", "b edited");
        assertItemsLeft(1);
    }

}
