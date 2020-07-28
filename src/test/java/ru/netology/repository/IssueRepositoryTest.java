package ru.netology.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Issue;
import ru.netology.manager.IssueManager;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IssueRepositoryTest {
    @Mock
    private IssueRepository repository;
    @InjectMocks
    private IssueManager manager;

    private Issue first = new Issue(true, "King", Collections.emptyList(), Collections.emptyList());
    private Issue second = new Issue(true, "Gerz", Arrays.asList("bug", "question", "task"), Arrays.asList("nobody", "somebody", "who"));
    private Issue third = new Issue(true, "somebody", Arrays.asList("bug", "question"), Arrays.asList("somebody", "who"));
    private Issue fourth = new Issue(false, "King", Collections.singletonList("bug"), Collections.singletonList("who"));
    private Issue fifth = new Issue(false, "Gerz", Arrays.asList("feature", "documentation", "something"), Arrays.asList("Timur", "Oleg", "Ivan"));


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