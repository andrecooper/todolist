package com.home.todoList.app.tasks.model;

public class TaskDto {

    private long id;
    private String taskName;
    private String owner;
    private String executionDate;
    private String creationDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskDto taskDto = (TaskDto) o;

        if (id != taskDto.id) return false;
        if (creationDate != null ? !creationDate.equals(taskDto.creationDate) : taskDto.creationDate != null)
            return false;
        if (executionDate != null ? !executionDate.equals(taskDto.executionDate) : taskDto.executionDate != null)
            return false;
        if (owner != null ? !owner.equals(taskDto.owner) : taskDto.owner != null) return false;
        if (taskName != null ? !taskName.equals(taskDto.taskName) : taskDto.taskName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (taskName != null ? taskName.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (executionDate != null ? executionDate.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", owner='" + owner + '\'' +
                ", executionDate='" + executionDate + '\'' +
                ", creationDate='" + creationDate + '\'' +
                '}';
    }
}
