## Specification (Updated for Phase 1)

Implemented functionalities are in **bold**.

We have built a program that allows students who are learning Regex Language to input Regex and plain language text and learn which part of the plain language text matches Regex in the calculation result by the compiler we have designed. It also allows students to see the meaning of the characters in the Regex through a visual presentation. The students will also be given some suggestions.

This will be helpful for students who are learning regular expressions in computational language theory.

When a regular expression is compiled, it is converted into an equivalent Finite State Automata (FSA) which is an abstract concept yet can be easily visualized.

Our software provides the following features:
- As a Regex tool, it should be able to **perform matching between a given regular expression and a given text string**,
  i.e. determine whether a string is in the language defined by a regular expression.  
  It should also be able to perform pattern matching, which is looking for substrings in the given text string which are 
  in the language.
  
  For example, the text string "a" will be in the language refined by `a|b` but "c" will not be.
  Our program will be able to correct determine this.

- The underlying method of matching will be by first constructing a Finite State Automaton which accepts the same language,
  so we will also implement a way to visualize the corresponding FSA on the interface. This will include
  the start state, every transition state, and the end state. **The regular expression is first parsed into an Abstract Syntax Tree,
  which is then compiled into an NFA and then converted again into a DFA.**

**The user will be confronted with an Android UI, where the user will download the software installation package and install the Regex App. When they open the software, they will interact with a Regex App that can be used offline. In the start screen, the system gives two sample Regexes, the user can click on the Regex to go to the next screen, the software will pass the `RegexCard` the user clicked on to the Regex text input box in the next screen (the user can modify the Regex content) and wait for the user to enter the common language text. Alternatively, the user can click on the floating button marked with a plus sign in the bottom right corner to create a new next screen, in which the text box will have a hint to indicate that the corresponding text box needs to be entered with the corresponding text in the common language and the Regex content. Regardless of how the second screen is accessed, once the user has entered the corresponding content, the user can click "Compile Now".** This will invoke our back-end compiler and perform the calculations, and the resulting data will be sent to the third screen. **The third screen will show the contents of the Matched Pattern, visualization and suggestion. If the user is not satisfied with what he/she entered, he/she can go back to the second screen and re-enter it. Or if the user is satisfied with the input, he/she can choose to click the "Save" button, then he will go back to the first screen directly, and the input Regex will be saved to the database, where the duplicate Regex will be judged and the user will be informed that the saving did not happen.**
