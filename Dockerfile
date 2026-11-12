
FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

# Install Chromium and its driver
RUN apt-get update && apt-get install -y \
    chromium \
    chromium-driver \
    && rm -rf /var/lib/apt/lists/*

ENV CHROME_BIN=/usr/bin/chromium

# Copy project files
COPY pom.xml .
COPY calculator ./calculator
COPY src ./src

# Run Selenium tests
RUN mvn clean test

CMD ["mvn", "test"]
