package factories;

import interfaces.Appender;
import interfaces.Factory;
import interfaces.Logger;
import models.MessageLogger;

public class LoggerFactory implements Factory<Logger> {
    @Override
    public Logger produce(String produceData) {
        String[] appendersTokens = produceData.split(System.lineSeparator());
        Appender[] appenders = new Appender[appendersTokens.length];
        AppenderFactory appenderFactory = new AppenderFactory();
        for (int i = 0; i < appenders.length; i++) {
            appenders[i] = appenderFactory.produce(appendersTokens[i]);
        }
        return new MessageLogger(appenders);
    }
}
