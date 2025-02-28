FROM gradle:8.11.1-jdk17 AS build
# 소스코드를 복사할 작업 디렉토리 생성
WORKDIR /myapp
# 호스트 머신의 소스코드를 이미지 작업 디렉토리로 복사
COPY . /myapp



#gradel종속성을 먼저 복사해서 캐싱
#COPY gradle /myapp/gradle
#COPY gradlew /myapp/
#COPY build.gradle settings.gradle /myapp/
# "gradlew"를 실행할 수 있도록 권한을 부여함
RUN chmod +x gradlew
# 이전 빌드에서 생성된 모든 /build/ 디렉토리 내용을 삭제하고 새롭게 빌드함
# 프로젝트를 빌드
# --no-daemon은 데몬을 이용하지 않고 빌드함
# Gradle은 설치되어 있는 Gradle을 이용해서 빌드하고, "gradlew"는 프로젝트에 포함된 Gradle을 이용함
# -x test -> test를 제외하고 작업함
# gradle종속성을 다운로드
RUN ./gradlew dependencies --no-daemon
#소스코드 복사
#COPY src /myapp/src
RUN ./gradlew clean build --no-daemon -x test

# 자바를 실행하기 위한 작업
FROM openjdk:17-alpine
WORKDIR /myapp
# 프로젝트 빌드 후 생성된 jar 파일을 런타임 이미지로 복사
COPY --from=build /myapp/build/libs/*.jar /myapp/getRand_userseivce.jar
EXPOSE 5002
ENTRYPOINT ["java", "-jar", "/myapp/getRand_userseivce.jar"]
