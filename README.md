## pkg

A universal package manager for linux. It wraps the functionality of the distro's
package manager and provides command for common package management operations.


#### Usage

```
usage: pkg [COMMAND] [PACKAGES]

commands:
  help        display this help message and exit.
  search      search for packages.
  install     install new packages.
  update      fetch and install package updates.
  remove      remove packages.
```

### Compilation commands

```bash
# install dependencies.
$ mvn install

# compile.
$ mvn clean compile assembly:single

# run jar.
$ java -jar ./target/pkg-1.0-jar-with-dependencies.jar
```