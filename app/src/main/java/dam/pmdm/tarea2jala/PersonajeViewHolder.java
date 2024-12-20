package dam.pmdm.tarea2jala;
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

        //Establecemos el fondo dependiendo de si el personaje es anigo o enemigo, aplicandoles un degradado en rojos si son enemigos y en azules si son amigos
        if (personaje.isAmigo()) {
            binding.cardlayoud.setBackgroundResource(R.drawable.degradado_amigo);
        } else {
            binding.cardlayoud.setBackgroundResource(R.drawable.degradado_enemigo);
        }
        //Asignamos cada elemento de la clase de datos a los elementos del CardView
        binding.nombre.setText(personaje.getNombre());
        binding.imagen.setImageResource(personaje.getId());
        binding.executePendingBindings();


    }
}
