# Personal Document Dossier

The software in this repository is designed to fix a personal problem.  
The influx of tax relevant documents, bills, contracts, pictures/videos
and emails is not manageable with a folder structure and a macbook search.

This project is a file management system using customizable filters,
adapters to various storage options, and file editing tools.

The front end is currently Primefaces/JSF. Additional front ends in using
other technologies will be added later.

## To Run the project a local database docker instance is needed

The following script starts a postgres instance

docker.sh

## Build and Execute the project using maven

mvn clean package -pl adapters/jsf
