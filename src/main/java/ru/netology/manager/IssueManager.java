package ru.netology.manager;

import ru.netology.domain.*;
import ru.netology.repository.IssueRepository;

import java.util.*;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add(Issue issue) {
        repository.save(issue);
    }

    public List<Issue> showOpened() {
        return filterBy(Issue::isOpen);
    }
    public List<Issue> showClosed() {
        return filterBy((issue) -> !issue.isOpen());
    }

    private List<Issue> filterBy(Predicate<Issue> predicate) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : repository.findAll()) {
            if (predicate.test(issue))
                result.add(issue);
        }
        return result;
    }

    public List<Issue> filterByAuthor(String author) {
        return filterBy((issue) -> issue.getAuthor().equals(author));
    }
    public List<Issue> filterByLabels(Collection<? extends String> labels) {
        return filterBy((issue) -> issue.getLabels().containsAll(labels));
    }
    public List<Issue> filterByAssignee(String assignee) {
        return filterBy((issue) -> issue.getAssignees().contains(assignee));
    }

    private List<Issue> sortBy(Comparator<Issue> comparator) {
        List<Issue> result = repository.findAll();
        result.sort(comparator);
        return result;
    }

    public List<Issue> sortByCreationDateToMore() {
        return sortBy(new CreationDateToMoreComparator());
    }

    public List<Issue> sortByCreationDateToLess() {
        return sortBy(new CreationDateToLessComparator());
    }

    public List<Issue> sortByLastUpdateToMore() {
        return sortBy(new LastUpdateToMoreComparator());
    }

    public List<Issue> sortByLastUpdateToLess() {
        return sortBy(new LastUpdateToLessComparator());
    }

    public void changeIssueStatusById(int id) {
        repository.findById(id).changeIssueStatus();
    }

    public void updateIssueStatus(int id, boolean isOpen) {
        repository.findById(id).setOpen(isOpen);
    }

//    public List<IssueItem> sortByCountCommentAsc() {
//        return sortBy(new CountCommentAscComparator());
//    }
//    public List<IssueItem> sortByCountCommentDesc() {
//        return sortBy(new CountCommentDescComparator());
//    }

}
