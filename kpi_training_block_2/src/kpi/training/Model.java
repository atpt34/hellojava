package kpi.training;

import java.util.concurrent.ThreadLocalRandom;

public class Model {
    
    private static final int DEFAULT_RANGE_MAX = 100;
    private static final int DEFAULT_RANGE_MIN = 0;
    
    private int random;
    private int rangeMin = DEFAULT_RANGE_MIN;
    private int rangeMax = DEFAULT_RANGE_MAX;
    private int missCount = 0;

    public Model(int min, int max) {
        rangeMin = min;
        rangeMax = max;
        random = rand(min, max);
    }
    
    public void initRandom() {
        random = rand(rangeMin, rangeMax);
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

    public enum Result { LESS, HIT, MORE }
    
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
    
    public static int rand(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

}
