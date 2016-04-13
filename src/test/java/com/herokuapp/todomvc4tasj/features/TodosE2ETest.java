package com.herokuapp.todomvc4tasj.features;

import com.herokuapp.todomvc4tasj.categories.Smoke;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.herokuapp.todomvc4tasj.pages.TodoMVC.*;

/**
 * Created by Valentyn on 11.04.2016.
 */
public class TodosE2ETest extends BaseTest {

    @Test
    @Category(Smoke.class)
    public void testTasksWorkFlow() {
        givenAtAll();

        add("a");
        startEdit("a", "a edited").pressEnter();
        assertTasks("a edited");
        assertItemsLeft(1);

        toggleAll();
        assertTasks("a edited");

        filterActive();
        assertNoVisibleTasks();
        add("b");
        startEdit("b", "b edited cancelled").pressEscape();

        //complete
        toggle("b");
        assertNoVisibleTasks();

        filterCompleted();
        assertVisibleTasks("a edited", "b");

        //reopen
        toggle("b");
        assertVisibleTasks("a edited");
        clearCompleted();
        assertNoVisibleTasks();

        filterAll();
        delete("b");
        assertNoTasks();
    }
}
