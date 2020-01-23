start cmd /k "call Gradlew.bat"
start cmd /k "call YarnServe.bat"

start "Chrome" chrome --new-window "http://localhost:8080/"