import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // Compose
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            // Navigation
            implementation(libs.androidx.navigation.compose)
            // Networking
            implementation(libs.ktor.client.core)
            implementation(libs.ksoup.kotlinx)
            implementation(libs.ksoup.network)
            // DI
            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            //Coroutines
            implementation(libs.kotlinx.coroutines.core)
            // Datetime
            implementation(libs.kotlinx.datetime)
            // Images
            implementation(libs.coil.compose)
            implementation(libs.coil.compose.core)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)
            // Database
            api(libs.room.runtime)
            implementation(libs.sqlite.bundeled)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
        androidMain.dependencies {
            implementation(libs.koin.androidx.compose)
            implementation(libs.koin.android)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.kotlinx.coroutines.test)
        }
    }
    dependencies {
        debugImplementation(compose.uiTooling)
        add("kspAndroid", libs.room.compiler)
        add("kspIosSimulatorArm64", libs.room.compiler)
        add("kspIosX64", libs.room.compiler)
        add("kspIosArm64", libs.room.compiler)
    }

}

room {
    schemaDirectory("$projectDir/schemas")
}

android {
    namespace = "by.freiding.keepit"
    compileSdk = 35
    defaultConfig {
        minSdk = 26
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_23
        targetCompatibility = JavaVersion.VERSION_23
    }
}


//**************************************************************************************************
//                                  KTLINT CONFIGURATION
//**************************************************************************************************
ktlint {
    android = true // Enable Android-specific linting rules
    ignoreFailures = false // Fail the build if KtLint finds any issues
//    disabledRules = ["final-newline", "no-wildcard-imports", "max-line-length"]

    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.HTML)
        reporter(ReporterType.JSON)
    }
}

// format Kotlin code using KtLint before the project is built.
tasks.getByPath("preBuild").dependsOn("ktlintFormat")

//**************************************************************************************************
//                                  DETEKT CONFIGURATION
//**************************************************************************************************
detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(
        File(rootProject.rootDir, "config/detekt/detekt.yml"),
        File(rootProject.rootDir, "config/detekt/detekt-compose.yml"),
    )
    // Applies the config files on top of detekt's default config file. `false` by default.
    buildUponDefaultConfig = false

    // Turns on all the rules. `false` by default.
    allRules = false

    // Specifying a baseline file. All findings stored in this file in subsequent runs of detekt.
    baseline = file("detekt-baseline.xml")

    // Disables all default detekt rulesets and will only run detekt with custom rules
    // defined in plugins passed in with `detektPlugins` configuration. `false` by default.
    disableDefaultRuleSets = false

    // Adds devug output during task execution. `false` by default.
    debug = false

    // If set to `true` the build does not fail when the
    // maxIssues count was reached. Defaults to `false`.
    ignoreFailures = false //true

    parallel = true

    // Has to be specified for it to correctly report the paths to GitLab
    basePath = rootDir.toString()
}

tasks.withType<Detekt>().configureEach {
    reports {
        html.required.set(true)
        custom {
            reportId = "DetektGitlabReport"
            // This tells detekt, where it should write the report to,
            // you have to specify this file in the gitlab pipeline config.
            outputLocation.set(file(layout.buildDirectory.file("reports/detekt/gitlab.json")))
        }
    }
}

dependencies.add("detektPlugins", libs.detektPlugin.nlopez.composeRules)
dependencies.add("detektPlugins", libs.detektPlugin.cromfire.gitlab.report)
dependencies.add("detektPlugins", libs.detektPlugin.artubosch.report.html)
