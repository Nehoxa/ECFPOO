import Service.FormatterLog;
import Service.LogWritter;
import View.Acceuil;

import javax.swing.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Main {
    private static FileHandler fh = null;

    public static void main(String[] args) {

        try {
            fh = new FileHandler("logAppli.log", true);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "L'application a rencontrée un problème va se fermer");
            System.exit(1);
        }


        LogWritter.LOGGER.setUseParentHandlers(false);
        LogWritter.LOGGER.addHandler(fh);

        fh.setFormatter(new FormatterLog());
        LogWritter.LOGGER.log(Level.INFO, "début pg");

        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);

        LogWritter.LOGGER.log(Level.INFO, "fin pg");
    }
}