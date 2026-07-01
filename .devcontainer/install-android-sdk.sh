#!/usr/bin/env bash
set -e

# Full Android SDK install for devcontainer (may take several minutes and several hundred MB)
SDK_ROOT=/usr/local/android-sdk
mkdir -p $SDK_ROOT/cmdline-tools
apt-get update
apt-get install -y wget unzip lib32stdc++6 lib32z1 || true

cd /tmp
# Command line tools - update URL if Google publishes newer version
CMDLINE_URL="https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip"

echo "Downloading Android commandline tools..."
wget -q "$CMDLINE_URL" -O cmdline.zip || true

if [ -f cmdline.zip ]; then
  echo "Extracting commandline tools..."
  unzip -o cmdline.zip -d cmdline || true
  mkdir -p $SDK_ROOT/cmdline-tools/latest
  mv cmdline/* $SDK_ROOT/cmdline-tools/latest/ || true
else
  echo "Warning: commandlinetools zip not downloaded. Skipping SDK install."
fi

export ANDROID_SDK_ROOT=$SDK_ROOT

if [ -x "$SDK_ROOT/cmdline-tools/latest/bin/sdkmanager" ]; then
  echo "Accepting licenses..."
  yes | $SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$SDK_ROOT --licenses || true

  echo "Installing platform-tools, platforms, build-tools (this may take a while)..."
  $SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$SDK_ROOT "platform-tools" "platforms;android-34" "build-tools;34.0.0" || true

  echo "Android SDK installed to $SDK_ROOT"
else
  echo "sdkmanager not found; devcontainer may need manual setup or updated commandlinetools URL."
fi

echo "Devcontainer setup complete (full Android CLI)."
