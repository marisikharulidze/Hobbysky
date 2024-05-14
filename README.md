# Hobbysky
Project is for people who wants to do what they enjoy, meet new people and be more outgoing.

## Stage 1:
Update README.md:
- add project overview
- add functional requirements 
- non functional requirements

## Stage 2:
- Clarify existing use-cases based on updated functional requirements
- Use "user story" template (e.g., "as a user, I want to ..., so that...)
- An example: an event should support different states/statuses (e.g., CREATED, BOOKE, IN PROGRESS, COMPLETED, ARCHIVED)

> Note: statuses -> enums in Java (think about db table) + read about `State` design pattern!

## Stage 3:
- Review updated use cases
- Identify missed entities and their attributes + relationships
- Update database tables + consider (`primary`/`foreign` keys dependencies)
- Check out potential data restrictions and constraints (e.g., unique fields, non-negative values, not-null values)
- Think about indexes (relevant for search operations - which columns should be indexed)


For more details refer to https://github.com/marisikharulidze/Hobbysky/wiki.
