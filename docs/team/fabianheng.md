---
layout: page
title: Fabian's Project Portfolio Page
---

### Project: ClientNest

ClientNest is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). From adding clients to tracking birthdays and policies, ClientNest supports meaningful engagement and confident client management.

Given below are my contributions to the project.

* **New Feature**: Added the ability to customize and display user profile.
  * What it does: Allows the user to edit and view their user profile by specifying their name, phone number, and email address.
  * Justification: This feature improves the product significantly by enabling users to store their contact details within the application, making it easier to share and manage personal information for networking and follow-ups.
  * Highlights: This enhancement integrates seamlessly with the existing UI, includes validation for contact fields, and persists the profile data even after the application is closed.

* **New Feature**: Added the ability to allow the user to navigate to previous commands using the up/down arrow keys.
  * What it does: Lets users scroll through previously entered commands in the Command Box using the keyboard, similar to terminal behavior.
  * Justification: This feature improves the product significantly by enhancing efficiency for power users and reducing the need to retype commonly used or recent commands.
  * Highlights: The implementation handles edge cases gracefully (e.g. at start or end of history), works smoothly across different command types, and improves overall user interaction with the CLI.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2425s2.github.io/tp-dashboard/#/widget/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=FabianHeng&tabRepo=AY2425S2-CS2103-F10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false&chartGroupIndex=9&chartIndex=1)

* **Project management**:
  * Managed release `v1.4` (1 release) on GitHub

* **Enhancements to existing features**:
  * Updated and improved the GUI color scheme and the UI design (Pull requests [\#154](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/154), [\#171](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/171), [\#180](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/180))
  * Improved Name, Phone Number and Email input verification by setting limits and checking regex styles (Pull request [\#232](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/232))
  * Added a default Address Book when a user first opens the application, and populate it with sample data (Pull request [\#235](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/235))
  * Update the product website to match styling and improve readability (Pull requests [\#242](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/242), [\#243](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/243), [\#244](https://github.com/AY2425S2-CS2103-F10-2/tp/pull/244))

* **Documentation**:
  * User Guide:
    * Added documentation for the feature `profile`.
    * Added implementation for the feature `profile`.
    * Did cosmetic tweaks to existing documentation of `Ui.png`, `Help`, `Find`.
  * Developer Guide:
    * Added and edited PUML Diagram for `Ui`.
