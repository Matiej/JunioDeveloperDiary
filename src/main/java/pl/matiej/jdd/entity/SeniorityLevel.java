package pl.matiej.jdd.entity;

public enum SeniorityLevel {
    TRAINEE ("Student, inter"),
    JUNIOR("junior developer"),
    MID("medium developer"),
    SENIOR("senior developer"),
    EXPERT ("just expert");

    private String desc;

    SeniorityLevel(String desc) {
        this.desc = desc;
    }
}
