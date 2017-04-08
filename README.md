# l2l-BatchToBash-plugin

[![tests][tests]][tests-url]
[![coverage][cover]][cover-url]

L2L plugin for the conversion from the windows batch program to the linux bash program.

## Example of the Conversion from the Windows BATCH to the Linux BASH

### Input BATCH

```bat
@echo off
cd /d %~dp0

set ARG=%1

if %ARG%==1 (
    echo 1
) else if %ARG%==2 (
    echo 2
) else (
    echo 3
)

for %%i in (*.txt) do (
    echo %%i
)

pause
```

### Output BASH

```bash
#!/bin/bash
cd `dirname $0`

ARG=$1

if [ ${ARG} -eq 1 ]; then
    echo 1
elif [ ${ARG} -eq 2 ]; then
    echo 2
else
    echo 3
fi

for i in `ls *.txt`; do
    echo i
done

read -p \"Press [Enter] key to resume.\"
```

## Gradle settings

You add the following configuration to your `build.gradle` file. `${version}` is the release version.

```groovy
repositories {
    jcenter()
}

dependencies {
    compile "com.keidrun.l2l-BatchToBash-plugin:l2l-BatchToBash-plugin:${version}"
}
```

## Not implemented list

- `for` options
- `goto` and `:LABEL`
- `if` command
- `setlocal` and `endlocal`
- `set enabledelayedexpansion` and `!value!`
- `%~value`
- `shift`
- `errorlevel`
- `defined`
- `call`
- `tokens`
- Other DOS commands's details

[tests]:https://travis-ci.org/keidrun/l2l-BatchToBash-plugin.svg?branch=master
[tests-url]:https://travis-ci.org/keidrun/l2l-BatchToBash-plugin

[cover]:https://codecov.io/gh/keidrun/l2l-BatchToBash-plugin/branch/master/graph/badge.svg
[cover-url]:https://codecov.io/gh/keidrun/l2l-BatchToBash-plugin