package karlina.spbau.ru.project;

import android.graphics.Canvas;

import karlina.spbau.ru.project.storageClasses.Drawer;
import karlina.spbau.ru.project.storageClasses.Greed;
import karlina.spbau.ru.project.storageClasses.Navigator;

/**
 * Created by liuba on 15.11.17.
 */

public class GameLogic {
    private static Canvas canvas;
    private static Drawer drawer;
    private Greed greed;
    private Puzzle[] puzzles;
    private boolean isPicked = false;
    private int pickedNumber;
    private Navigator navigator;

    public GameLogic( Canvas can, int size) {
        canvas = can;
        navigator = new Navigator(size, canvas.getWidth(), canvas.getHeight());
        greed = new Greed(navigator);

    }
/*
    public GameLogic( int size, int startPositionX,  int startPositionY, int imageSize ) {
        puzzles = new Puzzle[size * size];

        int Step = imageSize / size;

        startPositionX = startPositionX + Step / 2;
        startPositionY = startPositionY + Step / 2;

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                puzzles[i * size + j] = new Puzzle(startPositionX + Step * i, startPositionY + Step * j, size);

   }*/

    public boolean touchAt(float x, float y){
        if (!isPicked) {
            if (!greed.isEmpty(x, y)) {
                 isPicked = true;
                 pickedNumber = greed.getPuzzle(x, y);
            }
            return false;
        } else {
            if (greed.isEmpty(x, y)) {
                greed.setPuzzle(pickedNumber, puzzles[pickedNumber].getI(), puzzles[pickedNumber].getJ(), x, y);
                isPicked = false;
                return true;
            } else {
                return false;
            }
        }

    }

    private static class Puzzle {
        private int i;
        private int j;

        private int expectedI;
        private int expectedJ;

        private int size;

        public Puzzle(int correctX, int correctY, int size) {
            this.expectedI = correctX;
            this.expectedJ = correctY;

            this.size = size;
        }

        public void Draw(Canvas canvas)
        {

        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        public boolean isCorrect(){
            return i == expectedI && j == expectedJ;
        }
    }
}
