public class Counter
{
    private int min;
    private int max;
    private int counter;
    private boolean hasRolledOver;

    public Counter() {
        this(0, Integer.MAX_VALUE);
    }
    
    public Counter(int min, int max) {
        if (min < max) {
            this.min = min;
            this.max = max;
            counter = min;
        } else {
            throw new CounterInitializationException(
                "Exception thrown. Maximum value is smaller than minimum value"
            );
        }
    }
    
    public boolean equals(Object otherObject) {
        boolean result = false;
        if (otherObject instanceof Counter) {
            if ((min == ((Counter) otherObject).min) && (max == ((Counter) otherObject).max) &&
                (counter == ((Counter) otherObject).counter) && (hasRolledOver == ((Counter) otherObject).hasRolledOver)) {
            result = true;
            }
        }
        return result;
    }
    
    public void increase() {
        counter++;
        if (counter > max) {
            counter = min;
            hasRolledOver = true;
        } else {
            hasRolledOver = false;
        }
    }
 
    public void decrease() {
        counter--;
        if (counter < min) {
            counter = max;
            hasRolledOver = true;
        } else {
            hasRolledOver = false;
        }
    }

    public int value() {
        return counter;
    }
    
    public boolean rolledOver() {
        return hasRolledOver;
    }
    
    public String toString() {
        return "\nCurrent counter's value is " + counter +".\nThe minimum value is "
        + min + ".\nThe maximum value is " + max + ".\nRolled over? " + String.valueOf(hasRolledOver) + "\n";		
    }
 
}
