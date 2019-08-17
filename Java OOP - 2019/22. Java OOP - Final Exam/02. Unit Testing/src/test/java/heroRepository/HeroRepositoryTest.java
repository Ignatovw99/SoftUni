package heroRepository;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroRepositoryTest {
    private static final Item FIRST_ITEM = new Item(13, 42, 15);
    private static final Item SECOND_ITEM = new Item(15, 23, 52);
    private static final Item THIRD_ITEM = new Item(4, 13, 65);

    private static final Hero FIRST_HERO = new Hero("John", 2, FIRST_ITEM);
    private static final Hero SECOND_HERO = new Hero("SAD", 6, SECOND_ITEM);
    private static final Hero THIRD_HERO = new Hero("sdf", 43, THIRD_ITEM);

    private static final String DIFFERENT_NAME = "Terry";

    private HeroRepository heroRepository;

    @Before
    public void setUp() {
        this.heroRepository = new HeroRepository();
    }

    @Test
    public void addNonExistingHeroShouldIncreaseCount() {
        this.heroRepository.add(FIRST_HERO);
        assertEquals(1, this.heroRepository.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addExistingHeroShouldThrowException() {
        this.heroRepository.add(FIRST_HERO);
        this.heroRepository.add(FIRST_HERO);
    }

    @Test(expected = NullPointerException.class)
    public void removeNonExistingHeroShouldThrowException() {
        this.heroRepository.add(FIRST_HERO);
        this.heroRepository.remove(DIFFERENT_NAME);
    }

    @Test
    public void removeExistingHeroShouldDecreaseCount() {
        this.heroRepository.add(FIRST_HERO);
        this.heroRepository.remove(FIRST_HERO.getName());
        assertEquals(0, this.heroRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void getHeroWithHighestStrengthShouldThrow() {
        this.heroRepository.getHeroWithHighestStrength();
    }

    @Test
    public void getHeroWithHighestStrengthShouldWorkCorrectly() {
        this.heroRepository.add(FIRST_HERO);
        this.heroRepository.add(SECOND_HERO);
        this.heroRepository.add(THIRD_HERO);
        assertEquals(SECOND_HERO, this.heroRepository.getHeroWithHighestStrength());
    }

    @Test(expected = NullPointerException.class)
    public void getHeroWithHighestAgilityShouldThrow() {
        this.heroRepository.getHeroWithHighestAgility();
    }

    @Test
    public void getHeroWithHighestAgilityShouldWorkCorrectly() {
        this.heroRepository.add(FIRST_HERO);
        this.heroRepository.add(SECOND_HERO);
        this.heroRepository.add(THIRD_HERO);
        assertEquals(FIRST_HERO, this.heroRepository.getHeroWithHighestAgility());
    }

    @Test(expected = NullPointerException.class)
    public void getHeroWithHighestIntelligenceShouldThrow() {
        this.heroRepository.getHeroWithHighestIntelligence();
    }

    @Test
    public void getHeroWithHighestIntelligenceShouldWorkCorrectly() {
        this.heroRepository.add(FIRST_HERO);
        this.heroRepository.add(SECOND_HERO);
        this.heroRepository.add(THIRD_HERO);
        assertEquals(THIRD_HERO, this.heroRepository.getHeroWithHighestIntelligence());
    }

    @Test
    public void getCountShouldWorkCorrectly() {
        this.heroRepository.add(FIRST_HERO);
        assertEquals(1, this.heroRepository.getCount());
    }

    @Test
    public void toStringCorrectly() {
        assertEquals(this.heroRepository.toString(), this.heroRepository.toString());
    }
}