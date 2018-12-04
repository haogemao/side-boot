FROM appserver:0.1
MAINTAINER gaomingchao
VOLUME /Volumes
ADD Side-ServiceRegister-0.0.1-SNAPSHOT.jar registerserver.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/registerserver.jar"]