
public class DogLauncher {
    public static void main(String[] args) {
        Dog smalldog = new Dog(5);
        Dog mediumdog = new Dog(25);
        Dog hugedog = new Dog(50);

        Dog[] doghouse = new Dog[3];
        doghouse[0] = smalldog;
        doghouse[1] = mediumdog;
        doghouse[2] = new Dog(75);

        int i = 0;
        while (i < doghouse.length) {
            Dog.maxDog(doghouse[i], mediumdog).makeNoise();
            i++;
        }
    }
}
