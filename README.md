# Copyright &copy; Analyst1 2022. DO NOT publish publicly. #

Thank you for your interest in Analyst1!  The following technical evaluation is meant to be a "real life" example of some of the problems we work on every day.

# Problem background

Your employer would like to consume information from **DarknetInfo**, a new threat intelligence information service.  DarknetInfo provides an API listing information about **attackers** and
**malware**.  An **attacker** is a group that carries out network attacks against various targets, usually companies, via executable **malware** that can be identified through various
**indicators of compromise**, such as file hashes or domain names.  Your company provides a tool for users to correlate many pieces of threat intelligence, including attackers and malware, so
DarknetInfo would be a valuable source of information to consume.

The DarknetInfo data model is however, different from the data model your company's tool uses, so you will need to adapt their data into a form that your system understands.

# First steps

## Prerequisites

This project requires JDK17+ to compile/execute. 

## Start the DarknetInfo API

From the directory where the code was checked, execute:

`java -jar bin/eval-endpoint-1.0.0.jar`

This will expose the following endpoints on your local machine:

* API Console, for viewing data in the browser.
    * http://localhost:8081/api/explorer/index.html#uri=/api
* Swagger UI
    * http://localhost:8081/swagger-ui/index.html
* [Swagger Doc](https://swagger.io/specification/)
    * http://localhost:8081/v3/api-docs

## Preparing to build and test the code

The project can be run by navigating to the directory where the code was checked out (*project directory*) and executing the gradle wrapper appropriate for your operating system.

On MacOSX and Linux, the command is `./gradlew`.  On windows, execute `gradlew.bat`.  The instructions in this README assume Linux or MacOS X, replace the command below as appropriate.

## Test the code

From the project directory, execute:

`./gradlew test`

Test results will be printed to the console.

## Build the code

From the project directory, execute:

`./gradlew build`

to build an executable JAR into `build/libs/`

## Run the code

To run the code, execute a build using the steps above, and from the project directory, invoke:

`java -jar build/libs/a1-integration-eval-1.0.0.jar`

This will expose the following endpoints on your local machine:

* H2 console - for viewing the embedded in-memory [H2DB](https://www.h2database.com/html/main.html) data in browser. Connection URL: jdbc:h2:mem:a1eval Default Username/Password: **sa**/**password**
    * http://localhost:8080/h2-console
* API Console - for viewing the data over the API
    * http://localhost:8080/api/explorer/index.html#uri=/api

It will also print an exception with the "entry point" of changes to be made.

Optionally, go to http://localhost:8080/util/poll to re-run the **ingest** (_see **Design overview**, below_).

# Design overview

This project, which represents your company's tool, runs a periodic poll of all the threat intelligence information services that it integrates with. Your task will be to add the first data source integration with the provider DarknetInfo. The team will be adding integrations with other providers of similar data in the future. As
information is ingested from the API, it is translated into the project's data model (_see below_) and users are able to view the data via the API Console
(http://localhost:8080/api/explorer/index.html#uri=/api).  Additionally, the tool performs background analysis of this information, but that is outside the scope of this project.

## Data model background

The root of the project's data model is the **attacker web**, which aggregates all **attackers** and those designated as **related attackers** (as declared by the DarknetInfo API) together with all
the corresponding indicators of compromise (also known as IOCs or indicators).  In this manner, the tool is able to develop a comprehensive mapping of attackers to the indicators (e.g., domain names,
file hashes) that might be found on a network or computer.  In other words, the tool could determine based on the presence of certain **malware** on a network or computer that particular attackers
have infiltrated the network.

# Instructions #

Summary: Integrate/Ingest data from provided endpoint into this application as described below
Restrictions:

* Use any libraries you feel help get the job done.
* Don't modify the @Entity code unless there's something that's impossible to do otherwise.
    * If modified, please note what needed to be done and why.
* Please add tests for submitted code.
  
## Suggested steps to ingest DarknetInfo information

* Determine how to communicate with the DarknetInfo REST API.  DarknetInfo does not provide a native Java client, but they do provide [Swagger Docs](http://localhost:8081/v3/api-docs).
* Map **all** attackers from the DarknetInfo REST API format into the project's native format (`com.analyst1.eval.model.Attacker`)
    * The following [API endpoint](http://localhost:8081/swagger-ui/#/Attacker%20Entity/findAllAttackerUsingGET) will list all attackers known to DarknetInfo.
* Combine all "related attackers" (as returned by the DarknetInfo API) into the same attacker web (`com.analyst1.eval.model.AttackerWeb`).
* For all of the attackers in the same attacker web, add all of the indicators provided by the DarknetInfo API.
    * The DarknetInfo API does not directly provide a mapping between attackers and indicators, but they do provide a mapping between malware and indicators
* Track the unique number of "AttackerWeb", "Attacker", "Malware", and "Indicator" that are ingested.

## Assumptions ##

* Submissions should not be concerned with synchronization and race conditions.
* TBD

## Submission ##

* Either create a compressed git diff patch, or zip up everything other than `build`, `bin` and any hidden directories and email it back to us.
* Be prepared to present your solution to a team of engineers as if they were coworkers doing a peer review.
* REMINDER: Do not commit to a public git repository.


# What if I have questions? #

* Feel free to reach out via email, we're happy to help!
