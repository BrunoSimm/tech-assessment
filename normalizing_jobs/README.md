# Bruno Simm - Feefo Assessment

# Normalizing Job Titles

## Prerequisites

- **JDK (Java Development Kit) 21**
- **Maven**

## Solution Overview

The solution provides a REST API to normalise job titles. The normalised title with the highest quality
score is returned as the most similar match.

### Key Components

- **NormaliseService**: This class provides the method to normalise job titles. The method `normaliseJobTitle` takes a
  list of job titles and returns the most similar match based on the quality score (0 to 1).
  - The method uses the `Levenshtein distance` algorithm to calculate the similarity between two strings.
    - The algorithm calculates the minimum number of single-character edits (insertions, deletions, or substitutions)
      required to change one word into another.
- **NormaliseRepository**: This class provides the method to read the job titles from the H2 memory database.
- **NormaliseController**: This class provides the REST API to normalise job titles and retrieve a paginated list of normalised job titles.
  - Endpoints
    - `POST /normalise` accepts a input title and returns the most similar normalised job match.
    - `GET /normalise&page=0&size=10` accepts a page number and page size to return a paginated list of normalised job titles.

## Test Cases

- Unit test cases have been created to validate the scenarios provided in the assessment and also validate other common
  scenarios.
- Execute the following steps to compile the code and execute tests:
    - `mvn clean`
    - `mvn test`

## Running the application

Execute the following steps:
- `mvn clean`
- `mvn package`
- `java -jar .\target\normalizing_jobs-0.0.1-SNAPSHOT.jar`

Or simple run `mvn spring-boot:run` on the terminal.