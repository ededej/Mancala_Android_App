package sa.fakked.net.mancal_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Play_game extends AppCompatActivity {

    private String playerFirstName;
    private String playerSecondName;
    private TextView playerOneSetName;
    private TextView playerTwoSetName;
    Button playerOnePitOne, playerOnePitTwo,playerOnePitThree,playerOnePitFour,playerOnePitFive,playerOnePitSix;
    Button playerTwoPitone,playerTwoPitTwo,playerTwoPitThree,playerTwoPitFour,playerTwoPitFive,playerTwoPitSix;
    Button kalahOne,kalahTwo;
    Player one,two;
    private  StoneStorageObject k1;     //respective kalahs.
    private  StoneStorageObject k2;     //k1 always p1's kalah, and so on.

    private  StoneStorageObject h11;    //12 static houses that constitute the board.
    private  StoneStorageObject h12;    //next relations will be concrete.
    private  StoneStorageObject h13;    //so just print em without checking any next reference.
    private  StoneStorageObject h14;
    private  StoneStorageObject h15;    //h1 - h16 belong to p1.
    private  StoneStorageObject h16;

    private  StoneStorageObject h21;    //h21 - h26 belong to p2.
    private  StoneStorageObject h22;
    private  StoneStorageObject h23;
    private  StoneStorageObject h24;
    private  StoneStorageObject h25;
    private  StoneStorageObject h26;

    StoneStorageObject returnCurrentPit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        Intent intentMain = getIntent();
        playerFirstName = intentMain.getStringExtra(MainActivity.PLAYERONE);
        playerSecondName=intentMain.getStringExtra(MainActivity.PLAYERTWO);
        playerOneSetName= (TextView) findViewById(R.id.playerOneName);
        playerTwoSetName= (TextView) findViewById(R.id.playerTwoName);


        if(!playerFirstName.isEmpty()){
            playerOneSetName.setText("Player 1 turn: "+playerFirstName);
        }

        if(!playerSecondName.isEmpty()){
            playerTwoSetName.setText("Player 2: "+ playerSecondName);
        }
        one = new Player(playerFirstName);
        two = new Player(playerSecondName);

        setUpGame();
    }

    public void setUpGame(){
        setUpButton();
        setUpKalah();
        setUpPits();
        setUpBoard();
        addPitsToPlayer();

        one.setTurn(true);
        two.setTurn(false);
    }

    private void addPitsToPlayer() {
        //Give players their respective houses.
        one.pitAdd(h11);
        one.pitAdd(h12);
        one.pitAdd(h13);
        one.pitAdd(h14);
        one.pitAdd(h15);
        one.pitAdd(h16);

        two.pitAdd(h21);
        two.pitAdd(h22);
        two.pitAdd(h23);
        two.pitAdd(h24);
        two.pitAdd(h25);
        two.pitAdd(h26);
    }

    private void setUpBoard() {
        h11.setNext(h12);
        h12.setNext(h13);
        h13.setNext(h14);
        h14.setNext(h15);
        h15.setNext(h16);
        h16.setNext(k1);
        k1.setNext(h21);
        h21.setNext(h22);
        h22.setNext(h23);
        h23.setNext(h24);
        h24.setNext(h25);
        h25.setNext(h26);
        h26.setNext(k2);
        k2.setNext(h11);
        h11.setOpposite(h26);
        h12.setOpposite(h25);
        h13.setOpposite(h24);
        h14.setOpposite(h23);
        h15.setOpposite(h22);
        h16.setOpposite(h21);
    }

    private void setUpPits() {
        h11 = new StoneStorageObject(11,3);    //all pits have IDs similar to their
        h12 = new StoneStorageObject(12,3);    //variable names.
        h13 = new StoneStorageObject(13,3);
        h14 = new StoneStorageObject(14,3);    //all pits have 3 stones to begin with.
        h15 = new StoneStorageObject(15,3);
        h16 = new StoneStorageObject(16,3);

        h21 = new StoneStorageObject(21,3);
        h22 = new StoneStorageObject(22,3);
        h23 = new StoneStorageObject(23,3);
        h24 = new StoneStorageObject(24,3);
        h25 = new StoneStorageObject(25,3);
        h26 = new StoneStorageObject(26,3);
    }

    private void setUpKalah() {
        kalahOne=(Button) findViewById(R.id.buttonKalah1);
        k1 = new StoneStorageObject(10,0); //k1 is player 1's kalah.
        one.setKalah(k1);
        kalahTwo=(Button) findViewById(R.id.buttonKalah2);
        k2 = new StoneStorageObject(20,0); //k2 is p2's kalah.
        two.setKalah(k2);

    }

    private void setUpButton() {
        playerOnePitOne=(Button) findViewById(R.id.buttonPit11);
        playerOnePitTwo=(Button) findViewById(R.id.buttonPit12);
        playerOnePitThree=(Button) findViewById(R.id.buttonPit13);
        playerOnePitFour=(Button) findViewById(R.id.buttonPit14);
        playerOnePitFive=(Button) findViewById(R.id.buttonPit15);
        playerOnePitSix=(Button) findViewById(R.id.buttonPit16);

        playerTwoPitone=(Button) findViewById(R.id.buttonPit21);
        playerTwoPitTwo=(Button) findViewById(R.id.buttonPit22);
        playerTwoPitThree=(Button) findViewById(R.id.buttonPit23);
        playerTwoPitFour=(Button) findViewById(R.id.buttonPit24);
        playerTwoPitFive=(Button) findViewById(R.id.buttonPit25);
        playerTwoPitSix=(Button) findViewById(R.id.buttonPit26);
    }
    private void updateValuesOfPits() {
        playerOnePitOne.setText(String.valueOf(h11.getStones()));
        playerOnePitTwo.setText(String.valueOf(h12.getStones()));
        playerOnePitThree.setText(String.valueOf(h13.getStones()));
        playerOnePitFour.setText(String.valueOf(h14.getStones()));
        playerOnePitFive.setText(String.valueOf(h15.getStones()));
        playerOnePitSix.setText(String.valueOf(h16.getStones()));
        kalahOne.setText(String.valueOf(k2.getStones()));
        playerTwoPitone.setText(String.valueOf(h21.getStones()));
        playerTwoPitTwo.setText(String.valueOf(h22.getStones()));
        playerTwoPitThree.setText(String.valueOf(h23.getStones()));
        playerTwoPitFour.setText(String.valueOf(h24.getStones()));
        playerTwoPitFive.setText(String.valueOf(h25.getStones()));
        playerTwoPitSix.setText(String.valueOf(h26.getStones()));
        kalahTwo.setText(String.valueOf(k1.getStones()));


    }

    private boolean checkPlayerOneTurn() {
        Log.d("checkPlayerOneTurn:",""+one.isTurn());
        return one.isTurn();
    }
    private boolean checkPlayerTwoTurn() {
        Log.d("checkPlayerTwoTurn:", ""+two.isTurn());

        return two.isTurn();
    }


    private void doMovesOfCurrentStonesOnPit(Player currentPlayer, StoneStorageObject currentPit) {
        returnCurrentPit=currentPlayer.reDistributeCounterclockwise(currentPit);
        if(returnCurrentPit!=null) {
            setThePlayerTurn(returnCurrentPit, h11,currentPlayer);
            updateValuesOfPits();
            checkForWinner();
        }else{
            Toast.makeText(this, "Choose a pit that is not empty", Toast.LENGTH_LONG).show();
        }
    }

    private void setThePlayerTurn(StoneStorageObject returnCurrentPit, StoneStorageObject oldPit, Player currentPlayer) {
        int currentPitID=returnCurrentPit.getID();
        if(currentPitID==k1.getID()||(currentPitID==k2.getID())){
            currentPlayer.setTurn(true);
            return;
        }else if (returnCurrentPit.getStones()==1){
            currentPlayer.takeOppositePebbles(returnCurrentPit);
            setPlayersTurn(currentPlayer);
        }else{
            setPlayersTurn(currentPlayer);
        }
    }

    private void setPlayersTurn(Player currentPlayer) {
        if((currentPlayer.equals(one)) ){
            one.setTurn(false);
            two.setTurn(true);
            playerOneSetName.setText("Player 1: "+playerFirstName);
            playerTwoSetName.setText("Player 2 turn: "+playerSecondName + " -->Choose a pit");

        }else if((currentPlayer.equals(two))){
            one.setTurn(true);
            two.setTurn(false);
            playerOneSetName.setText("Player 1 turn: "+playerFirstName + " -->Choose a pit");
            playerTwoSetName.setText("Player 2: "+playerSecondName);
        }
    }

    private void checkForWinner() {
        if (one.allHousesEmpty()){
            Toast.makeText(this, "Game is finished", Toast.LENGTH_LONG).show();
            playerOneSetName.setText("Player: "+playerFirstName + " finished the game with :"+one.getKalah().getStones());
            playerTwoSetName.setText("Player: "+playerSecondName + " finished the game with :"+two.getKalah().getStones());
            one.setTurn(false);
            two.setTurn(false);
        }else if(two.allHousesEmpty()) {
            Toast.makeText(this, "Game is finished", Toast.LENGTH_LONG).show();
            playerOneSetName.setText("Player: "+playerFirstName + " finished the game with :"+one.getKalah().getStones());
            playerTwoSetName.setText("Player: "+playerSecondName + " finished the game with :"+two.getKalah().getStones());
            one.setTurn(false);
            two.setTurn(false);
        }
    }

    ///////////////////////////
    public void movePitOnePlayerOne(View view) {
        if(!checkPlayerOneTurn()){
            Toast.makeText(this, "Players two turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(one,h11);
        }
    }
    public void movePitTwoPlayerOne(View view) {
        if(!checkPlayerOneTurn()){
            Toast.makeText(this, "Players two turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(one,h12);

        }
    }
    public void movePitThreePlayerOne(View view) {
        if(!checkPlayerOneTurn()){
            Toast.makeText(this, "Players two turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(one,h13);
        }
    }

    public void movePitFourPlayerOne(View view) {
        if(!checkPlayerOneTurn()){
            Toast.makeText(this, "Players two turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(one,h14);
        }
    }
    public void movePitFivePlayerOne(View view) {
        if(!checkPlayerOneTurn()){
            Toast.makeText(this, "Players two turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(one,h15);

        }
    }
    public void movePitSixPlayerOne(View view) {
        if(!checkPlayerOneTurn()){
            Toast.makeText(this, "Players two turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(one,h16);
        }
    }
    public void movePitOnePlayerTwo(View view) {
        if(!checkPlayerTwoTurn()){
            Toast.makeText(this, "Players one turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(two,h21);
        }
    }
    public void movePitTwoPlayerTwo(View view) {
        if(!checkPlayerTwoTurn()){
            Toast.makeText(this, "Players one turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(two,h22);
        }
    }
    public void movePitThreePlayerTwo(View view) {
        if(!checkPlayerTwoTurn()){
            Toast.makeText(this, "Players one turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(two,h23);
        }
    }
    public void movePitFourPlayerTwo(View view) {
        if(!checkPlayerTwoTurn()){
            Toast.makeText(this, "Players one turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(two,h24);

        }
    }
    public void movePitFivePlayerTwo(View view) {
        if(!checkPlayerTwoTurn()){
            Toast.makeText(this, "Players one turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(two,h25);
        }
    }
    public void movePitSixPlayerTwo(View view) {
        if(!checkPlayerTwoTurn()){
            Toast.makeText(this, "Players one turn", Toast.LENGTH_LONG).show();
            return;
        }else {
            doMovesOfCurrentStonesOnPit(two,h26);
        }
    }
}
