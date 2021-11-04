while read -r line;
do
  echo $line;
  curl -s -v -X POST http://localhost:9871/members -H "Content-Type: application/json" --data "$line"
done < ../preset_data/fakeMembers.json

