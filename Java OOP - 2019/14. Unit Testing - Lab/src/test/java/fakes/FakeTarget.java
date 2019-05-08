package fakes;

import rpg_lab.Target;
import rpg_lab.Weapon;

import static utils.Constants.DUMMY_EXPERIENCE;

public class FakeTarget implements Target {
    @Override
    public int getHealth() {
        return 0;
    }

    @Override
    public void takeAttack(int attackPoints) {

    }

    @Override
    public int giveExperience() {
        return DUMMY_EXPERIENCE;
    }

    @Override
    public boolean isDead() {
        return true;
    }

    @Override
    public Weapon giveLoot() {
        return null;
    }
}
