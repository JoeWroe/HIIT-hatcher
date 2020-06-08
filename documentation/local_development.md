# Local Development :construction_worker:

## Installation

### HIIT Hatcher API App

Once you've pulled the code from GitHub, ensure you have an up to date version of Gradle and Java 11 installed and you should be good to go. Gradle will handle the rest for you.

### MongoDB

Follow the docs found [here](https://docs.mongodb.com/manual/installation/) for the initial installation of local MongoDB. Once you have it installed, you're going to need to setup a database and a collection within that database.

Start by running MongoDB with:

```shell script
$ brew services start mongodb-community
```

Open the Mongo shell:

```shell script
$ mongo
```

Setup a new local database and connect to it:

```shell script
use hiit_hatcher
```

Create your collection for exercises. A collection is Mongo's equivalent for a table in a relational database:

```shell script
db.createCollection("exercises");
```

From here, you can spin down your local Mongo and your databases and collections will remain.

```shell script
$ brew services stop mongodb-community
```

## Run Locally

To get the application running locally, start out by spinning up your local Mongo:

```shell script
$ brew services start mongodb-community
```

Next run the application from within the root of the directory:

```shell script
$ ./gradlew bootRun
```

You should now be able to navigate to [localhost:8080/api/exercises/](localhost:8080/api/exercises/) and view all exercises saved in the exercises collection.
