# Continuous integration

GitHub Actions runs the Android build, host tests, Java and Python coverage gates, style check, documentation validation, privacy scan, and dependency review. Workflow actions are pinned to full commit SHAs. Public CI uses an unsigned or temporary-key artifact; release signing credentials remain outside the repository and CI.

No SonarCloud, CodeFactor, Codacy, Aikido, or Scrutinizer integration is configured. Badges are limited to the release, repository, license, and workflows that run here.
