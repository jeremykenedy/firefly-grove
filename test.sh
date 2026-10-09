#!/usr/bin/env bash
set -euo pipefail
ROOT="$(cd "$(dirname "$0")" && pwd)"
OUT="$ROOT/build/tests"
mkdir -p "$OUT"
javac -source 8 -target 8 -d "$OUT" \
  "$ROOT/src/com/jeremykenedy/fireflygrove/FireflyOptions.java" \
  "$ROOT/src/com/jeremykenedy/fireflygrove/SettingsValues.java" \
  "$ROOT/tests/FireflyOptionsTest.java"
java -ea -cp "$OUT" com.jeremykenedy.fireflygrove.FireflyOptionsTest
python3 -m unittest -v tests.test_installer
