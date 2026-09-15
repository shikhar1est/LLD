package BuilderDesignPattern;

public class AfterBuilder {
    public class Car {
        // 1. All fields are immutable (final)
        private final String engine;
        private final int wheels;
        private final int airbags;
        private final boolean hasSunroof;
        private final boolean hasGps;

        // 2. Private constructor accessible only by the nested static Builder
        private Car(Builder builder) {
            this.engine = builder.engine;
            this.wheels = builder.wheels;
            this.airbags = builder.airbags;
            this.hasSunroof = builder.hasSunroof;
            this.hasGps = builder.hasGps;
        }

        // Getters only (no setters)
        public String getEngine() { return engine; }
        public int getWheels() { return wheels; }
        public int getAirbags() { return airbags; }
        public boolean hasSunroof() { return hasSunroof; }
        public boolean hasGps() { return hasGps; }

        // 3. Static nested builder class
        public static class Builder {
            // Required parameters
            private final String engine;
            private final int wheels;

            // Optional parameters initialized to sensible defaults
            private int airbags = 2;
            private boolean hasSunroof = false;
            private boolean hasGps = false;

            public Builder(String engine, int wheels) {
                this.engine = engine;
                this.wheels = wheels;
            }

            public Builder airbags(int airbags) {
                this.airbags = airbags;
                return this; // Allows method chaining
            }

            public Builder hasSunroof(boolean hasSunroof) {
                this.hasSunroof = hasSunroof;
                return this;
            }

            public Builder hasGps(boolean hasGps) {
                this.hasGps = hasGps;
                return this;
            }

            public Car build() {
                // Centralized validation point
                if (airbags < 0) {
                    throw new IllegalStateException("Airbags cannot be negative");
                }
                return new Car(this);
            }
        }
    }

    public static void main(String[] args) {
        // Readable, self-documenting, and thread-safe
        Car sportsCar = new Car.Builder("V8 Twin-Turbo", 4)
                .airbags(6)
                .hasSunroof(true)
                .hasGps(true)
                .build();

        Car basicCar = new Car.Builder("1.5L Inline-4", 4)
                .build();
        //comm
    }
}
