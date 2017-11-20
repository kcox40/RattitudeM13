package edu.gatech.cs2340.rattitudem4;

/**
 * used to check if data entered in the AddNewRatActivity is valid
 */
class RatReportEntryVerify {

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
        if (!"".equals(city)) {
            int i = 0;
            boolean containsDigits = false;
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
