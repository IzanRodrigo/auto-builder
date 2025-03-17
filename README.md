# AutoBuilder
<a href="https://central.sonatype.com/artifact/io.github.izanrodrigo/autobuilder-annotations" target="_blank"><img src="https://img.shields.io/maven-central/v/io.github.izanrodrigo/autobuilder-annotations?label=autobuilder-annotations"/><a/>
<a href="https://central.sonatype.com/artifact/io.github.izanrodrigo/autobuilder-processor" target="_blank"><img src="https://img.shields.io/maven-central/v/io.github.izanrodrigo/autobuilder-processor?label=autobuilder-processor"/><a/>

AutoBuilder is a symbol processor that generates builder classes from definition interfaces:

```kotlin
@AutoBuilder
interface User {
    val name: String
    val age: Int
}

// Later in the code...

val user = User {
    name = "John"
    age = 30
}
val userCopy = user.copy { age = 40 }
```

This library is inspired by [Kotlin data classes](https://kotlinlang.org/docs/data-classes.html), but without the binary compatibility issues that those may cause.
For more information about the motivation behind this library, see [this article](https://jakewharton.com/public-api-challenges-in-kotlin/) from [Jake Wharton](https://jakewharton.com/).

## Setup

```kotlin
dependencies {
    implementation("io.github.izanrodrigo:autobuilder-annotations:${latest_version}")
    ksp("io.github.izanrodrigo:autobuilder-processor:${latest_version}")
}
```

## Quick Start

### `@AutoBuilder`
The `@AutoBuilder` annotation is used to define the interface that will be used to generate the builder class:

```kotlin
@AutoBuilder
interface User {
    val name: String
    val age: Int
}
```

The code above will generate the following files:
- `User.builder.kt`
- `User.defaults.kt`

See [INTERNALS.md](https://github.com/IzanRodrigo/auto-builder/blob/main/INTERNALS.md) for more details.

In order to instantiate the `User`, we can use the appropriate method for `Kotlin` or `Java`:

**Kotlin**
```kotlin
val user = User {
    name = "John"
    age = 30
}
```

**Java**
```java
var user = UserBuilder()
    .setName("John")
    .setAge(30)
    .build();
```

That object can be copied or mutated using the `copy` method:

**Kotlin**
```kotlin
val userCopy = user.copy { age = 40 }
```

**Java** 

In `Java` the `copy` method is not available, but we can create a new builder reusing the previous user data:

```java
var userCopy = UserBuilder(user).setAge(40).build();
```

### `@DefaultValue`
By default, the generated builder will generate default values for some basic types.
However, for more complex types or custom default values, we can use the `@DefaultValue` annotation:

```kotlin
import java.util.UUID

@AutoBuilder
interface User {
    // Other properties...

    val id: UUID
        @DefaultValue get() = UUID.randomUUID()
}
```

NOTE: If you forget to add default values to non-null properties, there will be a compile error:

<img width="532" alt="auto-builder-compilation-error" src="https://github.com/user-attachments/assets/b2a0e5ce-e352-431e-ba84-9af6765d9445" />

## Inferred default values
When the default value can be inferred from the property type, the `@DefaultValue` annotation is not needed.
The default values for the following types are inferred:
- `String`, `CharSequence` ➞ `""`
- `Byte`, `Int`, `Number`, `Short`, ➞ `0`
- `Long` ➞ `0L`
- `Float` ➞ `0.0f`
- `Double` ➞ `0.0`
- `Char` ➞ `'0'`
- `Boolean` ➞ `false`
- `BigDecimal` ➞ `BigDecimal.ZERO`
- `BigInteger` ➞ `BigInteger.ZERO`
- `Array<T>` ➞ `emptyArray<T>()`
- `List<T>` ➞ `emptyList<T>()`

More types will be added in the future.

## License

    Copyright 2025 Izan Rodrigo Moreno Maroto.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

