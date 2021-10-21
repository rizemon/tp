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

1. Editor
    1. We highly recommend using Intellij IDEA, which can be downloaded from [here](https://www.jetbrains.com/idea/).
    2. However, you may still use other editors that you prefer. Just take note that most of our set ups are centered around Intellij.
2. Configure JDK
    1. Follow the guide at *[[se-edu/guides] IDEA: Configuring the JDK](https://se-education.org/guides/tutorials/intellijJdk.html)* to ensure Intellij is configured to use **JDK 11**.
3. Importing project
    1. Follow the guide at *[[se-edu/guides] IDEA: Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)* to import the forked project into Intellij.
> Note: Importing a Gradle project is slightly different from importing a normal Java project.
4. Verifying setup
    1. Run the `decodex.Decodex.java` and try a few commands.
    2. [Run the tests](https://se-education.org/addressbook-level3/Testing.html) to ensure they all pass.
    
### Before writing code

1. Configure the coding style
   1. If using IDEA, follow the guide [[se-edu/guides] IDEA: Configuring the code style](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to set up IDEA’s coding style to match ours.
> :bulb: Tip: Optionally, you can follow the guide [[se-edu/guides] Using Checkstyle](https://se-education.org/guides/tutorials/checkstyle.html) to find how to use the CheckStyle within IDEA e.g., to report problems as you write code.
2. Set up CI
   1. This project comes with a GitHub Actions config files (in `.github/workflows` folder). When GitHub detects those files, it will run the CI for your project automatically at each push to the `master` branch or to any PR. No set up required.
3. About our code structure
   1. OOP standardized
   2. Modularized.
      1. Any changes/additions to the current commands would simply require the changes within `Parser.java`.
      2. For any changes/additions to the modules, would simply require changes within the `src/main/java/decodex/modules` folder.
   3. This structure makes it easier for us as well as developers like you to maintain and further extend the capabilities of our application.
   
## Design

### Architecture

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
