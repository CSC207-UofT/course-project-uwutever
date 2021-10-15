# Feature 1: matching
1. User inputs a regular expression and a text for matching on the `interface`; for example, consider the regular expression `hallo`, and the text `yahallo, this is uwutever`;
2. A `RegEx` object is created
3. `Compiler` uses `Parser` and `Lexer` to converts the `RegEx` into `FSA`
4. `RegExController` calls `match` to use `FSA` to match with the given text.
5. The result of the matching is displayed on the `interface`.

# Feature 2: visualizing FSA
1. User inputs a regular expression and a text for matching on the `interface`
2. A `RegEx` object is created
3. `Compiler` uses `Parser` and `Lexer` to converts the `RegEx` into `FSA`
4. `FSADisplayer` converts the `FSA` into displayable style and display it on the `interface`. 
5. This function has not been implemented yet.