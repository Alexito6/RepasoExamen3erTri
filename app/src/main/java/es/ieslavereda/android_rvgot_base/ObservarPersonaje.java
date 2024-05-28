package es.ieslavereda.android_rvgot_base;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ieslavereda.android_rvgot_base.model.Personaje;

public class ObservarPersonaje extends AppCompatActivity {
    private ImageView imagenCasa;
    private TextView nomPersonaje;
    private TextView nomCasa;
    private Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.observar_personaje);
        imagenCasa=findViewById(R.id.imagenCasa);
        nomPersonaje=findViewById(R.id.nomPersonaje);
        nomCasa=findViewById(R.id.nomCasa);
        volver=findViewById(R.id.volver);
        Bundle extras=getIntent().getExtras();
        Personaje personaje=(Personaje) extras.get("personajeEnviado");
        assert personaje != null;
        nomPersonaje.setText(personaje.getNombre());
        nomCasa.setText(personaje.getCasa().getNombre());
        imagenCasa.setImageResource(personaje.getCasa().getEscudo());
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
