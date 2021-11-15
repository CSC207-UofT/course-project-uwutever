## Specification

We intend to build a tool for providing assistance in learning Regular Expression in computational language theory.

When a regular expression is compiled, it is converted into an equivalent Finite State Automata (FSA) which is an abstract concept yet can be easily visualized.

Our software provides the following features:
- As a Regex tool, it should be able to perform matching between a given regular expression and a given text string,
  i.e. determine whether a string is in the language defined by a regular expression.  
    For example, the text string "a" will be in the language refined by `a|b` but "c" will not be.
    Our program will be able to correct determine this.

- The underlying method of matching will be by first constructing a Finite State Automaton which accepts the same language,
  so we will also implement a way to visualize the corresponding FSA on the interface. This will include 
  the start state, every transition state, and the end state.
