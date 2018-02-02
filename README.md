# Social Network, Friends Simulation

This is a simple **Vertx** project deployed as **Docker** container.

It simulates searching and operations available for friends on social networks. Find all direct friends, friends of friends and suggested friends.

  ## API endpoints
- URL: `GET /person/:id`
  - Required Path Params: `id`
  - Output format: `json`
  - Output: `data for selected person/id`
  - Description: Returns data for provided person.
  
- URL: `GET /ping`
  - Output format: `text`
  - Output: `Ping`
  - Description: Check live status of service
  
- URL: `POST /dataSet`
  - Params type: `json`
  - Output: `OK`
  - Description: You can provide custom dataSet for the service to use.
  
## Default DataSet
Default dataSet is provided as .json file. If you want to proccess custom dateSet you can use service API to send it. Example dataSet is provided [here](https://gist.github.com/pendula95/2f308d8a24d4286b7d851a4527672628)


## Building, Deploying and Running
In order to build, deploy and run this app you will need **Maven** and **Docker**.  
Needed steps to build the image:
```sh
$ mvn clean package
$ mvn docker:build
```
If you don't have maven or just want to use an already created image you can download it on this [link](https://we.tl/zg3CmGRa2H)  
In order to add it to your docker use this command:
```sh
$ docker load -i <path to image tar file>
```
To run this service please you use:
```sh
$ docker run -p 8080:8080 -i -t lbulic/social-network
```
Application should be visible on localhost:8080

