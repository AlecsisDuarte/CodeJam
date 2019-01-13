
/**
 * --- Problem 
 * An alien robot is threatening the universe, using a beam that will destroy all algorithms knowledge. We have to stop it!
 * Fortunately, we understand how the robot works. It starts off with a beam with a strength of 1, and it will run a program that is a series of instructions, which will be executed one at a time, in left to right order. Each instruction is of one of the following two types:
 *
 * C (for "charge"): Double the beam's strength.
 * S (for "shoot"): Shoot the beam, doing damage equal to the beam's current strength.
 * For example, if the robot's program is SCCSSC, the robot will do the following when the program runs:
 * 
 * Shoot the beam, doing 1 damage.
 * Charge the beam, doubling the beam's strength to 2.
 * Charge the beam, doubling the beam's strength to 4.
 * Shoot the beam, doing 4 damage.
 * Shoot the beam, doing 4 damage.
 * Charge the beam, increasing the beam's strength to 8.
 * In that case, the program would do a total of 9 damage.
 * 
 * The universe's top algorithmists have developed a shield that can withstand a maximum total of D damage. But the robot's current program might do more damage than that when it runs.
 * 
 * The President of the Universe has volunteered to fly into space to hack the robot's program before the robot runs it. The only way the President can hack (without the robot noticing) is by swapping two adjacent instructions. For example, the President could hack the above program once by swapping the third and fourth instructions to make it SCSCSC. This would reduce the total damage to 7. Then, for example, the president could hack the program again to make it SCSSCC, reducing the damage to 5, and so on.
 * 
 * To prevent the robot from getting too suspicious, the President does not want to hack too many times. What is this smallest possible number of hacks which will ensure that the program does no more than D total damage, if it is possible to do so?
 * Input
 * The first line of the input gives the number of test cases, T. T test cases follow. Each consists of one line containing an integer D and a string P: the maximum total damage our shield can withstand, and the robot's program.
 * 
 * --- Output
 * For each test case, output one line containing Case #x: y, where x is the test case number (starting from 1) and y is either the minimum number of hacks needed to accomplish the goal, or IMPOSSIBLE if it is not possible.
 * 
 * --- Limits
 * 1 ≤ T ≤ 100.
 * 1 ≤ D ≤ 109.
 * 2 ≤ length of P ≤ 30.
 * Every character in P is either C or S.
 * Time limit: 20 seconds per test set.
 * Memory limit: 1GB.
 */
import java.util.*;
import java.io.*;

public class Solution {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
      int t = in.nextInt();
      for (int i = 1; i <= t; i++) {
        int maxDamage = in.nextInt();
        char[] program = in.nextLine().substring(1).toCharArray();
        Integer min = minimumChanges(maxDamage, program);
        if (min != null) {
          System.out.printf("Case #%d: %d\n", i, min);
        } else {
          System.out.printf("Case #%d: IMPOSSIBLE\n", i);
        }
      }
      in.close();
  }

  private static Integer minimumChanges(int d, char[] p) {
    int hacks = 0;
    while (calculatePower(p) > d){
      if (!makeSwap(p, p.length - 1)){
        return null;
      }
      ++hacks;
    }
    return hacks;
  }

  private static int calculatePower(char[] p) {
    int charge = 1;
    int power = 0;
    for (int i = 0; i < p.length; i++) {
      if (p[i] == 83) { //S
        power += charge;
      } else {
        charge *= 2;
      }
    }
    return power;
  }

  private static boolean makeSwap(char[] p, int index){
    int start = index - 1;
    int end = index;
    if (start < 0){
      return false;
    }
    if (p[start] == 67 && p[end] == 83){
      char temp = p[start];
      p[start] = p[end];
      p[end] = temp;
      return true;
    } else {
      return makeSwap(p, --index);
    }

  }
}