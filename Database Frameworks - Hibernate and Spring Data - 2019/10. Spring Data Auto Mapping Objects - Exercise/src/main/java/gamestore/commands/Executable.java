package gamestore.commands;

import org.springframework.stereotype.Component;

@Component
public interface Executable {

    String execute(String... arguments);
}
