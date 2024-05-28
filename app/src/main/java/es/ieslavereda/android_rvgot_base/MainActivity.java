package es.ieslavereda.android_rvgot_base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import es.ieslavereda.android_rvgot_base.model.AdaptadorRecyclerView;
import es.ieslavereda.android_rvgot_base.model.Personaje;
import es.ieslavereda.android_rvgot_base.model.PersonajeRepository;
import es.ieslavereda.android_rvgot_base.model.creadorDePersonajes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView recyclerView;
    private List<Personaje> personajes;
    private FloatingActionButton add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        add=findViewById(R.id.addPersonaje);
        AdaptadorRecyclerView adaptador=new AdaptadorRecyclerView(this);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result ->{
                    if (result.getResultCode()==RESULT_CANCELED){
                        Toast.makeText(this,"Se ha cancelado el proceso para crear un nuevo personaje",Toast.LENGTH_SHORT).show();
                    } else if (result.getResultCode()==RESULT_OK) {
                        Personaje personaje=(Personaje) result.getData().getExtras().get("personajeNuevo");
                        PersonajeRepository.getInstance().add(personaje);
                        adaptador.notifyDataSetChanged();
                    }
                }
        );
        add.setOnClickListener(view ->{
            Intent intent=new Intent(this, creadorDePersonajes.class);
            activityResultLauncher.launch(intent);
        });
    }
    @Override
    public void onClick(View view){
        Personaje personaje=PersonajeRepository.getInstance().get(recyclerView.getChildAdapterPosition(view));
        Intent intent=new Intent(this, ObservarPersonaje.class);
        intent.putExtra("personajeEnviado",personaje);
        startActivity(intent);
    }
}