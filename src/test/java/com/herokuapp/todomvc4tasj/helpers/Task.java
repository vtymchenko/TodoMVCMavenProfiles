package com.herokuapp.todomvc4tasj.helpers;

/**
 * Created by Valentyn on 05.04.2016.
 */
public class Task {
    private String taskText;
    private TaskType taskType;


    public Task(TaskType taskType, String taskText) {
        this.taskType = taskType;
        this.taskText = taskText;
    }

    public String getTaskText() {
        return taskText;
    }

    //localStorage.setItem('todos-troopjs','[{"completed":true,"title":"a"},{"completed":true,"title":"b"} ]')

    public static String prepareScript(Task... tasks) {
        String tempResult = "localStorage.setItem('todos-troopjs','[ ";
        for (Task task : tasks) {
            tempResult += "{" + "\"completed\":" + task.taskType.getStatus() + ",\"title\":" + "\""
                    + task.getTaskText() + "\"" + "},";
        }
        tempResult = tempResult.substring(0, tempResult.length() - 1);
        tempResult += "]')";
        return tempResult;
    }

    public static Task aTask(TaskType taskType, String taskText) {
        return new Task(taskType, taskText);
    }

    public static Task[] aTasks(TaskType taskType, String... taskTexts) {
        Task[] tasks = new Task[taskTexts.length];
        int i = 0;
        for (String taskText : taskTexts) {
            tasks[i] = new Task(taskType, taskText);
            i++;
        }
        return tasks;
    }
}
