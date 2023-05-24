FROM bellsoft/liberica-openjdk-alpine:latest
COPY ./src ./src
RUN mkdir ./out
RUN javac -sourcepath ./src/main/java -d ./out ./src/main/java/ru/gb/core/homework/one/App.java
CMD java -classpath ./out ru.gb.core.homework.one.App


