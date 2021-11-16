## Progress Report (Phase 1)
### FrontEnd: 

We have finished the most part of the front end. We have a working front end with database, history and add function now. 
Our next step will be beautifying the front end, add some features and make it more user friendly (like `delete all` and `about`).
We also need to add visualization for the regex objects, and intergrate with the backend on the next phase. 

The frontend is developed by `HanruiJerryFan` and `RealFakeAccount`.
To specify, `HanruiJerryFan` did the activities and the view models.
`HanruiJerryFan` also did the Adapter and the click function for reviewing the history regex.
`RealFakeAccount` did the database and its interface, together with the activity chain of the activities.

I (`RealFakeAccount`) would like thanks to `HanruiJerryFan` for the hard work and the help during the whole UI development.
He is really responsible and friendly person, and a quick learner. I am really glad to have him as a part of our team.

### Backend:

We have completed implementation of text-matching, where we can test whether the complete input string will be accepted.
To complete the specifications, we now only need to generalize this implementation to also achieve pattern matching, i.e.
testing whether substrings of the input text are matched with the regex.

The current implementation can be broadly divided into four steps, as follows: given a regex string, we first convert it 
into a list of *tokens* using our lexicographical analyser (``lexer``); next, we construct an abstract syntax tree 
(``AST``) which is a binary tree representation of the lexer; then, we compile a non-deterministic finite-state automaton
(``NFA``) from the AST corresponding to the input regex string; finally, we convert the NFA to a deterministic 
finite-state automaton (``FSA``) from which we can directly check any test string for match.

`GaoJunxuan` (Kevin Gao) implemented the lexer and the parser; `MakoStrwlkr` (Arkaprava Choudhury) implemented part of 
the parser, the compiler, and the NFA classes; `BBrianH` (Brian Ho) implemented the DFA classes, the NFA-to-DFA 
conversion algorithm, and greatly refined the code for the NFA classes by adding design patterns.

The tests were jointly worked upon by the three authors named above and `FrayeY` (Franklin Yeung). `FrayeY` was also 
responsible for a majority of the design document, specifications and debugging the code.

### Acknowledgements

I, Arkaprava, would like to thank my teammates for their hard work in making sure that we have a fun project for this 
course. I feel that part of the reason why I have enjoyed working on this project is because it is a unique and relevant
subject that is often neglected by most computer science students, especially those not involved with the theory of computing,
despite being an incredibly prominent concept in today's digital era. I would firstly like to thank Kevin for proposing
such a great project idea, and also for having some of the cleanest code I have ever seen. Next, I thank Brian for all the late night coding sessions, where we were able to make tremendous progress on the project; in particular,
I note the amount of assistance I received from Brian in ensuring that I make the best use of the design patterns we have
learnt in this course. I would like to thank Franklin for all his contributions towards the administrative side of the project
and ensuring that we adhere to all deadlines; in addition, I thank Franklin for his detailed specifications and elaborate
design document, which he was mostly responsible for.

I would also like to put on record my appreciation for Letian and Hanrui, without whom we would not have such a simple,
intuitive graphical interface which sets us apart from many other course projects. I eagerly look forward to many more coding
sessions where we focus on integrating the front-end and back-end of our project.

Lastly, I would like to extend my thanks to all the course instructors and our TA for providing extremely valuable feedback
on our progress so far, and for making this course an enjoyable experience so far.