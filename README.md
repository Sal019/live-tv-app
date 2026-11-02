# Live TV — Premium Live TV Channels App

A premium Android app for **live TV channels only** — no movies, no dramas,
no web series, no upload, no other sections. Built with **Jetpack Compose**
(Kotlin + Material 3), the native Android UI stack for the smoothest,
most premium look.

## Screens
- **Home** — featured LIVE banner, live channel row, all-channel grid
- **Player** — 16:9 player, channel info, Up Next schedule, more channels below
- **Channels** — search + TV category filters (News / Sports / Entertainment / Kids)
- **Favourites** — saved channels (heart action to be wired to storage)

## Bottom navigation (TV-only)
Home · Channels · Favourites · Search

## Build the APK (GitHub Actions)
Push to `main` and the `Android APK` workflow builds a debug APK
automatically — download it from the workflow run's artifacts
(`live-tv-debug-apk`). No local Android Studio needed.

## Local build
```sh
gradle :app:assembleDebug
# APK: app/build/outputs/apk/debug/app-debug.apk
```

## Next step for real playback
The player screen is UI-ready. For production, wire a licensed channel
stream URL into `TvChannel.streamUrl` (see `data/Channels.kt`) with
Media3 ExoPlayer. The sample catalogue is UI demo data only.
