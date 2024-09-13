FROM openjdk:17-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y git
RUN git clone https://github.com/kame-meka/portfolio.git /app
WORKDIR /app/backend
RUN ./gradlew build
RUN ./gradlew installDist
EXPOSE 8080
ENV SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/visual_dictionary
ENV SPRING_DATASOURCE_USERNAME=root
ENV SPRING_DATASOURCE_PASSWORD=root

# アプリケーションの起動
CMD ["./build/install/portfolio/bin/portfolio"]