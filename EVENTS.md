## MemberService events

### Producerende events
| Command | Event | PlayerEventData | API |
|---------|-------|--------|-----|
| | MemberSignedUp |
| | MemberPropertiesUpdated | firstName, lastName |

## Event field mappings

| MemberEventData | MemberAggregate<br>fieldType | MemberAggregate<br>Fieldname | Rest<br>PlayerData | CQRS |
|--------------| --------- | -------- | ----- | ----- |
| reference | MemberReference | reference | | id |
| firstName<br>lastName | MemberName | memberName | | firstName |
| birthDate | MemberBirthDate | memberBirthDate | | birthDate |
| address | MemberAddress | address |  | playerRole | 
| city | MemberCity | city |  | city |
| zip | MemberZipCode | zip | | zip |
