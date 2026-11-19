$ErrorActionPreference = 'Stop'
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

if (-not (Get-Command mvn -ErrorAction SilentlyContinue)) {
    Write-Host 'mvn not found'
    Write-Host 'install maven first: winget install Apache.Maven'
    exit 1
}

& mvn -B clean package
if ($LASTEXITCODE -ne 0) { exit $LASTEXITCODE }

Write-Host 'done: target/DWASearch-1.0.0.jar'
Write-Host 'run: java -jar target/DWASearch-1.0.0.jar sample_input.txt output.txt'
