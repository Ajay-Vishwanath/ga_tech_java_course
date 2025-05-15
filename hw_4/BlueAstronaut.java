public class BlueAstronaut extends Player implements Crewmate {
  private int numTasks;
  private int taskSpeed;

  public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
    super(name, susLevel);
    this.numTasks = numTasks;
    this.taskSpeed = taskSpeed;
  }

  public BlueAstronaut(String name) {
    this(name, 15, 6, 10);
  }

  @Override
  void emergencyMeeting() {
    // Frozen player cannot call emergencyMeeting
    if (this.isFrozen()) {
      return;
    }

    Player mostSusPlayer = null;
    int maxSusLevel = Integer.MIN_VALUE;
    int numWithMaxSusLevel = 0;
    for (Player p : Player.getPlayers()) {
      if (!p.isFrozen()){
        if (p.getSusLevel() > maxSusLevel) {
          mostSusPlayer = p;
          maxSusLevel = p.getSusLevel();
          numWithMaxSusLevel = 1;
        } else if (p.getSusLevel() == maxSusLevel) {
          numWithMaxSusLevel += 1;
        }
      }
    }

    if (numWithMaxSusLevel == 1) {
      mostSusPlayer.setFrozen(true);
    }

    this.gameOver();
  }

  @Override
  public void completeTask() {
    if (this.isFrozen()) {
      return;
    }

    int initialNumTasks = this.numTasks;

    if (this.taskSpeed > 20) {
      this.numTasks -= 2;
    } else {
      this.numTasks -= 1;
    }

    if (this.numTasks <= 0) {
      this.setNumTasks(0);
    }

    if (this.numTasks == 0 && initialNumTasks > 0) {
      System.out.println("I have completed all my tasks");
      this.setSusLevel(this.getSusLevel() / 2);
    }
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof BlueAstronaut) {
      BlueAstronaut other = (BlueAstronaut) obj;
      return super.equals(obj) && this.numTasks == other.numTasks && this.taskSpeed == other.taskSpeed;
    }
    return false;
  }

  @Override
  public String toString() {
    String finalString = super.toString() + " I have " + this.numTasks + " leftover.";
    return this.getSusLevel() > 15 ? finalString.toUpperCase() : finalString;
  }

  public int getNumTasks() {
    return numTasks;
  }

  public void setNumTasks(int numTasks) {
    this.numTasks = numTasks;
  }
}