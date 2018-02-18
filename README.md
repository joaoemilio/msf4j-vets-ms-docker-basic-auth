# How to build and run the sample

clone the repository
```
git clone https://github.com/joao-emilio/msf4j-vets-microservice-docker.git
```

## How to build the sample

From this directory, run

```
mvn clean install
docker build . -t vets:1.0.0
```

## How to run the sample

From this directory, run
```
docker-compose up
```

## How to test the sample

We will use the cURL command line tool for testing. You can use your preferred HTTP or REST client too.
This was only tested on a MacBook Pro, so if you are using Windows or Linux, please adapt accordingly


1.) Adding a veterinarian

``` 
curl -v -H "Content-Type: application/json" -X POST -d '{"firstName":"Joao","lastName":"Emilio"}' http://localhost:8080/vets
```

```
You should able to see following output. 

< HTTP/1.1 201 Created
```

2.) Get details of veterinarian

```
curl -X GET  http://localhost:8080/vets/1 | python -m json.tool
```

```
You should able to see following output.

{
    "firstName": "James",
    "id": 1,
    "lastName": "Carter",
    "specialties": []
}
```

3.) Delete a veterinarian

```
curl -v  -X DELETE  http://localhost:8080/vets/7
```
```
You should able to see following output.

 HTTP/1.1 202 Accepted
``` 

4.) Try to get the details of non-existing veterinarian

``` 
curl -v  -X GET  http://localhost:8080/catalog/7
 ```
``` 
 You should able to see following output.
 
 HTTP/1.1 404 Not Found
Problem accessing: /catalog/7. Reason: Not Found
 ``` 