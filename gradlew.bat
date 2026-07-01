@echo off
REM Gradle wrapper bat (Windows)
SET APP_HOME=%~dp0..
IF EXIST "%APP_HOME%\gradle\wrapper\gradle-wrapper.properties" (
  FOR /F "tokens=2 delims==" %%A IN ('findstr /B /C:"distributionUrl" "%APP_HOME%\gradle\wrapper\gradle-wrapper.properties"') DO (
    set DIST_URL=%%A
  )
) ELSE (
  set DIST_URL=https\://services.gradle.org/distributions/gradle-8.4.1-bin.zip
)
gradle %*
