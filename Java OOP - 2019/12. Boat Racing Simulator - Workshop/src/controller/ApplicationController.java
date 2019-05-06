package controller;

import application.Constants;
import exception.*;
import models.Race;
import models.boat.*;
import models.engine.Engine;
import models.engine.JetEngine;
import models.engine.SterndriveEngine;
import repository.BoatRepository;
import repository.EngineRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationController {
    private BoatRepository boatRepository;
    private EngineRepository engineRepository;
    private Race race;

    public ApplicationController() {
        this.boatRepository = new BoatRepository();
        this.engineRepository = new EngineRepository();
        this.race = null;
    }

    public String createBoatEngine(String model, int horsepower, int displacement, String type) throws DuplicateModelException {
        Engine engine;
        String actionMessage = String.format("Engine model %s with %d HP and displacement %d" +
                " cm3 created successfully.", model, horsepower, displacement);
        if (type.equals("Jet")) {
            engine = new JetEngine(model, horsepower, displacement);
        } else if (type.equals("Sterndrive")) {
            engine = new SterndriveEngine(model, horsepower, displacement);
        } else {
            throw new IllegalArgumentException(Constants.IncorrectEngineTypeMessage);
        }
        this.engineRepository.save(engine);
        return actionMessage;
    }

    public String createRowBoat(String model, int weight, int oars) throws DuplicateModelException {
        RowBoat rowBoat = new RowBoat(model, weight, oars);
        this.boatRepository.save(rowBoat);
        return this.getCreateBoatMessage("Row boat", model);
    }

    public String createSailBoat(String model, int weight, int sailEfficiency) throws DuplicateModelException {
        SailBoat sailBoat = new SailBoat(model, weight, sailEfficiency);
        this.boatRepository.save(sailBoat);
        return this.getCreateBoatMessage("Sail boat", model);
    }

    public String createPowerBoat(String model, int weight, String firstEngineModel, String secondEngineModel) throws NonExistantModelException, DuplicateModelException {
        Engine firstEngine = this.engineRepository.getByModel(firstEngineModel);
        Engine secondEngine = this.engineRepository.getByModel(secondEngineModel);
        PowerBoat powerBoat = new PowerBoat(model, weight, firstEngine, secondEngine);
        this.boatRepository.save(powerBoat);
        return this.getCreateBoatMessage("Power boat", model);
    }

    public String createYacht(String model, int weight, String engineModel, int cargoWeight) throws NonExistantModelException, DuplicateModelException {
        Engine engine = this.engineRepository.getByModel(engineModel);
        Yacht yacht = new Yacht(model, weight, engine, cargoWeight);
        this.boatRepository.save(yacht);
        return this.getCreateBoatMessage("Yacht", model);
    }

    private String getCreateBoatMessage(String boatType, String model) {
        return String.format("%s with model %s registered successfully.", boatType, model);
    }

    public String openRace(int distance, int windSpeed, int waterCurrentSpeed, boolean allowsMotorboats) throws RaceAlreadyExistsException {
        if (this.isRaceSet()) {
            throw new RaceAlreadyExistsException(Constants.RaceAlreadyExistsMessage);
        }
        this.race = new Race(distance, windSpeed, waterCurrentSpeed, allowsMotorboats);
        return String.format("A new race with distance %d meters, wind speed %d m/s and ocean current speed " +
                "%d m/s has been set.", distance, windSpeed, waterCurrentSpeed);
    }

    private boolean isRaceSet() {
        return this.race != null;
    }

    public String signUpBoat(String model) throws NoSetRaceException, NonExistantModelException, DuplicateModelException {
        if (!this.isRaceSet()) {
            throw new NoSetRaceException(Constants.NoSetRaceMessage);
        }
        Boat boat = this.boatRepository.getByModel(model);
        this.race.addParticipant(boat);
        return String.format("Boat with model %s has signed up for the current Race.", model);
    }

    public String startRace() throws NoSetRaceException, InsufficientContestantsException {
        if (!this.isRaceSet()) {
            throw new NoSetRaceException(Constants.NoSetRaceMessage);
        }
        if (!this.canRaceBeHeld()) {
            throw new InsufficientContestantsException(Constants.InsufficientContestantsMessage);
        }
        String raceMessage = this.race.start();
        this.race = null;
        return raceMessage.trim();
    }

    private boolean canRaceBeHeld() {
        return this.race.getParticipants().size() >= 3;
    }

    public String getStatistic() throws NoSetRaceException {
        if (this.race == null) {
            throw new NoSetRaceException(Constants.NoSetRaceMessage);
        }

        Set<Boat> participants = this.race.getParticipants();
        Map<String, List<Boat>> participantsByBoatType = participants.stream()
                .collect(Collectors.groupingBy(participant -> participant.getClass().getSimpleName()));
        int totalCountOfParticipantsInRace = participants.size();

        StringBuilder builder = new StringBuilder();

        participantsByBoatType.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .forEach(boatTypeEntry -> {
                    double percentageOfParticipants = boatTypeEntry.getValue().size() * 100.0 / totalCountOfParticipantsInRace;
                    builder.append(boatTypeEntry.getKey()).append(" -> ")
                            .append(String.format("%.2f", percentageOfParticipants)).append("%");
                    builder.append(System.lineSeparator());
                });
        return builder.toString().trim();
    }
}
