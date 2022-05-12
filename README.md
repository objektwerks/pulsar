Pulsar
------
>Apache Pulsar feature tests.

Install
-------
>Via Docker:
1. docker pull apachepulsar/pulsar

Run
---
>Via Docker:
1. run docker daemon
2. run:
```
docker run -it \
  -p 6650:6650 \
  -p 8080:8080 \
  --name pulsar \
  apachepulsar/pulsar:2.10.0 \
  bin/pulsar standalone
```

Test
----
1. sbt clean test