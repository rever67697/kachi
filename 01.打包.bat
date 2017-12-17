echo making package jar
cd %~dp0
mvn clean package  -Dmaven.test.skip=true > %~dp0build_log.txt
pause