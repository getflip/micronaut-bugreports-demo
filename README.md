# micronaut-bugreports-demo
Repository to hold the demo projects for bugreports

## How to add a new demo for a bugreport

1. Checkout a new branch with a fitting name for you problem
2. Go to https://micronaut.io/launch/ to generate a project from scratch. Set the version, features,... as close as possible to the enviroment you want to reproduce the bug in. The CLI also can be used: https://docs.micronaut.io/latest/guide/index.html#creatingServer
3. Use the previously generated branch to reproduce the bug. Then push the changes.
4. Open a bug report in the corresponding [micronaut github repo](https://github.com/micronaut-projects) and link the newly created branch.
