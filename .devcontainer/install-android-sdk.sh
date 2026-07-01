#!/usr/bin/env bash
set -e

SDK_ROOT=/usr/local/android-sdk
mkdir -p $SDK_ROOT/cmdline-tools
apt-get update
apt-get install -y wget unzip lib32stdc++6 lib32z1

cd /tmp
# Download Android command line tools (may need to update URL over time)
wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O cmdline.zip
unzip cmdline.zip -d cmdline
mkdir -p $SDK_ROOT/cmdline-tools/latest
mv cmdline/* $SDK_ROOT/cmdline-tools/latest/

export ANDROID_SDK_ROOT=$SDK_ROOT
yes | $SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$SDK_ROOT --licenses || true

# Install platform-tools and required platform/build-tools
$SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$SDK_ROOT "platform-tools" "platforms;android-34" "build-tools;34.0.0"

echo "Android SDK installed to $SDK_ROOT"
