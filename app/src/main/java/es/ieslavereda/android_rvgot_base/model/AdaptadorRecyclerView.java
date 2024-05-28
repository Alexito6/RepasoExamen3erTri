package es.ieslavereda.android_rvgot_base.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ieslavereda.android_rvgot_base.R;

public class AdaptadorRecyclerView extends RecyclerView.Adapter<AdaptadorRecyclerView.ViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Personaje> personajes;
    private View.OnClickListener onClickListener;
    public AdaptadorRecyclerView(Context context){
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.personajes=PersonajeRepository.getInstance().getPersonajes();
    }
    @NonNull
    @Override
    public AdaptadorRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.simple_element,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorRecyclerView.ViewHolder holder, int position) {
        Personaje personaje=personajes.get(position);
        holder.nombre.setText(personaje.getNombre());
        holder.casa.setText(personaje.getCasa().getNombre());
        holder.image.setImageResource(personaje.getCasa().getEscudo());
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView nombre,casa;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            image=itemView.findViewById(R.id.imageView);
            nombre=itemView.findViewById(R.id.textViewNombre);
            casa=itemView.findViewById(R.id.textViewCasa);
        }

    }
}

