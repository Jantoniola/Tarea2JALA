package dam.pmdm.tarea2jala;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dam.pmdm.tarea2jala.databinding.PersonajeCardviewBinding;

public class PersonajeRecyclerViewAdapter extends RecyclerView.Adapter<PersonajeViewHolder> {
    private final ArrayList<Personaje> personajes;
    private final Context context;

    public PersonajeRecyclerViewAdapter(ArrayList<Personaje> personajes, Context context) {
        this.personajes = personajes;
        this.context = context;
    }


    @NonNull
    @Override
    public PersonajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonajeCardviewBinding binding = PersonajeCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PersonajeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonajeViewHolder holder, int position) {
        Personaje personajeActual = this.personajes.get(position);
//Cambiamos el fondo del CardView dependiendo de la variable 'amigo' de la clase de datos. Si es amigo ponemos el fondo azul, en el caso contrario rojo
        if (personajeActual.isAmigo()) {
            ((CardView) holder.itemView).setCardBackgroundColor(BLUE);
        } else {
            ((CardView) holder.itemView).setCardBackgroundColor(RED);
        }
        holder.bind(personajeActual);
        //Vamos a controlar el evento Click
        holder.itemView.setOnClickListener(view -> personajeClick(personajeActual,view));
    }

    private void personajeClick(Personaje personajeActual, View view) {
        ((MainActivity)context).personajeClicked(personajeActual,view);
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }
}
