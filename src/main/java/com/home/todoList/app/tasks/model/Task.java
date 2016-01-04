package com.home.todoList.app.tasks.model;

import com.home.todoList.app.users.model.User;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    private String taskName;

    private Date creationDate;

    private Date executionDate;

    @ManyToOne
    private User owner;

    @ManyToMany
    @JoinTable(name = "task_assignment",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id")
        )
    private List<User> assignedUserList;

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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getAssignedUserList() {
        return assignedUserList;
    }

    public void setAssignedUserList(List<User> assignedUserList) {
        this.assignedUserList = assignedUserList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != task.id) return false;
        if (assignedUserList != null ? !assignedUserList.equals(task.assignedUserList) : task.assignedUserList != null)
            return false;
        if (!creationDate.equals(task.creationDate)) return false;
        if (executionDate != null ? !executionDate.equals(task.executionDate) : task.executionDate != null)
            return false;
        if (!owner.equals(task.owner)) return false;
        if (!taskName.equals(task.taskName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + taskName.hashCode();
        result = 31 * result + creationDate.hashCode();
        result = 31 * result + (executionDate != null ? executionDate.hashCode() : 0);
        result = 31 * result + owner.hashCode();
        result = 31 * result + (assignedUserList != null ? assignedUserList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", creationDate=" + creationDate +
                ", executionDate=" + executionDate +
                ", owner=" + owner +
                ", assignedUserList=" + assignedUserList +
                '}';
    }
}

