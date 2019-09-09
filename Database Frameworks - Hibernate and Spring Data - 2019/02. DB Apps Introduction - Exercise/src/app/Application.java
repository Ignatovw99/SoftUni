package app;

import config.Connector;
import constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Connector.createConnectionToHost(Constants.USER, Constants.PASSWORD);
        QueryExecutor queryExecutor = new QueryExecutor(Connector.getConnection());
        QueryManager queryManager = new QueryManager(reader, queryExecutor);
        Engine engine = new Engine(queryManager);
        engine.run();
        reader.close();
    }
}
