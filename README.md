# AutoBuilder
![Annotations](https://img.shields.io/maven-central/v/io.github.izanrodrigo/autobuilder-annotations?label=autobuilder-annotations)
![Processor](https://img.shields.io/maven-central/v/io.github.izanrodrigo/autobuilder-processor?label=autobuilder-processor)

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
}
```

If we try to initialize a `User` object without setting the `id` property, we will get a runtime error:

```kotlin
val user = User {
    name = "John"
    age = 30
}
```

```
Exception in thread "main" java.lang.IllegalArgumentException: Required value was null.
	at app.izantech.plugin.autobuilder.sample.UserBuilder.build(User.builder.kt:83)
	at app.izantech.plugin.autobuilder.sample.ModelProcessorSampleKt.main(ModelProcessorSample.kt:176)
```

To prevent that, we can use the `@DefaultValue` annotation to provide a default value for the `id`. 

```kotlin
import java.util.UUID

@AutoBuilder
interface User {
    // Other properties...

    val id: UUID
        @DefaultValue get() = UUID.randomUUID()
}
```

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

