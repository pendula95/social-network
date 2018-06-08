package org.lbulic;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.lbulic.helper.Graph;
import org.lbulic.helper.GraphVertex;
import org.lbulic.helper.Response;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class MainVerticle extends AbstractVerticle {

    private Graph graph;

    @Override
    public void start() throws Exception {

        Router router = Router.router(vertx);

        router.get("/person/:id").handler(this::personHandler);
        router.post("/dataSet").handler(this::addDateSet);
        router.get("/ping").handler(routingContext -> {
            routingContext.response().setStatusCode(200).end("Ping");
        });

        vertx.createHttpServer().requestHandler(router::accept).listen(8080);

        vertx.fileSystem().readFile("data.json", result -> {
            if (result.succeeded()) {
                JsonArray objects = new JsonArray(result.result().toString());
                Map<Integer, GraphVertex> verticles = new HashMap<>();
                for(int i =0; i < objects.size(); i++) {
                    GraphVertex tmp = objects.getJsonObject(i).mapTo(GraphVertex.class);
                    verticles.put(tmp.getId(), tmp);
                }
                graph = new Graph("Social Network Graph", verticles);
            } else {
                System.err.println("Oh oh ..." + result.cause());
            }
        });
    }


    private void personHandler(RoutingContext routingContext) {
        Integer id = Integer.parseInt(routingContext.request().getParam("id"));
        Response response = new Response();
        Map<Integer, Integer> suggestedFriends = new HashMap<>();
        GraphVertex me = graph.getVertices().get(id);
        response.setPerson(me);
        //map direct friends
        response.setDirectFriends(graph.getVertices().get(id).getFriends().stream().map(graph.getVertices()::get).collect(Collectors.toSet()));



        Map<GraphVertex, Integer> friendsOfFriends = new HashMap<>();
        //map friends of friends and suggested friends
        response.getDirectFriends().stream().map(GraphVertex::getFriends).forEach(friends -> {
            friends.forEach(friend -> {
                GraphVertex graphVertex = graph.getVertices().get(friend);
                if (!me.getFriends().contains(friend) && me.getId() != friend){
                    if (friendsOfFriends.containsKey(graphVertex)) {
                        friendsOfFriends.put(graphVertex, friendsOfFriends.get(graphVertex) + 1);
                    } else {
                        friendsOfFriends.put(graphVertex, 1);
                    }
                }
            });
        });
        response.setFriendsOfFriends(friendsOfFriends.keySet());
        response.setSuggestedFriends(friendsOfFriends.entrySet().stream().filter(suggested -> suggested.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toSet()));

        routingContext.response().setStatusCode(200)
                .putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(response));
    }



    private void addDateSet(RoutingContext routingContext) {
        routingContext.request().bodyHandler(bodyHandler -> {
            final JsonArray jsonArray = bodyHandler.toJsonArray();
            Map<Integer, GraphVertex> verticles = new HashMap<>();
            for(int i =0; i < jsonArray.size(); i++) {
                GraphVertex tmp = jsonArray.getJsonObject(i).mapTo(GraphVertex.class);
                verticles.put(tmp.getId(), tmp);
            }
            graph = new Graph("Social Network Graph", verticles);
        });
        routingContext.response().setStatusCode(200).end("OK");
    }


}
