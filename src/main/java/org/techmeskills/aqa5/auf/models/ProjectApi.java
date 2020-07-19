package org.techmeskills.aqa5.auf.models;

public class ProjectApi {
    private String name;
    private String job;
    private String email;
    private String password;

    public static class Builder {
        private ProjectApi newProjectApi;

        public Builder(){
            newProjectApi = new ProjectApi();
        }

        public Builder withName(String name){
            newProjectApi.name = name;
            return  this;
        }

        public Builder withJob(String job){
            newProjectApi.job = job;
            return  this;
        }

        public Builder withEmail(String email){
            newProjectApi.email = email;
            return  this;
        }

        public Builder withPassword(String password){
            newProjectApi.password = password;
            return  this;
        }

        public ProjectApi build(){
            return newProjectApi;
        }
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
