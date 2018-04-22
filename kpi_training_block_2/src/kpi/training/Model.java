package kpi.training;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * @author atpt34
 *
 */
public final class Model {
    
    private static final int DEFAULT_RANGE_MAX = 100;
    private static final int DEFAULT_RANGE_MIN = 0;
    
    private final int random;
    private int rangeMin;
    private int rangeMax;
    private int missCount = 0;

    public Model() {
        rangeMin = DEFAULT_RANGE_MIN;
        rangeMax = DEFAULT_RANGE_MAX;
        random = rand(rangeMin, rangeMax);
    }
    
    private Model(int min, int max) {
        rangeMin = min;
        rangeMax = max;
        random = rand(min, max);
    }
    
    private Model(Model model) {
        rangeMax = model.rangeMax;
        rangeMin = model.rangeMin;
        random = model.random;
        missCount = model.missCount;
    }
    
    public static Model of(int min, int max) {
        return new Model(new Model(min, max));
    }
    
    public int getRangeMin() {
        return rangeMin;
    }

    public int getRangeMax() {
        return rangeMax;
    }

    public int getMissCount() {
        return missCount;
    }

    public void setMissCount(int missCount) {
        this.missCount = missCount;
    }

    public static enum Result { LESS, HIT, MORE }
    
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
