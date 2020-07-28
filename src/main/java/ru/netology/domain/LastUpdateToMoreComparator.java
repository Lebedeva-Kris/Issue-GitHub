package ru.netology.domain;

import lombok.Data;

import java.util.Comparator;
import java.util.Date;

public class LastUpdateToMoreComparator implements Comparator<Issue>{
    @Override
    public int compare(Issue o1, Issue o2) {
        Date first = o1.getLastUpdate().getTime();
        Date second = o2.getLastUpdate().getTime();
        return (first.before(second) ? -1 : (first.equals(second) ? 0 : 1));
    }
}
