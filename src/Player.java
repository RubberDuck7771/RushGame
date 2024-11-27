public class Player {
    private int health = 20;
    private boolean alive = true;




    //constructor
    public Player(){ }


    //player getting hurt
    public void monsterAttacked(int hit) {
        health -= hit;
    }


    //player healing
    public void heal() {
        health += 5;
    }


    //return player's health
    public int playerHealth() {
        return(health);
    }


    //return if the player is alive or not
    public boolean alive(){
        if(health <= 0){
            alive = false;
        }
        return(alive);
    }
}
