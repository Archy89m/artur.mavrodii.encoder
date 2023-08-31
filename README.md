Caesar Cipher Application

The application has 3 modes of operation:

ENCRYPT - encrypting a text file with a chosen key using the Caesar cipher method;
DECRYPT - decrypting a text file with a chosen key using the Caesar cipher method;
BRUTE_FORCE - decrypting a text file with automatic key selection.

Features

Main features have been developed according to requirements.

Additional features:

Added support for text in Ukrainian;
Automatic language detection;
Command-line interface support;
Automatic key discovery when using the BRUTE_FORCE command, based on letter frequency analysis in the text.

Project specifics:

The project starts by choosing the mode of operation - command line or arguments.
If the command line is selected, follow the application's prompts to generate the file.
When using arguments, the program will check the correctness of the entered arguments and generate the file. When using the BRUTE_FORCE command, the program will attempt to automatically find the key and display the decryption result for the first line. If the decryption is successful, the file will be generated. If unsuccessful, a new key guessing attempt will be made. If a key is not found after 9 attempts, the program will throw an exception suggesting to use a larger file.

Interesting solution:

When using the BRUTE_FORCE command, the algorithm compares the frequency of letters in the text and the frequency of letters in the encrypted text. The larger the text, the faster the key is found. After the command is run, the program asks whether the text appears real or encrypted. If it appears real, the file is saved, including the key in the filename.

What to focus on when reviewing the project:

Feedback on the appropriateness of the chosen architecture, the proper use of classes and the interrelationships between them would be appreciated.

Example usage scenarios:

Scenario ENCRYPT:

Start application:

Choose app mode, 1 - CLI, 2 - Arguments

1

Choose command, 1 - ENCRYPT, 2 - DECRYPT, 3 - BRUTE_FORCE

1

Write file name

C:\Java\Test\1.txt

Write integer key

15

New file created: C:\Java\Test\1[ENCRYPT].txt


Scenario DECRYPT:

Start application:

Choose app mode, 1 - CLI, 2 - Arguments

1

Choose command, 1 - ENCRYPT, 2 - DECRYPT, 3 - BRUTE_FORCE

2

Write file name

C:\Java\Test\1[ENCRYPT].txt

Write integer key

15

New file created: "C:\Java\Test\1[ENCRYPT"[DECRYPT]".txt"

Scenario BRUTE_FORCE:

Choose app mode, 1 - CLI, 2 - Arguments

1

Choose command, 1 - ENCRYPT, 2 - DECRYPT, 3 - BRUTE_FORCE

3

Write file name

C:\Java\Test\1[ENCRYPT].txt

The narrator begins with a discussion on the nature of grownups and their inability to perceive "important things". As a test to determine if a grownup is as enlightened as a child, he shows them a picture depicting a boa constrictor that has eaten an elephant. The grownups always reply that the picture depicts a hat, and so he knows to only talk of "reasonable" things to them, rather than the fanciful.

Does it look correct? Please, choose, 1 - "Yes", any other character - "No"

1

New file created: C:\Java\Test\1[ENCRYPT] (B key-15).txt
