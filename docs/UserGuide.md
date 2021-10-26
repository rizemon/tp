# Decodex User Guide <!-- omit in toc -->

## Introduction

Decodex is a **Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly transform data from one format to another with extreme ease**. The intuitive interaction can help speed up a player's performance during CTFs and save time without having to manually code the tedious data transformations.

This guides serves to help you understand the usage of the program for quick and easy data manipulation.

> :information_source: This user guide is tailored for CTF players who have basic understanding of information security concepts.

![carbon(6).png](images/carbon(6).png)

## Table of Contents <!-- omit in toc -->
- [Quick Start](#quick-start)
- [Terminologies](#terminologies)
- [List of Available Modules](#list-of-available-modules)
- [Features](#features)
  - [Understanding the Application's Prompt](#understanding-the-application-prompt)
  - [Basic Commands](#basic-commands)
    - [Input of data: `input`](#input-of-data-input)
    - [List available modules: `list`](#list-available-modules-list)
    - [Selection of module: `select`](#selection-of-module-select)
    - [Resetting of data: `reset`](#resetting-of-data-reset)
    - [Exiting the program: `exit`](#exiting-the-program-exit)
  - [Recipe Commands](#recipe-commands)
    - [Create new recipe: `recipe`](#create-new-recipe)
    - [Select recipe for editing: `recipe select`](#selecting-recipe-for-editing)
    - [List modules in recipe: `recipe list`](#list-modules-in-recipe)
    - [Add module to recipe: `recipe push`](#add-module-to-recipe)
    - [Remove module from recipe: `recipe pop`](#remove-module-from-recipe)
    - [Clear all modules in recipe: `recipe reset`](#clear-all-modules-in-recipe)
    - [Delete recipe: `recipe delete`](#delete-recipe)
  - [Saving Recipes to file `[coming in v2.0]`]()
  - [Reading/writing data from/to file `[coming in v2.0]`]()
- [Command Summary](#command-summary)
- [FAQ]()

## Terminologies

This section serves to help the user better understand the terminologies used in this user guide.

| Terminology         | Definition                                                              |
| ------------------- | ----------------------------------------------------------------------- |
| Encoding            | Convert a message into a coded form.                                    |
| Decoding            | Convert a coded message into an intelligible form.                      |
| Base64, Hexadecimal | Common types of data encoding standards.                                |
| Module              | A self-contained set of instructions to process data into another form. |

## Quick Start
1. Ensure you have Java version 11 or above installed on your computer.
   1. If you haven't, you may download it [here](https://www.oracle.com/java/technologies/downloads/#java11-linux)
   > ❗ You should download the installation relative to your Operating System.
2. Next, download the latest `decodex.jar` [here](https://github.com/AY2122S1-CS2113T-T10-3/tp/releases)
   1. Simply click on the `decodex.jar` under "Assets" and the download should start.
3. After downloading, you can open up `command prompt`.
   > 💡To open command prompt, press `win + r` at the same time, then type and enter `cmd`.
4. Then, you can now run the `decodex.jar` by typing in `java -jar decodex.jar`  and the Decodex's prompt should appear. Also, please ensure that you are at the directory where `decodex.jar` is.
   1. In the screenshot below, the `decodex.jar` is located in the `Downloads` folder.
   ![carbon(23).png](images/carbon(23).png)
5. Here on, you can try out some of the basic commands below:
   1. `input I am groot`: Inputs into the program the data ("I am groot").
   2. `list`: Lists all available modules and recipes that you can use.
   3. `select module base64encode`: Selects and runs the base64-encoding module on the data.
   4. `reset`: Resets the changes made to data - resetting to its original data.
   5. `exit`: Exits the application.
   > 🎮 Side-Quest! You may try to find out what `NTEgMzEgNTIgNDcgNjUgMzAgNGUgNTQgNGQgNmEgNDUgNzggNGQgMzEgNTIgMzk=` is using our application! If you are new here, you can continue reading this guide to understand how to unravel this mysterious text!
6. For more information on Decodex's features, please refer to the Features Section.

## Features

### Input of data: `input`

Stores the data to be processed by modules.

Format: `input <data>`

> :information_source: `data` will be treated as text.

Examples:

- `input SGVsbG9Xb3JsZA==` Enter base64-encoded data.
- <code>input  hello world </code> Enter plain text as data.

![carbon(1).png](images/carbon(1).png)

> :exclamation: Note that the program will consider all characters as `data` following the first space character in the input command. Observe the leading and trailing whitespaces in the result of the second command in the above screenshot.

> :exclamation: Non-printable characters (e.g. null characters) may not be correctly displayed and could potentially scramble the user interface.

### List available modules: `list`

Shows a list of all available modules.

Format: `list`

![carbon(8).png](images/carbon(8).png)

### Selection of module: `select`

Selects a module and processes the data accordingly. Subsequent selection of modules will process the transformed data output from the previous module.

Format: `select <moduleName>`

`moduleName` is the name of an available module supported by the program.

Examples:

- `select base64decode` Decode the base64 encoded data.
- `select hexencode` Encode the data into hexadecimal format.

![carbon(9).png](images/carbon(9).png)

> :exclamation: Non-printable characters (e.g. null characters) may not be correctly displayed and could potentially scramble the user interface.

### Resetting of data: `reset`

Resets the transformed data back to the original input.

Format: `reset`

### Exiting the program: `exit`

Exit the program.

Format: `exit`

## Command Summary

| Action                   | Command Format        | Example Usage                                                |
| ------------------------ | --------------------- | ------------------------------------------------------------ |
| Enter input data         | `input <data>`        | `input SGVsbG9Xb3JsZA==`<br><code>input  hello world </code> |
| List available modules   | `list`                | `list`                                                       |
| Select processing module | `select <moduleName>` | `select base64decode`<br>`select hexencode`                  |
| Reset to original data   | `reset`               | `reset`                                                      |
| Exit program             | `exit`                | `exit`                                                       |