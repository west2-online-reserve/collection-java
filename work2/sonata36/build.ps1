$ErrorActionPreference = 'Stop'
New-Item -ItemType Directory -Force '.build/app', '.build/tools', '.build/tests', '.build/app/data', '.build/tmp' | Out-Null
javac -encoding UTF-8 -d .build/tools tools/GenerateData.java
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
java -cp .build/tools GenerateData
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
javac -encoding UTF-8 -d .build/app src/DWASearch.java src/CoreModule.java src/Lib.java
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
Copy-Item src/data/players.tsv, src/data/results.tsv .build/app/data/ -Force
javac -encoding UTF-8 -d .build/tests src/DWASearch.java src/CoreModule.java src/Lib.java tests/DWASearchTest.java tests/Benchmark.java
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
java -cp '.build/tests;.build/app' DWASearchTest
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
$jarTmp = (Resolve-Path '.build/tmp').Path
jar "-J-Djava.io.tmpdir=$jarTmp" --create --file DWASearch.jar --main-class DWASearch -C .build/app .
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }
Write-Output 'Built DWASearch.jar'
