package com.sp.p2124786assignment.Model;

public class UsersNameDatabaseModel {
    private String name;


    public UsersNameDatabaseModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // toString is necessary for printing the contents of a class object
    @Override
    public String toString() {
        return name;
    }

    public static class UsersPinDatabaseModel {
        private String pin;

        public UsersPinDatabaseModel(String pin) {
            this.pin = pin;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        // toString is necessary for printing the contents of a class object
        @Override
        public String toString() {
            return pin;
        }
    }
}
