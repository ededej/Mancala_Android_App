package sa.fakked.net.mancal_app;

/**
 * Created by Shelby on 3/17/17.
 */
/**
 * Created by FA on 3/9/2017.
 */
public class StoneStorageObject {
    private int ID;
    private int stones;
    private StoneStorageObject opposite;    //this will always be null in kalah.
    private StoneStorageObject next;       //these references will be used to ensure bidirectional integrity in
    private StoneStorageObject previous;   //the next and opposite associations.

    private Player owner;         //this reference is to ensure bidirectional integrity in the "has"
    //associations with Player.


    public StoneStorageObject(int ID, int stones) {
        this.ID = ID;
        this.stones = stones;
    }

    public void setNext(StoneStorageObject nextObj){
        if(this.next != nextObj){
            StoneStorageObject old = this.next;
            this.next = nextObj;

            if(old != null){
                old.setPrevious(null);
            }

            if(this.next != null){
                this.next.setPrevious(this);
            }
        }
    }

    public void setPrevious(StoneStorageObject prev){
        if(prev != this.previous){
            StoneStorageObject oldp = this.previous;
            this.previous = prev;
            if(oldp != null){
                oldp.setNext(null);
            }
            if(this.previous != null){
                this.previous.setNext(this);
            }
        }
    }

    public void setOpposite(StoneStorageObject opp){
        if(this.opposite != opp){
            StoneStorageObject oldop = this.opposite;
            this.opposite = opp;

            if(oldop != null){
                oldop.setOpposite(null);
            }

            if(this.opposite != null){
                this.opposite.setOpposite(this);
            }
        }
    }

    public void setOwnerk(Player owner){           //assumption: this object is being used as a kalah.
        if(this.owner != owner){
            Player oldOwner = this.owner;
            this.owner = owner;

            if(oldOwner != null){
                if(oldOwner.getKalah() == this){
                    oldOwner.setKalah(null);
                }
            }

            if(this.owner != null){
                this.owner.setKalah(this);
            }
        }
    }

    public void setOwnerp(Player owner){            //assumption: this object is being used as a pit.
        if(this.owner != owner){
            Player oldOwner = this.owner;
            this.owner = owner;

            if(oldOwner != null){
                if(oldOwner.pitContains(this)){
                    oldOwner.pitRemove(this);
                }
            }

            if(this.owner != null){
                this.owner.pitAdd(this);
            }
        }
    }

    public int getID() {
        return ID;
    }

    public int getStones() {
        return stones;
    }
//    public int getStonesToString() {
//
//        return stones;
//    }
    public StoneStorageObject getOpposite() {
        return opposite;
    }

    public StoneStorageObject getNext() {
        return next;
    }

    public StoneStorageObject getPrevious() {
        return previous;
    }

    public Player getOwner() {
        return owner;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public void addStones(int stones){
        this.stones  += stones;
    }
}
