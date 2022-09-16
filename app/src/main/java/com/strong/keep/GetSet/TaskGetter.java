package com.strong.keep.GetSet;

public class TaskGetter {
    String TaskName;
    String TaskSummery;

    public String getTaskName() {
        return TaskName;
    }

    public String getTaskSummery() {
        return TaskSummery;
    }

    public TaskGetter(String TaskName, String TaskSummery) {
        this.TaskName = TaskName;
        this.TaskSummery = TaskSummery;
    }


}
