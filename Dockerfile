FROM java:8
VOLUME /tmp
ADD target/load_tests-1.0-SNAPSHOT.jar load_tests.jar
RUN sh -c 'touch /load_tests.jar'
ENTRYPOINT ["java","-jar","/load_tests.jar"]