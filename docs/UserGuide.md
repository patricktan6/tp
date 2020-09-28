---
layout: page
title: User Guide
---

fitNUS is a **desktop app for managing your workout sessions and school timetable, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, fitNUS can get your scheduling tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `fitNUS.jar` from [here](https://github.com/AY2021S1-CS2103T-T09-2/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your fitNUS.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)
   
1. Type the command in the command box and press Enter to execute it. Here are some sample commands you can try:

   * **`routine list`** : Lists all routines within fitNUS.

   * **`routine create`**`r/Upper Body Session` : Creates a routine names "Upper Body Session"

   * **`exercise add `**`r/Upper Body Session e/Curls` : Adds an exercise named "Curls" to a routine named "Upper Body Session"

   * **`schedule add`**`r/Upper Body Session d/Monday t/1600-1800` : Adds the routine "Upper Body Session" to your
   timetable on Monday at 1600HRS - 1800HRS.
   
1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

### Help: `help`

Lists out all useful commands and their functions.

Format: `help`

### Create new routine : `routine create`

Creates a new routine by the given routine name.

Format: `routine create r/ROUTINE_NAME`

Examples:
* `routine create r/Leg Day Session` Creates new routine with the name "Leg Day Session".

### Delete routine : `routine delete`

Deletes an existing routine corresponding to the given index.

Format: `routine delete INDEX`

Examples:
* `routine delete 10` Deletes routine with the index 10.

### Add exercise to routine : `exercise add`

Add a new exercise to a specific routine.

Format: `exercise add r/NAME_OF_TARGET_ROUTINE e/EXERCISE_NAME`

Examples:
* `exercise add r/Leg Day Session e/Squats` Adds an exercise named "Squats" to the routine "Leg Day Session".

### Delete exercise from routine : `exercise delete`

Add a new exercise to a specific routine.

Format: `exercise delete r/NAME_OF_TARGET_ROUTINE e/EXERCISE_NAME`

Examples:
* `exercise delete r/Leg Day Session e/Squats` Delete the exercise "Squats" from the routine "Leg Day Session".

### View routine details : `routine view`

View the details of a certain routine.

Format: `routine view INDEX`

Examples:
* `routine view 2` Views routine with the index 2.

### List routines : `routine list`

Lists out all the routines in fitNUS.

Format: `routine list`

### Add routine to schedule : `schedule add`

Adds a complete routine into the application's schedule.

Format: `schedule add r/ROUTINE_NAME d/DAY_OF_THE_WEEK t/TIMING`

Examples:
* `schedule add r/Leg Day Session d/Monday t/1600-1800` Adds routine "Leg Day Session" to schedule on Monday, 1600-1800.

### Delete routine from schedule : `schedule delete`

Deletes a routine from the application's schedule.

Format: `schedule delete d/DAY_OF_THE_WEEK t/TIMING`

Examples:
* `schedule delete d/Monday t/1600-1800` Deletes the routine scheduled on Monday, 1600-1800.

### View timetable : `timetable view`

View the user's timetable.

Format: `timetable view`

Examples:
* `timetable view` Views timetable.

### Saving the data

fitNUS data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Archiving data files `[coming in v2.0]`

_{explain the feature here}_

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous fitNUS home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**List commands** | `help`
**Create routine** | `routine create r/ROUTINE_NAME​` <br> e.g., `routine create r/Leg Day Session`
**Delete routine** | `routine delete INDEX` <br> e.g., `routine delete 10`
**Add exercise to routine** | `exercise add r/NAME_OF_TARGET_ROUTINE e/EXERCISE_NAME`<br> e.g., `exercise add r/Leg Day Session e/Squats`
**Delete exercise from routine** | `exercise delete r/NAME_OF_TARGET_ROUTINE e/EXERCISE_NAME​`<br> e.g.,`exercise delete r/Leg Day Session e/Squats`
**View routine details** | `routine view INDEX`<br> e.g., `routine view 2`
**List routines** | `routine list`
**Add routine to schedule** | `schedule add r/ROUTINE_NAME d/DAY_OF_THE_WEEK t/TIMING` <br> e.g., `schedule add r/Leg Day Session d/Monday t/1600-1800`
**Delete routine from schedule** | `schedule delete d/DAY_OF_THE_WEEK t/TIMING` <br> e.g., `schedule delete d/Monday t/1600-1800`
**View timetable** | `timetable view`

