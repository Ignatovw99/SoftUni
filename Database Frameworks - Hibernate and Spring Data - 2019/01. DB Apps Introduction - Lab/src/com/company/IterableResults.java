package com.company;

import java.sql.ResultSet;
import java.util.Iterator;

public class IterableResults implements Iterable {
    private final ResultsIterator resultsIterator;

    public IterableResults(ResultSet resultSet) {
        this.resultsIterator = new ResultsIterator(resultSet);
    }

    @Override
    public Iterator iterator() {
        return this.resultsIterator;
    }
}
