# Developer Guide <!-- omit in toc -->

## Introduction

Decodex is a **Command Line Interface (CLI) application for Capture-The-Flag (CTF) players to quickly transform data from one format to another with extreme ease**. The intuitive interaction can help speed up a player's performance during CTFs and save time without having to manually code the tedious data transformations.

### Purpose of This Guide

The purpose of this guide is to provide more information on our application, Decodex, such as the overall architecture, implementation and design rationales to developers who wish to contribute and enhance Decodex to it's fullest potential. As of the release of this developer guide, it is written for Decodex V2.0.

> :information_source: This guide may also serve as a start for software testers to find bugs and possibly edge cases within our applications.

### Developer Guide Usage

This developer guide is made for developers who wish to further understand and/or develop **Decodex**.
This guide includes the setup instructions, design, implementation, testing, product scope, and other sections to give developers a better understanding of the application.

## Table of Contents <!-- omit in toc -->
- [Introduction](#introduction)
  - [Purpose of This Guide](#purpose-of-this-guide)
  - [Developer Guide Usage](#developer-guide-usage)
- [Acknowledgements](#acknowledgements)
  - [Terminologies](#terminologies)
  - [Symbols](#symbols)
- [Getting Started](#getting-started)
  - [Setting Up the Project](#setting-up-the-project)
  - [Additional Considerations](#additional-considerations)
- [Design](#design)
  - [Architecture](#architecture)
  - [UI Component](#ui-component)
  - [Logic Component](#logic-component)
  - [Data Component](#data-component)
  - [Module Component](#module-component)
  - [Recipe Component](#recipe-component)
  - [Storage Component](#storage-component)
- [Implementation](#implementation)
  - [Decodex Initialisation](#decodex-initialisation)
  - [Decodex Main Logic](#decodex-main-logic)
  - [Parser - Basic Command Logic](#parser---basic-command-logic)
  - [Parser - Recipe Command Logic](#parser---recipe-command-logic)
  - [Modules](#modules)
    - [Implemented Modules:](#implemented-modules)
  - [List of Commands](#list-of-commands)
    - [InputCommand](#inputcommand)
    - [ExitCommand](#exitcommand)
    - [ResetCommand](#resetcommand)
    - [SelectCommand](#selectcommand)
      - [SelectCommand (Module)](#selectcommand-module)
      - [SelectCommand (Recipe)](#selectcommand-recipe)
    - [ListCommand](#listcommand)
  - [Recipe Commands](#recipe-commands)
    - [RecipeNewCommand](#recipenewcommand)
    - [RecipeSelectCommand](#recipeselectcommand)
    - [RecipeListCommand](#recipelistcommand)
    - [RecipePushCommand](#recipepushcommand)
    - [RecipePopCommand](#recipepopcommand)
    - [RecipeResetCommand](#reciperesetcommand)
    - [RecipeDeleteCommand](#recipedeletecommand)
- [Appendix A: Product Scope](#appendix-a-product-scope)
  - [Target User Profile](#target-user-profile)
  - [Value Proposition](#value-proposition)
- [Appendix B: User Stories](#appendix-b-user-stories)
- [Appendix C: Non-Functional Requirements](#appendix-c-non-functional-requirements)
- [Appendix D: Glossary](#appendix-d-glossary)
- [Appendix E: Instructions for Manual Testing](#appendix-e-instructions-for-manual-testing)

## Acknowledgements

1. SE-EDU
    1. [AB3 Developer Guide Format](https://se-education.org/addressbook-level3/DeveloperGuide.html)
    2. [AB3 User Guide Format](https://se-education.org/addressbook-level3/UserGuide.html)
    3. [AB3 Setting up and getting started page and related links](https://se-education.org/addressbook-level3/SettingUp.html)
    4. [AB3 Appendix: Requirements](https://se-education.org/addressbook-level3/DeveloperGuide.html#appendix-requirements)
    5. [AB2 Code Structure](https://github.com/se-edu/addressbook-level2)
2. [AY2021S2-CS2113-T10-1's Developer Guide](https://ay2021s2-cs2113-t10-1.github.io/tp/DeveloperGuide.html)

### Terminologies

| Terminology                 | Definition                                                                                                                                                    |
| --------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Data transformation         | The conversion of one data format to another.                                                                                                                 |
| Application, Program        | Refer to the `Decodex` program. This two terms are used interchangeably in this User Guide.                                                                   |
| Encoding                    | Convert a message into a coded form.                                                                                                                          |
| Decoding                    | Convert a coded message into an intelligible form                                                                                                             |
| Base64, Binary, Hexadecimal | Common types of data encoding standards.                                                                                                                      |
| Console                     | This refers to your command prompt window.                                                                                                                    |
| Argument                    | The additional information you provide to the program's command.                                                                                              |
| Module                      | A self-contained set of instructions to process your data into another form.                                                                                  |
| Recipe                      | Acts as a container for you to select your modules. When multiple modules are selected, this forms a "module chain". By default, you do not have any recipes. |

### Symbols

| Name                 | Definition                                                                                            |
| -------------------- | ----------------------------------------------------------------------------------------------------- |
| :bulb:               | Represents a good tip for you.                                                                        |
| :exclamation:        | Represents something important that you should take note of.                                          |
| :information_source: | Represents additional information regarding commands/features for you better understand how it works. |
| :pen:                | Represents our rationale behind the design/implementation.                                            |

## Getting Started

### Setting Up the Project

1. Forking and cloning
    1. **Fork** our repository.
    2. Then, **clone** the fork onto your computer.

    > 💡 For convenience, our repository can be found [here](https://github.com/AY2122S1-CS2113T-T10-3/tp).

2. Editor
    1. We highly recommend using Intellij IDEA, which can be downloaded from [here](https://www.jetbrains.com/idea/).
    2. However, you may still use other editors that you prefer. Just take note that most of our set ups are centered around Intellij.
3. Configure JDK
    1. Follow the guide at *[[se-edu/guides] IDEA: Configuring the JDK](https://se-education.org/guides/tutorials/intellijJdk.html)* to ensure Intellij is configured to use **JDK 11**, since our application runs on `Java 11`.
4. Importing project
    1. Follow the guide at *[[se-edu/guides] IDEA: Importing a Gradle project](https://se-education.org/guides/tutorials/intellijImportGradleProject.html)* to import the forked project into Intellij.

    > ❗Note: Importing a Gradle project is slightly different from importing a normal Java project.

5. Verifying setup
    1. Run the `decodex.Decodex.java` and try a few commands.
    2. [Run the tests](https://se-education.org/addressbook-level3/Testing.html) to ensure they all pass.


### Additional Considerations

1. Configure the coding style
    1. If using IDEA, follow the guide [[se-edu/guides] IDEA: Configuring the code style](https://se-education.org/guides/tutorials/intellijCodeStyle.html) to set up IDEA’s coding style to match ours.
   > 💡 Tip: Optionally, you can follow the guide [[se-edu/guides] Using Checkstyle](https://se-education.org/guides/tutorials/checkstyle.html) to find how to use the CheckStyle within IDEA e.g., to report problems as you write code.
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

The ***Architecture Diagram*** given below shows the high-level design of Decodex.

![architecture.png](images/dg/architecture.png)

**Main Components of The Architecture**

`Decodex` is responsible for initialising the components at launch.

The rest of the program consists of 6 other components:

- `UI`: Handles user input and message output to the console
- `Logic`: Parses user input and executes commands
- `Recipe`: Manages a sequence of module to be executed
- `Module`: Manages a set of encoding and decoding processes
- `Data`: Holds the data that is to be encoded or decoded
- `Storage`: Manages the reading and writing of data to disk

### UI Component

![UiClass.png](images/dg/UiClass.png)

The `Ui` component consists of:

- `Ui`: Manages access to the `Scanner` object that reads user input and also contains all the methods for printing to the user.

### Logic Component

Below is a partial class diagram that shows an overview of the `Logic` component.

![LogicComponent(4).png](images/dg/LogicComponent.png)

The `Logic` component consists of:

- `Parser`: Handles user input and decides the `Command` object to create.
- `Command`: An abstract class that defines the blueprint for the derived `*Command` classes.
    - `InputCommand`: Takes in a string from the user and sets it as the current `Data` object to perform operations on.
    - `HelpCommand`: Displays all command syntaxes to the user.
    - `ListCommand`: Displays all `Module` objects and loaded `Recipe` objects to the user.
    - `SelectCommand`: Executes a supported `Module` object or a loaded `Recipe` object on the current `Data` object and replaces it with the resulting `Data` object from the execution.
    - `ResetCommand`: Reverts the current `Data` object to a state before any modules/recipes were executed on it.
    - `ExitCommand`: Exits the application.
    - `RecipeNewCommand`: Creates a new `Recipe` object with name provided by the user and creates a save file for it on the file system.
    - `RecipeSelectCommand`: Set a `Recipe` object as the recipe that is currently being edited.
    - `RecipeListCommand`: Display all `Module` objects in a `Recipe` object.
    - `RecipePushCommand`: Appends a `Module` object to the `Recipe` object that is currently being edited.
    - `RecipePopCommand`: Removes the latest `Module` object from the `Recipe` object that is currently being edited.
    - `RecipeResetCommand`: Removes all `Module` objects from the `Recipe` object that is currently being edited.
    - `RecipeDeleteCommand`: Deletes a `Recipe` object from the application as well as its corresponding save file on the file system.


Below is the class diagram showing the association between the `Decodex` class and the `Parser` class.

![ParserClass.png](images/dg/ParserClass.png)

Below is the class diagram showing the association between the abstract `Command` class and its derived `*Command` classes.

![CommandClass1.png](images/dg/CommandClass1.png)

![CommandClass2.png](images/dg/CommandClass2.png)
### Data Component

Below is a partial class diagram that shows an overview of the `Data` component.

![DataComponent.png](images/dg/DataComponent.png)

The `Data` component consists of:

1. `Data`: Used to hold the bytes which the data transformations will act on.
2. `DataManager`: Holds a reference to the current `Data` object and updates it when a `Module` or `Recipe` is executed.

Below is the class diagram showing the association between the `Decodex` class, the `DataManager` class and the `Data` class.

![DataClass.png](images/dg/DataClass.png)

### Module Component

Below is a partial class diagram that shows an overview of the `Module` component.

![ModuleComponent.png](images/dg/ModuleComponent.png)

The `Module` component consists of:

- `Module`: An abstract class that defines the blueprint for the derived `*Encoder` or `*Decoder` classes.
    - `Base64Encoder`, `Base64Decoder`: Performs base64 encoding/decoding operations
    - `HexEncoder`, `HexDecoder`: Performs hexadecimal encoding/decoding operations
    - `BinaryEncoder`, `BinaryDecoder`: Performs binary encoding/decoding operations
    - `RotEncoder`: Performs rotational cipher operation
- `ModuleManager`: Decides and generates the necessary `Module` objects to be added to `Recipe` objects or to be executed on `Data` objects.

Below is the class diagram showing the association between the `Decodex` class, the `ModuleManager` class.

![ModuleManagerClass.png](images/dg/ModuleManagerClass.png)

Below is the class diagram showing the association between the abstract `Module` class and its derived `*Decoder` classes.

![ModuleClass.png](images/dg/ModuleClass.png)

### Recipe Component

Below is a partial class diagram that shows an overview of the `Recipe` component.

![RecipeComponent.png](images/dg/RecipeComponent.png)

The `Recipe` component consists of:

- `Recipe`: Holds a chain of `Module` objects that, when ran, will execute sequentially on a `Data` object.
- `RecipeManager`: Manages all loaded `Recipe` objects and holds a reference to the `Recipe` object that is currently being edited.

![RecipeClass.png](images/dg/RecipeClass.png)

> :pen: The reason for having `Recipe` objects is to enable users to run multiple modules at one go sequentially.

### Storage Component

![StorageClass.png](images/dg/StorageClass.png)

The `Storage` component consists of:

- `Storage` : Performs all file related operations such as reading/saving to files and deleting of files.

To add on, the `Storage` component is designed to access only the following folders:

1. `recipe/` : For recipe files.
2. `output/` : For output data files.
3. `input/` : For input data file.

> :pen: The rationale behind standardizing the specific folders to read/save to, is to ensure that all relevant files can be found in the same location, which makes it easier for users to find the files they are looking for.

## Implementation

This section focuses on explaining the specific applications flows and the interactions between the classes and their methods.

💡 The lifeline of the objects should terminate at the destroy marker (`X`), however due to a limitation of the PlantUML software, the lifeline extends beyond the destroy marker.

### Decodex Initialisation

Below shows the sequence diagram of the initialisation of Decodex.

![Decodex Initialisation](images/dg/DecodexInitSeq.png)

During the initialisation sequence of Decodex, all the dependencies (i.e `DataManager`, `ModuleManager` .etc) are instantiated, which will later on be passed to the relevant commands and methods for usage.

### Decodex Main Logic

Below is the sequence diagram showing the overall flow of Decodex.

![Decodex Main Logic](images/dg/DecodexMainSeq.png)

This main logic occurs after the initialisation sequence in the previous figure. It comprises of the main loop that provides the interactivity to the user by reading input from them and attempting to execute it as a command. The loop terminates when the user enters the `exit` command.

### Parser - Basic Command Logic

Below is the sequence diagram that shows the overall flow of the `Parser` logic.

![Parser Basic Command Logic](images/dg/ParserBasicSeq.png)

The flow of the `Parser` logic:

1. Receives the user input and parses it to get the command type.
2. Prepares the corresponding `XYZCommand`.
    1. However, if the command is a `Recipe*Command` , it will be passed to `RecipeCommandParser` for subcommand parsing.
3. Returns the command back to `Decodex`.

### Parser - Recipe Command Logic

Below is the sequence diagram that shows the overall flow of the `RecipeCommandParser` logic.

![Parser Recipe Command Logic](images/dg/ParserRecipeSeq.png)

The general process of the `RecipeCommandParser` logic consists of:

1. Receives the user input from `Parser` then parses it to get the subcommand type.
2. Prepares the corresponding `RecipeXYZCommand`
3. Returns the command back to `Decodex`

### Modules

The abstract `Module` class serves as the base class for all modules to be inherited from. It contains essential information that must be present in every module developed for Decodex.

- `name`: The name of the module
- `description`: The description of the module
- `run(Data data)`: The abstract method to process the provided `Data`

The `name` and `description` variables must be set during the instantiation of the module, that is to say in the constructor.

The abstract `run(Data data)` method should be implemented to return a `Data` object as the purpose of this method is solely for data processing.

There are two groups of modules supported by Decodex:

- Modules that do not take in any parameters
- Modules that take in at least one parameter

Modules that do not take in any parameters

This group of modules do not require any additional parameters as their encoding and decoding processes are fixed. Hence, the constructors of these modules should not receive any parameters.

Modules that take in at least one parameter

This group of modules require at least one additional parameter as their encoding and decoding processes are dependent on the provided parameters. The constructors of these modules should receive parameters as required and stored in private variables. These variables will then be used in their respective `run(Data data)` method.

The current implementation of the abstract `Module` class provides a strong foundation top be inherited by much more complex modules, and developed into full-functioning modules for Decodex.

#### Implemented Modules:

| No Parameters                                                                                | At Least One Parameter |
| -------------------------------------------------------------------------------------------- | ---------------------- |
| Base64Encoder<br>Base64Decoder<br>HexEncoder<br>HexDecoder<br>BinaryEncoder<br>BinaryDecoder | RotEncoder             |

### List of Commands

#### InputCommand

![InputCommand](images/dg/InputCommand.png)

When the `Parser` recognises the `input` keyword from the user input, an `InputCommand` is instantiated.

1. Create a `Data` object from the user input.
2. Set the created `Data` object as the `originalData` in the `DataManager` .
3. Prints `input` to the console.

#### ExitCommand

![ExitCommand](images/dg/ExitCommand.png)

When the `Parser` recognises the `exit` keyword from the user input, an `ExitCommand` is instantiated.

1. Prints the goodbye message to the console.

#### ResetCommand

![ResetCommand](images/dg/ResetCommand.png)

When the `Parser` recognises the `reset` keyword from the user input, a `ResetCommand` is instantiated.

1. Replace the contents of `currentData` in `DataManager` with that of `originalData`.
2. Prints a successful reset message to the console.

#### SelectCommand

![SelectCommand](images/dg/SelectCommand.png)

When the `Parser` recognises the `select` keyword from the user input, a `SelectCommand` is instantiated.

1. If `selectCategory` is
    1. `module`, The selected `Module` is retrieved and executed on the provided `Data`. See [SelectCommand (Module)](#selectcommand-module) for the full sequence diagram.
    2. `recipe`, The selected `Recipe` is retrieved and its `Module`objects are executed on the provided `Data`. See [SelectCommand (Recipe)](#selectcommand-recipe) for the full sequence diagram.

##### SelectCommand (Module)

![SelectCommand](images/dg/SelectCommandModule.png)

1. Retrieves the corresponding `Module` with the provided `itemName` and `parameters` from `ModuleManager`.
2. Retrieves the current `Data` object from `DataManager`.
3. Executes the selected `Module` on the retrieved `Data` object and stores the processed content in a new `Data` object.
4. Sets the new `Data` object as the current data in `DataManager`.
5. Prints the contents of the new `Data` object to the console.
6. Retrieves the current `Data` object from `DataManager`.
7. Executes the selected `Module` on the retrieved `Data` object and creates a new `Data` object.
8. Sets the new `Data` object as the current data in `DataManager`.
9. Prints the content of the new `Data` object to the console.

##### SelectCommand (Recipe)

![SelectCommand (Recipe)](images/dg/SelectCommandRecipe.png)

1. Retrieves the corresponding Recipe with the provided `itemName` from `RecipeManager`.
2. Retrieves the current `Data` object from `DataManager`.
3. Executes the selected `Recipe` on the retrieved `Data` object and stores the processed content in a new `Data` object.
4. Sets the new `Data` object as the current data in `DataManager`.
5. Prints the `Module`s in the `Recipe` to the console.
6. Prints the content of the new `Data` object to the console.

#### ListCommand

![ListCommand](images/dg/ListCommand.png)

When the `Parser` recognises the `list` keyword from the user input, a `ListCommand` is instantiated.

The `ListCommand` would then parse any optional arguments, setting the `isPrintModuleList` and `isPrintRecipeList` boolean variables appropriately.

1. If `isPrintModuleList` is true
    1. Retrieves a list of all `Module` objects from `ModuleManager`. 
    2. Prints the retrieved list of `Module` objects to the console.
2. If `isPrintRecipeList` is true
    1. Retrieves a list of all `Recipe` objects from `RecipeManager`.
    2. Prints the retrieved list of `Recipe` objects to the console.

### Recipe Commands

The following commands are specific to managing modules within recipes.

The command format begins with the command keyword `recipe`, followed by a subcommand keyword, and finally any arguments.

Format: `recipe <subcommand> {arguments}`

Example: `recipe new newRecipe`

#### RecipeNewCommand

![RecipeNewCommand](images/dg/RecipeNewCommand.png)

When the `RecipeCommandParser` recognises the `new` subcommand keyword from the user input, a `RecipeNewCommand` is instantiated.

1. Creates a new `Recipe` object with the provided `recipeName`.
2. Add the newly created `Recipe` into the `RecipeManager`.
3. Sets `recipeName` as the `editingRecipeName` in `RecipeManager`.
4. Prints a successful creation message to the console.

#### RecipeSelectCommand

![RecipeSelectCommand](images/dg/RecipeSelectCommand.png)

When the `RecipeCommandParser` recognises the `select` subcommand keyword from the user input, a `RecipeSelectCommand` is instantiated.

1. Retrieves the `Recipe` corresponding with the provided `recipeName` from `RecipeManager`.
2. Sets `recipeName` as the `editingRecipeName` in `RecipeManager`.
3. Prints a successful selection message containing the `recipeName` to the console.

#### RecipeListCommand

![RecipeListCommand](images/dg/RecipeListCommand.png)

When the `RecipeCommandParser` recognises the `list` subcommand keyword from the user input, a `RecipeListCommand` is instantiated.

1. If `recipeName` is
   1. blank, the `Recipe` with the current `editingRecipeName` is retrieved.
   2. not blank, the `Recipe` with `recipeName` is retrieved.
2. Retrieves the list of `Module` objects belonging to the `Recipe` with `recipeName`.
3. Prints the names and parameters of each `Module` object in the retrieved list.

#### RecipePushCommand

![RecipePushCommand](images/dg/RecipePushCommand.png)

When the `RecipeCommandParser` recognises the `push` subcommand keyword from the user input, a `RecipePushCommand` is instantiated.

1. Retrieves the corresponding `Module` with the provided `moduleName` and `parameters` from `ModuleManager`.
2. Retrieves the current editing `Recipe`.
3. Adds the retrieved `Module` into the current editing `Recipe`.
4. Prints the a message of the added `Module` to the console.

#### RecipePopCommand

![RecipePopCommand](images/dg/RecipePopCommand.png)

When the `RecipeCommandParser` recognises the `pop` subcommand keyword from the user input, a `RecipePopCommand` is instantiated.

1. Retrieves the latest `Module` added to the `Recipe`, and removes it from the `Recipe` after retrieval.
2. Retrieves the current editing `Recipe`.
3. Prints the a message of the removed `Module` to the console.

#### RecipeResetCommand

![RecipeResetCommand](images/dg/RecipeResetCommand.png)

When the `RecipeCommandParser` recognises the `Reset` subcommand keyword from the user input, a `RecipeResetCommand` is instantiated.

1. Retrieves the `Recipe` with the current `editingRecipeName` is retrieved.
2. Removes all modules contained in the `Recipe`
3. Prints a successful reset message containing the `recipeName` to the console.

#### RecipeDeleteCommand

![RecipeDeleteCommand](images/dg/RecipeDeleteCommand.png)

When the `RecipeCommandParser` recognises the `delete` subcommand keyword from the user input, a `RecipeDeleteCommand` is instantiated.

1. Removes the `Recipe` with the `recipeName` from `RecipeManager`.
2. Prints `recipeName` as the deleted `Recipe` to the console.

## Appendix A: Product Scope

### Target User Profile

1. Prefer using CLI over other types
2. Can type fast
3. Does Capture-the-flag (CTF) competitions
4. Requires to use multiple data manipulation techniques at once
5. Is comfortable with CLI
6. May be for both normal and expert users (in terms of technical capabilities)

### Value Proposition

This application helps users (mainly CTF players) to quickly transform data from one format to another (e.g., from plain text to base64-encoded text). It includes features such as the ability to perform basic data transformations with a few simple commands. Furthermore, it also includes the use of recipes to allow for multiple modules to be executed in sequence, which would be useful when multiple consecutive data transformations are needed.

To sum it up, this application helps users to reduce the time needed to transform data from one form to another, especially when consecutive data transformations are required.

## Appendix B: User Stories

| version | priority | as a ...        | I want to...                                 | so that i can ...                                        |
| ------- | -------- | --------------- | -------------------------------------------- | -------------------------------------------------------- |
| V1.0    | ***      | user            | input data                                   | perform data manipulation on it                          |
| V1.0    | ***      | user            | see the output of my processed data          | see the effects of the change                            |
| V1.0    | ***      | user            | view a list of modules                       | see what modules I can use on my data                    |
| V1.0    | ***      | user            | add a module                                 | decided to process data with the selected module         |
| V1.0    | ***      | user            | run a module                                 | process data with the selected module                    |
| V2.0    | ***      | user            | create a new recipe                          | create different combinations of module chains           |
| V2.0    | ***      | user            | view a list of modules while creating recipe | decide what modules to be added to the recipe            |
| V2.0    | ***      | user            | add a module to the recipe                   | use it process my data                                   |
| V1.0    | **       | user            | remove all modules in the recipe             | see my original input data                               |
| V2.0    | **       | expert user     | read input data from a file                  | process data that are not printable or terminal-friendly |
| V2.0    | **       | expert user     | edit the exported recipes                    | inspect and modify it in an editor                       |
| V2.0    | **       | user            | see the list of the commands                 | know what commands I can use                             |
| V2.0    | **       | user            | see the syntax of the commands               | know how to use the commands                             |
| V2.0    | **       | CTF Participant | save my decoded output                       | reuse the output later                                   |
| V2.0    | **       | user            | import recipes from a file                   | I do not have to create the recipe from scratch/manually |
| V2.0    | **       | user            | save my recipes to a file                    | I can reuse the recipe on a different computer           |
| V2.0    | **       | user            | list the recipes I have                      | use them again                                           |

## Appendix C: Non-Functional Requirements

1. Should work on any *mainstream OS* as long as it has Java `11` or above installed.
2. Should be able to have up to 20 recipes without any impact on the performance.
3. A CTF participant should be able to work more efficiently on their CTF challenges compared to manual scripting in terms of time.
4. A user should be able to comfortably use and understand the application if they are within the IT field.
5. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.

## Appendix D: Glossary

- **Mainstream OS**: Windows, Linux, Unix, OS-X

## Appendix E: Instructions for Manual Testing