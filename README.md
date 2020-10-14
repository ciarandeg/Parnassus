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
- As a user, I want to be able to verify that my composition is contains the same number of notes in each voice
- As a user, I want to be able to verify that my composition is wholly made up of consonances
- As a user, I want to be able to verify that my composition does not contain parallel perfect intervals
- As a user, I want to be able to verify that my composition begins and ends with perfect consonances