public class Gameplay {

    public static void main(String[] args) {
      BlueAstronaut blue_astronaut_1 = new BlueAstronaut("Bob", 20, 6, 30);
      BlueAstronaut blue_astronaut_2 = new BlueAstronaut("Heath", 30, 3, 21);
      BlueAstronaut blue_astronaut_3 = new BlueAstronaut("Albert", 44, 2, 0);
      BlueAstronaut blue_astronaut_4 = new BlueAstronaut("Angel", 0, 1, 0);
      RedAstronaut red_astronaut_1 = new RedAstronaut("Liam", 19, "experienced");
      RedAstronaut red_astronaut_2 = new RedAstronaut("Suspicious Person", 100, "expert");

      red_astronaut_1.sabotage(blue_astronaut_1);
      red_astronaut_1.freeze(red_astronaut_2);
      red_astronaut_1.freeze(blue_astronaut_3);
      blue_astronaut_3.emergencyMeeting();
      red_astronaut_2.emergencyMeeting();
      blue_astronaut_1.emergencyMeeting();
      blue_astronaut_2.completeTask();
      blue_astronaut_2.completeTask();
      blue_astronaut_2.completeTask();
      red_astronaut_1.freeze(blue_astronaut_4);
      red_astronaut_1.sabotage(blue_astronaut_1);
      red_astronaut_1.sabotage(blue_astronaut_1);
      red_astronaut_1.freeze(blue_astronaut_1);
      blue_astronaut_4.emergencyMeeting();
    }
}