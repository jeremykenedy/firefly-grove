# Installation

## Install or update from the latest release

Enable ADB debugging on the TV, connect it on the same network, and run:

```bash
adb connect TV_IP:5555
python3 install.py --serial TV_IP:5555
```

The guided installer downloads the latest release APK and matching SHA-256 file from this repository, verifies the checksum, and installs or updates Firefly Grove. It contacts GitHub only after the command is run. It does not collect or send device or usage data. The app itself has no network permission.

## Install a local build

```bash
bash build.sh
python3 install.py --serial TV_IP:5555 --apk build/firefly-grove.apk
```

After installation, select Firefly Grove in the TV's screensaver or ambient-mode settings. Menu names vary by device. The installer does not change the selected screensaver, power settings, or system update behavior.

## Remove

```bash
python3 install.py --serial TV_IP:5555 --uninstall
```

For unattended scripts use `--yes`; unattended removal also requires `--force`. Removing the app deletes its local settings. The installer does not change unrelated apps or device preferences.
