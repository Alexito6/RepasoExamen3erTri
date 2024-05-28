package es.ieslavereda.android_rvgot_base.model;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.ieslavereda.android_rvgot_base.R;

public class creadorDePersonajes extends AppCompatActivity {
    private String casaSeleccionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creador_de_personajes);
        EditText nombre = findViewById(R.id.editTextNombrePersonaje);
        Spinner spinner = findViewById(R.id.spinner);
        Button aceptar = findViewById(R.id.buttonAceptar);
        Button cancelar = findViewById(R.id.buttonCancelar);
        List<String> casas=new ArrayList<>(Arrays.asList("Arryn","Baratheon","Greyjoy","Lannister","Stark","Targaryen","Tully","Tyrell"));
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,casas);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                casaSeleccionada=casas.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                setResult(RESULT_CANCELED);
                finish();
            }
        });
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                String nombrePersonaje=nombre.getText().toString();
                if (casaSeleccionada.equals("Arryn")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.ARRYN));
                }
                if (casaSeleccionada.equals("Baratheon")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.BARATHEON));
                }
                if (casaSeleccionada.equals("Lannister")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.LANNISTER));
                }
                if (casaSeleccionada.equals("Greyjoy")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.GREYJOY));
                }
                if (casaSeleccionada.equals("Stark")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.STARK));
                }
                if (casaSeleccionada.equals("Targaryen")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.TARGARYEN));
                }
                if (casaSeleccionada.equals("Tully")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.TULLY));
                }
                if (casaSeleccionada.equals("Tyrell")){
                    intent.putExtra("personajeNuevo",new Personaje(nombrePersonaje,Casa.TYRELL));
                }
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
