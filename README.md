## Description

This app converts Markdown syntax into corresponding HTML tags or ANSI escape codes. It recognizes bold, italic and monospaced text, and identifies paragraphs and reformatted sections of text, replacing them with the appropriate tags.

## How to assemble

Make sure you have JDK version 17 (or higher) installed. To begin with, you need to clone this repository using the command:
```
git clone https://github.com/thent1/mdToHtmlParser2.git
```


Go to the /src package
```
cd src
```

Build the project using the following command

```
./gradlew build
```

## How to run

An example of running a program with output to the console

```
./gradlew run --args='path_to_file.html --out path_to_output_file --format format_name'
```

```
--format flag is optional
--out flag is optinal
```

An example of running a program with output to file

```
./gradlew run --args='your_path.md --out example.html'
```

An example of running a program with ansi output to terminal

```
./gradlew run --args='your_path.md --format ansi'
```

CI check fail link: [https://github.com/thent1/mdToHtmlParser2/commit/6e8a0ae48031d5ee4231aeccfecefeb7a5abc374](https://github.com/thent1/mdToHtmlParser2/commit/6e8a0ae48031d5ee4231aeccfecefeb7a5abc374)

Revert commit link: [https://github.com/thent1/mdToHtmlParser2/commit/596f9424c4114e586241524dd2cadbae61c386b7](https://github.com/thent1/mdToHtmlParser2/commit/596f9424c4114e586241524dd2cadbae61c386b7)

## Conslusion

Unit tests have undeniably proven their value by uncovering bugs early in the development process, ensuring code quality, and facilitating easier refactoring. While they require an initial investment of time, the long-term benefits far outweigh the upfront costs, making them an indispensable tool in software development.
