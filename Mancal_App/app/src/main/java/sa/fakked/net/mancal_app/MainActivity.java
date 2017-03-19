package sa.fakked.net.mancal_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public final static String PLAYERONE = "sa.fakked.net.mancal_app.PLAYERONE";
    public final static String PLAYERTWO = "sa.fakked.net.mancal_app.PLAYERTWO";
    private String playerOne;
    private String playertwo;
    private EditText playerOneName;
    private EditText playertwoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView t = (TextView) findViewById(R.id.textRules);
        t.setText("Rules: \n"+
                            "1. Player 1 starts the game first\n" +
                            "2. Game is played in landscape mode view.\n" +
                            "3. The Kalah on the right of the player position is the players Kalah(house) of stones\n" +
                            "4. Each Pit starts with 3 stones on it. \n" +
                            "5. If the move ends on one of Kalah's the player has again the turn\n"+
                            "6. If the move ends on empty Pit the player gets the pits of the opposite side into his Kalah");
        playerOneName = (EditText) findViewById(R.id.playerOneInput);
        playertwoName = (EditText) findViewById(R.id.playerTwoInput);



    }
    public void startGameClick(View view){
        Intent playGameIntent = new Intent(MainActivity.this,Play_game.class);
        playerOne=playerOneName.getText().toString();
        playertwo=playertwoName.getText().toString();
        playGameIntent.putExtra(PLAYERONE,playerOne);
        playGameIntent.putExtra(PLAYERTWO,playertwo);

        startActivity(playGameIntent);
    }


}