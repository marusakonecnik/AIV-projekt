package Strategy;

import java.io.Serializable;

public class InformalMessage implements MessageStrategy, Serializable {
    @Override
    public String getMessage() {
        return "To je neformalno sporočilo";
    }
}
