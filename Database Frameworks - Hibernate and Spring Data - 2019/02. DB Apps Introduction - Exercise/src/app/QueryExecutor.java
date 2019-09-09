package app;

import constants.Constants;
import constants.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryExecutor {
    private Connection connection;

    public QueryExecutor(Connection connection) {
        this.connection = connection;
    }

    //2. Get Villains’ Names
    public String getVillainsNames() throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(Queries.SELECT_VILLAINS_WITH_MORE_THAN_15_MINIONS);
        ResultSet resultSet = statement.executeQuery();
        return this.getResultFromResultSet(resultSet);
    }

    //3. Get Minion Names
    public String getMinionNames(long villainId) throws SQLException {
        PreparedStatement prepStmtVillain = this.connection.prepareStatement(Queries.SELECT_VILLAIN_BY_ID);
        PreparedStatement prepStmtMinions = this.connection.prepareStatement(Queries.SELECT_ALL_MINIONS_FOR_A_VILLAIN);
        prepStmtVillain.setLong(1, villainId);
        prepStmtMinions.setLong(1, villainId);

        ResultSet rsVillain = prepStmtVillain.executeQuery();

        if (!rsVillain.next()) {
            return String.format(Constants.INVALID_VILLAIN_ID, villainId);
        }

        ResultSet rsMinions = prepStmtMinions.executeQuery();
        StringBuilder result = new StringBuilder("Villain: " + rsVillain.getString("name") + System.lineSeparator());

        int index = 1;
        while (rsMinions.next()) {
            result.append(index).append(". ").append(this.getResultForOneRecord(rsMinions));
            if (!rsMinions.isLast()) {
                result.append(System.lineSeparator());
            }
            index += 1;
        }

        return result.toString();
    }

    //4. Add Minion
    public String addMinion(String minionLine, String villainLine) throws SQLException {
        String[] minionTokens = minionLine.split("\\s+");
        String minionName = minionTokens[1];
        int minionAge = Integer.parseInt(minionTokens[2]);
        String minionTown = minionTokens[3];

        String villainName = villainLine.split("\\s+")[1];

        PreparedStatement insertTownStatement = this.connection.prepareStatement(Queries.INSERT_NEW_TOWN);
        PreparedStatement selectTownStatement = this.connection.prepareStatement(Queries.SELECT_TOWN_BY_NAME);
        PreparedStatement insertVillainStatement = this.connection.prepareStatement(Queries.INSERT_NEW_VILLAIN);
        PreparedStatement selectVillainStatement = this.connection.prepareStatement(Queries.SELECT_VILLAIN_BY_NAME);
        PreparedStatement insertMinionStatement = this.connection.prepareStatement(Queries.INSERT_NEW_MINION);
        PreparedStatement selectMinionStatement = this.connection.prepareStatement(Queries.SELECT_MINION_ID_BY_NAME);
        PreparedStatement insertMinionToVillainStmt = this.connection.prepareStatement(Queries.INSERT_MINION_TO_VILLAIN);

        StringBuilder resultMessages = new StringBuilder();

        selectTownStatement.setString(1, minionTown);
        ResultSet town = selectTownStatement.executeQuery();
        if (!town.next()) {
            insertTownStatement.setString(1, minionTown);
            insertTownStatement.executeUpdate();
            resultMessages.append(String.format(Constants.SUCCESSFULLY_ADDED_TOWN, minionTown)).append(System.lineSeparator());
        }

        town = selectTownStatement.executeQuery();
        town.first();
        int townId = town.getInt("id");

        selectVillainStatement.setString(1, villainName);
        ResultSet villain = selectVillainStatement.executeQuery();
        if (!villain.next()) {
            insertVillainStatement.setString(1, villainName);
            insertVillainStatement.executeUpdate();
            resultMessages.append(String.format(Constants.SUCCESSFULLY_ADDED_VILLAIN, villainName)).append(System.lineSeparator());
        }

        villain = selectVillainStatement.executeQuery();
        villain.first();
        int villainId = villain.getInt("id");

        insertMinionStatement.setString(1, minionName);
        insertMinionStatement.setInt(2, minionAge);
        insertMinionStatement.setInt(3, townId);
        insertMinionStatement.executeUpdate();

        selectMinionStatement.setString(1, minionName);
        ResultSet minion = selectMinionStatement.executeQuery();
        minion.first();
        int minionId = minion.getInt("id");

        insertMinionToVillainStmt.setInt(1, minionId);
        insertMinionToVillainStmt.setInt(2, villainId);
        insertMinionToVillainStmt.executeUpdate();

        resultMessages.append(String.format(Constants.SUCCESSFULLY_ADDED_MINION_TO_VILLAIN, minionName, villainName));

        return resultMessages.toString();
    }

    //5. Change Town Names Casing
    public String changeTownNamesCasing(String country) throws SQLException {
        PreparedStatement updateStatement = this.connection.prepareStatement(Queries.UPDATE_TOWN_NAMES_CASING_TO_UPPER);
        PreparedStatement selectAffectedTownsStatement = this.connection.prepareStatement(Queries.SELECT_AFFECTED_TOWN_NAMES);
        updateStatement.setString(1, country);
        int affectedTownsCount = updateStatement.executeUpdate();

        if (affectedTownsCount == 0) {
            return Constants.NO_AFFECTED_TOWN_NAMES;
        }

        selectAffectedTownsStatement.setString(1, country);
        ResultSet affectedTownNames = selectAffectedTownsStatement.executeQuery();
        List<String> affectedTowns = new ArrayList<>();

        while (affectedTownNames.next()) {
            affectedTowns.add(affectedTownNames.getString("name"));
        }

        return affectedTownsCount + Constants.AFFECTED_TOWN_NAMES_COUNT + System.lineSeparator()
                + "[" + String.join(", ", affectedTowns) + "]";
    }

    //6. *Remove Villain
    public String removeVillain(int villainId) throws SQLException {
        PreparedStatement selectVillainStmt = this.connection.prepareStatement(Queries.SELECT_VILLAIN_BY_ID);
        PreparedStatement selectMinionsCountStmt = this.connection.prepareStatement(Queries.SELECT_MINIONS_COUNT_FOR_VILLAIN);
        PreparedStatement deleteRelationVillainMinionsStmt = this.connection.prepareStatement(Queries.DELETE_RELATION_BETWEEN_VILLAIN_AND_MINIONS);
        PreparedStatement deleteVillainStmt = this.connection.prepareStatement(Queries.DELETE_VILLAIN_BY_ID);

        selectVillainStmt.setInt(1, villainId);
        ResultSet villain = selectVillainStmt.executeQuery();
        if (!villain.next()) {
            return Constants.VILLAIN_DOES_NOT_EXIST;
        }

        String villainName = villain.getString("name");

        selectMinionsCountStmt.setInt(1, villainId);
        ResultSet count = selectMinionsCountStmt.executeQuery();
        count.first(); //TODO- check for SQLException
        int minionsCountToBeReleased = count.getInt("count");

        deleteRelationVillainMinionsStmt.setInt(1, villainId);
        deleteRelationVillainMinionsStmt.execute();

        deleteVillainStmt.setInt(1, villainId);
        deleteVillainStmt.execute();

        return String.format(Constants.MESSAGE_DELETED_VILLAIN, villainName)
                + System.lineSeparator()
                + String.format(Constants.MESSAGE_RELEASED_MINIONS, minionsCountToBeReleased);
    }

    //7. Print All Minion Names
    public String getAllMinionNames() throws SQLException {
        PreparedStatement selectAllMinionNamesStmt = this.connection.prepareStatement(Queries.SELECT_ALL_MINION_NAMES);
        ResultSet resultSetWithMinionNames = selectAllMinionNamesStmt.executeQuery();

        List<String> minionNamesInOriginalOrder = new ArrayList<>();

        while (resultSetWithMinionNames.next()) {
            minionNamesInOriginalOrder.add(this.getResultForOneRecord(resultSetWithMinionNames).toString());
        }

        StringBuilder outputMinionNames = new StringBuilder();

        //If the records count is even
        if (minionNamesInOriginalOrder.size() % 2 == 0) {
            for (int i = 0; i < minionNamesInOriginalOrder.size() / 2; i++) {
                outputMinionNames.append(minionNamesInOriginalOrder.get(i)).append(System.lineSeparator());
                outputMinionNames.append(minionNamesInOriginalOrder.get(minionNamesInOriginalOrder.size() - 1 - i));
                if (i < minionNamesInOriginalOrder.size() / 2 - 1) {
                    outputMinionNames.append(System.lineSeparator());
                }
            }
        } else { //If it is odd
            for (int i = 0; i <= minionNamesInOriginalOrder.size() / 2; i++) {
                //This is the middle record in the minion table, if the count is odd.
                if (i == minionNamesInOriginalOrder.size() / 2) {
                    outputMinionNames.append(minionNamesInOriginalOrder.get(i));
                } else {
                    outputMinionNames.append(minionNamesInOriginalOrder.get(i)).append(System.lineSeparator());
                    outputMinionNames.append(minionNamesInOriginalOrder.get(minionNamesInOriginalOrder.size() - 1 - i)).append(System.lineSeparator());
                }
            }
        }

        return outputMinionNames.toString();
    }

    //8. Increase Minions Age
    public String increaseMinionsAge(String minionIds) throws SQLException {
        int[] minionIdsTokens = Arrays.stream(minionIds.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        PreparedStatement increaseMinionAgeStmt = this.connection.prepareStatement(Queries.INCREASE_MINION_AGE_AND_MODIFY_NAME);

        Arrays.stream(minionIdsTokens)
                .forEach(minionId -> {
                    try {
                        increaseMinionAgeStmt.setInt(1, minionId);
                        increaseMinionAgeStmt.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

        PreparedStatement selectAllMinionInfoStmt = this.connection.prepareStatement(Queries.SELECT_ALL_MINIONS_NAMES_AND_AGE);
        ResultSet allMinions = selectAllMinionInfoStmt.executeQuery();
        return this.getResultFromResultSet(allMinions);
    }

    //9. Increase Age Stored Procedure
    public String increaseAgeWithStoredProcedure(int minionId) throws SQLException {
        PreparedStatement increaseAgeStmt = this.connection.prepareStatement(Queries.INCREMENT_MINION_AGE_WITH_STORED_PROCEDURE);
        increaseAgeStmt.setInt(1, minionId);
        increaseAgeStmt.execute();

        PreparedStatement selectMinionByIdStmt = this.connection.prepareStatement(Queries.SELECT_MINION_NAME_AND_AGE_BY_ID);
        selectMinionByIdStmt.setInt(1, minionId);
        ResultSet minionResult = selectMinionByIdStmt.executeQuery();
        minionResult.first();

        return this.getResultForOneRecord(minionResult).toString();
    }

    private String getResultFromResultSet(ResultSet resultSet) throws SQLException {
        StringBuilder sbResult = new StringBuilder();

        while (resultSet.next()) {
            String currentRecord = this.getResultForOneRecord(resultSet).toString();

            if (!resultSet.isLast()) {
                currentRecord += System.lineSeparator();
            }

            sbResult.append(currentRecord);
        }

        return sbResult.toString();
    }

    private StringBuilder getResultForOneRecord(ResultSet resultSet) throws SQLException {
        int columnCount = this.getColumnCount(resultSet);
        StringBuilder currentRecord = new StringBuilder();
        for (int i = 1; i <= columnCount; i++) {
            currentRecord.append(resultSet.getObject(i));
            if (i != columnCount) {
                currentRecord.append(" ");
            }
        }
        return currentRecord;
    }

    private int getColumnCount(ResultSet resultSet) throws SQLException {
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        return resultSetMetaData.getColumnCount();
    }
}
