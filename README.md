### Kover plugin in Gradle buildSrc: multiple versions on classpath

If a kover plugin dependency appears in `buildSrc/build.gradle.kts`, the Kotlin compiler warns
> Runtime JAR files in the classpath should have the same version

and
> Consider providing an explicit dependency on kotlin-reflect 1.6 to prevent strange errors

However, providing an explicit dependency on kotlin-reflect 1.6 does not help.

##### Reproduce
```
./gradlew -p buildSrc clean
./gradlew -p buildSrc build
```

##### Output
```
> Task :compileKotlin
'compileJava' task (current target is 11) and 'compileKotlin' task (current target is 1.8) jvm target compatibility should be set to the same Java version.
w: Runtime JAR files in the classpath should have the same version. These files were found in the classpath:
/home/oliver/.gradle/wrapper/dists/gradle-7.4-bin/c0gwcg53nkjbqw7r0h0umtfvt/gradle-7.4/lib/kotlin-stdlib-1.5.31.jar (version 1.5)
/home/oliver/.gradle/wrapper/dists/gradle-7.4-bin/c0gwcg53nkjbqw7r0h0umtfvt/gradle-7.4/lib/kotlin-stdlib-common-1.5.31.jar (version 1.5)
/home/oliver/.gradle/wrapper/dists/gradle-7.4-bin/c0gwcg53nkjbqw7r0h0umtfvt/gradle-7.4/lib/kotlin-stdlib-jdk7-1.5.31.jar (version 1.5)
/home/oliver/.gradle/wrapper/dists/gradle-7.4-bin/c0gwcg53nkjbqw7r0h0umtfvt/gradle-7.4/lib/kotlin-stdlib-jdk8-1.5.31.jar (version 1.5)
/home/oliver/.gradle/wrapper/dists/gradle-7.4-bin/c0gwcg53nkjbqw7r0h0umtfvt/gradle-7.4/lib/kotlin-reflect-1.5.31.jar (version 1.5)
/home/oliver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk8/1.6.10/e80fe6ac3c3573a80305f5ec43f86b829e8ab53d/kotlin-stdlib-jdk8-1.6.10.jar (version 1.6)
/home/oliver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-reflect/1.5.31/1523fcd842a47da0820cea772b19c51056fec8a9/kotlin-reflect-1.5.31.jar (version 1.5)
/home/oliver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-jdk7/1.6.10/e1c380673654a089c4f0c9f83d0ddfdc1efdb498/kotlin-stdlib-jdk7-1.6.10.jar (version 1.6)
/home/oliver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib/1.6.10/b8af3fe6f1ca88526914929add63cf5e7c5049af/kotlin-stdlib-1.6.10.jar (version 1.6)
/home/oliver/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/kotlin-stdlib-common/1.6.10/c118700e3a33c8a0d9adc920e9dec0831171925/kotlin-stdlib-common-1.6.10.jar (version 1.6)
w: Consider providing an explicit dependency on kotlin-reflect 1.6 to prevent strange errors
w: Some runtime JAR files in the classpath have an incompatible version. Consider removing them from the classpath
```

##### Notes

The error no longer occurs after removing the following dependency in `buildSrc/build.gradle.kts`:
```kotlin
    implementation("org.jetbrains.kotlinx:kover:0.5.0")
```

##### Related
* [KT-38010 – Invalid warning "Runtime JAR files in the classpath should have the same version." with `java-gradle-plugin`](https://youtrack.jetbrains.com/issue/KT-38010)
* [KT-41142 – Kotlin version conflict when using Kotlin Gradle plugins in pre\-compiled script plugin](https://youtrack.jetbrains.com/issue/KT-41142)
