#!/bin/bash

CONTAINER_ID=$(docker ps | grep member_couchdb | awk '{print $1}')
echo "Container id: ${CONTAINER_ID}"
if [ ! -z "${CONTAINER_ID}" ];
then
  docker stop member_couchdb
fi
docker run -d --rm --name member_couchdb -p 9874:5984 couchdb:2.3.1
sleep 5
curl http://localhost:9874

curl -X PUT http://localhost:9874/_users
curl -X PUT http://localhost:9874/_replicator
curl -X PUT http://localhost:9874/members

cat <<'EOF' | curl -v http://localhost:5984/members -H "Content-type: application/json" -d "$(</dev/stdin)"
{
    "_id": "_design/member",
    "views": {
#        "new-players": {
#            "map": "function (doc) {\n  if (doc.type === \"PLAYER\" && doc.team_reference === null) {\n    emit(doc._id, doc);\n  }\n}"
#          },
#        "team-players": {
#            "map": "function (doc) {\n  if (doc.team_reference) {\n    emit(doc.team_reference, doc);\n  }\n}"
#          },
#        "all-players": {
#            "map": "function (doc) {\n  if (doc.type === 'PLAYER') {\n    emit(doc._id, doc);\n  }\n}"
#          },
#        "all-teams-and-teamplayers": {
#            "map": "function (doc) {\n  if (doc.type === \"TEAM\") {\n    emit(doc._id, doc);\n  }\n  else {\n    if (doc.type === \"PLAYER\" && doc.team_reference !== null) {\n      emit(doc.team_reference, doc);\n    }\n  }\n}"
#          },
        "all-members": {
            "map": "function (doc) {\n  if (doc.type === 'MEMBER') {\n    emit(doc._id, doc);\n  }\n}"
          }
    }
}
EOF
