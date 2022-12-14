for /f %%i in ('call mvn -q --non-recursive "-Dexec.executable=cmd" "-Dexec.args=/C echo ${project.version}" 
\"org.codehaus.mojo:exec-maven-plugin:1.6.0:exec"') do set DICTIONARIES_VERSION=%%i

echo %DICTIONARIES_VERSION%

echo Package and Deploy fonts to Nexus
call mvn clean deploy

pause
exit