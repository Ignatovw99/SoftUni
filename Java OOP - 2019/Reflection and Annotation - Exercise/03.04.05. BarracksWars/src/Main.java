import contracts.Repository;
import contracts.Runnable;
import contracts.UnitFactory;
import core.CommandInterpreterImpl;
import core.Engine;
import core.factories.UnitFactoryImpl;
import data.UnitRepository;

public class Main {

	public static void main(String[] args) {
		Repository repository = new UnitRepository();
		UnitFactory unitFactory = new UnitFactoryImpl();
		Runnable engine = new Engine(new CommandInterpreterImpl(repository, unitFactory));
		engine.run();
	}
}
