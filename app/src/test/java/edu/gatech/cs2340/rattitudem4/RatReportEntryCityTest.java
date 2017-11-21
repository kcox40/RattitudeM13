package edu.gatech.cs2340.rattitudem4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Created by Carlos Priddy. Tests checkCity of AddNewRatReportActivity
 */
public class RatReportEntryCityTest {
    /**
     * Test that checks if entered text is a valid city
     * @throws Exception an exception if error occurs
     */
    @Test
    public void checkCityEmpty() throws Exception {
        assertEquals(RatReportEntryVerify.checkCity(""), "City Field Empty");
    }

    /**
     * check for short city
     * @throws Exception throws when error
     */
    @Test
    public void checkCityShort() throws Exception {
        assertEquals(RatReportEntryVerify.checkCity("ab"), "City Must Be Longer Than 2 Characters");
    }

    /**
     * check for digits city
     * @throws Exception throws when error
     */
    @Test
    public void checkCityDigits() throws Exception {
        assertEquals(RatReportEntryVerify.checkCity("1abc"), "City Must Not Contain Digits");
    }

    /**
     * check for long city
     * @throws Exception throws when error
     */
    @Test
    public void checkCityLong() throws Exception {
        assertEquals(RatReportEntryVerify.checkCity(
                "askfhbakfjbakkahbfkahsfbasfkahsfbasfkahsfbafsafksfhbaf" +
                "kjhbskghdgksjbskdhgbskdhbskdhgbsdksdhbgksh"),
                "City Must Not Be Longer Than 35 Characters");
    }

    /**
     * check for specChar city
     * @throws Exception throws when error
     */
    @Test
    public void checkCitySpecChar() throws Exception {
        assertEquals(RatReportEntryVerify.checkCity("!*abc"),
                "Cities Must Not Contain Special Characters");
    }
}