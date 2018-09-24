package com.example.lifestyleapp;


// the methods here take a userProfile instead
// of pieces of said profile to make it easier to use in other sections
// of the program.
public class FitnessUtils {

    private static double kgPerlb = .45;
    private static double metersPerIn = .025;
    private static double cmPerIn = 2.5;
    private static int caloriesPerlb = 3500;
    private static int daysPerWeek = 7;

    // Calculates BMI using height(in inches)
    // and weight(in pounds) obtained from the profile.
    // Rounds and returns an int instead of a double to
    // make BMI easier to read and remember.
    public static int calculateBMI(UserProfile profile) {
        return (int) ((profile.getWeight() * kgPerlb) / Math.pow(profile.getHeight() * metersPerIn, 2));
    }

    // Takes height, weight, biological gender, activity level and age from the profile.
    // Uses parameters to calculate basal metabolic rate. If it's possible
    // that portions of the profile will not be filled out when this gets called
    // the if statement regarding activity level is important. If not, they can be removed.
    public static int calculateBMR(UserProfile profile) {
        // The BMR equation takes kgs, cms and years
        double BMR = ((10 * profile.getWeight() * kgPerlb) +
                (6.25 * profile.getHeight() * cmPerIn) - (5 * profile.getAge()));

        if(profile.getActivityLevel() > 0.1){ // doubles are initialized to 0.0
            // Presumes gender binary Male=true, Female=false.
            // Men have a slightly higher standard BMR than women.
            if(profile.getGender()) {
                BMR += 5;
                BMR *= profile.getActivityLevel();
            }
            else {
                BMR -= 161;
                BMR *= profile.getActivityLevel();
            }
        }
        else {
            if(profile.getGender()) {
                BMR += 5;
            }
            else {
                BMR -= 161;
            }
        }
        // This sanitizes the calculation to round to the nearest 5
        return (int) (BMR - (BMR % 5));
    }

    // Daily caloric intake is based on BMR and fitness goal (lbs / week)
    public static int calculateExpectedCaloricIntake(UserProfile profile) {

        // Goal will be either negative or positive. That way
        // intake can be calculated up or down. (goal will be 0
        // if the user hopes to maintain their current fitness level.)
        double expectedIntake = calculateBMR(profile) +
                ( (profile.getGoal() * caloriesPerlb) / daysPerWeek );

        // This sanitizes the calculation to round to the nearest 5
        return (int) (expectedIntake - (expectedIntake % 5));
    }
}
