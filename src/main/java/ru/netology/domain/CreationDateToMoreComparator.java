package ru.netology.domain;

import java.util.Comparator;
import java.util.Date;

public class CreationDateToMoreComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue o1, Issue o2) {
        Date first = o1.getCreationDate().getTime();
        Date second = o2.getCreationDate().getTime();
        return (first.before(second) ? -1 : (first.equals(second) ? 0 : 1));
    }
}
