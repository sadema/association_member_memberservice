# MemberService events

## AggregateRoot: Member

### Producerende events
| Command | Event | MemberEventData | API |
|---------|-------|--------|-----|
| SignUpMember | MemberSignedUp | firstName, lastName, birthDate, state |
| EditMember | MemberEdited | firstName, lastName, birthDate |
| QuitMember | MemberQuited | |
| ChangeMemberState | MemberStateChanged | memberState | 
| AssignMemberRole | MemberRoleAssigned |
| AddAddressToMember | AddressAddedToMember |
| ClearAddress | AddressCleared |
| MoveMember | memberMoved |
| AddConcernedPerson | concernedPersonAdded |
| DisConnectConcernedPerson | concernedPersonDisconnected

## Event field mappings

| MemberEventData | MemberAggregate<br>fieldType | MemberAggregate<br>Fieldname | Rest<br>MemberData | CQRS |
|--------------| --------- | -------- | ----- | ----- |
| reference | MemberReference | reference | | id |
| firstName<br>lastName | MemberName | memberName | firstName, lastName |
| birthDate | MemberBirthDate | memberBirthDate | birthDate |
| state | MemberState | memberState | memberState |
| roles | MemberRoles | memberRoles |
| addressReferences | AddressReferences | addressReferences |  |  | 

## AggregateRoot: Address

### Producerende events
| Command | Event | AddressEventData | API |
|---------|-------|--------|-----|
| AddAddress | AddressAdded |
| EditAddress | AddressEdited | firstName, lastName |
|

## Event field mappings

| AddressEventData | AddressAggregate<br>fieldType | AddressAggregate<br>Fieldname | Rest<br>AddressData | CQRS |
|--------------| --------- | -------- | ----- | ----- |
| reference | AddressReference | reference | | id |
| street | Street | street | street | street | 
| city | City | city | city | city |
| zip | ZipCode | zipCode | zipCode | zipCode |

## AggregateRoot: ConcernedPerson

### Producerende events
| Command | Event | PlayerEventData | API |
|---------|-------|--------|-----|
| SignUpConcernedPerson | ConcernedPersonSignedUp |
| EditConcernedPerson | ConcernedPersonEdited | firstName, lastName |
| AddAddressToConcernedPerson | AddressAddedToConcernedPerson |
| MoveConcernedPerson | ConcernedPersonMoved |

## Event field mappings

| ConcernedPersonEventData | ConcernedPersonAggregate<br>fieldType | ConcernedPersonAggregate<br>Fieldname | Rest<br>ConcernedPersonData | CQRS |
|--------------| --------- | -------- | ----- | ----- |
| reference | ConceernedPersonReference | reference | | id |
| firstName<br>lastName | ConcernedPersonName | memberName | | firstName, lastName |
| phone | ConcernedPersonPhone | concernedPersonPhone |
| email | ConcernedPersonEmail | concernedPersonEmail |
| roles | ConcernedPersonRoles | concernedPersonRoles |
| addressReferences | AddressReferences | addressReferences |  |  | 

