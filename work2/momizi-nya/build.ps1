#requires -Version 5.1
# Build without Maven: compile -> run tests -> package a runnable jar with dependencies.
# Usage: powershell -ExecutionPolicy Bypass -File build.ps1
$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

$javaHome = $env:JAVA_HOME
$javac = if ($javaHome) { Join-Path $javaHome 'bin\javac.exe' } else { 'javac' }
$java  = if ($javaHome) { Join-Path $javaHome 'bin\java.exe' } else { 'java' }
$jar   = if ($javaHome) { Join-Path $javaHome 'bin\jar.exe' } else { 'jar' }

Write-Host '[1/4] compile main sources'
New-Item -ItemType Directory -Force -Path out\classes, out\test-classes | Out-Null
$mainSources = Get-ChildItem -Recurse -Filter *.java src\main\java | ForEach-Object { $_.FullName }
& $javac -encoding UTF-8 -cp 'lib/*' -d out\classes $mainSources
if ($LASTEXITCODE -ne 0) { throw 'main compile failed' }

Write-Host '[2/4] compile tests'
$testSources = Get-ChildItem -Recurse -Filter *.java src\test\java | ForEach-Object { $_.FullName }
& $javac -encoding UTF-8 -cp 'out\classes;lib/*' -d out\test-classes $testSources
if ($LASTEXITCODE -ne 0) { throw 'test compile failed' }

Write-Host '[3/4] run tests'
$cp = 'out\classes;out\test-classes;lib/jackson-databind-2.17.2.jar;lib/jackson-core-2.17.2.jar;lib/jackson-annotations-2.17.2.jar'
& $java '-Dfile.encoding=UTF-8' -jar 'lib\junit-platform-console-standalone-1.10.2.jar' --class-path $cp --select-package dwasearch --details=summary
if ($LASTEXITCODE -ne 0) { throw 'tests failed' }

Write-Host '[4/4] package fat jar'
if (Test-Path build) { Remove-Item build -Recurse -Force }
New-Item -ItemType Directory -Force -Path build | Out-Null
Copy-Item out\classes\* build -Recurse -Force
Push-Location build
Get-ChildItem ..\lib\jackson-*.jar | ForEach-Object { & $jar xf $_.FullName }
Remove-Item META-INF\MANIFEST.MF -Force -ErrorAction SilentlyContinue
Pop-Location
'Main-Class: dwasearch.DWASearch' | Set-Content -Encoding ASCII build\MANIFEST.MF
& $jar cfm DWASearch.jar build\MANIFEST.MF -C build .
if ($LASTEXITCODE -ne 0) { throw 'jar failed' }

Write-Host ("done: DWASearch.jar ({0:N0} bytes)" -f (Get-Item DWASearch.jar).Length)
Write-Host 'run: java -jar DWASearch.jar sample_input.txt sample_output.txt'
