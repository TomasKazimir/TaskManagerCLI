# --- Build Stage ---
FROM eclipse-temurin:25 AS build
WORKDIR /app

COPY src ./src
COPY TaskManagerCLI.iml ./

RUN mkdir build \
    && find src -name "*.java" > sources.txt \
    && javac -d build @sources.txt

# --- Runtime Stage ---
FROM eclipse-temurin:25 AS runtime
WORKDIR /app

COPY --from=build /app/build ./build
COPY README.md ./

ENTRYPOINT ["java", "-cp", "build", "main.TaskManagerApp"]
