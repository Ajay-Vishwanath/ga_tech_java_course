public class Cat extends Pet {
  private int miceCaught;

  public Cat(String name, double health, int painLevel, int miceCaught) {
    super(name, health, painLevel);
    if (miceCaught < 0) {
      this.miceCaught = 0;
    } else {
      this.miceCaught = miceCaught;
    }
  }

  public Cat (String name, double health, int painLevel) {
    this(name, health, painLevel, 0);
  }

  public int getMiceCaught() {
    return miceCaught;
  }

  @Override
  public int treat() {
    int healTime;

    if (miceCaught < 4) {
      healTime = (int) Math.ceil((getPainLevel() * 2) / getHealth());
    } else if (miceCaught >= 4 && miceCaught <= 7) {
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
    String meowString = "";

    for (int i = 0; i < getPainLevel(); i++) {
      meowString += "meow ";
    }

    if (getPainLevel() > 5) {
      System.out.println(meowString.toUpperCase().trim());
    } else {
      System.out.println(meowString.trim());
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Cat) {
      Cat cat = (Cat) obj;
      return super.equals(cat) && this.miceCaught == cat.miceCaught;
    }
    return false;
  }
}