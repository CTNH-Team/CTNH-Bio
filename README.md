# CTNH-Bio

[![Build](https://github.com/CTNH-Team/CTNH-Bio/actions/workflows/build.yml/badge.svg?branch=dev)](https://github.com/CTNH-Team/CTNH-Bio/actions/workflows/build.yml)

Core mod of the biological part for the modpack Create: New Horizon (CTNH).

## Building

This mod should be built under [CTNH-Team/CTNH-Modules](https://github.com/CTNH-Team/CTNH-Modules) repository using Gradle.

```shell
$ git clone --recursive https://github.com/CTNH-Team/CTNH-Modules.git 
$ cd CTNH-Modules   # And you may need to update the submodules manually
$ ./gradlew :modules:CTNH-Bio:build            # To build the mod .jar
$ ./gradlew :modules:CTNH-Bio:runData          # To generate data
$ ./gradlew :modules:CTNH-Bio:spotlessCheck    # To check code formatting
$ ...
```

Nightly builds are available on the [Actions](https://github.com/CTNH-Team/CTNH-Bio/actions/workflows/build.yml) page.

## License

All code is licensed under the [GNU LGPL v3 License](https://www.gnu.org/licenses/lgpl-3.0.en.html).

All artwork (images, textures, models, animations, etc.) is licensed under the [Creative Commons Attribution-NonCommercial 4.0 International License](http://creativecommons.org/licenses/by-nc/4.0/), unless stated otherwise.