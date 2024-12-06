package dam.pmdm.tarea2jala;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;

import dam.pmdm.tarea2jala.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private FragmentDetailsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflar el layout para este fragmento
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Rescatamos los datos pasados por el bundle
        if (getArguments() != null) {
            String nombre = getArguments().getString("nombre");
            int id = getArguments().getInt("imagen");
            String habilidades = getArguments().getString("habilidades");
            String descripcion = getArguments().getString("descripcion");

            //Ahora asignamos los datos a los elementos del fragment
            binding.nombre.setText(nombre);
            binding.image.setImageResource(id);
            binding.descripcion.setText(descripcion);
            binding.habilidades.setText(habilidades);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.titulo_detalles);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setIcon(null);
        }

    }
}