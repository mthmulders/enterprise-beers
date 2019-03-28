# Enterprise Beers
[![CircleCI](https://circleci.com/gh/mthmulders/enterprise-beers.svg?style=svg)](https://circleci.com/gh/mthmulders/futbolin)
[![SonarCloud quality gate](https://sonarcloud.io/api/project_badges/measure?project=mthmulders_enterprise-beers&metric=alert_status)](https://sonarcloud.io/dashboard?id=mthmulders_futbolin)
[![SonarCloud vulnerability count](https://sonarcloud.io/api/project_badges/measure?project=mthmulders_enterprise-beers&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=mthmulders_futbolin)
[![SonarCloud technical debt](https://sonarcloud.io/api/project_badges/measure?project=mthmulders_enterprise-beers&metric=sqale_index)](https://sonarcloud.io/dashboard?id=mthmulders_futbolin)

This repository contains a sample application used in my talk [Mastering Microservices with Kong](https://maarten.mulders.it/talks/#mastering-microservices-with-kong).

The application is built with Java 11 and Java EE 8; it is tested with [OpenLibery 19.0.1](https://openliberty.io/).

To play around in each of the demo scenarios, see the instructions below.

## Getting Started - Demo 1
* Start all required containers with `docker-compose -f demo-1.yml up`.
* Run `docker-compose -f demo-1.yml down`.
* See `./demo-1.sh` for the demo script.

## Getting Started - Demo 2
* Remove old database directory with `rm -Rf pg-data`.
* Start PostgreSQL container with `docker-compose -f demo-2.yml up postgresql`.
* Start Kong database migrations with `docker-compose -f demo-2.yml up kong-db-migration`.
* Start all other required containers with `docker-compose -f demo-2.yml up -d`.
* See `./demo-2.sh` for the demo script. 
* Finally, run `docker-compose -f demo-2.yml down` to stop and remove all containers.

## Getting Started - Demo 3
Note that this demo builds upon demo 2, so we don't remove the directory that contains PostgreSQL database files.

* Start PostgreSQL container again with `docker-compose -f demo-3.yml up postgresql`.
* Start Kong database migrations with `docker-compose -f demo-3.yml up kong-db-migration`.
* Start all other required containers with `docker-compose -f demo-3.yml up -d`.
* See `./demo-3.sh` for the demo script. 
* Finally, run `docker-compose -f demo-3.yml down` to stop and remove all containers.
* You might also remove the PostgreSQL database directory with `rm -Rf pg-data`.

# License
The Apache License, version 2.0, applies.
See [the license file](./LICENSE) for details.
