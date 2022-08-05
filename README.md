Java STAC client
==============

A Java client for working with [STAC-APIs](https://github.com/radiantearth/stac-api-spec).
The client allows to fetch various STAC objects and maps them to POJOs.


```java
StacClient client = new StacClientImpl(new URL("https://someserver/api/stac/v1/"));
Catalog catalog = client.getCatalog();
```
Furthermore, searching for items is supported.

```java
QueryParameter parameter = new QueryParameter();
parameter.addCollection("sentinel-2-l2a");
parameter.setDatetime("2022-02-13/2022-04-15");

ItemCollection result = client.search(parameter);
```


## Using Java STAC client
### Maven dependency ###

* Declare the dependency
```
<dependency>
  <groupId>com.github.11904212</groupId>
  <artifactId>java-stac-client</artifactId>
  <version>0.2</version>
</dependency>
```


### Remote Dependencies ###

* [Simple Features GeoJSON Java](https://github.com/ngageoint/simple-features-geojson-java) (The MIT License (MIT)) - Simple Features GeoJSON Lib
* [Jackson Data Processor](https://github.com/FasterXML/jackson-databind) (Apache License, Version 2.0) - Jackson data-binding functionality and tree-model
