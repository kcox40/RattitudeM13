package edu.gatech.cs2340.rattitudem4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * used to check if data entered in the AddNewRatActivity is valid
 */
class RatReportEntryVerify {
    private static final int TOOLONG = 36;
    /**
     * Checks if the Zip is valid
     * @param zip user entered Zip
     * @return returns the entered zip if it is valid, or the appropriate error message if
     * it is not a valid zip entry
     */
    public static String checkZip(String zip) {
        if (!"".equals(zip)) {
            if (zip.length() == 5) {
                boolean containsChar = false;
                int i = 0;
                while ((i < zip.length()) && (!containsChar)) {
                    if (Character.isLetter(zip.charAt(i))) {
                        containsChar = true;
                    }
                    i++;
                }
                if (!containsChar) {
                    return zip;
                } else {
                    return "Zip Must Contain Digits Only";
                }
            } else {
                return "Zip must be 5 digits long";
            }
        }
        return "Zip field is empty";
    }

    public static String checkCity(String city) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(city);
        if (!"".equals(city)) {
            int i = 0;
            boolean containsDigits = false;
            if (city.length() <= 2) {
                return "City Must Be Longer Than 2 Characters";
            } else if(city.length() >= TOOLONG) {
                return "City Must Not Be Longer Than 35 Characters";
            }
            if (m.find()) {
                return "Cities Must Not Contain Special Characters";
            }
            while ((i < city.length()) && !containsDigits) {
                if (Character.isDigit(city.charAt(i))) {
                    containsDigits = true;
                }
                i++;
            }
            if (containsDigits) {
                return "City Must Not Contain Digits";
            }
        } else {
            return "City Field Empty";
        }
        return city;
    }


}
