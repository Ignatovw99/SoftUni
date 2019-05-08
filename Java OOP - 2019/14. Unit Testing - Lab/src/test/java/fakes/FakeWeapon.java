package fakes;

import rpg_lab.Target;
import rpg_lab.Weapon;

import static utils.Constants.ATTACK;

public class FakeWeapon implements Weapon {
    @Override
    public void attack(Target target) {

    }

    @Override
    public int getAttackPoints() {
        return ATTACK;
    }

    @Override
    public int getDurabilityPoints() {
        return 0;
    }
}
