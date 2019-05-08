import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Dummy;

import static utils.Constants.*;

public class DummyTests {
    private Dummy aliveDummy;
    private Dummy deadDummy;

    @Before
    public void createDummy() { //Arrange
        this.aliveDummy = new Dummy(HP_WHEN_DUMMY_ALIVE, XP);
        this.deadDummy = new Dummy(HP_WHEN_DUMMY_DEAD, XP);
    }
    @Test
    public void shouldLoseHealthIfAttacked() {
        this.aliveDummy.takeAttack(ATTACK);
        Assert.assertEquals("rpg_lab.Dummy's health is not correct after attack.",
                HP_WHEN_DUMMY_ALIVE - ATTACK, this.aliveDummy.getHealth());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionWhenDeadOnAttack() {
        this.deadDummy.takeAttack(ATTACK);
    }

    @Test
    public void shouldGiveExperienceWhenDead() {
        int actual = this.deadDummy.giveExperience();
        Assert.assertEquals("Dead rpg_lab.Dummy cannot give experience when it is dead.",
                XP, actual);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionIfGettingExperienceOnAlive() {
        this.aliveDummy.giveExperience();
    }
}
