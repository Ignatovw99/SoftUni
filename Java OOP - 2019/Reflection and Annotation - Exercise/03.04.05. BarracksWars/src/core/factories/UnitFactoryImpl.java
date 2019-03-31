package core.factories;

import contracts.Unit;
import contracts.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"models.units.";

	@SuppressWarnings("unchecked")
	@Override
	public Unit createUnit(String unitType) {
		// TODO: implement for problem 3 (New Factory) - DONE
		Unit unit = null;
		try {
			Class<? extends Unit> unitClass =
					(Class<? extends Unit>) Class.forName(UnitFactoryImpl.UNITS_PACKAGE_NAME + unitType);
			Constructor constructor = unitClass.getDeclaredConstructor();
			constructor.setAccessible(true);
			unit = (Unit) constructor.newInstance();
		} catch (ClassNotFoundException
				| ClassCastException
				| NoSuchMethodException
				| InstantiationException
				| IllegalAccessException
				| InvocationTargetException exception) {
			exception.printStackTrace();
		}
		return unit;
	}
}
