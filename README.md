![header-image](header.png)

Sensible logging for Android aim to provide no-nonsense logging API that is easily extended. 
The goal of this library is *not* to be rich in features, but to provide a stable baseline for you to build on in your own projects.

> *"The members of the android guild at Volvo Car Mobility had different needs for logging. Some enjoy a nice and clean LogCat log while others like a more verbose approach to logging.*
>
>*With **Sensible logging for Android** we satisfied both of those needs."*

## Core concepts
The library consists of a few fundamental elements:

### Log class
The `Log` class is the main interaction point of this library. 
Inside it you will find the familiar log statement methods such as `Log.d()`

### Channels
`Log` directs the log statements to `Channel` implementations. Think of Channels as sinks you print your statements to.
Currently, the library includes `LogCatChannel`, `NotificationChannel` and `StandardOutChannel` (for unit tests).

#### Channel ids
A channel has a string identifier. You can optionally specify a channel ID in your log statement to also print to that channel.

As an example, you can log non-fatal exceptions and messages to your crash reporting service via a `CrashReportingChannel`.
Using that you can easily log to your crash reporting service from wherever in your code.
```kotlin
    Log.e("Something fatal occurred", exception, Channels.CrashReporting)
```

While the channel parameter is a string. We recommend organising your channels in one file. Like so:
```kotlin
typealias Channel = String

object Channels {
    const val LogCat: Channel = LogCatChannel.id
    const val CrashReporting: Channel = CrashReportingChannel.id
    const val Notification: Channel = NotificationChannel.id
}
```

### Filters
To control what a `Channel` should output you pass an instance of `Filter`. You can combine different filters by using the infix functions
`and` & `or`: for instance, `SimpleLogLevelFilter(Level.ERROR) and SimpleCategoryFilter(Categories.UI)` would only print messages with level *error* and above, and of the category *UI*.
If you don't care about filters, you can pass the `AllowAllFilter` to your `Channel`.

### Formatters
![formatter-screenshot](screenshot.png)

A `Channel` uses a `Formatter` to control the format of the output. The formatter in the screenshot above is called `LogCatFormatterExtended`. 
If you are directing your output to a file, we recommend using `SimpleFormatter`

### Categories
All log statement methods inside `Log` allow the passing of a log category. This can be used to order your statements into high level areas of interest.
Want to know what is going on with your backend? Direct your network client log statements to the `Network` category, and enable only that category.

Similar to channels, the category parameter is a string. Here we also recommend organising your categories in one file. Like so:

```kotlin
typealias Category = String

object Categories {
    const val Default: Category = "Default"
    const val Analytics: Category = "Analytics"
    const val Network: Category = "Network"
    const val Process: Category = "Process"
    const val Activity: Category = "Activity"
    const val Service: Category = "Service"
    const val Fragment: Category = "Fragment"
    const val RxJava: Category = "RxJava"
    const val Push: Category = "Push"
    const val UI: Category = "UI"
}
```

## Usage

### Step 1

```kotlin
if (BuildConfig.DEBUG) {
    // Sane defaults filter
    val logFilter = SimpleLogLevelFilter(Level.ERROR) or SimpleCategoryFilter(
        listOf(
            Categories.Default,
            Categories.Process,
            Categories.Service,
            // add more categories here
        )
    )

    // attach your printers to the Log framework
    Log.printers(LogCatChannel(LogCatFormatterExtended, logFilter))

    // optionally opt-in to logging out Process, Activity and Fragment lifecycle methods
    registerLifecycleLoggers(
        processCategory = Categories.Process,
        activityCategory = Categories.Activity,
        fragmentCategory = Categories.Fragment
    )
}
```

### Step 2
```kotlin
 // Log from your code
 // Passing "Default" as category is optional, if no category is passed default will be used 
 Log.d("Initialising the flux capacitor", Categories.Default)
```

### Step 3
Build your own Channels to solve your project needs

For example: you can log non-fatal exceptions to your crash reporting service via a `CrashReportingChannel`.
The `CrashReportingChannel` can be configured with a `Filter` that only pass the category `"CrashReportingService"`.
Using that you can easily log to this channel from wherever in your code.

Want persisted logs? Implement a `SQLiteChannel` using your favourite ORM library. You can then display those statements from
your debug UI. Or provide a shortcut from your app settings to dump the database to a text file that your users can email to you.

Want to control the log categories in runtime? Use the `SharedPreferencesCategoryFilter` with your `LogCatChannel` and enable updating of it from your debug UI.

## Download

```groovy
repositories {
  mavenCentral()
}

dependencies {
  implementation 'co.m.android:log:x.y.z'
}
```

## Requirements

 - `minSdk` is currently set to `16`
 - This library is currently dependant on `androidx.appcompat` and `androidx.lifecycle` libraries

## License

    Copyright 2022 Volvo Car Mobility AB

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
