package com.planner.todo;

public class TaskModel {

    Integer id;
    String  task;
    Boolean imp_tag, urg_tag;

    public TaskModel(int id, String task, boolean imp_tag, boolean urg_tag)
    {
        this.id=id;
        this.task=task;
        this.imp_tag=imp_tag;
        this.urg_tag=urg_tag;
    }


    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", task='" + task + '\'' +
                ", imp_tag=" + imp_tag +
                ", urg_tag=" + urg_tag +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Boolean getImp_tag() {
        return imp_tag;
    }

    public void setImp_tag(Boolean imp_tag) {
        this.imp_tag = imp_tag;
    }

    public Boolean getUrg_tag() {
        return urg_tag;
    }

    public void setUrg_tag(Boolean urg_tag) {
        this.urg_tag = urg_tag;
    }
}
