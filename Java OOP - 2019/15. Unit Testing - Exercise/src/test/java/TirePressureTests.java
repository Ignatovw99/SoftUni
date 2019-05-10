import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import p06_TirePressureMonitoringSystem.Alarm;
import p06_TirePressureMonitoringSystem.Sensor;

import java.lang.reflect.Field;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TirePressureTests {
    private Alarm alarm;
    @Before
    public void setUp() {
        this.alarm = new Alarm();
    }

    @Test
    public void alarmShouldBeOnWhenTirePressureBelowThreshold() throws NoSuchFieldException, IllegalAccessException {
        Sensor mockedSensor = Mockito.mock(Sensor.class);
        Mockito.when(mockedSensor.popNextPressurePsiValue())
                .thenReturn(16.0);

        Field sensorOfAlarm = Alarm.class.getDeclaredField("sensor");
        sensorOfAlarm.setAccessible(true);
        sensorOfAlarm.set(this.alarm, mockedSensor);

        this.alarm.check();
        assertTrue(this.alarm.getAlarmOn());
    }

    @Test
    public void alarmShouldBeOnWhenTirePressureAboveThreshold() throws NoSuchFieldException, IllegalAccessException {
        Sensor mockedSensor = Mockito.mock(Sensor.class);
        Mockito.when(mockedSensor.popNextPressurePsiValue())
                .thenReturn(22.0);

        Field sensorOfAlarm = Alarm.class.getDeclaredField("sensor");
        sensorOfAlarm.setAccessible(true);
        sensorOfAlarm.set(this.alarm, mockedSensor);

        this.alarm.check();
        assertTrue(this.alarm.getAlarmOn());
    }

    @Test
    public void alarmShouldBeOffWhenTirePressureInRangeOfPressureThreshold() throws NoSuchFieldException, IllegalAccessException {
        Sensor mockedSensor = Mockito.mock(Sensor.class);
        Mockito.when(mockedSensor.popNextPressurePsiValue())
                .thenReturn(18.0);

        Field sensorOfAlarm = Alarm.class.getDeclaredField("sensor");
        sensorOfAlarm.setAccessible(true);
        sensorOfAlarm.set(this.alarm, mockedSensor);

        this.alarm.check();
        assertFalse(this.alarm.getAlarmOn());
    }
}
