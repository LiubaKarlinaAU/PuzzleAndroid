package karlina.spbau.ru.project;

/**
 * Created by liuba on 15.11.17.
 */

public class GameLogic {
    private Puzzle[] puzzles;

    public GameLogic( int size, int startPositionX,  int startPositionY, int imageSize ) {
        puzzles = new Puzzle[size * size];

        int Step = imageSize / size;

        startPositionX = startPositionX + Step / 2;
        startPositionY = startPositionY + Step / 2;

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                puzzles[i * size + j] = new Puzzle(startPositionX + Step * i, startPositionY + Step * j, size);
    }
    static private class Puzzle {
        private int currentPositionX;
        private int currentPositionY;

        private int correctPositionX;
        private int correctPositionY;

        private int size;
        // private Shape;

        public Puzzle(int correctPositionX, int correctPositionY, int size) {
            this.correctPositionX = correctPositionX;
            this.correctPositionY = correctPositionY;

            this.size = size;
        }

        public void Draw()
        {

        }
    }
}
