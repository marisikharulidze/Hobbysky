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

## Homeworkon Wednesday:
- move functional & non-functional requirements to project documentation (Wiki)
- class diagrams (under architecture design page)
- update database diagram to include constraints:
  - not null checks
  - unique
  - database index (for performant search) - nice-to-have
- Java constraints:
  - description should not be blank (not null + not empty) + min description length (1 character) + max description length (500 characters)
  - creation date (should not be in the past)
- stage #4 task (user interface mockup):
  - HTML + CSS + JS
  - GPT prompts - nice-to-have

## Stage 3:
- Review updated use cases
- Identify missed entities and their attributes + relationships
- Update database tables + consider (`primary`/`foreign` keys dependencies)
- Check out potential data restrictions and constraints (e.g., unique fields, non-negative values, not-null values)
- Think about indexes (relevant for search operations - which columns should be indexed)


For more details refer to https://github.com/marisikharulidze/Hobbysky/wiki.
