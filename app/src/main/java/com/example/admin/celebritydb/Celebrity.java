package com.example.admin.celebritydb;

/**
 * Created by Admin on 11/13/2017.
 */

public class Celebrity {
    String firstName;
    String lastName;
    String favorite;

    @Override
    public String toString() {
        return "Celebrity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", favorite='" + favorite + '\'' +
                '}';
    }

    public Celebrity(String firstName, String lastName, String favorite) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favorite = favorite;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
