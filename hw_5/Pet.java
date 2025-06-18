public abstract class Pet {
    private String name;
    private double health;
    private int painLevel;

    public Pet(String name, double health, int painLevel) {
        System.out.println("Initial Pain Level: " + painLevel);
        this.name = name;
        this.health = Math.max(0.0, Math.min(health, 1.0));
        this.painLevel = Math.max(0, Math.min(painLevel, 10));
    }

    public String getName() {
      return name;
    }

    public double getHealth() {
      return health;
    }

    public int getPainLevel() {
      return painLevel;
    }

    public abstract int treat();

    public void speak() {
      if (painLevel > 5) {
        System.out.println(("Hello! My name is " + name).toUpperCase());
      } else {
        System.out.println("Hello! My name is " + name);
      }
    }

    public boolean equals(Object obj) {
      if (obj instanceof Pet) {
          Pet pet = (Pet) obj;
          return this.name.equals(pet.name);
      }
      return false;
    }

    protected void heal() {
      this.health = 1.0;
      this.painLevel = 1;
    }
}