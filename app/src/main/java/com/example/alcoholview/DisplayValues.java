package com.example.alcoholview;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

class alcohol
{

    public double alc_level;
    public double alc_limit;

    alcohol()
    {
        alc_level = 0;
        alc_limit = 0;
    }

    alcohol(double alc_level, double alc_limit)
    {
        this.alc_level= alc_level;
        this.alc_limit = alc_limit;
    }

}


public class DisplayValues extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_values);

        Intent intent = getIntent();
        final String name = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        final String phoneno = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        // Capture the layout's TextView and set the string as its text


        final TextView textView1 = findViewById(R.id.textView5);
        TextView textView2 = findViewById(R.id.textView6);
        textView1.setText(name);
        textView2.setText(phoneno);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();

// Attach a listener to read the data at our posts reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                alcohol post = dataSnapshot.getValue(alcohol.class);
                TextView textView9 = findViewById(R.id.textView9);
                TextView textView10 = findViewById(R.id.textView10);
                textView9.setText(String.valueOf(post.alc_level));
                textView10.setText(String.valueOf(post.alc_limit));

                if (post.alc_level>post.alc_limit)
                {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneno));
                    intent.putExtra("sms_body", name+" is intoxicated and driving.");
                    startActivity(intent);
                    Toast.makeText(DisplayValues.this, "Your Breath Alcohol Level is too high. Stop Driving - Emergency Contact has been alerted", Toast.LENGTH_LONG).show();

                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}






