package org.techmeskills.aqa5.auf.models;

public class Project {
    private String name;
    private String announcement;
    private boolean show_announcement;
    private boolean is_completed;
    private int suite_mode;

    public static class Builder {
        private Project newProject;

        public Builder() {
            newProject = new Project();
        }

        public Builder withName(String name) {
            newProject.name = name;
            return this;
        }

        public Builder withAnnouncement(String announcement) {
            newProject.announcement = announcement;
            return this;
        }

        public Builder withIsShowAnnouncement(boolean isShowAnnouncement) {
            newProject.show_announcement = isShowAnnouncement;
            return this;
        }

        public Builder withIsCompleted(boolean is_completed) {
            newProject.is_completed = is_completed;
            return this;
        }

        public Builder withType(int suite_mode) {
            newProject.suite_mode = suite_mode;
            return this;
        }

        public Project build() {
            return newProject;
        }
    }

    public String getName() {
        return name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public int getSuiteMode() {
        return suite_mode;
    }

    public boolean isShow_announcement() {
        return show_announcement;
    }

    public boolean isIs_completed() {
        return is_completed;
    }

    public int getSuite_mode() {
        return suite_mode;
    }
}
