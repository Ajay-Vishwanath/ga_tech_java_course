public class Fly {
  private double mass;
  private double speed;

  // constructor with mass and speed
  public Fly(double mass, double speed) {
    this.mass = mass;
    this.speed = speed;
  }

  // constructor with mass
  public Fly(double mass) {
    this(mass, 10);
  }

  // constructor with no parameters
  public Fly() {
    this(5,10);
  }

  public double getMass() {
    return mass;
  }

  public double getSpeed() {
    return speed;
  }

  public void setMass(double mass) {
    this.mass = mass;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public String toString() {
    if (this.mass == 0){
      return "I'm dead, but I used to be a fly with a speed of " + String.format("%.2f", this.speed) + ".";
    } else {
      return "I'm a speedy fly with " + String.format("%.2f", this.speed) + " speed and " + String.format("%.2f", this.mass) + " mass.";
    }
  }

  public void grow(int mass) {
    for (int i=0; i < mass; i++) {
      this.mass += 1;
      if (this.mass <= 20){
        this .speed += 1;
      } else {
        this.speed -= .5;
      }
    }
  }

  public boolean isDead() {
    return this.mass == 0;
  }
}