// Moving the build.gradle to kts soon just haven't been bothered to fix a few errors.
plugins {
    "java"
    kotlin("jvm") version "1.6.10"
}

group "me.perry"
version "1.0.0"

repositories {
    mavenCentral()
}

test {
    useJUnitPlatform()
}

configurations {
    // Adds a reference so that we extend/add on to it with depends so we can grab them and compile them into the jar.
    embed
    // Grabs what's extended/added on from/to embed and compiles it into the jar when being built.
    implementation.extendsFrom (embed)
}

compileJava {
    options.encoding = "UTF-8"
}

dependencies {
    embed("org.jetbrains.kotlin:kotlin-stdlib-jdk8") {
        exclude module ("kotlin-stdlib-jdk7")
        exclude module ("annotations")
    }
    embed("net.dv8tion:JDA:5.0.0-alpha.5") {
        exclude module ("opus-java")
        exclude module ("jsr305")
        exclude module ("annotations")
    }
    embed ("com.google.code.gson:gson:2.8.9")
    embed ("org.junit.jupiter:junit-jupiter:5.8.1")
}

jar {
    from(configurations.embed.collect {
        if (it.isDirectory()) it else zipTree(it)
    }) {
        // Excludes/Removes useless bloat files from the compiled jar.
        exclude ("dummyThing",
                "LICENSE.txt",
                "META-INF/**.RSA",
                "META-INF/maven/**",
                "META-INF/proguard/**",
                "META-INF/LICENSE**",
                "META-INF/NOTICE**",
                "org/**/*.html",
                "okhttp3/**/publicsuffixes.gz",
                "module-info.class",
                "kotlin/**/*.kotlin_metadata",
                "kotlin/**/*.kotlin_builtins",
                "META-INF/*.kotlin_module",
                "META-INF/versions/**")
    }
    manifest.attributes(
            "Main-Class" ("me.perry.bot.Starter")
    )
}