package patterns;

/**
 * Generate below pattern to number of times as provided in 'pattern' variable
 * & count will be the maximum number of characters in a row.
 * HGFEDCBA
   GFEDCBA
   FEDCBA
   EDCBA
   DCBA
   CBA
   BA
   A
   BA
   CBA
   DCBA
   EDCBA
   FEDCBA
   GFEDCBA
   HGFEDCBA
 * @author Nitin
 *
 */
public class Pattern {

    public static void main(String[] args) {

           int count = 8;
           int temp = count;
           int pattern = 4;
           int counter = 0;
           while(pattern > 0) {
                  if(counter == 1) {
                        temp--;
                  }
                  while(pattern%2 == 0 && temp > 0) {
                        for(int i = temp; i > 0; i--) {
                               char ch = (char)(64 + i);
                               System.out.print(ch);
                        }
                        System.out.println();
                        temp--;
                        counter = 0;
                  }
                  while(pattern%2 != 0 && temp <= count) {
                        for(int i = temp; i > 0; i--) {
                               char ch = (char)(64 + i);
                               System.out.print(ch);
                        }
                        System.out.println();
                        temp++;
                        if(temp == count)
                               counter++;
                  }
                  pattern--;
                  if(pattern%2 == 0){
                        temp = count;
                  }
                  else
                        temp = temp + 2;

           }
    }

}
