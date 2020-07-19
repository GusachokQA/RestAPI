package org.techmeskills.aqa5.auf.models;

public class ProjectSimple {
    private String name;
    private String announcement;
    private boolean show_announcement;
    private int suite_mode;

    public ProjectSimple(String name, String announcement, boolean show_announcement, int suite_mode) {
        this.name = name;
        this.announcement = announcement;
        this.show_announcement = show_announcement;
        this.suite_mode = suite_mode;
    }

    public ProjectSimple() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public void setShow_announcement(boolean show_announcement) {
        this.show_announcement = show_announcement;
    }

    public void setSuite_mode(int suite_mode) {
        this.suite_mode = suite_mode;
    }

    public String getName() {
        return name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public boolean isShow_announcement() {
        return show_announcement;
    }

    public int getSuite_mode() {
        return suite_mode;
    }
}
