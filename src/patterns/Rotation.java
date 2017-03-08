package patterns;

/**
 * Rotation of the given matrix either NxN or NxM.
 * It will rotate the matrix to the given degree for rotation in 'rotateDegree' variable.
 * @author Nitin
 *
 */
public class Rotation {

    public static void main(String[] args) {

           int[][] array = {
                        { 1,2,3,4 },
                        { 5,6,7,8 },
                        { 9,0,1,2 },
                        { 3,4,5,6 }
           };


           int rows = array.length;
           int cols = 0;
           int rotateDegree = 90;
           int count = rotateDegree/90;
           
           if(rows != 0)
                  cols = array[0].length;
           else
                  System.exit(1);

           int[][] result;
           if(count%2 != 0)
                  result = new int[cols][rows];
           else
                  result = new int[rows][cols];

           int s = 0;
           int t = 0;

           while(count > 0) {
                  for(int i = 0; i < cols; i++) {
                        for(int j = rows-1; j >= 0; j--) {
                               int a = array[j][i];
                               result[s][t] = a;
                               t++;
                        }
                        s++;
                        t = 0;
                  }
                  count--;
                  int temp = cols;
                  cols = rows;
                  rows = temp;
                  array = result;
                  result = new int[cols][rows];
                  s = 0;
                  t = 0;
           }
           
           for(int i = 0; i < rows; i++) {
                  for(int j = 0; j < cols; j++) {
                        System.out.print(array[i][j] + " ");
                  }
                  System.out.println();
           }
    }
}
