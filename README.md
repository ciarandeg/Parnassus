# Parnassus
## A species counterpoint validator

Parnassus is a tool for validating notated music according to the rules stated in Johann Joseph Fux's *Gradus ad Parnassum*, a classic educational text for learning contrapuntal harmony.
*Gradus ad Parnassum* provides a set of compositional approaches (referred to as "species"), which grow progressively more complex and are governed by strict rulesets.
This approach to pedagogy is known as "species counterpoint".

This project aims to provide a full-featured validation suite for each of the five contrapuntal species Fux outlines in *Gradus*.
This is a useful resource for any student of classical harmony, as it will notice easily-missed compositional errors.
I see this as a useful practice tool, and am personally compelled to implement it because I think the rigidity of Fux's approach inherently translates well into a computational problem.
I also see the development process of this program as a good opportunity to solidify my knowledge of contrapuntal harmony.

## User Stories

- As a user, I want to be able to select a species to work in
- As a user, I want to be able to input musical notes into a composition
- As a user, I want to be able to input clefs, time signatures, and key signatures into a composition
- As a user, I want to be able to check a composition for errors
- As a user, I want to be able to save a composition to an external file
- As a user, I want to be able to import an already-existing composition
- As a user, I want to be able to convert from one species to a more complex one
- As a user, I want to be able to visualize my composition as notation, as text or graphically