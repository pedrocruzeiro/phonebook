# inventory-management

A cloud ready service based on Spring Boot to manage phonebook operations.

This service provides a single rest API responsible to aggregate valid phone numbers by prefixes and business sectors.

## Usage:

- REST API

## Use case:

- Input a list of phone numbers (valid and/or invalid) and the service will return the count of the valid phone numbers
  aggregated by prefixes and sectors.

## Resources:

- Prefixes file: The application uses a prefixes txt file to use has reference data for prefix validation.
- Business Sector API: To retrieve the business sector of a phone number this service uses an external API.

## Getting Started:

### Docker Compose

A docker and a docker compose file are provided in the root of the project to run the containerized service.

### Aggregate Operation

#### Endpoint

- http://localhost:8080/aggregate

#### Input Example

```
["+1983236248", "+1 7490276403", "001382355A", "+351917382672", "+35191734022","+4439877"]
```

#### Output Example

```
{
    "44": {
        "Banking": 1
    },
    "1": {
        "Technology": 1,
        "Banking": 1
    },
    "3519173": {
        "Clothing": 2
    }
}
```

### Configuration

A configuration file (CONFIG.md) is provided in the project root folder.

### Running the service

The application can be executed in the following ways:

In the root folder of the project run one of the following commands:

- Standalone application service: mvn spring-boot:run
- Containerized service:
    - mvn clean install
    - docker-compose up web