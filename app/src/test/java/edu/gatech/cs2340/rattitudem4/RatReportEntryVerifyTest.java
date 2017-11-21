package edu.gatech.cs2340.rattitudem4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test Created by Katie Cox. Tests checkZip of AddNewRatReportActivity
 */
public class RatReportEntryVerifyTest {

    /**
     * Checks correct error message if "" is entered
     * @throws Exception in case of error
     */
    @Test
    public void checkEmpty() throws Exception {
        assertEquals(RatReportEntryVerify.checkZip(""), "Zip field is empty");
    }

    /**
     * tests correct error message if the String entered's length is less than 5
     * @throws Exception in case of error
     */
    @Test
    public void checkLessThanFive() throws Exception {
        assertEquals(RatReportEntryVerify.checkZip("1"), "Zip must be 5 digits long");
        assertEquals(RatReportEntryVerify.checkZip("a"), "Zip must be 5 digits long");
    }

    /**
     * tests correct error message if the String entered's length is greater than 5
     * @throws Exception in case of error
     */
    @Test
    public void checkGreaterThanFive() throws Exception {
        assertEquals(RatReportEntryVerify.checkZip("1sdfghjokjohigufy"),
                "Zip must be 5 digits long");
        assertEquals(RatReportEntryVerify.checkZip("acfvgbhinjbhuvgycfy"),
                "Zip must be 5 digits long");
    }

    /**
     * checks for error message for a String that has length 5 with a digit
     * @throws Exception in case of error
     */
    @Test
    public void checkFiveWithDigit() throws Exception {
        assertEquals(RatReportEntryVerify.checkZip("1234t"), "Zip Must Contain Digits Only");
    }

    /**
     * check if valid
     * @throws Exception error
     */
    @Test
    public void checkValid() throws Exception {
        assertEquals(RatReportEntryVerify.checkZip("12345"), "12345");
    }
}