package gamestore.constants;

import java.time.format.DateTimeFormatter;

public final class CommandValues {

    public static final String SEPARATOR = "\\|";

    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private CommandValues(){
    }
}
