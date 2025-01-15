public class Dog {
    public int weight;

    public Dog(int w) // Constructor
    {
        weight = w;
    }

    public void makeNoise() {
        if (weight < 10) {
            System.out.println("yipyipyip...");
        } else if (weight < 30) {
            System.out.println("bark. bark.");
        } else { // weight >= 30
            System.out.println("woof!");
        }
    }

    public static void method1() {
        System.out.println("static method should be called by class name.");
    }

    public static Dog maxDog(Dog d1, Dog d2) {
        if (d1.weight > d2.weight) {
            return d1;
        }

        return d2;
    }

    public Dog maxDog(Dog d2) {
        if (this.weight > d2.weight) {
            return this;
        }
        return d2;
    }
}

