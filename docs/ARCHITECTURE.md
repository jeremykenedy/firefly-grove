# Architecture

Firefly Grove is a dependency-free Android app with a native DreamService, a remote-friendly settings activity, and a small exported settings provider. Its habitat scene is drawn with hardware-accelerated Android Canvas. Fireflies have independent seeded paths, slow drift, pulsing amber or colored light, a soft radial bloom, and simple silhouette layers over a low-contrast woodland, meadow, or riverbank gradient.

The scene uses a capped array of 110 fireflies, posts redraws at roughly 30 frames per second while active, and removes callbacks when the dream stops. Density, speed, habitat, and color are resolved at dream start and remain stable for that session. The app has no runtime internet permission, third-party runtime dependency, background updater, or reporting service. The standalone installer makes a user-initiated request to GitHub to fetch the published APK and checksum.
