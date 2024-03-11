package Test;

import Job.Prospect;
import Exception.FormException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProspectTest extends Prospect {
    @ParameterizedTest
    @NullAndEmptySource
    void testRsThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setRaisonSociale(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Apple" })
    void testSetRaceNotThrow(String param) {
        assertDoesNotThrow(() -> setRaisonSociale(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testNumRueThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setNumRue(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "12" })
    void testNumRueNotThrow(String param) {
        assertDoesNotThrow(() -> setNumRue(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testNomRueThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setNomRue(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Rue de l'innovation" })
    void testNomRueNotThrow(String param) {
        assertDoesNotThrow(() -> setNomRue(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testCpThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setCodePostale(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "54200" })
    void testCpNotThrow(String param) {
        assertDoesNotThrow(() -> setCodePostale(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testVilleThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setVille(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Paris" })
    void testVilleNotThrow(String param) {
        assertDoesNotThrow(() -> setVille(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "6789" })
    void testTelephoneThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setTelephone(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "0845654434" })
    void testTelephoneNotThrow(String param) {
        assertDoesNotThrow(() -> setTelephone(param));
    }


    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "dfsdf@" })
    void testMailThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setMail(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "apple@contact.com" })
    void testMailNotThrow(String param) {
        assertDoesNotThrow(() -> setMail(param));
    }


    @ParameterizedTest
    @ValueSource(strings = { "2023-05-23" })
    void testDateProspectNotThrow(String param) {
        assertDoesNotThrow(() -> setDateProspect(LocalDate.parse(param)));
    }


    @ParameterizedTest
    @NullAndEmptySource
    void testInterretThrows(String param) throws FormException {
        assertThrows(FormException.class, () -> setInterret(param));
    }

    @ParameterizedTest
    @ValueSource(strings = { "OUI" })
    void testInterretNotThrow(String param) {
        assertDoesNotThrow(() -> setInterret(param));
    }
}