SET WINRAR="..\..\WinRAR.exe"

@echo off
title Building RESTPapi
cls
call gradlew.bat dependencies
call gradlew.bat shadowJar
echo Gradle Completed, adding resources

cd .\build\libs
%WINRAR% x RestPAPI-1.0.4-all.jar"
copy ..\..\resources\log4j.properties .
copy ..\..\resources\plugin.yml .
del .\config.yml
%WINRAR% a -r ..\..\restpapiout.jar
cd ..\..\
rmdir /s .\build

echo Build completed jarfile in project directory named "restpapiout.jar"
pause