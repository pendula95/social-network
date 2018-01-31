package org.lbulic.helper;

import java.util.Set;

public class GraphVertex {

    /*
    {
        "id": 1,
        "firstName": "Paul",
        "surname": "Crowe",
        "age": 28,
        "gender": "male",
        "friends": [
            2
         ]
    }
    */

    private int id;
    private String firstName;
    private String surname;
    private Integer age;
    private String gender;
    private Set<Integer> friends;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Set<Integer> getFriends() {
        return friends;
    }

    public void setFriends(Set<Integer> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphVertex)
            return id == ((GraphVertex) obj).getId();
        return false;
    }

    @Override
    public String toString() {
        return "GraphVertex{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", friends=" + friends +
                '}';
    }
}