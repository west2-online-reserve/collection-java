#!/usr/bin/env bash
# 打包后的真实命令行检查：跑一次 java -jar，确认能读输入、写输出、格式没错。
# 用法（在项目根目录，或任意目录）：bash tools/smoke-test.sh
set -euo pipefail

cd "$(dirname "$0")/.."

echo "[1/3] 构建"
mvn -q package

work="$(mktemp -d)"
trap 'rm -rf "$work"' EXIT

echo "[2/3] 运行 java -jar"
printf 'players\nresult men 10m platform\nbadcmd\nresult men 10m     synchronised\n' > "$work/in.txt"
java -jar target/DWASearch.jar "$work/in.txt" "$work/out.txt"

echo "[3/3] 检查输出"
grep -q '^Full Name:COLE Ellie$' "$work/out.txt"        || { echo "缺少 COLE Ellie"; exit 1; }
grep -q '^Full Name:BAI Yuming$'  "$work/out.txt"        || { echo "缺少 BAI Yuming"; exit 1; }
grep -q '^Error$'                 "$work/out.txt"        || { echo "缺少 Error"; exit 1; }
grep -q '^N/A$'                   "$work/out.txt"        || { echo "缺少 N/A"; exit 1; }
if grep -q $'\r' "$work/out.txt"; then
  echo "输出里有 CR，要求只使用 \\n"
  exit 1
fi

records=$(grep -c '^Full Name:' "$work/out.txt")
lines=$(wc -l < "$work/out.txt" | tr -d ' ')
echo "通过：$records 条选手/成绩记录，共 $lines 行，没有 CR"
