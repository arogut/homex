1. Running InfluxDB and Grafana

```
docker-compose up
```

2. First set up

Create database _measurement_ in influxDB
```
$ docker exec -it influxdb "/bin/bash"
$ influx
$ CREATE DATABASE "measurement"
``` 