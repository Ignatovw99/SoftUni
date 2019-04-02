package models;

import application.Constants;
import comparators.BoatSpeedComparator;
import exception.DuplicateModelException;
import models.boat.Boat;
import models.boat.Motorboat;
import application.Validator;

import java.util.LinkedHashSet;
import java.util.Set;

public class Race {
    private int distance;
    private int windSpeed;
    private int waterCurrentSpeed;
    private Set<Boat> participants;
    private boolean allowsMotorboats;

    public Race(int distance, int windSpeed, int waterCurrentSpeed, boolean allowsMotorboats) {
        this.setDistance(distance);
        this.windSpeed = windSpeed;
        this.waterCurrentSpeed = waterCurrentSpeed;
        this.allowsMotorboats = allowsMotorboats;
        this.participants = new LinkedHashSet<>();
    }

    public Set<Boat> getParticipants() {
        return this.participants;
    }

    public int getDistance() {
        return this.distance;
    }

    private void setDistance(int distance) {
        Validator.validateNumericalValue(distance, "Distance");
        this.distance = distance;
    }

    public int getWaterCurrentSpeed() {
        return this.waterCurrentSpeed;
    }

    public int getWindSpeed() {
        return this.windSpeed;
    }

    public void addParticipant(Boat boat) throws DuplicateModelException {
        if (this.isMotorboat(boat)) {
            if (this.areMotorboatsAllowed()) {
                this.add(boat);
            } else {
                throw new IllegalArgumentException(Constants.IncorrectBoatTypeMessage);
            }
        } else {
            this.add(boat);
        }
    }

    private void add(Boat boat) throws DuplicateModelException {
        boolean addResult = this.participants.add(boat);
        if (!addResult) {
            throw new DuplicateModelException(Constants.DuplicateModelMessage);
        }
    }

    private boolean areMotorboatsAllowed() {
        return this.allowsMotorboats;
    }

    private boolean isMotorboat(Boat boat) {
        return boat instanceof Motorboat;
    }

    public String start() {
        Boat[] winners = this.participants.stream()
                .sorted(new BoatSpeedComparator(this))
                .limit(3)
                .toArray(Boat[]::new);
        StringBuilder ratingBuilder = new StringBuilder();
        for (int i = 0; i < winners.length; i++) {
            if (i == 0) {
                ratingBuilder.append("First place: ");
            } else if (i == 1) {
                ratingBuilder.append("Second place: ");
            } else {
                ratingBuilder.append("Third place: ");
            }
            ratingBuilder.append(winners[i].getInfo(this));
            if (i < winners.length - 1) {
                ratingBuilder.append(System.lineSeparator());
            }
        }
        return ratingBuilder.toString();
    }
}
