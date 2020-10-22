---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture (Jing Qian)

<img src="images/ArchitectureDiagram.png" width="450" />

The ***Architecture Diagram*** given above explains the high-level design of the App. Given below is a quick overview of each component.

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document can be found in the [diagrams](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/docs/diagrams) folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.

</div>

**`Main`** has two classes called [`Main`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/java/seedu/address/MainApp.java). It is responsible for,
* At app launch: Initializes the components in the correct sequence, and connects them up with each other.
* At shut down: Shuts down the components and invokes cleanup methods where necessary.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

The rest of the App consists of four components.

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

Each of the four components,

* defines its *API* in an `interface` with the same name as the Component.
* exposes its functionality using a concrete `{Component Name}Manager` class (which implements the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component (see the class diagram given below) defines its API in the `Logic.java` interface and exposes its functionality using the `LogicManager.java` class which implements the `Logic` interface.

![Class Diagram of the Logic Component](images/LogicClassDiagram.png)

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

The sections below give more details of each component.

### UI component (Jing Qian)

![Structure of the UI Component](images/UiClassDiagram.png)

**API** :
[`Ui.java`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/java/seedu/address/ui/Ui.java)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class.

The `UI` component uses JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* Executes user commands using the `Logic` component.
* Listens for changes to `Model` data so that the UI can be updated with the modified data.

### Logic component (Jing Qian)

![Structure of the Logic Component](images/LogicClassDiagram.png)

**API** :
[`Logic.java`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/java/seedu/address/logic/Logic.java)

1. `Logic` uses the `FitNusBookParser` class to parse the user command.
1. This results in a `Command` object which is executed by the `LogicManager`.
1. The command execution can affect the `Model` (e.g. adding a person).
1. The result of the command execution is encapsulated as a `CommandResult` object which is passed back to the `Ui`.
1. In addition, the `CommandResult` object can also instruct the `Ui` to perform certain actions, such as displaying help to the user.

Given below is the Sequence Diagram for interactions within the `Logic` component for the `execute("exercise_delete 1")` API call.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ExerciseDeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

### Model component (Jing Qian)

![Structure of the Model Component](images/ModelClassDiagram.png)

**API** : [`Model.java`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/java/seedu/address/model/Model.java)

The `Model`,

* stores a `UserPref` object that represents the user’s preferences.
* stores the fitNUS data.
* exposes an unmodifiable `ObservableList<Exercise>`, `ObservableList<Routine>` and `ObservableList<Lesson>`  that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* does not depend on any of the other three components.


<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Person` references. This allows `AddressBook` to only require one `Tag` object per unique `Tag`, instead of each `Person` needing their own `Tag` object.<br>

![BetterModelClassDiagram](images/BetterModelClassDiagram.png)

</div>


### Storage component (Jing Qian)

![Structure of the Storage Component](images/StorageClassDiagram.png)

**API** : [`Storage.java`](https://github.com/AY2021S1-CS2103T-T09-2/tp/tree/master/src/main/java/seedu/address/storage/Storage.java)

The `Storage` component,
* can save `UserPref` objects in json format and read it back.
* can save the fitNUS data in json format and read it back.

### Common classes 

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Create routine (Jing Qian)

The feature to create a routine in fitNUS is implemented using `RoutineCreateCommand` class, which inherits from
`Command` class. The argument is supplied after parsing the user input by the `RoutineCreateCommandParser` class.

It is important to note that the Routine object in question must not already exist within fitNUS.

Given below is an example usage scenario and how the routine-creating mechanism behaves at each step.

**Step 1:**

The user types into fitNUS `routine_create r/Leg Workout`.

**Step 2:**

This input is passed to `LogicManager` as a String and from there, the input is parsed by the `parseCommand`
method of `FitNusParser`.

**Step 3:**

`parseCommand` identifies that this is a command to create a Routine, so it calls the `parse` method of
`RoutineCreateCommandParser` on the input.

**Step 4:**

Within `parse`, the name of the routine is produced by `ParserUtil` parsing the given argument. This method then creates
the Routine object and returns it as an argument in the `RoutineCreateCommand`.

**Step 5:**

`LogicManager` then calls the `execute` method of this returned `RoutineCreateCommand` to add the Routine into fitNUS.

**Step 6:**

`LogicManager` then saves the updated fitNUS data to the storage.

Given below is the sequence diagram showing how the routine creation command is executed:

![Routine Create](./images/RoutineAddSequenceDiagram.png)

### Add Routine to Timetable (Nicholas)

The `TimetableAddRoutineCommandParser` and `TimetableAddRoutineCommand` classes parse and execute the user input
to add a routine to the timetable in fitNUS.

The `TimetableAddRoutineCommandParser` class takes in the user input and parses them to return a
`TimetableAddRoutineCommand` object that contains the routine, day and duration. The `TimetableAddRoutineCommand` class
then executes the command by storing the slot in the timetable.

Note that for the command to be successful, the routine to be added has to exist in fitNUS.

Given below is an example usage scenario and how the mechanism behaves at each step.

**Step 1:**

The user types into fitNUS `timetable_add_routine r/Leg workout d/Monday T/1600-1800`.

**Step 2:**

`LogicManager` takes in this input as a String and calls the `parseCommand` method of `FitNusParser` to parse this string.

**Step 3:**

`parseCommand` identifies that this is a command add a Routine to Timetable, so it calls the `parse` method of
`TimetableAddRoutineCommandParser` on the input.

**Step 4:**

The `parse` method calls the necessary methods of `ParserUtil` to parse the given argument and produce the name, day,
and duration. It uses the name to create the Routine object and returns this object, the day, and the duration as an
argument in `TimetableAddRoutineCommand`.<br>
Note that this Routine object is a placeholder, the actual Routine object in fitNUS is yet to be found.

**Step 5:**

`LogicManager` calls the `execute` method of this returned `TimetableAddRoutineCommand` to add the Routine into the timetable.<br>
Note that the `execute` method retrieves the actual Routine object in fitNUS before adding it to the timetable.

**Step 6:**

`LogicManager` then saves the updated fitNUS data to the storage.

Given below is the Sequence Diagram for interactions within the Logic component for the
execute("timetable_add_routine r/Leg Workout d/Monday T/1600-1700") API call.

![TimetableAddRoutineSequenceDiagram](images/TimetableAddRoutineSequenceDiagram.png)

### Find exercises (Licheng)

The find exercises feature is implemented using `FindExercisesCommandParser`, as well as the following command:
* `FindExercisesCommand`, to be executed when the user inputs the command into fitNUS.

`FindExercisesCommandParser` takes in the user input and parses them to return a FindExercisesCommand containing the
corresponding predicate for finding the exercises. When executed, `FindExercisesCommand` will set the predicate of
the respective `FilteredList` for exercises in `ModelManager` such that only exercises matching the predicate will be
displayed in the list.

Given below is an example usage scenario and how the find exercise mechanism behaves at each step.

**Step 1:**

The user types into fitNUS `find_exercises bench`.

**Step 2:**

This input is passed to `LogicManager` as a String and from there, the input is parsed by the `parseCommand`
method of `FitNusParser`.

**Step 3:**

`parseCommand` identifies that this is a command to find exercises, so it calls the `parse` method of
`FindExercisesCommandParser` on the input.

**Step 4:**

Within `parse`, the keywords to match are added to a `List`, and an `ExerciseNameContainsKeywordsPredicate` object
is created based on this list. A `FindExercisesCommand` object is created using this
`ExerciseNameContainsKeywordsPredicate` object.

**Step 5:**

`LogicManager` then calls the `execute` method of this returned `FindExercisesCommand`.
Within `execute`, `ModelManager`'s `updateFilteredExerciseList` method is called with the
`ExerciseNameContainsKeywordsPredicate` object as its argument. This filters out the relevant exercises.

**Step 6:**

The GUI then lists the filtered exercises.

Given below is the Sequence Diagram for interactions within the Logic component for the execute("find_exercises bench")
API call.

![FindExercisesSequenceDiagram](images/FindExercisesSequenceDiagram.png)

### Delete lesson (Iqbal)

The delete lesson feature is implemented using `LessonDeleteCommandParser`, as well as the following command:
* `LessonDeleteCommand`, to be executed when the user inputs the command into fitNUS.

`LessonDeleteCommandParser` takes in the user input and parses them to return a `LessonDeleteCommand` object. The
`LessonDeleteCommand` class then executes the command by deleting the lesson from the respective `FilteredList` for
lessons in `ModelManager`.

Given below is an example usage scenario and how the delete lesson mechanism behaves at each step.

**Step 1:**

The user types into fitNUS `lesson_delete 1`.

**Step 2:**

This input is passed to `LogicManager` as a String and from there, the input is parsed by the `parseCommand`
method of `FitNusParser`.

**Step 3:**

`parseCommand` identifies that this is a command to delete lesson, so it calls the `parse` method of
`LessonDeleteCommandParser` on the input.

**Step 4:**

Within `parse`, the name of the lesson is produced by `ParserUtil` parsing the given argument. This method then creates
the Lesson object.

**Step 5:**

`LogicManager` then calls the `execute` method of this returned `LessonDeleteCommand`.
Within `execute`, `ModelManager`'s `updateFilteredLessonList` method is called. This removes the unwanted lesson.

**Step 6:**

The GUI then lists the deleted lesson.



--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* has a need to manage their busy schedule
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps
* interested in fitness, or aspiring to start
* is an NUS student

**Value proposition**: provide a platform for NUS students of any fitness experience to conveniently plan their workout
around their classes.


### User stories (Jing QIan)

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *`  | student who has no knowledge of working out|view what exercise routines the application has|choose the right one for me
| `* * *`  | busy student                               | add workout routines into my schedule | have the time to exercise
| `* * *`  | student                                    | delete a workout routine       | keep my schedule up-to-date
| `* * *`  | varsity NUS athlete                        | customise my routine               | better target specific muscle groups
| `* * *`  | health-conscious individual                 | log my BMI              | keep better track of my health.


### Use cases (Licheng)

(For all use cases below, the **System** is the `fitNUS` and the **Actor** is the `user`, unless specified otherwise)

**Use case: Create a new routine**

**MSS**

1.  User requests for the current list of routines
2.  fitNUS shows the list of routines
3.  User requests to create a new routine
4.  fitNUS adds that routine to its list of routines

    Use case ends.

**Extensions**

* 3a. The given routine name already exists.

    * 3a1. fitNUS shows an error message.

      Use case resumes at step 2.

**Use case: Delete a routine**

**MSS**

1.  User requests to list routines
2.  fitNUS shows a list of routines
3.  User requests to delete a specific routine in the list
4.  fitNUS deletes the routine

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. fitNUS shows an error message.

      Use case resumes at step 2.

**Use case: Add exercise to routine**

**MSS**

1.  User requests to list routines
2.  fitNUS shows a list of routines
3.  User requests to view exercises in a specific routine
4.  fitNUS shows a list exercises in that specific routine
5.  User adds the exercise

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. fitNUS shows an error message.

      Use case resumes at step 2.

* 5a. The given exercise name already exists.

    * 5a1. fitNUS shows an error message.

      Use case resumes at step 4.

**Use case: Delete exercise from routine**

**MSS**

1.  User requests to list routines
2.  fitNUS shows a list of routines
3.  User requests to view exercises in a specific routine
4.  fitNUS shows a list exercises in that specific routine
5.  fitNUS deletes the exercise

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

* 3a. The given index is invalid.

    * 3a1. fitNUS shows an error message.

      Use case resumes at step 2.

* 5a. The given index of exercise to delete is invalid.

    * 5a1. fitNUS shows an error message.

      Use case resumes at step 4.

**Use case: List all routines in fitNUS**

**MSS**

1.  User requests for all the routines available.
2.  fitNUS displays all the routines that are available, if there are any.

**Use case: View details of a certain routine**

**MSS**

1.  User requests to view a certain routine.
2.  fitNUS displays all information of the specified routine.

**Extensions**

* 1a.   Index given by user is invalid.
    * 1a1.  fitNUS shows an error message.

      Use case ends.

* 1b.   No routine exists within fitNUS.
    * 1b1.  fitNUS informs the user that there are no routines to view.

      Use case ends.

**Use case: Add completed routine to schedule**

**MSS**

1.  User requests to view current schedule.
2.  fitNUS shows the user's schedule for the day.
3.  User requests to add a specific routine to a specific slot in schedule.
4.  fitNUS adds the routine to schedule.

    Use case ends.

**Extensions**

* 3a. Routine requested by user does not exist.
    * 3a1. fitNUS shows an error message.

    Use case ends.

* 3b. The slot to add the routine is invalid.
    * 3b1. fitNUS shows an error message.

    Use case ends.

* 3c. The slot to add the routine is already in use.
    * 3c1. fitNUS informs the user to input another slot.

    Use case resumes at step 3.

**Use case: Delete routine from schedule**

**MSS**

1.  User requests to view current schedule.
2.  fitNUS shows the user's schedule for the day.
3.  User requests to delete a routine from a specific slot in schedule.
4.  fitNUS deletes the routine from schedule.

    Use case ends.

**Extensions**

* 3a. The slot requested is not assigned to any routine.
    * 3a1. fitNUS informs the user that the slot is empty.

    Use case resumes at step 3.

* 3b. The slot requested is invalid.
    * 3b1. fitNUS shows an error message.

    Use case ends.

**Use case: View timetable**

**MSS**

1.  User requests to view their timetable.
2.  fitNUS displays their timetable.

**Extensions**

* 1a.   No timetable exists within fitNUS.
    * 1a1.  fitNUS informs the user that there is no timetable to view yet.

      Use case ends.

*{More to be added}*

### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3.  A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The system should respond under a second.
5.  The system should be able to be used by a fitness novice, in the sense that the fitness terms used in fitNUS should not
confuse anyone.
6. The system should remember the details entered by the user during the session, and be carried forward to the next time.
7. Schedule should be clear and easy to read for the user, and not display too much information at once.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a Routine (Jing Qian)

1. Deleting a Routine

   1. Prerequisites: Routine must already exist in fitNUS. In this case, the first Routine is the one to be deleted.

   1. Test case: `routine_delete 1`<br>
      Expected: First Routine is deleted from the list. Successful message will be shown.

   1. Test case: `routine_delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `routine_delete`, `routine_delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
