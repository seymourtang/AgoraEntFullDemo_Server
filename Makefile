.PHONY: build-ktv-online run-ktv-online
build-ktv-online:
	@mvn clean package -f ./ktvOnline/pom.xml

run-ktv-online:
	@java -jar ./ktvOnline/target/ktvOnline-0.0.1-SNAPSHOT.jar