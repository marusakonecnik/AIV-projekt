package Strategy;

import java.io.Serializable;

public class FormalMessage implements MessageStrategy, Serializable {
    @Override
    public String getMessage() {
        return "To je formalno sporočilo";
    }
}
