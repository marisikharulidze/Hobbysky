# Hobbysky
Web application is for finding new hobbies, meeting new people and having fun. Hobbysky is a platform where you can join in events with people doing your favorite activities. Such as: painting, swimming, running, skiing, reading, playing sports, cooking and a lot of different things. Event members can make only one event by themselves, not more, other events are made by administrator. Event locations, time or other details can be changed or discussed by the group members. 
Project is for people who wants to do what they enjoy, meet new people and be more outgoing.
With the help of this application it will be easier and more fun to do what they like with the people, who are interested in the same fields. 

Functional Requirements: 
- The system must display events, where people can join in(can have event chat)
- Profile
- administration
- searching through events
- events can be archived(only admin can remove the event).
Non-Functional Requirements: The system should be
- fast
- secure
- working 24/7. 

## Stage 1:
Update README.md:
- add project overview
- add functional requirements 
- non functional requirements

## Stage 2:
- Clarify existing use-cases based on updated functional requirements
- Use "user story" template (e.g., "as a user, I want to ..., so that...)
- An example: an event should support different states/statuses (e.g., CREATED, BOOKED, COMPLETED/ARCHIVED)

> Note: statuses -> enums in Java (think about db table) + read about `State` design pattern!

## Stage 3:
- Review updated use cases
- Identify missed entities and their attributes + relationships
- Update database tables + consider (`primary`/`foreign` keys dependencies)
- Check out potential data restrictions and constraints (e.g., unique fields, non-negative values, not-null values)
- Think about indexes (relevant for search operations - which columns should be indexed)


For more details refer to https://github.com/marisikharulidze/Hobbysky/wiki.
