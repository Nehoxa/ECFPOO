import Exception.FormException;
import Service.FormatterLog;
import Service.LogWritter;
import View.Acceuil;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Main {
    private static FileHandler fh = null;

    public static void main(String[] args) throws SQLException, IOException, FormException {
        fh = new FileHandler("logAppli.log", true);

        LogWritter.LOGGER.setUseParentHandlers(false);
        LogWritter.LOGGER.addHandler(fh);

        fh.setFormatter(new FormatterLog());
        LogWritter.LOGGER.log(Level.INFO, "début pg");

        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);

        LogWritter.LOGGER.log(Level.INFO, "fin pg");
    }
}