## Prerequisites
Kafka must be running and the topics must exist.

## Owner of topics
- public.association.memberservice.member

## Build java service
```shell
mvn clean

# Build the avro data types in the domain module
cd domain
mvn avro:schema
cd ..

mvn install
```

## CouchDB
### run couchdb in docker
```shell script
docker run -d --rm --name team_couchdb -p 5984:5984 -v /opt/couchdb/data --volumes-from team-data couchdb:2.3.1
```
