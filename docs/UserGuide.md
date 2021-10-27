# Decodex User Guide <!-- omit in toc -->

## Introduction

Decodex is a **Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly process data from one encoding format to another with extreme ease**. It also allows you to build recipes, sequences of data processing instructions, to speed up repetitive tasks. The intuitive interaction can help speed up a player's performance during CTFs and save time without having to manually code the tedious data transformations.

This guide serves to help you understand the usage of the program to encode and decode data, as well as create recipes to automate multiple encoding or decoding processes in sequence.

> :information_source: This user guide is tailored for CTF players who have basic understanding of information security concepts.

![carbon(6).png](images/carbon(6).png)

## Table of Contents <!-- omit in toc -->
- [Introduction](#introduction)
- [Terminologies](#terminologies)
- [Symbols](#symbols)
- [Quick Start](#quick-start)
- [List of Available Modules](#list-of-available-modules)
- [Features](#features)
  - [Understanding the Application's Prompt](#understanding-the-applications-prompt)
  - [Basic Commands](#basic-commands)
    - [Input of Data: `input`](#input-of-data-input)
    - [List Available Modules or Recipes: `list`](#list-available-modules-or-recipes-list)
    - [Selecting a Module or Recipe: `select`](#selecting-a-module-or-recipe-select)
    - [Resetting Data: `reset`](#resetting-data-reset)
    - [Exiting the Program: `exit`](#exiting-the-program-exit)
  - [Recipe Commands: `recipe`](#recipe-commands-recipe)
    - [Create a New Recipe: `recipe new`](#create-a-new-recipe-recipe-new)
    - [Select a Recipe for Editing: `recipe select`](#select-a-recipe-for-editing-recipe-select)
    - [List Modules in a Recipe: `recipe list`](#list-modules-in-a-recipe-recipe-list)
    - [Add a Module to a Recipe: `recipe push`](#add-a-module-to-a-recipe-recipe-push)
    - [Remove a Module from a Recipe: `recipe pop`](#remove-a-module-from-a-recipe-recipe-pop)
    - [Clear All Modules in a Recipe: `recipe reset`](#clear-all-modules-in-a-recipe-recipe-reset)
    - [Delete a Recipe: `recipe delete`](#delete-a-recipe-recipe-delete)
  - [Saving Recipe to File](#saving-recipe-to-file)
- [Command Summary](#command-summary)
- [FAQ](#faq)

## Terminologies

This section serves to help you better understand the terminologies used in this user guide.

| Data transformation         | The conversion of one data format to another.                                                                                                                 |
| --------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Application, Program        | Refers to the Decodex program. This two terms are used interchangeably in this User Guide.                                                                    |
| Encoding                    | Convert a message into a coded form.                                                                                                                          |
| Decoding                    | Convert a coded message into an intelligible form.                                                                                                            |
| Base64, Binary, Hexadecimal | Common types of data encoding standards.                                                                                                                      |
| Console                     | This refers to your command prompt window.                                                                                                                    |
| Argument                    | The additional information you provide to the program's command.                                                                                              |
| Module                      | A self-contained set of instructions to process your data into another form.                                                                                  |
| Recipe                      | Acts as a container for you to select your modules. When multiple modules are selected, this forms a "module chain". By default, you do not have any recipes. |

## Symbols

| Symbol               | Definition                                                                                                |
| -------------------- | --------------------------------------------------------------------------------------------------------- |
| :bulb:               | Represents a good tip for you.                                                                            |
| :exclamation:        | Represents something important that you should take note of.                                              |
| :information_source: | Represents additional information regarding commands/features for you to better understand how to use it. |
| :video_game:         | Represents something optional that you can try out, mostly for exploring or fun.                          |


## Quick Start
1. Ensure you have Java version `11` or above installed on your computer.
   1. If you haven't, you may download it [here](https://www.oracle.com/java/technologies/downloads/#java11-linux).
    > :exclamation: You should download the installation relative to your Operating System.
2. Next, download the latest `decodex.jar` [here](https://github.com/AY2122S1-CS2113T-T10-3/tp/releases).
   1. Simply click on the `decodex.jar` under "Assets" and the download should start.
3. After downloading, you can open up `command prompt`.
    > :bulb: To open command prompt, press `win + r` at the same time, then type and enter `cmd`.
4. Afterwards, you can run `decodex.jar` by typing in `java -jar decodex.jar` and Decodex's prompt should appear. Please also ensure that you are in the same directory as where you have downloaded `decodex.jar`.
   1. In the screenshot below, `decodex.jar` is located in the `Downloads` folder.
   ![carbon(23).png](images/carbon(23).png)
5. You can try out some of the basic commands below:
   1. `input I am groot`:  Inputs the text data `I am groot` into the program.
   2. `list`: Lists all available modules and recipes that you can use.
   3. `select module base64encode`: Selects and runs the base64-encoding module on the data.
   4. `reset`: Resets the changes made to data - resetting to its original data.
   5. `exit`: Exits the application.
    > :video_game: Side-Quest! You may try to find out what `NTEgMzEgNTIgNDcgNjUgMzAgNGUgNTQgNGQgNmEgNDUgNzggNGQgMzEgNTIgMzk=` is using our application! If you are new here, you can continue reading this guide to understand how to unravel this mysterious text!
6. For more information on Decodex's features, please refer to the Features Section.

## List of Available Modules
1. Base64
   1. `base64encode` - Encodes the data using base64 format
   2. `base64decode` - Decodes the data using base64 format
2. Hexadecimal
   1. `hexencode` - Converts the input string to hexadecimal bytes
   2. `hexdecode` - Converts a hexadecimal string back into its raw value
3. Binary
   1. `binencode` - Encodes the data using binary format
   2. `bindecode` - Decodes the data using binary format
4. Rotational Cipher
   1. `rotencode` - Rotates alphabetical characters by a specified integer offset

> :information_source: These are some of the more common encoding/decoding/cipher methods that can be found in CTF competitions.

## Features

> :information_source: Arguments enclosed in `<>` are **mandatory** arguments while arguments enclosed in `{}` are **optional** arguments. For example, `select <moduleName> {moduleArgument}` would mean that `moduleName` is mandatory while `moduleArgument` is optional.

> :information_source: Arguments must be entered in the **exact order and position** as specified in the commands' respective formats.

> :information_source: All commands and arguments are **case-sensitive**.

### Understanding the Application's Prompt

![carbon(17).png](images/carbon(17).png)

After running the program, it would display a prompt showing the name of the program `Decodex` , followed by the name of the recipe that is "currently being edited" (if any) in `[]`. This currently edited recipe will be the target for some `recipe` commands such as `recipe push`, `recipe pop` and `recipe reset`.

### Basic Commands

#### Input of Data: `input`

Stores the data to be processed by modules.

Format: `input <data>`

> :information_source: `data` will be treated as text.

Examples:

- `input SGVsbG9Xb3JsZA==` Enter base64-encoded data.
- <code>input  hello world</code> Enter plain text as data.

![carbon(1).png](images/carbon(1).png)

> :exclamation: Note that the program will consider all characters as `data` following the first space character in the input command. Observe the leading and trailing whitespaces in the result of the second command in the above screenshot.

> :exclamation: Non-printable characters (e.g. null characters) may not be correctly displayed and could potentially scramble the user interface.

#### List Available Modules or Recipes: `list`

Shows a list of all available modules.

Format: `list`

![carbon(8).png](images/carbon(8).png)

#### Selecting a Module or Recipe: `select`

Selects a module and processes the data accordingly. Subsequent selection of modules will process the transformed data output from the previous module.

Format: `select <moduleName>`

`moduleName` is the name of an available module supported by the program.

Examples:

- `select base64decode` Decode the base64 encoded data.
- `select hexencode` Encode the data into hexadecimal format.

![carbon(9).png](images/carbon(9).png)

> :exclamation: Non-printable characters (e.g. null characters) may not be correctly displayed and could potentially scramble the user interface.

#### Resetting Data: `reset`

Resets the transformed data back to the original input.

Format: `reset`

#### Exiting the Program: `exit`

Exit the program.

Format: `exit`

### Recipe Commands: `recipe`

#### Create a New Recipe: `recipe new`

#### Select a Recipe for Editing: `recipe select`

#### List Modules in a Recipe: `recipe list`

#### Add a Module to a Recipe: `recipe push`

#### Remove a Module from a Recipe: `recipe pop`

#### Clear All Modules in a Recipe: `recipe reset`

#### Delete a Recipe: `recipe delete`

### Saving Recipe to File

## Command Summary

| Action                   | Command Format        | Example Usage                                                |
| ------------------------ | --------------------- | ------------------------------------------------------------ |
| Enter input data         | `input <data>`        | `input SGVsbG9Xb3JsZA==`<br><code>input  hello world </code> |
| List available modules   | `list`                | `list`                                                       |
| Select processing module | `select <moduleName>` | `select base64decode`<br>`select hexencode`                  |
| Reset to original data   | `reset`               | `reset`                                                      |
| Exit program             | `exit`                | `exit`                                                       |

## FAQ

**Q:** How do I transfer my recipes to another computer?  
**A:** Install the app in the other computer and copy the recipe files into the recipe folder.
