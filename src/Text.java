public class Text {


    //constructor
    public Text(){ }


    //print messages
    public String message(String part){
        String message = "";
        if(part.equals("intro")) { //returns the intro messages
            String line1 = "You rush headfirst into the cave.";
            String line2 = "Your back is sticky with sweat from the battle raging outside.";
            String line3 = "All you can hear is the blood rushing through your head and the";
            String line4 = "hammering of your heart under the clank of your armor.";
            String line5 = "Your breath quickens with every deadend.";
            String line6 = "She couldn't have gone too far, could she?";
            String[] lines = new String[]{line1, line2, line3, line4, line5, line6};
            StringBuilder paragraph = new StringBuilder();
            for(int i=0;i<6;i++){
                paragraph.append(lines[i]).append("\n"); //creates a new line after each line
            }
            message = paragraph.toString();
        }
        return(message);
    }
