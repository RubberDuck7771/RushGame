import java.util.Scanner;


public class Runner {
    public static void main(String[] args) {
        Player hero = new Player();
        Scanner s = new Scanner(System.in);
        boolean fightOccuring = false;
        boolean playerAlive = true;


        System.out.println("");
        System.out.println("");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Rush");
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("");






        //context
        Text context = new Text();
        System.out.println(context.message("intro"));
        System.out.println("");




        //tunnel event
        System.out.println("You run into a clearing with two tunnels. Which do you go through? [A] or [B]");
        String tunnel = s.nextLine();
        int chance = (int) (Math.random()*2) + 1; //1 in 2 chance to go through the right door
        System.out.println("You descend into tunnel " + tunnel + "...");
        if (chance == 1){
            System.out.println("Suddenly, the floor beneath you collapses and you're caught in a rockslide!");
            System.out.println("A boulder slams into your leg! -5 health");
            hero.monsterAttacked(5);
            System.out.println("You now have " + hero.playerHealth() + "/20 health.");
            System.out.println("There's no time to attend to your leg, though.");
        } else if (chance == 2){
            System.out.println("You just barely fit through the tunnel with your armor on, but when you manage to escape it...");
        }




        //fight event
        fightOccuring = true;
        System.out.println("You find yourself entirely surrounded by monsters!");
        System.out.println("Gasping, you realize these monsters are the very ones she had chased into this cave.");
        System.out.println("Yet she was no where in sight.");
        System.out.println("Roaring, you raise your blade, ready to exact revenge.");
        Monster ogre = new Monster(25, 10);
        Monster slime = new Monster();
        Monster bokoblin = new Monster((int)(Math.random()*11) + 10, 5);
        int printOgreHealth = 1;
        int printSlimeHealth = 1;
        int printBokoblinHealth = 1;
        int turn = 1;


        //repeats until either all monsters are dead, or player is dead
        while(fightOccuring){
            System.out.println("");
            System.out.println("---------------------------------------------------------------------------------------------");
            System.out.println("");
            System.out.println("Turn " + turn + ":");
            turn++;


            //player action
            System.out.println("What do you do next?");
            System.out.println("[A] Slash: Deal 5 damage to all three monsters.");
            System.out.println("[B] Pummel: Deal 15 damage to one target.");
            System.out.println("[C] Heal: Gain 5 health back.");
            String choice = s.nextLine();
            if(choice.equals("A")){ //slash
                int hitChanceSlash = (int) (Math.random()*5)+1;
                if(hitChanceSlash == 1){ //slash has a 4 in 5 hit chance
                    System.out.println("Uh oh, your attack missed!");
                } else {
                    System.out.println("You slash at the monsters!");
                    ogre.playerSlashed();
                    slime.playerSlashed();
                    bokoblin.playerSlashed();
                }
            } else if (choice.equals("B")) { //pummel
                System.out.println("Which monster do you want to target?"); //player chooses target
                System.out.println("[A] Ogre (health: " + ogre.monsterHealth() + ")");
                System.out.println("[B] Slime (health: " + slime.monsterHealth() + ")");
                System.out.println("[C] Bokoblin (health: " + bokoblin.monsterHealth() + ")");
                String target = s.nextLine();
                int hitChancePummel = (int) (Math.random() * 5) + 1;
                if (hitChancePummel == 1 || hitChancePummel == 2) { //pummel has a 3 in 5 hit chance
                    System.out.println("Uh oh, your attack missed!");
                } else if (target.equals("A") && ogre.alive()) { //pummels the monster iff it's alive and atk doesn't miss
                    System.out.println("You pummel the ogre!");
                    ogre.playerPummeled();
                } else if (target.equals("B") && slime.alive()) {
                    System.out.println("You pummel the slime!");
                    slime.playerPummeled();
                } else if (target.equals("C") && bokoblin.alive()) {
                    System.out.println("You pummel the bokoblin!");
                    bokoblin.playerPummeled();
                } else {
                    System.out.println("There's nothing there, the monster is already dead!");
                }
            } else if (choice.equals("C")){ //heal
                System.out.println("You chug a potion. +5 health");
                hero.heal();
            }


            //monster action
            if (slime.alive()) { //slime attacks every turn if alive
                System.out.println("The slime tackles you! -1 health");
                hero.monsterAttacked(1);
            } else if (!slime.alive()) {
                printSlimeHealth = 0;
            }
            if (turn % 5 == 0 && ogre.alive()) { //ogre attacks every 5 turns if alive
                System.out.println("The ogre slams its warhammer into your chest! -10 health");
                hero.monsterAttacked(10);
            } else if (!ogre.alive()) {
                printOgreHealth = 0;
            }
            if (turn % 2 == 0 && bokoblin.alive()) { //bokoblin attacks every 2 turns if alive
                System.out.println("The bokoblin claws at you! -5 health");
                hero.monsterAttacked(5);
            } else if (!bokoblin.alive()) {
                printBokoblinHealth = 0;
            }


            //printing all the healths
            if(printOgreHealth == 1){
                System.out.println("Ogre: " + ogre.monsterHealth() + " health");
            } else if(printOgreHealth == 0){
                System.out.println("You have killed the ogre!");
                printOgreHealth = 5;
            }
            if(printSlimeHealth == 1){
                System.out.println("Slime: " + slime.monsterHealth() + " health");
            } else if(printSlimeHealth == 0){
                System.out.println("You have killed the slime!");
                printSlimeHealth = 5;
            }
            if(printBokoblinHealth == 1){
                System.out.println("Bokoblin: " + bokoblin.monsterHealth() + " health");
            } else if(printBokoblinHealth == 0){
                System.out.println("You have killed the bokoblin!");
                printBokoblinHealth = 5;
            }
            System.out.println("You: " + hero.playerHealth() + " health");


            //lose condition
            if(!hero.alive()){
                fightOccuring = false;
            } else if (hero.alive() && (!ogre.alive() && !slime.alive() && !bokoblin.alive())){
                System.out.println("All the monsters are gone. But so is she.");
                fightOccuring = false;
            }
        }




        if(!hero.alive()){ //messages when you lose
            System.out.println("YOU DIED. FILLED WITH REGRET.");
            System.out.println("Rerun this program if you want to try again!");
        } else { //messages when you win
            System.out.println("");
            System.out.println("You wander the tunnels for what feels like years.");
            System.out.println("Your steps are aimless. You feel lost in more ways than one.");
            System.out.println("But then you something.");
            System.out.println("WOOF WOOF WOOF");
            System.out.println("You run faster than you ever have before towards the sound.");
            System.out.println("The sounds grow louder and louder until--");
            System.out.println("Your dog leaps into your arms!!");
            System.out.println("Giddiness, happiness, and relief warm your soul as your best friend licks your");
            System.out.println("face through your visor.");
            System.out.println("");
            System.out.println("You quickly escape the cave together and walk away from the battlefield.");
            System.out.println("You and your dog settle down and live happily ever after.");
            System.out.println("THE END!!");
        }
    }
}
