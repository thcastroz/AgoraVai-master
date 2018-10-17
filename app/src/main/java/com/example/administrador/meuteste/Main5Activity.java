package com.example.administrador.meuteste;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class Main5Activity extends Activity {
Button btvoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        btvoltar = (Button) findViewById(R.id.btvoltar);


btvoltar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Main5Activity.this, Main3Activity.class);
        startActivity(intent);
    }
});


    }


}
