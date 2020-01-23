@echo off
cd Backend
cd src
cd main
cd resources
del /f DB.mv.db
del /f DB.trace.db
cd ..
cd ..
cd ..
Gradlew bootrun

pause