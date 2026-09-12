package BuilderDesignPattern;

//Without a builder, code relies on either telescoping constructors (excessive constructor overloading) or JavaBeans (setters).
// Both introduce clear defects

import AbstractFactoryPattern.BeforeAbstract;

public class BeforeBuilder {
    public class Car {
        private String engine;
        private int wheels;
        private int airbags;
        private boolean hasSunroof;
        private boolean hasGps;

        public Car(String engine, int wheels) {
            this(engine, wheels, 2, false, false);
        }

        public Car(String engine, int wheels, int airbags) {
            this(engine, wheels, airbags, false, false);
        }

        // Hard to read: which boolean is sunroof and which is GPS?
        public Car(String engine, int wheels, int airbags, boolean hasSunroof, boolean hasGps) {
            this.engine = engine;
            this.wheels = wheels;
            this.airbags = airbags;
            this.hasSunroof = hasSunroof;
            this.hasGps = hasGps;
        }
    }
        // Client usage:
// Error-prone: easy to swap boolean flags or integer positions accidentally
    Car car = new Car("V8", 4, 6, true, false);


    //Issues:
    // Easy to swap identical-type parameters (e.g., passing true, false instead of false, true), constructor explosion
    // for optional fields, and poor readability.
    //If using setters instead: The object remains mutable across its lifetime, and is left in an
    // inconsistent state midway through calling setters.
    public static void main(String[] args) {
    }
}
