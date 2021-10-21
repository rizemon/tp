# Decodex User Guide <!-- omit in toc -->

## Introduction

Decodex is a **Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly transform data from one format to another with extreme ease**. The intuitive interaction can help speed up a player's performance during CTFs and save time without having to manually code the tedious data transformations.

> :information_source: This user guide is tailored for CTF players who have basic understanding of information security concepts.

![carbon(6).png](images/carbon(6).png)

## Table of Contents <!-- omit in toc -->
- [Introduction](#introduction)
- [Terminologies](#terminologies)
- [Features](#features)
  - [Input of data: `input`](#input-of-data-input)
  - [List available modules: `list`](#list-available-modules-list)
  - [Selection of module: `select`](#selection-of-module-select)
  - [Resetting of data: `reset`](#resetting-of-data-reset)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
- [Command Summary](#command-summary)

## Terminologies

This section serves to help the user better understand the terminologies used in this user guide.

| Terminology         | Definition                                                              |
| ------------------- | ----------------------------------------------------------------------- |
| Encoding            | Convert a message into a coded form.                                    |
| Decoding            | Convert a coded message into an intelligible form.                      |
| Base64, Hexadecimal | Common types of data encoding standards.                                |
| Module              | A self-contained set of instructions to process data into another form. |

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