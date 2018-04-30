/*
 * Philip Tenteromano
 * 3/5/2018
 * Int set and Summable set with FX
 * Java Programming
 *
 * Sub Class SummableSet with sum function
 *
 */
package setfx;
import java.util.Collections;

public class SummableSet extends IntSet {
    public int Sum() {
        int index = 0, sum = 0;

        while (index < this.sizeof()) {
            sum += numSet.elementAt(index);
            index++;
        }
        return sum;
    }

    public String sortInOrder() {
        Collections.sort(numSet);
        return numSet.toString();
    }

    public String sortPostOrder() {
        Collections.sort(numSet);
        Collections.reverse(numSet);
        return numSet.toString();
    }

    public int getMax() {
        return Collections.max(numSet);
    }

    public int getMin() {
        return Collections.min(numSet);
    }
}
