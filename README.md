# Parnassus
## A species counterpoint validator

Parnassus is a tool for validating notated music according to the rules stated in Johann Joseph Fux's *Gradus ad Parnassum*, a classic educational text on contrapuntal harmony.
*Gradus ad Parnassum* provides a set of compositional approaches (referred to as "species"), which grow progressively more complex and are governed by strict rulesets.
This approach to pedagogy is known as "species counterpoint".

This project aims to provide a full-featured validation suite for the first contrapuntal species Fux outlines in *Gradus* (note-against-note counterpoint).
This is a useful resource for any student of classical harmony, as it will notice easily-missed compositional errors.
I see this as a useful practice tool, and am personally compelled to implement it because I think the rigidity of Fux's approach inherently translates well into a computational problem.
I also see the development process of this program as a good opportunity to solidify my knowledge of contrapuntal harmony.

## User Stories

- As a user, I want to be able to add multiple musical notes to a composition
- As a user, I want to be able to verify that my composition contains the same number of notes in each voice
- As a user, I want to be able to verify that my composition is wholly made up of consonances
- As a user, I want to be able to verify that my composition does not contain parallel perfect intervals
- As a user, I want to be able to verify that my composition begins and ends with perfect consonances
- As a user, I want to be able to save a composition to file
- As a user, I want to be able to load a composition from file

## Phase 4: Task 2
The Validator class is robust. Its validate() method uses exceptions to provide multiple failstates without having to represent them as integer error codes.

## Phase 4: Task 3
I'm fairly happy with the way I'm representing objects in this project. If I could change anything, it would be the following:
- I was fairly reliant on private classes, especially in my GUI class. I'd like to refactor some of those private classes, it's a bit messy.
- I think there are too many classes with no clear associations other than their usage in inner methods (Interval, Motion, NoteSpinnerModel, etc).