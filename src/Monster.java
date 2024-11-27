public class Monster {
    private int health;
    private int atkStrength;
    private boolean alive = true;


    //constructor, monsters' health can vary
    public Monster(int maxHealth, int strength){
        health = maxHealth;
        atkStrength = strength;
    }
    public Monster(){
        health = 10;
        atkStrength = 1;
    }




    //monster getting hurt
    public void playerSlashed() {
        health -= 5;
    }
    public void playerPummeled() {
        health -= 15;
    }


    //return monster's health
    public int monsterHealth() {
        return(health);
    }


    //return monster's atkStrength
    public int monsterAtkStrength(){
        return(atkStrength);
    }


    //return if the monster is alive or not
    public boolean alive(){
        if(health <= 0){
            alive = false;
        }
        return(alive);
    }
}
