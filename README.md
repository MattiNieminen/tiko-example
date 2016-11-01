# Tiko-example

Simple full-stack Clojure example project.

## Where are the slides?
[Here]
(https://docs.google.com/presentation/d/1ncfUwq0dvhjPWmyrwDvP2fupv9Vzq7RMKlV_f1ij4HI/edit?usp=sharing)

## Development

Open terminal, go to the working directory and run

```bash
lein develop
```

Then open your IDE, and start the repl from there. Use ```(reset)```
to start the server, and reset it when you make changes.

## Deployment

Open terminal, go to the working directory and run

```bash
lein production
```

This will produce and uberjar, which you run with command

```bash
java -jar target/uberjar/tiko-example-0.1.0-SNAPSHOT-standalone.jar
```

## License

Copyright © 2016 Matti Nieminen

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
