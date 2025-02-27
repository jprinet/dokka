# Developer documentation

This module contains developer documentation which is published to GitHub pages: 
[kotlin.github.io/dokka](https://kotlin.github.io/dokka/).

It is built using the [gradle-mkdocs-plugin](https://github.com/xvik/gradle-mkdocs-plugin).

## Building

You can build the documentation locally:

```Bash
./gradlew :docs-developer:mkdocsBuild
```

The output directory is `build/mkdocs`.

### Docker

Alternatively, you can use Docker:

```bash
docker run --rm -it -p 8000:8000 -v ./docs-developer/src/doc:/docs squidfunk/mkdocs-material
```

This will build the docs and start a web server under [localhost:8000/Kotlin/dokka](http://localhost:8000/Kotlin/dokka/).

### Livereload server

Alternatively, you can run a livereload server that automatically rebuilds documentation on every change:

```Bash
./gradlew :docs-developer:mkdocsServe
```

By default, it is run under [localhost:3001](http://localhost:3001/), but you can change it in 
[mkdocs.yml](src/doc/mkdocs.yml) by setting the `dev_addr` option.

## Publishing

The documentation is published automatically for all changes in master and for every GitHub release.

See [gh-pages.yml](../.github/workflows/gh-pages-deploy-dev-docs.yml) workflow configuration for more details.
