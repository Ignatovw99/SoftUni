import fakes.FakeTarget;
import fakes.FakeWeapon;
import org.junit.Test;
import org.mockito.Mockito;
import rpg_lab.Hero;
import rpg_lab.Target;
import rpg_lab.Weapon;

import static org.junit.Assert.assertEquals;
import static utils.Constants.XP;

public class HeroTests {
    @Test
    public void shouldGainExperienceWhenTargetDies() {
        Hero hero = new Hero("Name", new FakeWeapon());
        Target target = new FakeTarget();
        hero.attack(target);
        assertEquals(XP, hero.getExperience());
    }

    @Test
    public void shouldReceiveLootFromTargetAfterDeath() {
        Weapon loot = new FakeWeapon();
        Hero hero = new Hero("Name", new FakeWeapon());
        Target target = Mockito.mock(Target.class);

        Mockito.when(target.isDead()).thenReturn(true);
        Mockito.when(target.giveLoot()).thenReturn(loot);

        hero.attack(target);

        assertEquals(loot, hero.getInventory().get(0));
    }
}
