FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER joaoemilio@devthinkers.com
VOLUME /tmp
ADD target/vets-2.1.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /app.jar" ]
