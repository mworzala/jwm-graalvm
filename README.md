# JWM native-image

Tested using GraalVM 22.1.0-dev (build 17.0.3+4-jvmci-22.1-b03)

A test project for native image with jwm, particularly on arm macOS.

The agent proved slightly problematic, I had to temporarily terminate the 
program after 5 seconds of running (with `System#exit`), or the native image
agent never wrote its information. Not sure the cause of this.

Should be able to simply run `./gradlew nativeBuild` and run the execuable
in `build/native/nativeBuild`.