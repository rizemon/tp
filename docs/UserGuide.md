# Decodex User Guide <!-- omit in toc -->

# Introduction

Decodex is a **Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly transform data from one format to another with extreme ease**. The intuitive interaction can help speed up a player's performance during CTFs and save time without having to manually code the tedious data transformations.

This user guide is tailored for CTF players who have basic understanding of information security concepts.

> ❗ This user guide is tailored for CTF players who have basic understanding of information security concepts.

![carbon(6).png](images/carbon(6).png)

# Table of Contents <!-- omit in toc -->
- [Introduction](#introduction)
- [Terminologies](#terminologies)
- [Features](#features)
  - [List available modules: `list`](#list-available-modules-list)
  - [Selection of module: `select`](#selection-of-module-select)
  - [Exiting the program: `exit`](#exiting-the-program-exit)
- [Command Summary](#command-summary)

# Terminologies

This section serves to help the user better understand the terminologies used in this user guide.

| Terminology | Definition |
| ----------- | ---------- |
|             |            |
|             |            |


# Features

## List available modules: `list`

Shows a list of all available modules.

Format: `list`

![carbon(8).png](images/carbon(8).png)

## Selection of module: `select`

Selects a module and processes the data accordingly. Subsequent selection of modules will process the transformed data output from the previous module.

Format: `select <moduleName>`

`moduleName` is the name of an available module supported by the program.

Examples:

- `select base64decode` Decode the base64 encoded data.
- `select hexencode` Encode the data into hexadecimal format.

![carbon(9).png](images/carbon(9).png)

## Exiting the program: `exit`

Exit the program.

Format: `exit`

# Command Summary

| Action                   | Command Format        | Example Usage                                                      |
|--------------------------|-----------------------|--------------------------------------------------------------------|
| Enter input data         | `input <data>`        | `input SGVsbG9Xb3JsZA==`<br><code>input  hello world </code> |
| List available modules   | `list`                | `list`                                                             |
| Select processing module | `select <moduleName>` | `select base64decode`<br>`select hexencode`                        |
| Reset to original data   | `reset`               | `reset`                                                            |
| Exit program             | `exit`                | `exit`                                                             |