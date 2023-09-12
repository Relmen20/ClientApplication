mkdir -p ./out/
find ./src/ -type f -name "*.java" | xargs javac -cp ./src/ -d ./out/
java -cp ./out/ Main 12312
