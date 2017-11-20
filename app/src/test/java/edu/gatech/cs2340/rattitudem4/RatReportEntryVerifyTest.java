package edu.gatech.cs2340.rattitudem4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Created by Katie Cox. Tests checkZip of AddNewRatReportActivity
 */
public class RatReportEntryVerifyTest {
    /**
     * Test that checks if entered text is a valid zip
     * @throws Exception an exception if error occurs
     */
    @Test
    public void checkZip() throws Exception {
        assertEquals(RatReportEntryVerify.checkZip(""), "Zip field is empty");
        assertEquals(RatReportEntryVerify.checkZip("1"), "Zip must be 5 digits long");
        assertEquals(RatReportEntryVerify.checkZip("1234t"), "Zip Must Contain Digits Only");
        assertEquals(RatReportEntryVerify.checkZip("12345"), "12345");
    }
}