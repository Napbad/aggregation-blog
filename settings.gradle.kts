plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "AggregationBlog"
include("server-backend")
include("client-backend")
include("common")
