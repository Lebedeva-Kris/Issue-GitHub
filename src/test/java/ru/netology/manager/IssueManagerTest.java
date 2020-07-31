package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class IssueManagerTest {
    @Mock
    private IssueRepository repository;
    @InjectMocks
    private IssueManager manager;

    private Issue first = new Issue(true, "author1", Collections.emptyList(), Collections.emptyList(), 0, new GregorianCalendar(2019, Calendar.JANUARY, 1), new GregorianCalendar(2019, Calendar.JANUARY, 1, 13, 13));
    private Issue second = new Issue(true, "author2", Arrays.asList("label1", "label2", "label3"), Arrays.asList("assignee1", "assignee2", "assignee3"), 1, new GregorianCalendar(2019, Calendar.FEBRUARY, 1), new GregorianCalendar(2019, Calendar.FEBRUARY, 1, 13, 13));
    private Issue third = new Issue(true, "author3", Arrays.asList("label1", "label2"), Arrays.asList("assignee2", "assignee3"), 2, new GregorianCalendar(2019, Calendar.APRIL, 1), new GregorianCalendar(2019, Calendar.APRIL, 1, 13, 13));
    private Issue fourth = new Issue(false, "author1", Collections.singletonList("label1"), Collections.singletonList("assignee3"), 3, new GregorianCalendar(2019, Calendar.MARCH, 1), new GregorianCalendar(2019, Calendar.MARCH, 1, 13, 13));
    private Issue fifth = new Issue(false, "author2", Arrays.asList("label4", "label5", "label6"), Arrays.asList("assignee4", "assignee5", "assignee6"), 4, new GregorianCalendar(2020, Calendar.JANUARY, 1), new GregorianCalendar(2020, Calendar.JANUARY, 1, 13, 13));
    private Issue sixth = new Issue(false, "author2", Arrays.asList("label4", "label5", "label6"), Arrays.asList("assignee4", "assignee5", "assignee6"), 5, new GregorianCalendar(2020, Calendar.JANUARY, 2), new GregorianCalendar(2020, Calendar.JANUARY, 2, 13, 13));
    private Issue seventh = new Issue(false, "author2", Arrays.asList("label4", "label5", "label6"), Arrays.asList("assignee4", "assignee5", "assignee6"), 6, new GregorianCalendar(2020, Calendar.MAY, 1), new GregorianCalendar(2020, Calendar.MAY, 1, 13, 13));
    private Issue eight = new Issue(false, "author2", Arrays.asList("label4", "label5", "label6"), Arrays.asList("assignee4", "assignee5", "assignee6"), 7);


    @BeforeEach
    void setUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
        manager.add(fifth);
        manager.add(sixth);
        manager.add(seventh);
        manager.add(eight);
    }

    @Test
    void shouldFilterByAuthorIfHeExists() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<Issue> expected = new HashSet<>(Arrays.asList(first, fourth));
        Set<Issue> actual = new HashSet<>(manager.filterByAuthor("author1"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAuthorIfHeIsNotExist() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<Issue> expected = new HashSet<>(Collections.emptyList());
        Set<Issue> actual = new HashSet<>(manager.filterByAuthor("author4"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssigneeIfExists() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<Issue> expected = new HashSet<>(Arrays.asList(second, third, fourth));
        Set<Issue> actual = new HashSet<>(manager.filterByAssignee("assignee3"));
        ;

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByAssigneeIfNotExists() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<Issue> expected = new HashSet<>(Collections.emptyList());
        Set<Issue> actual = new HashSet<>(manager.filterByAssignee("assignee7"));

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabelsIfExists() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<String> labels = new HashSet<>(Arrays.asList("label1", "label2"));

        Set<Issue> expected = new HashSet<>(Arrays.asList(second, third));
        Set<Issue> actual = new HashSet<>(manager.filterByLabels(labels));

        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterByLabelsIfNotExists() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<String> labels = new HashSet<>(Arrays.asList("label1", "label7"));

        Set<Issue> expected = Collections.emptySet();
        Set<Issue> actual = new HashSet<>(manager.filterByLabels(labels));

        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByCreationDateAsc() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        List<Issue> expected = Arrays.asList(first, second, fourth, third, fifth, sixth, seventh, eight);
        List<Issue> actual = manager.sortByCreationDateToMore();

        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByDateOfCreationDesc() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        List<Issue> expected = Arrays.asList(eight, seventh, sixth, fifth, third, fourth, second, first);
        List<Issue> actual = manager.sortByCreationDateToLess();

        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByLastUpdateAsc() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        List<Issue> expected = Arrays.asList(first, second, fourth, third, fifth, sixth, seventh, eight);
        List<Issue> actual = manager.sortByLastUpdateToMore();

        assertEquals(expected, actual);
    }

    @Test
    void shouldSortByLastUpdateDesc() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        List<Issue> expected = Arrays.asList(eight, seventh, sixth, fifth, third, fourth, second, first);
        List<Issue> actual = manager.sortByLastUpdateToLess();

        assertEquals(expected, actual);
    }

    @Test
    void shouldShowOpenedIssues() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<Issue> expected = new HashSet<>(Arrays.asList(first, second, third));
        Set<Issue> actual = new HashSet<>(manager.showOpened());

        assertEquals(expected, actual);
    }

    @Test
    void shouldShowClosedIssues() {
        doReturn(Arrays.asList(first, second, third, fourth, fifth, sixth, seventh, eight)).when(repository).findAll();

        Set<Issue> expected = new HashSet<>(Arrays.asList(fourth, fifth, sixth, seventh, eight));
        Set<Issue> actual = new HashSet<>(manager.showClosed());

        assertEquals(expected, actual);
    }

    @Test
    void shouldChangeIssueStatusById() {
        int idIssue = first.getId();

        doReturn(first).when(repository).findById(idIssue);
        manager.changeIssueStatusById(idIssue);

        assertFalse(first.isOpen());

        manager.updateIssueStatus(idIssue, true);

        assertTrue(first.isOpen());
    }
}