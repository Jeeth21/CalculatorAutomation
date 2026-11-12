
FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

RUN apt-get update && apt-get install -y \
    chromium \
    chromium-driver \
    && rm -rf /var/lib/apt/lists/* \
    && if command -v chromium >/dev/null 2>&1; then \
         ln -sf "$(command -v chromium)" /usr/bin/chromium; \
       elif command -v chromium-browser >/dev/null 2>&1; then \
         ln -sf "$(command -v chromium-browser)" /usr/bin/chromium; \
       else \
         echo "Chromium browser not found"; exit 1; \
       fi

ENV CHROME_BIN=/usr/bin/chromium

COPY pom.xml .
COPY calculator ./calculator
COPY src ./src

RUN mvn clean test

CMD ["mvn", "test"]
