import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rpg_lab.Axe;
import rpg_lab.Dummy;

import static utils.Constants.*;

public class AxeTests {
    private Axe axe;
    private Dummy dummy;

    @Before
    public void initializeTestObjects() { //Arrange
        this.axe = new Axe(AXE_ATTACK, AXE_DURABILITY);
        this.dummy = new Dummy(DUMMY_HEALTH, DUMMY_EXPERIENCE);
    }
    @Test
    public void shouldLoseDurabilityAfterAttack() {
        this.axe.attack(this.dummy); //Act
        Assert.assertEquals(EXPECTED_DURABILITY, axe.getDurabilityPoints()); //Assert
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotBeAbleToAttackWhenBroken() {
        Axe axe = new Axe(AXE_ATTACK, AXE_ZERO_DURABILITY);
        axe.attack(this.dummy);
    }
}
