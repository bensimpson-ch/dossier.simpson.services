# Personal Document Dossier

The software in this repository is designed to fix a personal problem managing documents, images and videos.
The "dossier" is a file management system delivering document filtering, tagging, text extraction, viewers,
adapters to various storage options, and file editing tools.

The front end is Primefaces/JSF.

## To Run

The project requires a database to store files before they are persisted elsewhere. The database will hold onto file
metadata to incorporate the files into your searches.

## Build and Execute the project using maven locally

mvn clean package -pl servers/openliberty -amd -Plocal-h2

## Customize the docker-compose.yaml file found next to this README

## Public Site

A public version of this software will be available at https://dossier.simpson.services

The real intention however, is to install this software on a network area storage device like a Synology or an in house
network storage server where you can manage your own security and local storage amounts.
