import java.lang.*;

public class StringBasic {
    public static void main(String[] args) {
        String str = "helo helo helo";

        // System.out.println(str);
        
        char [] ch = str.toCharArray();

        //Normal Print letteres

        // for(int i=0; i<ch.length ;i++){
        //     System.out.println(ch[i]);
        // }

        //For Reverse Prints

        // for(int i =ch.length -1;i>=0;i--){
        //     System.out.println(ch[i]);
        // }


        // Palindrom 

        // int left = 0;
        // int right = ch.length-1;
        
        // boolean ispalindrom = true;

        // while(left<right){
        //     if(str.charAt(left) != str.charAt(right)){
        //         ispalindrom = false;
        //         break;
        //     }

        //     left++;
        //     right--;
        // }


        // if(ispalindrom){
        //    System.out.println(" palindrom");
        // }else{
        //     System.out.println("not a palindrom");
        // }
       
        // remove spaces

        // String str2 = ""; 
        // for( int i=0;i<ch.length;i++){
           
        //     // Build in 

        //     // String str2 = "helo helo helo";
        //     // String result = str2.replaceAll(" ","");
        //     // System.out.println(result);

        //     char ch2 = str.charAt(i);

        //     if(ch2 != ' '){
        //        str2 += ch2;
        //     }

        // }
        // System.out.println(str2);
        

        // Reverse Each Word

        String sentence = "this is soldier boy";

        // String[] words = sentence.split(" ");

        // String result = "";

        // for(int i =0; i<words.length;i++){

        //     String word = words[i];
            
        //     for(int j= word.length()-1;j>=0;j--){
        //         result += word.charAt(j);
        //     }

        //     if(i != word.length()-1){
        //        result += " ";
        //     }
        // }

        // System.out.println(result);


        ///  reverse word order
        String result ="";
        for(int i=sentence.length()-1;i>=0;i--){
            char ch = ;
            result += ch;
        }
        
        System.out.println(result);

    }
}
