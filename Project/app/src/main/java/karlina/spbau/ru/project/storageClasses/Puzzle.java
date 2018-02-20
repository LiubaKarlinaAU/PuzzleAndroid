package karlina.spbau.ru.project.storageClasses;

/**
 * This class is needed for representing a puzzle.
 * It contains puzzle picture location.
 */

class Puzzle {
    private int x;
    private int y;

    /**
     * Class constructor
     *
     * @param correctX - first correct coordinate
     * @param correctY - second correct coordinate
     */
    public Puzzle(int correctX, int correctY) {
        x = correctX;
        y = correctY;
    }

    /**
     * Get puzzle first coordinate
     *
     * @return first coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Get puzzle second coordinate
     *
     * @return second coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Check is this puzzle on its place
     *
     * @param i first coordinate to make comparing
     * @param j second coordinate to make comparing
     * @return boolean if puzzle located on correct place
     */
    public boolean isCorrect(int i, int j){
        return i == x && j == y;
    }
}