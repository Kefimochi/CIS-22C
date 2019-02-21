public class Rational {
    private int numerator; // top number of the fraction
    private int denominator; // bottom number of the fraction

    public Rational() {       
        this(1, 1);
    }
  
    public Rational(int n, int d) {
        if (d != 0) { 
            numerator = n;
            denominator = d;
            normalize();
        } else {
            throw new ZeroDenominatorException(
                "Exception is thrown, can't use 0 as denominator in Rational constractor"
            );
        } 
    }
    
    public int getNumerator() {
        return numerator;
    }
    
    public int getDenominator() {
        return denominator;
    }

    public Rational negate() { // -3/5 -> 3/5
        return new Rational(numerator * -1, denominator);
    }

    public Rational invert() { //  2/5 --> 5/2 
        return new Rational(denominator, numerator);
    }
 
    public Rational add(Rational other) {
        int nume1 = numerator;
        int den1 = denominator;
        int nume2 = other.getNumerator(); 
        int den2 = other.getDenominator();     
        
        if (den1 != den2) {
            nume1 *= den2;
            nume2 *= den1;
            den1 *= den2;
        }

        return new Rational(nume1 + nume2, den1);       
    }
      
    public Rational subtract(Rational other) {
        int nume1 = numerator;
        int den1 = denominator;
        int nume2 = other.getNumerator();
        int den2 = other.getDenominator();
        
        if (den1 != den2) {
            nume1 *= den2;
            nume2 *= den1;
            den1 *= den2;
        }
        return new Rational(nume1 - nume2, den1);
    }
   
    public Rational multiply(Rational other) {         
        return new Rational(numerator * other.getNumerator(), denominator * other.getDenominator());
    }
  
    public Rational divide(Rational other) {   
        Rational temp = new Rational(other.denominator, other.numerator); 
        temp = temp.multiply(this);
        temp.normalize();
        return temp;
    }
     
    private void normalize() {
        boolean isNegative = false;
        if (numerator < 0) {
            numerator *= -1;
            isNegative = !isNegative;
        }
        if (denominator < 0) {
            denominator *= -1;
            isNegative = !isNegative;
        }

        int gcdNum = gcd(numerator, denominator);

        if (gcdNum != 1) { //for gcd to work n & d have to be positive
            numerator /= gcdNum;
            denominator /= gcdNum;
        }

        if (isNegative) {
            numerator *= -1;
        }
    }
    
    private int gcd(int a, int b) {
        int result = 0;
        if(a < b) {
            result = gcd(b, a);
        } else if(b == 0) {
            result = a;
        } else {
            int remainder = a % b;
            result = gcd(b, remainder);
        }
        return result;
    }
}
