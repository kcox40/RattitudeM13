package edu.gatech.cs2340.rattitudem4;

import org.junit.Test;

import org.junit.Before;

import static org.junit.Assert.assertTrue;


/**
 * A test for the equals() method in User.java
 *
 * @author Dalton Touchberry
 * @version 1.0
 */
public class DaltonUnitTest {
    private User fox;
    private User foxCopy;
    private User foxRef;
    private User foxWrongPass;
    private User scully;
    private User scullyCopy;
    private User queequeg;
    private String notAUser;

    private static final int TIMEOUT = 200;

    /**\
     * Sets up the test users
     */
    @Before
    public void setUp() {
        fox = new User("Fox", "Mulder",
                "aliensarereal@fbi.gov", "foxmulder", "aliens",
                "normal");
        foxCopy = new User("Fox", "Mulder",
                "aliensarereal@fbi.gov", "foxmulder", "aliens",
                "normal");
        foxWrongPass = new User("Fox", "Mulder",
                "aliensarereal@fbi.gov", "foxmulder", "scully",
                "normal");
        scully = new User("Dana", "Scully", "foxiscrazy@fbi.gov",
                "danascully", "queequeg", "normal");
        scullyCopy = new User("Dana", "Scully", "foxiscrazy@fbi.gov",
                "danascully", "queequeg", "normal");
        foxRef = fox;
        notAUser = "I'm just a string";
    }

    /**
     * Tests when exact copy
     */
    @Test(timeout = TIMEOUT)
    public void testExactCopyEqual() {
        assertTrue(fox.equals(foxCopy));
    }

    /**
     * tests second reference
     */
    @Test(timeout = TIMEOUT)
    public void testSecondReference() {
        assertTrue(fox.equals(foxRef));
    }

    /**
     * tests for a null user
     */
    @Test(timeout = TIMEOUT)
    public void testNullUser() {
        assertTrue(!fox.equals(queequeg));
    }

    /**
     * tests wrong class type
     */
    @Test(timeout = TIMEOUT)
    public void testWrongClassType() {
        assertTrue(!fox.equals(notAUser));
    }

    /**
     * tests wrong password
     */
    @Test(timeout = TIMEOUT)
    public void testWrongPassword() {
        assertTrue(!fox.equals(foxWrongPass));
    }

}