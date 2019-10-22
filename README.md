# gradle-profiles
_Example of how to use Gradle profiles for multi-project Spring Boot applications._

The Profiles-Blog-Sender project sends simple HTTP POST requests to a Profiles-Blog-Receiver project running on the same host. Both projects share a common database with one table that records the number of requests sent and received for each payload type. Two payload types are available, TEST1 and TEST2.

The location of the database can be changed by using the following three profiles:
* test
* dev
* prod

Run gradle bootRun tasks with the -P argument to utilize a profile.  
For example, within a subproject's directory, use:  
gradle -Pdev bootRun

Or, if in the parent project's directory, individual projects can be targeted using their name:  
gradle -Pdev Profiles-Blog-Sender:bootRun

You can also run all subprojects in a monolithic stack of the app with:  
gradle -Ptest Profiles-Blog-Monolithic:bootRun

See common\_test\_application.properties and common\_dev\_application.properties for information about where each database is located.

The prod profile requires database credentials to be passed using -P arguments as follows:  
gradle -Pprod -Pspring.datasource.url=value -Pspring.datasource.username=value -Pspring.datasource.password=value bootRun

**Profiles-Blog-Sender supports two HTTP GET commands:**

http://localhost:9001/TEST1/send  
Sends a payload of the type specified in the URL to the Profiles-Blog-Sender project.  
Sample Response" {"payloadType":"TEST1","timesSent":1}

http://localhost:9001/sender/TEST1/count  
Displays the current number of times the specified payload type has been sent.  
Sample Response: {"payloadType":"TEST1","timesSent":1}

**Profiles-Blog-Receiver supports one HTTP GET command:**

http://localhost:9002/receiver/TEST1/count  
Displays the current number of times the specified payload type has been received.  
Sample Response: {"payloadType":"TEST1","timesReceived":1}

_TEST1 can be replaced with TEST2 for all of the aforementioned URLs._
