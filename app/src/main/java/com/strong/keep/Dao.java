package com.strong.keep;

import androidx.lifecycle.LiveData;

import com.strong.keep.GetSet.TaskGetter;
import com.strong.keep.GetSet.listTaskGetter;

import java.util.List;

public interface Dao {
    void insertTask(TaskGetter model);

    void updateTask(TaskGetter model);

    void deleteTask(TaskGetter model);

    LiveData<List<TaskGetter>> getAllTask();

    /*LIST OPERATION*/
    void insertList(listTaskGetter model);

    void updateList(listTaskGetter model);

    void deleteList(listTaskGetter model);

    LiveData<List<listTaskGetter>> getAllList();
}
