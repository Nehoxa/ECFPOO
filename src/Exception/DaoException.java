package Exception;

import java.util.logging.Level;

public class DaoException extends Exception {
    private Level errorLevel;

    public DaoException(String message, Level level) {
        super(message);
        setErrorLevel(level);
    }

    public void setErrorLevel(Level errorLevel) {
        this.errorLevel = errorLevel;
    }

    public Level getLevel() {
        return errorLevel;
    }
}

