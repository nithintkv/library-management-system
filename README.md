# Tests

## Member should
- add to reserved books list
- add to checked out books list
- remove from reserved books list
- remove from reserved books list

## Book should
- be available if not reserved or checked out
- not be available if reserved
- not be available if checked out
- be loaned by a member


## Repository should
- return list of books when searched by Author name
- return single book when searched by uid
- return list of books when searched by book name
- return list of different books written by same author


## Library service should
- add BookDetail if not present to catalog
- add only Book and not BookDetail if BookDetail already present
- checkout book for a member
- not let member checkout a book if already checked out
- reserve book for a member if checked out
- not let member reserve book if it is already reserved
- not let member reserve book if same member checked it out
- let member return book
- not let member return book which is available
- change status of book after returning
- not change status if reserved
- clear borrower field after returning
- clear book from checked out list of member
