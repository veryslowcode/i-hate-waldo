# ihw

Simple file comparison tool.

>[!NOTE]
> Use `--recurse` argument when cloning: `git clone --recurse <URL>`

## Building

Use the gradle `fatJar` task to build and copy the `ihw.jar` file
(located at `app/build/libs/`) to the appropriate destination.

## Recommended Usage

The recommended usage is to alias ihw using bash or Powershell.

```ps1
function ihw($compare, $to) { &java -jar <PATH-TO-JAR> $compare $to }
```

```sh
ihw() { java -jar <PATH-TO-JAR> "$@" ;}
```

>[!NOTE]
> Piping to `more` is recommended for large files

## Arguments

```ps1
- `compare` The comparison file (the one displayed)
- `to`      The file compared against
```

## Flags

```ps1
- `h` Display help menu
- `n` Disable color output
```

