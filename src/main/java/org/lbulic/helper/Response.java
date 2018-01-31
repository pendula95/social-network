package org.lbulic.helper;

import java.util.Set;

public class Response {

    private GraphVertex person;
    private Set<GraphVertex> directFriends;
    private Set<GraphVertex> friendsOfFriends;
    private Set<GraphVertex> suggestedFriends;


    public GraphVertex getPerson() {
        return person;
    }

    public void setPerson(GraphVertex person) {
        this.person = person;
    }

    public Set<GraphVertex> getDirectFriends() {
        return directFriends;
    }

    public void setDirectFriends(Set<GraphVertex> directFriends) {
        this.directFriends = directFriends;
    }

    public Set<GraphVertex> getFriendsOfFriends() {
        return friendsOfFriends;
    }

    public void setFriendsOfFriends(Set<GraphVertex> friendsOfFriends) {
        this.friendsOfFriends = friendsOfFriends;
    }

    public Set<GraphVertex> getSuggestedFriends() {
        return suggestedFriends;
    }

    public void setSuggestedFriends(Set<GraphVertex> suggestedFriends) {
        this.suggestedFriends = suggestedFriends;
    }

    @Override
    public String toString() {
        return "Response{" +
                "person=" + person +
                ", directFriends=" + directFriends +
                ", friendsOfFriends=" + friendsOfFriends +
                ", suggestedFriends=" + suggestedFriends +
                '}';
    }
}
