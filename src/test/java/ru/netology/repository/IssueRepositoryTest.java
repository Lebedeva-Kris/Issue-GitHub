package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {
    private IssueRepository repository = new IssueRepository();

    private Issue first = new Issue(true, "author1", Collections.emptyList(), Collections.emptyList());
    private Issue second = new Issue(true, "author2", Arrays.asList("label1", "label2", "label3"), Arrays.asList("assignee1", "assignee2", "assignee3"));
    private Issue third = new Issue(true, "author3", Arrays.asList("label1", "label2"), Arrays.asList("assignee2", "assignee3"));
    private Issue fourth = new Issue(false, "author1", Collections.singletonList("label1"), Collections.singletonList("assignee3"));
    private Issue fifth = new Issue(false, "author2", Arrays.asList("label4", "label5", "label6"), Arrays.asList("assignee4", "assignee5", "assignee6"));

    @BeforeEach
    void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    void shouldFindAll() {
        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(first, second, third, fourth, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldFindByIdIfExist() {
        int idToFind = first.getId();

        Issue actual = repository.findById(idToFind);

        assertEquals(first, actual);
    }

    @Test
    void shouldFindByIdIfNotExist() {
        int idToFind = first.getId() - 1;

        Issue actual = repository.findById(idToFind);

        assertNull(actual);
    }


    @Test
    void shouldRemoveByIdIfExist() {
        int idToRemove = second.getId();

        repository.removeById(idToRemove);

        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(first, third, fourth, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveByIdIfNotExist() {
        int idToRemove = first.getId() - 1;

        repository.removeById(idToRemove);

        List<Issue> actual = repository.findAll();
        List<Issue> expected = Arrays.asList(first, second, third, fourth, fifth);

        assertEquals(expected, actual);
    }

    @Test
    void shouldRemoveAll() {
        repository.removeAll();

        List<Issue> actual = repository.findAll();
        List<Object> expected = Collections.emptyList();

        assertEquals(expected, actual);
    }

}