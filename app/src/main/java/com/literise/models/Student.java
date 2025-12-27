package com.literise.models;

public class Student {
    private int studentId;
    private String nickname;
    private String fullName;
    private String avatar;
    private int level;
    private int currentXp;
    private int xpToNextLevel;
    private int streakDays;
    private String readingLevel; // "BEGINNER", "DEVELOPING", "PROFICIENT"
    private float placementTheta;
    private boolean hasCompletedPlacement;
    private boolean hasCompletedPosttest;

    public Student() {
    }

    public Student(int studentId, String nickname, String fullName, String avatar, int level,
                   int currentXp, int xpToNextLevel, int streakDays, String readingLevel,
                   float placementTheta, boolean hasCompletedPlacement, boolean hasCompletedPosttest) {
        this.studentId = studentId;
        this.nickname = nickname;
        this.fullName = fullName;
        this.avatar = avatar;
        this.level = level;
        this.currentXp = currentXp;
        this.xpToNextLevel = xpToNextLevel;
        this.streakDays = streakDays;
        this.readingLevel = readingLevel;
        this.placementTheta = placementTheta;
        this.hasCompletedPlacement = hasCompletedPlacement;
        this.hasCompletedPosttest = hasCompletedPosttest;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public void setXpToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }

    public int getStreakDays() {
        return streakDays;
    }

    public void setStreakDays(int streakDays) {
        this.streakDays = streakDays;
    }

    public String getReadingLevel() {
        return readingLevel;
    }

    public void setReadingLevel(String readingLevel) {
        this.readingLevel = readingLevel;
    }

    public float getPlacementTheta() {
        return placementTheta;
    }

    public void setPlacementTheta(float placementTheta) {
        this.placementTheta = placementTheta;
    }

    public boolean isHasCompletedPlacement() {
        return hasCompletedPlacement;
    }

    public void setHasCompletedPlacement(boolean hasCompletedPlacement) {
        this.hasCompletedPlacement = hasCompletedPlacement;
    }

    public boolean isHasCompletedPosttest() {
        return hasCompletedPosttest;
    }

    public void setHasCompletedPosttest(boolean hasCompletedPosttest) {
        this.hasCompletedPosttest = hasCompletedPosttest;
    }
}
