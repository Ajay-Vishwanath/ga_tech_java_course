public class Frog {
  private String name;
  private int age;
  private boolean isFroglet;
  private double tongueSpeed;
  private static String species = "Rare Pepe";

  // constructor with name, age, and tongueSpeed
  public Frog(String name, int age, double tongueSpeed) {
    this.name = name;
    this.age = age;
    this.tongueSpeed = tongueSpeed;

    this.isFroglet = (this.age > 1 && this.age < 7) ? true : false;
  }

  // constructor with name, ageinYears, and tonguespeed
  public Frog(String name, double ageinYears, double tongueSpeed) {
   this(name, (int)(ageinYears *12), tongueSpeed);
  }

  // constructor with just name
  public Frog(String name) {
    this(name, 5, 5);
  }

  public void grow(int months){
    for (int i=0; i<months; i++){
      this.age += 1;
      if (this.age <=12){
        this.tongueSpeed += 1;
      } else if (this.age > 30 && this.tongueSpeed > 5){
        this.tongueSpeed -= 1;
      }
    }

    this.isFroglet = (this.age > 1 && this.age < 7) ? true : false;
  }

  public void grow(){
    this.grow(1);
  }

  public void eat(Fly fly){
    if (fly.isDead()){
      return;
    } else {
      boolean flyCaught = this.tongueSpeed > fly.getSpeed();
      if (flyCaught){
        if (fly.getMass() >= this.age / 2){
          this.grow();
        }
        fly.setMass(0);
      } else {
        fly.grow(1);
      }
    }
  }

  public String toString(){
    if (this.isFroglet){
      return "My name is " + this.name + " and I'm a rare froglet! I'm " + this.age + " months old and my tongue has a speed of " + String.format("%.2f", this.tongueSpeed) + ".";
    }

     return "My name is " + this.name + " and I'm a rare frog. I'm " + this.age + " months old and my tongue has a speed of " + String.format("%.2f", this.tongueSpeed) + ".";
  }

  public static String getSpecies(){
    return species;
  }

  public static void setSpecies(String species){
    Frog.species = species;
  }
}