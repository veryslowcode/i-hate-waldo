plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "ihw"
include("app")
include("dajfa")
include("wari")
project(":dajfa").projectDir = file("lib/debates-are-just-fancy-arguments/lib")
project(":wari").projectDir = file("lib/who-actually-reads-instructions/lib")
