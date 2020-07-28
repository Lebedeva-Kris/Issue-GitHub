package ru.netology.domain;

import lombok.Data;

import java.util.*;

@Data
public class Issue {
    private int id;
    private String label;
    private String milestones;
    private boolean isOpen;
    private String author;
    private Set<String> labels = new HashSet<>();
    private Set<String> assignees = new HashSet<>();

    private int commentNumber;
    private Calendar creationDate = new GregorianCalendar();
    private Calendar lastUpdate = new GregorianCalendar();

    private String title;
    private String description;

    public Issue() {
    }

    @Override
    public String toString() {
        return "Issue{" +
                "id=" + id +
                ", isOpen=" + isOpen +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Issue(boolean isOpen, String author, Collection<? extends String> labels, Collection<? extends String> assignees) {
        this.isOpen = isOpen;
        this.author = author;
        this.labels = new HashSet<>(labels);
        this.assignees = new HashSet<>(assignees);
    }

    public Issue(boolean isOpen, String author, Collection<? extends String> labels, Collection<? extends String> assignees, int commentNumber) {
        this.isOpen = isOpen;
        this.author = author;
        this.labels = new HashSet<>(labels);
        this.assignees = new HashSet<>(assignees);
        this.commentNumber = commentNumber;
    }

    public Issue(boolean isOpen, String author, Collection<? extends String> labels, Collection<? extends String> assignees, int commentNumber, Calendar creationDate, Calendar lastUpdate) {
        this.isOpen = isOpen;
        this.author = author;
        this.labels = new HashSet<>(labels);
        this.assignees = new HashSet<>(assignees);
        this.commentNumber = commentNumber;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
    }

    public Issue(boolean isOpen, String author, Collection<? extends String> labels, Collection<? extends String> assignees, int commentNumber, Calendar creationDate, Calendar lastUpdate, String title, String description) {
        this.isOpen = isOpen;
        this.author = author;
        this.labels = new HashSet<>(labels);
        this.assignees = new HashSet<>(assignees);
        this.commentNumber = commentNumber;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.title = title;
        this.description = description;
    }

    public void changeIssueStatus(){
        isOpen = !isOpen;

    }
}
