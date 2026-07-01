#!/usr/bin/env bash
set -e

SDK_ROOT=/usr/local/android-sdk
mkdir -p $SDK_ROOT/cmdline-tools
apt-get update
apt-get install -y wget unzip lib32stdc++6 lib32z1 || true

cd /tmp
# Lightweight: download Android command line tools and install only platform-tools (smaller)
# Update the URL if Google publishes a newer commandlinetools package
CMDLINE_URL="https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip"

if [ ! -f cmdline.zip ]; then
  wget "$CMDLINE_URL" -O cmdline.zip || true
fi

if [ -f cmdline.zip ]; then
  unzip -o cmdline.zip -d cmdline || true
  mkdir -p $SDK_ROOT/cmdline-tools/latest
  mv cmdline/* $SDK_ROOT/cmdline-tools/latest/ || true
else
  echo "Warning: commandlinetools zip not downloaded. Skipping SDK install."
fi

export ANDROID_SDK_ROOT=$SDK_ROOT
if [ -x "$SDK_ROOT/cmdline-tools/latest/bin/sdkmanager" ]; then
  yes | $SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$SDK_ROOT --licenses || true
  # Install only platform-tools (smaller) to enable basic adb/adb functionality and build tooling
  $SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$SDK_ROOT "platform-tools" || true
  echo "Installed platform-tools to $SDK_ROOT"
else
  echo "sdkmanager not found; devcontainer may need manual setup or updated commandlinetools URL."
fi

echo "Devcontainer setup complete (minimal)."