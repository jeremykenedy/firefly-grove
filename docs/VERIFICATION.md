# Verification

## Test scope

Host tests cover option mapping, random-selection bounds, settings validation, installer URL trust, release checksum verification, install/update/uninstall arguments, cancellation, and failure cleanup. Java line and branch coverage is enforced for the option resolver and settings validator. Python line and branch coverage is enforced for the standalone installer. Android framework rendering and DreamService lifecycle are exercised on an emulator or TV and are not represented as host-JVM coverage.

## Device evidence

Record device model, OS/API, logical and panel resolution, app version, settings, command, and screenshot path for each manual run. Verify the app appears in the system dream picker, the scene animates, D-pad focus works in settings, preview opens, and Back exits normally. Confirm system selection and enabled state before and after testing.

| Platform | Device | Result |
| --- | --- | --- |
| Android TV | `sdk_google_atv64_arm64` emulator, Android 12/API 31, 1920x1080 | Preview and settings were exercised on the running app. This does not verify physical device selection or idle activation. |
| Physical Fire TV | Not tested for this release | We are looking for a Fire TV owner to test installation, screensaver selection and activation, and remote settings, then report the model, Fire OS/API, resolution, and results in the [issue tracker](https://github.com/jeremykenedy/firefly-grove/issues). |
| Physical Google TV | Not tested for this release | We are looking for a Google TV owner to run the same checks and report the model, OS/API, resolution, and results in the [issue tracker](https://github.com/jeremykenedy/firefly-grove/issues). |

Screenshots in `docs/screenshots/` must come from the running app. Keep the original device capture; do not retouch a screenshot to imply runtime behavior. Emulator captures establish Android application rendering only, not vendor idle activation, sustained thermal behavior, or native 4K composition.


## Captures in this repository

`firefly-grove-preview.png` and `firefly-grove-settings.png` were captured from Firefly Grove running on the Android TV emulator (`sdk_google_atv64_arm64`, API 31, Android 12, 1920x1080). The preview was captured from the app preview activity after the animated scene was visible. These are emulator results, not a physical Fire TV runtime test.
