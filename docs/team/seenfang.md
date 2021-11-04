# Project Portfolio Page - Decodex

## Overview

This is a student project for Software Engineering & Object-Oriented Programming (CS2113/T).

I am **Sean Phang**, one of the contributors of `Decodex`.

## Project Name: `Decodex`
`Decodex` is a Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly process data from one encoding format to another with extreme ease in the form of intuitive shorthand usage.

## Summary of Contributions
Below are my contributions to `Decodex`

- Code contributed: [RepoSense](https://nus-cs2113-ay2122s1.github.io/tp-dashboard/?search=SeenFang&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-25&tabOpen=true&tabType=authorship&tabAuthor=SeenFang&tabRepo=AY2122S1-CS2113T-T10-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false)

### Features/Functionalities

1. Added hexadecimal modules (`hexdecode` and `hexencode`)
    - Allows users to use these modules to encode/decode hexadecimal formats.
    - Related pull requests - [#36](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/36), [#37](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/37)

2. Added the `recipe` and the `recipeManager` classes
   - Implements the base structure for the recipe functionality that `Decodex` offers.
   - Related pull requests - [#156](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/156), [#156](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/157)

3. Added the commands for `recipe new`, `recipe delete` and `recipe list`.
   - Allows the user to create and delete recipes as well as list all currently available recipes
   - Related pull requests - [#197](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/197), [#198](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/198), [#222](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/222)

4. Added automatic saving of recipes to a file
   - Adds the functionality of saving a created recipe to a file whenever it is created/modified
   - Related pull requests - [#239](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/239)

5. Added the `exit` command
   - Allows the user to exit the program.
   - Related pull requests - [#39](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/39)

## Enhancements to Existing Features
- Added JUnit tests to features written - [#81](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/81)
- Added logging to modules selected - [#97](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/97)
- Added assertions to command - [#101](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/101)
- Shifted the running of modules in a recipe from the command to the `recipe` class - [#187](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/187)

## Documentation
- [User Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/UserGuide.html)
- [Developer Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/DeveloperGuide.html)
