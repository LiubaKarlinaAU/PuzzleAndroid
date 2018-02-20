package karlina.spbau.ru.project.storageClasses;

/**
 * This class is needed for representing a puzzle.
 * It contains expected puzzle location.
 */

class Puzzle {
    private int expectedI;
    private int expectedJ;

    /**
     * Class constructor
     *
     * @param int correctX - first correct coordinate
     * @param int correctY - second correct coordinate
     */
    public Puzzle(int correctX, int correctY) {
        this.expectedI = correctX;
        this.expectedJ = correctY;
    }

    /**
     * Check is this puzzle on its place
     *
     * @param i first coordinate to make comparing
     * @param j second coordinate to make comparing
     * @return boolean if puzzle located on correct place
     */
    public boolean isCorrect(int i, int j){
        return i == expectedI && j == expectedJ;
    }
}