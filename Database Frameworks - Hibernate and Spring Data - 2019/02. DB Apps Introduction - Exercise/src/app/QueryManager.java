package app;

import constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;

public class QueryManager {
    private BufferedReader reader;
    private QueryExecutor queryExecutor;

    public QueryManager(BufferedReader reader, QueryExecutor queryExecutor) {
        this.reader = reader;
        this.queryExecutor = queryExecutor;
    }

    public String executeExercise() {
        System.out.println(Constants.CHOOSE_PROBLEM_MESSAGE);
        String output = null;
        try {
            int problemNumber = Integer.parseInt(this.reader.readLine());

            switch (problemNumber) {
                case 2:
                    output = this.queryExecutor.getVillainsNames();
                    break;
                case 3:
                    System.out.print(Constants.ENTER_VILLAIN_ID_MESSAGE);
                    int villainId = Integer.parseInt(this.reader.readLine());
                    output = this.queryExecutor.getMinionNames(villainId);
                    break;
                case 4:
                    System.out.print(Constants.ENTER_MINION_INFORMATION_MESSAGE);
                    String minionLine = this.reader.readLine();
                    System.out.print(Constants.ENTER_VILLAIN_INFORMATION_MESSAGE);
                    String villainLine = this.reader.readLine();
                    output = this.queryExecutor.addMinion(minionLine, villainLine);
                    break;
                case 5:
                    System.out.print(Constants.ENTER_COUNTRY_MESSAGE);
                    String country = this.reader.readLine();
                    output = this.queryExecutor.changeTownNamesCasing(country);
                    break;
                case 6:
                    System.out.print(Constants.ENTER_VILLAIN_ID_MESSAGE);
                    villainId = Integer.parseInt(this.reader.readLine());
                    output = this.queryExecutor.removeVillain(villainId);
                    break;
                case 7:
                    output = this.queryExecutor.getAllMinionNames();
                    break;
                case 8:
                    System.out.print(Constants.ENTER_MINION_IDS_MESSAGE);
                    String minionIds = this.reader.readLine();
                    output = this.queryExecutor.increaseMinionsAge(minionIds);
                    break;
                case 9:
                    System.out.print(Constants.ENTER_MINION_ID_MESSAGE);
                    int minionId = Integer.parseInt(this.reader.readLine());
                    output = this.queryExecutor.increaseAgeWithStoredProcedure(minionId);
                    break;
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

        if (output == null) {
            return Constants.INVALID_EXERCISE_MESSAGE;
        }

        return output;
    }
}
