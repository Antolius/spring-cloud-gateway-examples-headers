# Spring Cloud Gateway Headers Example

An example project that illustrates how to configure [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) app to add [_Connection_](https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Connection) header to all requests to downstream backend service.

## Use-Case

The requirement of Spring Cloud Gateway app is to add a _Connection: Keep-Alive_ header to all downstream requests, regardless of the upstream client request.

```
                                                                          
+--------+              +---------+                         +------------+
|        +------------->| Spring  | Connection: keep-alive  | Downstream |
| Client |              | Cloud   +------------------------>| Backend    |
|        |              | Gateway |<------------------------+ Service    |
|        |<-------------+ App     |                         |            |
+--------+              +---------+                         +------------+
                                                                          
```

## Getting Started

In case you wish to run the app yourself, feel free to clone the git repo and play around with it.

### Prerequisites

The project uses:

* [Java 12](https://openjdk.java.net/projects/jdk/12/)
* [Maven](https://maven.apache.org/)

### Installing

Since this is a Maven project, you can install it with:

```shell
$ mvn clean install
```

## Testing

In the test I use [WireMock](http://wiremock.org/) to simulate the downstream backend service and [`WebTestClient`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/reactive/server/WebTestClient.html) to simulate the client. Test client makes HTTP requests to our app, which proxies them to WireMock server. At the end I assert that request made to WireMock server had `Connection: keep-alive` header.

You can run tests with maven:

```shell
$ mvn test
```

## Author

* [Josip Antoliš](https://github.com/Antolius)