### Project Domain

An Android app that allows you to creating, testing, simplifying, and visualizing regular expression.

### Specification

### CRC model

<https://utoronto-my.sharepoint.com/:p:/g/personal/sudo_cheng_mail_utoronto_ca/EUcEI-rGDH9Kk4xAMEZJfisBRmDbR_N4gvdK3gY3CZauiw?e=k3KpjB
>

### Scenario Walk-Through

A user write an regex in `UI`, together with a test text. `Regex` received the input, then call `parser` to convert string to an AST. It then call `compiler` to convert AST into FSA object. The FSA object goes into `Matcher` to return the match result of the input test text. The result returns to UI, and UI shows the matched text in color. If user wants to see the visualization of FSA, then the FSA model will draw a picture and return to UI.


### Skeleton Program

To be upload.

### Progress Report
