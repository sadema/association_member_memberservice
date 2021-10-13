#!/bin/bash

CONTAINER_ID=$(docker ps | grep association_couchdb | awk '{print $1}')
echo "Container id: ${CONTAINER_ID}"
if [ ! -z "${CONTAINER_ID}" ];
then
  docker stop association_couchdb
fi
docker run -d --rm --name team_couchdb -p 5984:5984 association_couchdb:1.0.0
sleep 5
curl http://localhost:5984

curl -X PUT http://localhost:5984/_users
curl -X PUT http://localhost:5984/_replicator
curl -X PUT http://localhost:5984/members

cat <<'EOF' | curl -v http://localhost:5984/members -H "Content-type: application/json" -d "$(</dev/stdin)"
{
    "_id": "_design/team",
    "views": {
        "new-players": {
            "map": "function (doc) {\n  if (doc.type === \"PLAYER\" && doc.team_reference === null) {\n    emit(doc._id, doc);\n  }\n}"
          },
        "team-players": {
            "map": "function (doc) {\n  if (doc.team_reference) {\n    emit(doc.team_reference, doc);\n  }\n}"
          },
        "all-players": {
            "map": "function (doc) {\n  if (doc.type === 'PLAYER') {\n    emit(doc._id, doc);\n  }\n}"
          },
        "all-teams-and-teamplayers": {
            "map": "function (doc) {\n  if (doc.type === \"TEAM\") {\n    emit(doc._id, doc);\n  }\n  else {\n    if (doc.type === \"PLAYER\" && doc.team_reference !== null) {\n      emit(doc.team_reference, doc);\n    }\n  }\n}"
          },
        "all-teams": {
            "map": "function (doc) {\n  if (doc.type === 'TEAM') {\n    emit(doc._id, doc);\n  }\n}"
          }
    }
}
EOF
