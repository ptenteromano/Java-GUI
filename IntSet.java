/*
 * Philip Tenteromano
 * 3/5/2018
 * Int set and Summable set with FX
 * Java Programming
 *
 * Super Class IntSet with primary functions
 *
 */
package setfx;

import java.util.Vector;

public class IntSet {
   private final int MAXSIZE = 10;
   private final int grow = 4;
   protected int numCount = 0;
   protected Vector<Integer> numSet = new Vector<Integer>(MAXSIZE, grow);

   protected boolean insert(int a) 
   {
       if (numSet.contains(a)) 
           return true;
       else {
           numSet.addElement(new Integer(a));
           return false;
       }
   }
   
   public boolean search(int a) 
   {
       int index = numSet.indexOf(a);
       if ( index != -1 )
           return true;
       else
           return false;
   }
   
   public boolean removeNum(int a) 
   {
       boolean found = false;
       found = numSet.removeElement(new Integer(a));
       return found;
   }
   public boolean clrVect() 
   {
       try {
           numSet.clear();
           return true;
       } catch(Exception e) {
           return false;
       }
   }
   
   public int sizeof() 
   {
       return numSet.size();
   }
   
   public String vectToString() 
   {
       return numSet.toString();
   }

   public boolean emptySet() {
       return numSet.isEmpty();
   }

}


