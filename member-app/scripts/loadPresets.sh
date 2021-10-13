#!/bin/bash

# start couchdb and create databases
. start_couchdb.sh


HOST='http://localhost:9871/actuator/health'

wait-for-url() {
  echo "Testing $1"
  timeout -s TERM 45 bash -c \
  'while [[ "$(curl -s -o /dev/null -L -w ''%{http_code}'' ${0})" != "200" ]];\
  do echo "Waiting for ${0}" && sleep 2;\
  done' ${1}
  echo "OK!"
  curl -I $1
}
wait-for-url ${HOST}

# member presets by http api
curl http://localhost:9871/members -H "Content-Type: application/json" -d '{ "firstName": "Lorenzo", "lastName": "de Jager", "birthDate": 132447600000, "city": "Hensbroek", "zip": "5307 RE", "address": "Kickdreef 38" }'

