# Configuration

Open Firefly Grove from the TV launcher and use the remote to choose habitat, density, flight speed, and glow color. Settings are stored locally and take effect when the screensaver starts. Each choice supports Random. The separate randomize-all control selects new values for all four options at every start.

| Setting | Choices | Default |
| --- | --- | --- |
| Habitat | Woodland, Meadow, Riverbank, Random | Woodland |
| Firefly density | A few, A handful, Many, A ton, Schools, Random | A handful |
| Flight speed | Slow, Natural, Quick, Random | Natural |
| Glow color | Amber, Lime, Aqua, Random | Amber |
| Randomize all | On, Off | Off |

The exported provider is `com.jeremykenedy.fireflygrove.settings`:

- `content://com.jeremykenedy.fireflygrove.settings/schema` describes setting keys, labels, defaults, choices, and random support.
- `content://com.jeremykenedy.fireflygrove.settings/settings` returns the saved key-value pairs.
- Update a setting by writing `key` and `value` to the settings URI. Invalid values are rejected.

The provider lets a host app discover these options. The current Fire TV UI picker does not yet include a generic editor for third-party screensaver settings.
