# Decodex Developer Guide <!-- omit in toc -->

## Acknowledgements

1. SE-EDU
    1. [AB3 Developer Guide Format](https://se-education.org/addressbook-level3/DeveloperGuide.html)
    2. [Setting up and getting started page and related links](https://se-education.org/addressbook-level3/SettingUp.html)
    3. [AB2 Code Structure](https://github.com/se-edu/addressbook-level2)

## Setting Up, Getting Started

### Setting up the on your computer

1. Forking and cloning
    1. **Fork** our repository.
    2. Then, **clone** the fork onto your computer.

> For convenience, our repository can be found [here](https://github.com/AY2122S1-CS2113T-T10-3/tp).
>
1. Editor
    1. We highly recommend using Intellij IDEA, which can be downloaded from [here](https://www.jetbrains.com/idea/).
    2. However, you may still use other editors that you prefer. Just take note that most of our set ups are centered around Intellij.
2. Configure JDK
    1. Follow the guide at *[[se-edu/guides] IDEA: Configuring the JDK](https://se-education.org/guides/tutorials/intellijJdk.html)* to ensure Intellij is configured to use **JDK 11**.
3. Importing project
    1. Follow the guide at *[[se-edu/guides] IDEA: Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)* to import the forked project into Intellij.
> Note: Importing a Gradle project is slightly different from importing a normal Java project.
>
1. Verifying setup
    1. Run the `decodex.Decodex.java` and try a few commands.
    2. [Run the tests](https://se-education.org/addressbook-level3/Testing.html) to ensure they all pass.

## Design

### Architecture

![Architecture diagram](images/architecture.png "Architecture diagram")

The ***Architecture Diagram*** given above explains the high-level design of Decodex.

Given below is a quick overview of the main components and how they interact with one another.

**Main Components of the architecture**

`Decodex` is responsible initialising the components at launch.

The rest of the program consists of 6 other components:

- `UI`: Handles user input and message output to the console
- `Logic`: Parses user input and executes commands
- `Recipe`: Manages a list of module sequences
- `Module`: Manages a set of encoding and decoding processes
- `Data`: Holds the data that is to be encoded or decoded
- `Storage`: Manages the reading and writing of data to disk

### UI Component

### Logic Component

### Data Component

### Module Component

### Recipe Component

### Storage Component

## Documentation, Logging, Testing, Configuration and Dev-Ops

## Appendix A: Product Scope

## Appendix B: User Stories

## Appendix C: Non Functional Requirements

## Appendix D: Glossary

## Appendix E: Instructions for Manual Testing
