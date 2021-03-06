package main.sleepapp.model;

import main.sleepapp.controller.DatabaseController;

/**
 * Denne model indeholder informationer om søvn, beskrevet af attributterne elevID, sove- og
 * opvågningstidspunkt.
 */
public class SleepModel {

    private String student_id;
    private String sleep_time;
    private String awoke_time;

    /**
     * Default constructor som kaldes, når denne klasse oprettes.
     * Har til formål at udfylde informationerne student_id, sleep_time og awoke_time.
     */
    public SleepModel(String student_id, String sleep_time, String awoke_time) {
        this.setStudent_id(student_id);
        this.setSleep_time(sleep_time);
        this.setAwoke_time(awoke_time);
    }

    /**
     * Håndterer opdateringen af data i databasen med data fra denne model.
     */
    public void updateModel() {
        String type = "timer";
        DatabaseController dbController = new DatabaseController();
        dbController.execute(type, getStudent_id(), getSleep_time(), getAwoke_time());
    }

    /**
     * Returnerer attributten student_id af typen String.
     */
    public String getStudent_id() {
        return student_id;
    }

    /**
     * Sætter attributten student_id af typen String til inputargumentet student_id
     */
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    /**
     * Returnerer attributten sleep_time af typen String.
     */
    public String getSleep_time() {
        return sleep_time;
    }

    /**
     * Sætter attributten sleep_time af typen String til inputargumentet sleep_time
     */
    public void setSleep_time(String sleep_time) {
        this.sleep_time = sleep_time;
    }

    /**
     * Returnerer attributten awoke_time af typen String.
     */
    public String getAwoke_time() {
        return awoke_time;
    }

    /**
     * Sætter attributten awoke_time af typen String til inputargumentet awoke_time
     */
    public void setAwoke_time(String awoke_time) {
        this.awoke_time = awoke_time;
    }
}
