# Project Portfolio Page - Decodex

## Overview

This is a student project for Software Engineering & Object-Oriented Programming (CS2113/T).

I am **Sim Tian Boon**, one of the contributors of `Decodex`.

## Project Name: `Decodex`
`Decodex` is a Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly process data from one encoding format to another with extreme ease in the form of intuitive shorthand usage.

## Summary of Contributions
Below are my contributions to the `Decodex`

*Do note that the PR links shows just the more notable ones, since the other PRs may also consist of changes/adjustments to this functionality/features*

Features/Functionalities

1. Added `Parser` functionality to parse user inputs (For non-recipe commands)
   - Functionality - Performs the parsing of user input to generate the corresponding command to be executed.
   - Justification - Helps to parse the user input so that `Decodex` can run the correct command that users expect However, this is only limited to parsing of "Basic" Commands, and `recipe` related commands will have it's additional parsing done on `RecipeCommandParser`.
   - PR - [#53](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/53), [#72](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/72/files)

2. Added the ability to show current data
   - Feature - Shows the user the current data that they have.
      - eg. before base64encode, if the input is "hi", then `show` will show the user the current data that is "hi". But after base64encode is done, then the current data is "aGk="
   - Justification - Allows user to keep track of the current data they are working on, mainly for convenience and also credits to @alwinangys for the [suggestion](https://github.com/alwinangys/ped/issues/4).
   - PR - [#274](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/274)

3. Added Base64 Modules (Base64 Encoder and Decoder)
   - Feature - Allows users to use these module to encode/decode using base64 formats.
   - Justification - This is one of the more common encoding formats that appear in CTFs.
   - PR - [#35](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/35)

4. Added ability to load recipes from files on start up
   - Functionality - Performs the loading of recipe files from the recipe directory upon run of `Decodex`.
   - Justification - Allows users to load previously saved recipes, instead of having to create from scratch each time.
   - PR - [#208](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/208/)

5. Added ability to save recipe into file, and delete the recipe file
   - Functionality - Saves the recipe into a file in the format of `recipeName.txt` and stored in the `recipe/` directory. Do note that, this is more of an auto-save whenever changes are made to `recipe`. For the deletion, the recipe file is deleted when the user deletes the `recipe` from `Decodex`.
   - Justification - Allows users to auto-save their crafted recipes into a file which provides convenience for users. And the deletion of the recipe file also provides convenience over having to delete manually.
   - PR - [#208](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/208/)


## Enhancements to Existing Features
- Refactored and improved Coding Standards
   - `Storage` - Remove the magic literals [#280](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/280) and removed dead code [#251](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/251/files)
   - `Decodex` - Grouped a code fragment together and made the while loop less complex/more readable [#280](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/280)
- Assertion for `Parser` - [#99](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/99/files)
- Logging for `Decodex.class` - [#96](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/96)
- Wrote JUnit Test for Base64 Modules and Parser - [#35](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/35), [#72](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/72/), [#91](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/91)
- Attempted to standardize coding styles/documentation - [#47](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/47), [#147](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/147)
   - I learnt that this is probably one of the hardest thing to do since everyone has their own style of codes and the way they describe/explain things.
- JavaDocs for `Decodex` and `Storage` - Some notable ones ([#280](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/280))
- Bug Fixes - [#274](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/274), [#286](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/286)

## Documentation
- [User Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/UserGuide.html)
   - Added introduction - [#103](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/103)
   - Added table of contents - [#220](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/220/files)
   - Added basic commands documentation - [#246](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/246)
   - Added command summary documentation [#246](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/246)
- [Developer Guide](https://ay2122s1-cs2113t-t10-3.github.io/tp/DeveloperGuide.html)
   - Added Introduction, Purpose, Terminologies, Symbols - [#231](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/231)
   - Added "Before writing code" portion - [#180](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/180)
   - Added User Profiles, Value Proposition, User Stories - [#231](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/231)

## Community
- Non-trivial PR reviews - [#39](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/39), [#52](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/52), [#148](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/148) , [#156](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/156), [#166](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/166), [#183](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/183), [#190](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/190), [#192](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/192), [#194](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/194), [#196](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/196), [#275](https://github.com/AY2122S1-CS2113T-T10-3/tp/pull/275)
- Contributions to CS2113/T forum - [1 (not tP related)](https://github.com/nus-cs2113-AY2122S1/forum/issues/12), [2](https://github.com/nus-cs2113-AY2122S1/forum/issues/101), [3](https://github.com/nus-cs2113-AY2122S1/forum/issues/110)
- Bugs reported and other team reviews - [1](https://github.com/nus-cs2113-AY2122S1/tp/pull/32), [2](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/219), [3](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/210)
  , [4](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/212), [5](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/198), [6](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/195), [7](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/179), [8](https://github.com/AY2122S1-CS2113T-T09-2/tp/issues/184)
