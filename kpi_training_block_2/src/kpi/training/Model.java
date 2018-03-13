package kpi.training;

import java.util.concurrent.ThreadLocalRandom;

public class Model {
    
    private int random;
    private int rangeMin = 0;
    private int rangeMax = 100;
    private int missCount = 0;

    public Model() {
        random = rand(rangeMin, rangeMax);
    }
    
    private int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public int getRangeMin() {
        return rangeMin;
    }

    public void setRangeMin(int rangeMin) {
        this.rangeMin = rangeMin;
    }

    public int getRangeMax() {
        return rangeMax;
    }

    public void setRangeMax(int rangeMax) {
        this.rangeMax = rangeMax;
    }

    public int getMissCount() {
        return missCount;
    }

    public void setMissCount(int missCount) {
        this.missCount = missCount;
    }

    public enum Result { LESS, HIT, MORE };
    
    public Result processValue(int value) {
        if (value == random) {
            return Result.HIT;
        }
        ++missCount;
        if (value < random) {
            rangeMin = value + 1;
            return Result.LESS;
        }
        rangeMax = value - 1;
        return Result.MORE;
    }

    public boolean checkValueInRange(int val) {
        return val >= rangeMin && val <= rangeMax;
    }
}
