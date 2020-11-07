# Project structure and files

- **build.gradle** – This contains the dependencies needed for the project.
- **src/main**
    * **java**
        * ```com.droideparanoico.albumcatalog```
            * **ServletInitializer.java** – this class extends SpringBootServletInitializer to make it eligible to deploy as a traditional WAR application.
            * **SpringBootAlbumCatalog.java** – This class is annotated with @SpringBootApplication which does the auto-configuration of the application.
        * ```com.droideparanoico.albumcatalog.config```
            * **SwaggerConfiguration.java** – This is a @Configuration annotated class that contains the beans that initialize Swagger for this application.
        * ```com.droideparanoico.albumcatalog.controller```
            * **ArtistController.java** – This is a @Controller annotated classes that contains the request handler method for the following endpoints:
              GET / – The request handler method listArtistAndAlbums(Model) is called when we land on the root application.
        * ```com.droideparanoico.albumcatalog.controller.data```
            * **ArtistRestController.java** – This is a @RestController annotated class that contains the request handler method for the following endpoints:
           
            |HTTP Method|Endpoint|Request Handling Method|Description|
            |:---:|:---:|:---:|---|
            |```GET```|```/artist/```|```root()```|returns **“application is working!”** when called|
            |```GET```|```/artist/all```|```getAllArtists()```|returns ```Iterable<Artist>``` when called|
            |```GET```|```/artist/{id}```|```getArtistById(BigInteger)```|returns the ```Artist``` with the given id when called|
            |```POST```|```/artist/{name}```|```createArtist(String)```|for creating a new **Artist** with the given name and returns the same after creation|
            |```DELETE```|```/artist/{id}```|```deleteArtist(BigInteger)```|for deleting an **Artist** by id|
            |```GET```|```/artist/{id}/albums```|```getAlbumsByArtist(BigInteger)```|for fetching all the albums of the **Artist** with the given id|
            |```DELETE```|```/artist/{id}/albums```|```deleteAllAlbumsFromArtist(BigInteger)```|for deleting all the albums of the **Artist** with the given id|
            |```POST```|```/artist/{id}/add```|```addAlbumToArtist(BigInteger, Album)```|for adding the given **Album** to the **Artist** with the given id and returns the created album|
            |```GET```|```/artist/albums```|```getAllAlbums()```|returns ```List<Album>``` that fetches all the albums from all the artists|
            |```PUT```|```/artist/albums/{id}/move```|```moveAlbumToDifferentArtist(BigInteger, BigInteger)```|for moving an album from one artist to another|
            |```DELETE```|```/artist/{id}/albums/{album_id}```|```deleteFromArtist(BigInteger, BigInteger)```|for deleting an album from an artist|
            
            > **ArtistRestController** uses **ArtistService** for business logic and interacting with persistence layer.
        * ```com.droideparanoico.albumcatalog.database```
            * **ArtistRepository.java** – this interface extends JpaRepository for interacting with artist database table.
            * **AlbumsRepository.java** – this interface extends JpaRepository for interacting with album database table.
        * ```com.droideparanoico.albumcatalog.exception```
            * **ExceptionController.java** – this class is annotated with @ControllerAdvice and contains @ExceptionHandler annotated method to handle exceptions.
            * **ArtistNotFoundException.java** – a custom exception class for managing an invalid artist.
            * **AlbumNotFoundException.java** – a custom exception class for managing an invalid album.
        * ```com.droideparanoico.albumcatalog.model```
            * **ErrorCodes.java** – and enum that holds the error codes associated with our custom exceptions.
            * **Artist.java** – a POJO class for saving the artist information.
            * **Album.java** – a POJO class for saving the album information.
        * ```com.droideparanoico.albumcatalog.service```
            * **ArtistService.java** – a Business Facade Service that controllers use to interact with the database repositories and contains the business logic to manipulate the data that is transferred between the controller and persistence layer.
    * resources
        * ```templates```
            * **index.html** – the HTML template with embedded Thymeleaf constructs for rendering the server-side response.
        * ```static```
            * **custom.css** – a cascading stylesheet for our application.
            * **scripts.js** – a Javascript file containing the method to interact with our backend RESTful APIs that we have built.
        * **application.properties** – contains the Spring Boot configurations.
        * **application-dev.properties** – contains the Spring Boot configurations for ```dev``` profile.
        * **data.sql** – initializing database script that is executed at the time of application boot up.                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  