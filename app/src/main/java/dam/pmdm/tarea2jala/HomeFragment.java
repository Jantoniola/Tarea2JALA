package dam.pmdm.tarea2jala;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

import dam.pmdm.tarea2jala.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private ArrayList<Personaje> personajes;
    private PersonajeRecyclerViewAdapter adaptador;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Inflamos el fragment_home
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        //Cargamos los datos en una lista.
        personajes = CargarDatos.loadDatos();

        /*
        El RecyclerView necesita dos datos para funcionar.
        Uno es el adaptador, al que se le envía el vector de la lista con los datos y
        el tipo de Layout
         */

        adaptador=new PersonajeRecyclerViewAdapter(personajes,getActivity());
        binding.personajesRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.personajesRecyclerview.setAdapter(adaptador);

    }

    @Override
    public void onStart() {
        super.onStart();
        // El ToolBar tiene el título del Fragment. Vamos a cambiarlo
        if (getActivity() != null) {

            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }
}