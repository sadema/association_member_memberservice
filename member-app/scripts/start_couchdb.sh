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
curl -X PUT http://localhost:9874/memberserviceview

cat <<'EOF' | curl -v -X PUT http://localhost:9874/memberserviceview/_design/member -H "Content-type: application/json" -d "$(</dev/stdin)"
{
    "_id": "_design/member",
    "views": {
        "all-members": {
            "map": "function (doc) {\n  if (doc.type === 'MEMBER') {\n  var key = [doc.lastName, doc.firstName]; \n emit(key, doc);\n  }\n}"
        },
        "addressByZipCodeAndStreetNumber": {
            "map": "function (doc) {\n if (doc.addresses) {\n doc.addresses.forEach(function(it) {\n if (it.zipCode && it.streetNumber) {\n emit([it.zipCode,it.streetNumber], it);\n }\n })\n }\n }"
        }
    }
}
EOF
