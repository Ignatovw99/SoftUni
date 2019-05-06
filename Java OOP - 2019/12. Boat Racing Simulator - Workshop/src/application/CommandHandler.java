package application;

import controller.ApplicationController;
import exception.*;

import java.util.Arrays;

public class CommandHandler {
    private static final String TRUE_STRING = "true";

    private ApplicationController controller;

    public CommandHandler() {
        this.controller = new ApplicationController();
    }

    public String handle(String inputLine) throws DuplicateModelException, NonExistantModelException,
            RaceAlreadyExistsException, NoSetRaceException, InsufficientContestantsException {
        String[] tokens = inputLine.split("\\\\");
        String command = tokens[0];
        String[] objectParameters = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        String messageResult = "";

        switch (command) {
            case "CreateBoatEngine":
                messageResult = this.parseBoatEngine(objectParameters);
                break;
            case "CreateRowBoat":
                messageResult = this.parseRowBoat(objectParameters);
                break;
            case "CreateSailBoat":
                messageResult = this.parseSailBoat(objectParameters);
                break;
            case "CreatePowerBoat":
                messageResult = this.parsePowerBoat(objectParameters);
                break;
            case "CreateYacht":
                messageResult = this.parseYacht(objectParameters);
                break;
            case "OpenRace":
                messageResult = this.parseRace(objectParameters);
                break;
            case "SignUpBoat":
                messageResult = this.parseSignUpBoatCommand(objectParameters);
                break;
            case "StartRace":
                messageResult = this.controller.startRace();
                break;
            case "GetStatistic":
                messageResult = this.controller.getStatistic();
        }

        return messageResult;
    }

    private String parseSignUpBoatCommand(String[] tokens) throws NoSetRaceException, NonExistantModelException, DuplicateModelException {
        String model = tokens[0];
        return this.controller.signUpBoat(model);
    }

    private String parseRace(String[] tokens) throws RaceAlreadyExistsException {
        int distance = Integer.parseInt(tokens[0]);
        int windSpeed = Integer.parseInt(tokens[1]);
        int waterCurrentSpeed = Integer.parseInt(tokens[2]);
        boolean allowsMotorboats = tokens[3].equals(TRUE_STRING);
        return this.controller.openRace(distance, windSpeed, waterCurrentSpeed, allowsMotorboats);
    }

    private String parseYacht(String[] tokens) throws DuplicateModelException, NonExistantModelException {
        String model = tokens[0];
        int weight = Integer.parseInt(tokens[1]);
        String engineModel = tokens[2];
        int cargoWeight = Integer.parseInt(tokens[3]);
        return this.controller.createYacht(model, weight, engineModel, cargoWeight);
    }

    private String parsePowerBoat(String[] tokens) throws DuplicateModelException, NonExistantModelException {
        String model = tokens[0];
        int weight = Integer.parseInt(tokens[1]);
        String firstEngineModel = tokens[2];
        String secondEngineModel = tokens[3];
        return this.controller.createPowerBoat(model, weight, firstEngineModel, secondEngineModel);
    }

    private String parseSailBoat(String[] tokens) throws DuplicateModelException {
        String model = tokens[0];
        int weight = Integer.parseInt(tokens[1]);
        int sailEfficiency = Integer.parseInt(tokens[2]);
        return this.controller.createSailBoat(model, weight, sailEfficiency);
    }

    private String parseRowBoat(String[] tokens) throws DuplicateModelException {
        String model = tokens[0];
        int weight = Integer.parseInt(tokens[1]);
        int oars = Integer.parseInt(tokens[2]);
        return this.controller.createRowBoat(model, weight, oars);
    }

    private String parseBoatEngine(String[] tokens) throws DuplicateModelException {
        String model = tokens[0];
        int horsepower = Integer.parseInt(tokens[1]);
        int displacement = Integer.parseInt(tokens[2]);
        String type = tokens[3];
        return this.controller.createBoatEngine(model, horsepower, displacement, type);
    }
}
