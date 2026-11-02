# Live TV — Premium Live TV Channels App

A premium Android app for **live TV channels only** — no movies, no dramas,
no web series, no upload, no other sections. Built with **Jetpack Compose**
(Kotlin + Material 3) and **Google's Material Symbols Extended icon pack**
(the best official SVG icon library for Android), with a dynamic dark
gradient theme.

App name: **Live TV** (an original name — Toffee belongs to another company,
so it is not copied).

## Screens
- **Home** — featured LIVE banner, live channel row, all-channel grid
- **Player** — real demo video playback (Media3 ExoPlayer), channel info,
  Up Next schedule, more channels below
- **Channels** — search + TV category filters (News / Sports / Entertainment / Kids)
- **Favourites** — demo favourite channels for now

## Demo data (temporary)
Channels and videos are **demo only** — sample channel names and Google's
public sample videos. Real licensed channels and streams replace them in a
later update, exactly as planned.

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

## Next step for real channels
The player already plays video via Media3 ExoPlayer. For production, replace
the demo stream URLs in `data/Channels.kt` with licensed channel stream URLs.
