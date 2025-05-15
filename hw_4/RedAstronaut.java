public class RedAstronaut extends Player implements Impostor {
    private String skill;

    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill.toLowerCase();
    }

    public RedAstronaut(String name) {
        super(name, 15);
        this.skill = "experienced";
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
        if (!p.equals(this) && !p.isFrozen()) {
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
    public void freeze(Player p) {
      if (p.isFrozen() || this.isFrozen() || p instanceof RedAstronaut) {
        return;
      } else {
        if (this.getSusLevel() < p.getSusLevel()) {
          p.setFrozen(true);
        } else {
          this.setSusLevel(this.getSusLevel() * 2);
        }

        this.gameOver();
      }
    }

    @Override
    public void sabotage(Player p) {
      if (this.isFrozen() || p.isFrozen() || p instanceof RedAstronaut) {
        return;
      } else {
        if (this.getSusLevel() < 20) {
          int half = p.getSusLevel() / 2;
          p.setSusLevel(p.getSusLevel() + half);
        } else {
          int quarter = p.getSusLevel() / 4;
          p.setSusLevel(p.getSusLevel() + quarter);
        }

        this.gameOver();
      }
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof RedAstronaut) {
        RedAstronaut other = (RedAstronaut) o;
        return super.equals(other) && this.skill.equals(other.skill);
      }
      return false;
    }

    @Override
    public String toString() {
      String finalString = super.toString() + " I am an " + this.skill + " player!";
      return this.getSusLevel() > 15 ? finalString.toUpperCase() : finalString;
    }

    public String getSkill() {
      return this.skill;
    }

    public void setSkill(String skill) {
      this.skill = skill.toLowerCase();
    }
}