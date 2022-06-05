package codility;

/**
 * @author nitin
 * @Date 12-Sep-2021 
 */
public class NationalCodingWeekChallenge {
    public static void main(String... args) {
        System.out.println(solution("ababb")); // baaaa
        System.out.println(solution("abbbabb")); //babaaaa
        System.out.println(solution("aaabab")); // aaabab
    }
    
    // Golden Award : https://app.codility.com/cert/view/certVJBJYZ-2ZKNEN8HW8CJ566Y/details/
    public static String solution(String S) {
        int len = S.length();
        char[] sb = S.toCharArray();
        int start = 0;
        char[] chars = new char[3];
        for(int i = 0; i < len; i++) {
            if(sb[i] == 'a') {
                start = 0;
                chars[start++] = 'a';
            } else if(chars[0] == 'a') {
            chars[start++] = S.charAt(i);
            }
            while(start > 2 && chars[0] == 'a' && chars[1] == 'b' && chars[2] == 'b') {
                sb[i--] = 'a';
                sb[i--] = 'a';
                sb[i--] = 'b';
                start = 0;
                i--;
                if(i >= 0) {
                    chars[start++] = sb[i++];
                    chars[start++] = sb[i++];
                    chars[start++] = sb[i];
                }
            }
            if(i < 0)
               i = 1;
        }
        return new String(sb);
    }
}
