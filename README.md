## user-list-demo: a simple web app

A simple example web app to display a list of users with the ability to add new users and filter the display results by a partial name.

* Front end: ReactJS / Thymeleaf + Bootstrap
* Back end: Spring Boot using Spring Data Rest, JPA, Thymeleaf, Lombok and H2 database

Run with
```
./mvnw spring-boot:run
```
on Unix, or double-click on `mvnw.cmd` in Windows.

The filtering is performed on the back end, which means a new page is loaded rather than being updated in-place.

Inspired by the tutorial at https://spring.io/guides/tutorials/react-and-spring-data-rest/

### Upgrade list

* Add paging: only displays 20 entries
* No error checking: it's possible to add blank users
* No delete or update capability
* Text inputs are not sanitized

### Security

The Spring security module (and the corresponding Thymeleaf extras) allows the app to be secured against non-privileged access.

A new entity for one or more user accounts would be added, passwords would be encrypted with Bcrypt and prevented from being returned in plain text.

Classes for a UserDetailsService and SecurityConfiguration would be added, the latter of which contains access rules for determining which resources can be viewed publicly and which require a login.
