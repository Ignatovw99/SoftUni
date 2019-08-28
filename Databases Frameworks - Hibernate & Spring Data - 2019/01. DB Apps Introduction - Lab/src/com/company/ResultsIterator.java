package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

public class ResultsIterator implements Iterator {
    private final ResultSet resultSet;

    public ResultsIterator(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public boolean hasNext() {
        try {
            return this.resultSet.next();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Object next() {
        try {
            return String.format("%s %s %s %f",
                    this.resultSet.getString("first_name"),
                    this.resultSet.getString("last_name"),
                    this.resultSet.getString("job_title"),
                    this.resultSet.getDouble("salary"));
        } catch (SQLException e) {
            return null;
        }
    }
}
