SRTPIE001
Assignment 1: Tetris

It's tetris as you know and love it, except for my unique "twist", I-pieces no longer fall normally. Instead, you must at least have a score of 1, at which point you
can press the space-bar to spend a point and queue up an I-Piece as your next piece. Left and right arrow keys to move in their respective directions, and the up key rotates your piece.
If you manage to score 16 points, a green win screen will display. Upon losing, a red lose screen displays instead.

COMPILING AND RUNNING:
1: Install gradle, if you haven't yet. libGDX uses this when it builds your project. Instructions here: https://gradle.org/install/
2: Navigate to the tetris2 directory, and run the following command:
	gradle desktop:run
   I know this works using Windows Powershell, but it might work with the regular commandline too.

NB: I did this on windows, so if you're attempting to run it in another operating system I can't guarantee that the gradle command will work. Maybe try "gradlew" in place
of "gradle", as that's what the official libGDX documentation suggests (https://github.com/libgdx/libgdx/wiki/Gradle-on-the-Commandline).
