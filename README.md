Pulsar
------
>Apache Pulsar feature tests.

Install
-------
1. [Docker Desktop App](https://www.docker.com/products/docker-desktop/)
2. run Docker Desktop App
3. ```docker pull apachepulsar/pulsar:latest```
>Also see [Pulsar Standalone](https://pulsar.apache.org/docs/getting-started-standalone/)

Docker Desktop App
------------------
1. run Docker Desktop
2. open Docker Dashboard
3. run Apache Pulsar Image

Docker Commandline
------------------
```
docker run -it \
    -p 6650:6650 \
    -p 8080:8080 \
    -v $PWD/data:/pulsar/data \
    apachepulsar/pulsar:latest \
    bin/pulsar standalone
```

Test
----
1. sbt clean test

Run
---
1. sbt run

Resources
---------
1. [Apache Pulsar](https://pulsar.apache.org/)
2. [Docker Apache Pulsar](https://hub.docker.com/r/apachepulsar/pulsar)
