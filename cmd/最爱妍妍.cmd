@echo off & title Love & setlocal enabledelayedexpansion
chcp 65001
mode con: cols=36 lines=19
:loop
set colors=12345689abcde 
set /a rand=%random%%%13+1
set color=!colors:~%rand%,1! 
color 0%color% 
echo.  
echo. 
echo          ****          ****
echo        *******        *******
echo      ***********    ***********
echo     *************  *************
echo     ****************************
echo     ****************************
echo     ****************************
echo      **************************
echo        ***********************
echo          *******************
echo            ***************
echo              ***********
echo                *******
echo                  ***
echo                   *
echo		最爱妍妍
ping 127.1 -n 2 >nul
cls
goto loop
echo. & pause > nul