# koin plugin for ktor-server

Slightly more customizable Koin plugin for Ktor v2 (beta)

## Usage

```kotlin
val koinPlugin = install(Koin) { // configure Koin plugin
    globalContext = false // default = true, use false in apps with multiple Koin contexts
    koinApp { // configure your KoinApplication
        koinKLogger()
        modules(koinKLoggingModule, configModule, /*...*/)
        createEagerInstances() // this needs to be called manually if required
    }
}

// final KoinApplication instance can be accessed like this
val koinApp = koinPlugin.koinApp
```

KoinApplication instance then can 

## License

```
Copyright 2022 aviadmini

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```