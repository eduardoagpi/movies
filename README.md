# Movies proyect

Este es un proyecto de ejemplo de una aplicacion que consume 
[The Movie Database API](https://developers.themoviedb.org/3/getting-started/introduction)

Entre las características del proyecto, el objetivo ha sido utilizar las recientes técnicas/tecnologías para el desarrollo de apps en Android

 - LiveData
 - Room DB
 - RxJava

Bajo una filosofía de Domain Driven Developmente usando Clean Architecture.
Se han incluidos algunos tests para demostrar independencia y por tanto testeabilidad de las capas, sin embargo el proyecto está lejos de haber sido implementado mediante TDD

# Capas de la aplicacion
La aplicación se compone básicamente de 3 capas

 - Domain 
 En está capa se encuentra toda la lógica de negocio (Entidades, Interactors o usecases)
 - Data
 En esta capa se incluye el manejo de datos dentro de la aplicación.
 Los datos pueden provenir ya sea de una base de datos local (En room) o bien de internet (Se utiliza Retrofit)
 - Presentación
En esta capa estará toda la lógica para presentar la información. Es aquí en donde se incluiran (y solo en esta capa) las clases de Android (Activities, Fragments, etc)
El patron de presentación utilizado es a través de Model-View-ViewModel (MVVM). Dichos viewModels tambien se incluyen en esta capa.
 
 ## Independencia de capas
 Siguiendo los guidelines de Clean Arquitecture, las capas de la app son círculos concentricos en donde las capas internas no tienen conocimiento de las capas externas
Las siguientes son las capas (vistas desde un CleanArchitecture approach) en orden de adentro hacia afuera.
 1. Entidades
 2. UseCases y Repositorios
 3. Android classes, Database (Room)

 ## Repositorios
 Para el acceso de datos desde la capa de dominio se utiliza el Repository Pattern. Usando el principio de inversión de dependencias, la definicion de los repositorios se encuentra en la capa de dominio y la implementacion en la capa de datos. De esta manera las fuentes de datos son intercambiables y la capa de dominio es agnóstica sobre la BD utilizada, en este caso Room
 El uso de repositorios permite tener una "single source of truth" y así obtener datos de la BD si no hay red.
 
## Presentación
La presentación de los datos se hace a través de viewModels. Cualquier cambio en el modelo de la vista provocará que se renderice la misma por completo. 
Para ello se utiliza ViewModels en conjunto con LiveData

*Se utiliza rxJava para la capa de domain y datos, y se utiliza liveData para la capa de presentación. Esta decisión en gran medida es porque si bien se podria utilizar también live data en room, eso haría que nuestro sistema estuviera casado con la base de datos de Room (y Uncle Bob nos advierte de no caer en eso!) 
En la capa de vista se hace uso de LiveData por ser componentes aware of lifecycle. De paso esto permite que el soporte de la app a cambios en la orientacion entre portrait-landscape sea trivial*

# Estructura del Proyecto
En el proyecto se divide principalmente en 4 paquetes.
3 corresponden con las clases de cada capa (domain, data, presentation) y un último paquete es para la injeccion de dependencias (Dagger2)


## Heading ¿En qué consiste el principio de responsabilidad única? Cuál es su propósito?
El principio de Responsabilidad Unica establece que las clases deben de hacer solamente una cosa y por tanto solo deberían de modificarse por una sola razon.
Entre sus propósitos se encuentra

 1. Evitar la presencia de God clases gigantescas con muchas responsabilidades
 2. Facilitar el testing de los componentes
 3. Cuando se crean nuevos features se reduce el impacto a solo las clases relacionadas con la nueva lógica
## Qué características tiene, según su opinión, un “buen” código o código limpio?
Aquél código que se apega a cada uno de los principios de SOLID

# Entregables
- [Video de app funcionando](https://github.com/eduardoagpi/movies/blob/master/delivery/video_full_movies.mp4?raw=true)
- [APK de la app](https://github.com/eduardoagpi/movies/blob/master/delivery/app-debug.apk?raw=true)
