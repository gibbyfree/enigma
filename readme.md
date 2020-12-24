# Running the simulator

Ensure that Java has been installed on your machine. From the main directory, run the following commands in a terminal:

```javac Main.java
java Main *[day_key_file_location]* *input_file_location]*```

# Day key file format

In order to use the Enigma simulator, a *day key* must be provided. When Enigma machines were used during World War II, operators received a key each day that outlined how the machine should be configured for that day. A sample day key can be found in the main directory. 

| Line      | Description |
| ----------- | ----------- |
| (A,I) (J,F) (E,M) (Z,X) (W,O) (S,B)| **Plugboard configuration**: Which characters should be swapped at the plugboard? Pairs in parentheses, separated by spaces.|
| 2 3 1 | **Rotor order**: In what order should the rotors be placed into the machine? The first position in the list (2) represents the firstmost rotor, on the far right of the machine. Separated by spaces.|
| K W O | **Rotor orientation**: Which letter should appear on top of each rotor? The first character in the list (K) applies to Rotor 1, the second character to Rotor 2, and so on. Further information on the significance of this orientation is described in another section.|

The sample day key describes a configuration where the pairs (A,I), (J,F), (E,M), (Z,X), (W,O), and (S,B) are swapped at the plugboard. Rotor 1 displays K on top, and is in the far left position. Rotor 2 displays W on top, and is in the far right position. Rotor 3 displays O on top, and is in the middle position.

# Specifications

## Plugboard

The plugboard is capable of taking up to 13 swapped pairs. When the Enigma cipher was in use, typically 10 pairs were used. It's not possible to have more than one swap per character, i.e. (A,B) (B,P).

## Rotors

Rotor 1, 2, and 3 are based off of the historic Rotor I, II, and III that were used in Enigma I. Each rotor consists of a regular alphabet alongside a scrambled alphabet. This scrambled alphabet is the rotor's **wire configuration**. When the rotors are rotated, the regular alphabet shifts its indices along the wire configuration. Here are the wire configurations for each of the historic rotors:

| Rotor # | ABCDEFGHIJKLMNOPQRSTUVWXYZ |
|-----------|-----------|
|1|EKMFLGDQVZNTOWYHXUSPAIBRCJ|
|2|AJDKSIRUXBLHWTMCQGZNPYFVOE|
|3|BDFHJLCPRTXVZNYEIWGAKMUSQO|

So, when Rotor 1 is rotated such that the letter 'A' appears on top, an 'A' input would encipher as a 'E'. However, it's worth noting that each rotor rotates **at the time of initial input. So in reality, if 'A' appears on top when the 'A' key is pressed, Rotor 1 will first rotate so that B appears on top. This would cause an input of 'A' to encipher as 'K'. 

### Ring setting
In reality, each of these rotors also had a ring setting. I became interested in the Enigma cipher machine while reading *The Code Book* by Simon Singh. I took most of the information about the machine's from *The Code Book*. The book omits most information about the machine's ring setting, so the simulator does not currently support a ring setting. From my (limited) understanding, the ring setting just allows the operator to rotate both the wire configuration and the regular alphabet along each other during configuration.

## Reflector

At the end of the leftmost rotor, a reflector exists as a sort of stationary rotor, which maps the output of the leftmost rotor to a new character before "reflecitng" the current back through the leftmost rotor. Currently, the simulator uses reflector **UKW-B**, which was the standard reflector used during World War II. This reflector has the following wire specification: `YRUHQSLDPXNGOKMIEBFZCWVJAT`