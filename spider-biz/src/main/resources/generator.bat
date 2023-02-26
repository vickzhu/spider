@echo off  
set localdir=%~dp0  
call mvn mybatis-generator:generate
pause  