package sa.fakked.net.mancal_app;

/**
 * Created by Shelby on 3/17/17.
 */
import java.util.*;
/**
 * Created by FA on 3/9/2017.
 */
public class Player {
    private  Boolean turn;
    private String name;

    private LinkedHashSet<StoneStorageObject> pits;      //these will be the pits that the player can choose stones to play with.

    private StoneStorageObject kalah;                    //the player's kalah

    public Player(String name) {
        this.name = name;
        this.pits = new LinkedHashSet<StoneStorageObject>();
        this.turn = false;                                  //to start with, all players will have turn false.
    }

    public void setKalah(StoneStorageObject nwKalah){
        if(this.kalah != nwKalah){
            StoneStorageObject oldKlh = this.kalah;
            this.kalah = nwKalah;

            if(oldKlh != null){
                oldKlh.setOwnerk(null);
            }

            if(this.kalah != null){
                this.kalah.setOwnerk(this);
            }
        }
    }

    public boolean pitContains(StoneStorageObject pit){
        return this.pits.contains(pit);
    }

    public boolean pitRemove(StoneStorageObject pit){
        boolean changed = this.pits.remove(pit);
        if(changed && (pit != null)){
            pit.setOwnerp(null);
        }
        return changed;
    }

    public boolean pitAdd(StoneStorageObject pit){
        boolean changed = this.pits.add(pit);
        if(changed && (pit != null)){
            pit.setOwnerp(this);
        }
        return changed;
    }

    public StoneStorageObject getKalah(){
        return this.kalah;
    }

    public Set<StoneStorageObject> getPits(){
        return Collections.unmodifiableSet(this.pits);
    }

    //House:takeOppositePebbles
    //suppose the move ends in an empty pit. this pit is the
    //current pit, this method will take all the pebbles in the pit opposite
    //current pit and put it in the players'  kalah.
    public StoneStorageObject takeOppositePebbles(StoneStorageObject currentPit){
        int inHand = (currentPit.getOpposite()).getStones();
        (currentPit.getOpposite()).setStones(0);
        this.kalah.addStones(inHand);
        return currentPit;
    }

    // House:ReDistributeCounterclockwise
    //given a pit, take all the pebbles in the pit and redistribute
    //it to subsequent next pits. return the pit where the move ended.
    public StoneStorageObject reDistributeCounterclockwise(StoneStorageObject currPit){
        int inHand = currPit.getStones();
        if (inHand == 0){
            return null;        //If player tried to move an empty pit, return null.
        }
        currPit.setStones(0);
        while (inHand > 0){
            currPit = currPit.getNext();
            currPit.addStones(1);
            inHand --;
        }
        return currPit;
    }

    //boolean check if all of this player's pits
    //are empty.
    //boolean check if all of this player's pits
    //are empty.
    public boolean allHousesEmpty(){
        Iterator it = this.pits.iterator();
        while(it.hasNext()){
            StoneStorageObject cur = (StoneStorageObject) it.next();
            if(cur.getStones() != 0){ return false; }
        }
        return true;
    }

    public static final String PROPERTY_TURN = "turn";

    public boolean isTurn()
    {
        return this.turn;
    }

    public void setTurn(boolean value)
    {
        if (this.turn != value) {

            boolean oldValue = this.turn;
            this.turn = value;
        }
    }

    public Player withTurn(boolean value)
    {
        setTurn(value);
        return this;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj == this) {
//            return true;
//        }
//        if(!(obj instanceof Player)) {
//            return false;
//        }
//        Player p = (Player)obj;
//        return p.name.equals(name);
//    }
}