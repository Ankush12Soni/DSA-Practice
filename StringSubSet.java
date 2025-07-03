public class StringSubSet {
    public static void main(String[] args) {
        skipApple("","accapplebad");
    }
//    static void skip(String p,String up){
//        if(up.isEmpty()){
//            System.out.println(p);
//            return;
//        }
//         char ch = up.charAt(0);
//        if (ch == 'a') {
//             skip(p,up.substring(1));
//        }else{
//            skip(p+ch,up.substring(1));
//        }
//    }

    static void skipApple(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
//        char ch = up.charAt(0);
        if (up.startsWith("apple")) {
            skipApple(p,up.substring(5));
        }else{
            skipApple(p+up.charAt(0),up.substring(1));
        }
    }
}
