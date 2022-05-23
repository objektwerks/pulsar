Pulsar
------
>Apache Pulsar feature tests.

Install
-------
1. [Docker Desktop](https://www.docker.com/products/docker-desktop/)
2. run Docker Desktop
3. docker pull apachepulsar/pulsar:latest ( via commandline )
>Also see [Pulsar Standalone](https://pulsar.apache.org/docs/getting-started-standalone/)

Docker
------
1. run Docker Desktop
2. open Docker Dashboard
3. run Apache Pulsar Image

Docker Commandline
------------------
```
docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  --mount source=pulsardata,target=/pulsar/data \
  --mount source=pulsarconf,target=/pulsar/conf \
  apachepulsar/pulsar:latest \
  bin/pulsar standalone
```

Test
----
1. sbt clean test

Run
---
1. sbt run
