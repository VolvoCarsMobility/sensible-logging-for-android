# Change log

## [Unreleased]
 
 - *BREAKING*: Channel now has two subtypes; `ReleaseChannel` and `DebugChannel`.
   - Motivation: `Meta` is captured using `Throwable.stacktrace`. This may impact performance negatively.
   - `ReleaseChannel` no longer have `Meta` passed to it's `print()` method and it can be used in Release builds.
   - `DebugChannel` are to be used in development builds.
   - `Meta` parameter in `Formatter.format()` method is now nullable.

Migrating: let your channel subtype inherit from either Release- or DebugChannel instead of Channel

## [1.1.0] - 2022-12-22

 - Adopted `LogCatFormatterExtended` to new LogCat window in Android Studio
 - Lifecycle logging is now in a separate package
   - BREAKING: the lifecycle extension moved to `sh.vcm.sensiblelogging.lifecycle` package
 - Removed `NotificationChannel`(you can still find it [here](https://github.com/VolvoCarsMobility/sensible-logging-for-android/blob/1fb3acc1e2288b13fbfdb83135c0b3d7b3ab0fdd/sensible-logging/src/main/java/sh/vcm/sensiblelogging/channel/NotificationChannel.kt))
 - Moved `Log.addChannels(...)` and `Log.removeChannels(...)` to `Log.Setup`
 - Easier setup of sane defaults using `Log.Setup.Configuration()` builder style object.

## [1.0.0] - 2022-11-24

First public release. See README.md for all an introduction to this library

### Changed

### Fixed
