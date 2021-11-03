# Project Portfolio Page - Decodex

## Overview

This is a student project for Software Engineering & Object-Oriented Programming (CS2113/T).

I am Sim Tian Boon, one of the contributors in this `Decodex` project.

## Project Name: `Decodex`
`Decodex` is a Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly process data from one encoding format to another with extreme ease in the form of intuitive shorthand usage.

## Summary of Contributions
Below are my contributions to the `Decodex`

Features/Functionalities

1. Added ability to parse user inputs (For non-recipe commands)
    - Functionality - Performs the parsing of user input to generate the corresponding command to be executed.
    - Justification - Helps to parse the user input so that `Decodex` runs the correct command that users expect However, this is only limited to parsing of "Basic" Commands, and `recipe` related commands are passed to `RecipeCommandParser`.
    - PR - [222](3333)

2. Added the ability to show current data
    - Feature - Shows the user the current data that they have.
      - eg. before base64encode, if the input is "hi", then `show` will show the user the current data that is "hi". But after base64encode is done, then the current data is "aGk="
    - Justification - Allows user to keep track of the current data they are working on, mainly for convenience and also credits to @alwinangys for the [suggestion](https://github.com/alwinangys/ped/issues/4).
    - PR:

3. Added the Base64 Modules
    - Feature - Allows users to use the module to encode/decode using base64 formats.
    - Justification - This is one of the more common encoding formats that appear in CTFs.
    - PR:

4. Added ability to load recipes on start up
    - Functionality - Performs the loading of recipe files from the recipe directory upon run of `Decodex`.
    - Justification
