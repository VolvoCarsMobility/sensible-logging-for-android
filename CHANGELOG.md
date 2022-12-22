# Change log

## [Unreleased]

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
