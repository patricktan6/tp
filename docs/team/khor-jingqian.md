---
layout: page
title: Khor Jing Qian's Project Portfolio P{age
---

## Project: fitNUS

### Overview

fitNUS is a desktop address book application that is targeted at NUS students that are looking to improve their fitness. 
The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10
kLoC.

### About the Team

We are a group of 5 Computer Science undergraduates from Year 2 and Year 3 reading CS2103T: Software Engineering.

#### About the Project

fitNUS represents a culmination of our team's efforts in morphing an existing [Address Book Level 3 (AB3) project](https://github.com/se-edu/addressbook-level3)
into a new product via Brownfield software development. fitNUS is a personal fitness tracker assistant which allows
users to create customised workout routines, by having the flexibility to add exercises to the routines they have
created. To facilitate workouts around their busy schedules, users have an added option of slotting lessons and routines
to their timetable.

The team has come up with a vast number of user-centric features to make fitNUS well-suited in order to provide both
convenience and utility in the long-run. Aimed at NUS students who are keen on improving their fitness levels, fitNUS is
the perfect solution in which the Graphical User Interface is integrated with Command Line Interface to provide a
wholesome user experience. 

## Summary of Contributions 

* **New Feature**: Added Routine classes and implemented its relevant implementations.
  * What it does: allows the user to store pre-existing Exercises and users are able to add Routine into their timetable.
  * Justification: This is a core feature of fitNUS and allows users to customise what exercises they want in a routine and the time and date of when they want to try out this Routine.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Highlights: Implementing Routine required a deep understanding of how the original AB3 functioned. The implementation was made even more challenging by the fact that
  it was built on Exercise and maintaining low coupling was tough.
 
* **New Feature**: Added DailyCalorie classes and implemented its relevant implementations.
  * What it does: allows the user to track their daily calorie intake up to 7 days.
  * Justification: Calories is an important part of tracking your fitness and this implementation allows fitNUS to better help their users.
  * Highlights: This enhancement affects existing commands and commands to be added in future. It required an in-depth analysis of design alternatives. The implementation too was challenging as it required changes to existing commands.
  * Highlights: Calorie tracking is done on a daily basis because the team believes that users should not be able to change future or past calorie count.

* **New Feature**: Implemented the graph for calorie tracking on the fitNUS GUI.
  * What it does: allows the user to track their daily calorie intake up to 7 days.
  * Justification: Calories is an important part of tracking your fitness and this implementation allows fitNUS to better help their users.
  * Highlights: Calorie tracking is done on a daily basis because the team believes that users should not be able to change future or past calorie count.

* **New Feature**: Added a history command that allows the user to navigate to previous commands using up/down keys.

* **Code contributed**: You can view my code contributions to fitNUS [here](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=&sort=groupTitle&sortWithin=title&since=2020-08-14&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=khor-jingqian&tabRepo=AY2021S1-CS2103T-T09-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other).

* **Project management**:
  * Managed releases `v1.3(trial)` and `v1.3` (2 releases) on GitHub

* **Enhancements to existing features**:
  * Updated the GUI to fit our vision for fitNUS, adjusting the arrangement and finding icons for fitNUS.
  * Wrote test cases for Routine, UniqueRoutineList, DailyCalorie, CalorieLog, as well as its relevant commands and parser.
  
## Contributions to the team-based tasks:

* Release management, handled v1.3(trial) and v1.3.
* Maintaining issue-tracker and milestone, reminding team mates to make sure their workload is reflected in the issues.
* Timely reminders to close their issues and finish up before milestone deadline.
* Necessary code enhancements such as renaming it to fitNUS, finding a suitable product icon, recommending a layout.

## Contribution to the User Guide:

* Wrote most of the documentation for Exercise, Routine, BMI.

[Exercise commands](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#41-exercise)

[Routine commands](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#42-routine)

[BMI Commands](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#45-bmi)

* Added most of the images in the User Guide with respect to features.

* Wrote the command summary for User Guide.

[Command Summary](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/UserGuide.md#5-command-summary)

## Contributions to the Developer Guide:

Added implementation details of the command `routine_create`, showing in depth understanding of the sequence of method calls in fitNUS.

[Create Routine](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#create-routine)

Refactored architecture diagrams from AB3 to better reflect how fitNUS works.

[Logic Diagram & Sequence diagram](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#architecture)

[Ui Diagram](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#ui-component)

[Logic Component & its sequence diagram](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#logic-component)

[Model Components](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#model-component)

[Storage Diagram](https://github.com/AY2021S1-CS2103T-T09-2/tp/blob/master/docs/DeveloperGuide.md#storage-component)


##Community
  * Reported bugs and suggestions for other teams in the class during PE Dry Run.

