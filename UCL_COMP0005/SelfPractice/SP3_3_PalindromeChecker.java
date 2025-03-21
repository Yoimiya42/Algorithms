public class SP3_3_PalindromeChecker {
    public static void main(String[] args){

    }

    public boolean PalindromeChecker(String str){
        int front  = 0;
        int rear   = str.length() -1;

        while(front < rear){
            char f = Character.toLowerCase(str.charAt(front));
            char r = Character.toLowerCase(str.charAt(rear));
            if(f != r) {return false;}
            f++;
            r--;
        }

        return true;
    }
}
