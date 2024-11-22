package dam.pmdm.tarea2jala;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import dam.pmdm.tarea2jala.databinding.PersonajeCardviewBinding;

public class PersonajeViewHolder extends RecyclerView.ViewHolder {
    private PersonajeCardviewBinding binding;

    //En el constructor llamamos al constructor del padre y llenamos nuestro binding
    public PersonajeViewHolder(PersonajeCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    /**
     * Asociamos los datos a lo que visualizaremos en el RecyclerView
     *
     * @param personaje Objeto de datos que nos llega para llenar los componentes del CardView
     */
    public void bind(Personaje personaje) {

        //Asignamos cada elemento de la clase de datos a los elementos del CardView
        binding.nombre.setText(personaje.getNombre());
        binding.descripcion.setText(personaje.getDescripcion());

        binding.imagen.setImageResource(personaje.getId());
        binding.executePendingBindings();


    }
}
