public class Dog extends Pet {
  private double droolRate;

  public Dog(String name, double health, int painLevel, double droolRate) {
    super(name, health, painLevel);
    if (droolRate <= 0) {
      this.droolRate = 0.5;
    } else {
      this.droolRate = droolRate;
    }
  }

  public Dog (String name, double health, int painLevel) {
    this(name, health, painLevel, 5.0);
  }

  public double getDroolRate() {
    return droolRate;
  }

  @Override
  public int treat() {
    int healTime;

    if (droolRate < 3.5) {
      healTime = (int) Math.ceil((getPainLevel() * 2) / getHealth());
    } else if (droolRate >= 3.5 && droolRate <= 7.5) {
      healTime = (int) Math.ceil((getPainLevel()) / getHealth());
    } else {
      healTime = (int) Math.ceil(getPainLevel() / (getHealth() * 2));
    }
    heal();

    return healTime;
  }

  @Override
  public void speak() {
    super.speak();
    String barkString = "";

    for (int i = 0; i < getPainLevel(); i++) {
      barkString += "bark ";
    }

    if (getPainLevel() > 5) {
      System.out.println(barkString.toUpperCase());
    } else {
      System.out.println(barkString);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Dog) {
      Dog dog = (Dog) obj;
      return super.equals(dog) && this.droolRate == dog.droolRate;
    }
    return false;
  }
}