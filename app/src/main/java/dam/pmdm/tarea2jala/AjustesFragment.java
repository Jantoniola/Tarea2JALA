package dam.pmdm.tarea2jala;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;
import java.util.Objects;

import dam.pmdm.tarea2jala.databinding.FragmentAjustesBinding;


public class AjustesFragment extends Fragment {

    private FragmentAjustesBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() != null) {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(R.string.ajustes);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setIcon(null);
            DrawerLayout drawerLayout = requireActivity().findViewById(R.id.drawer_layout);
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START); // Cierra el drawer
            }
            binding.lenguajeRadioGroup.setOnCheckedChangeListener(this::idiomaSeleccionado);
            comprobarIdioma();
        }
    }


    private void comprobarIdioma() {
        String lenguaje = "es";
        //Acedemos a nuestro fichero de configuración
        if (getActivity() != null) {
            SharedPreferences preferencias = getActivity().getSharedPreferences("lenguaje", Context.MODE_PRIVATE);
            //Leemos el valor almacenado o si no existe, le damos un valor por defecto. En este caso, Español.
            lenguaje = preferencias.getString("lenguaje", "es");
        }
        if (lenguaje.equals("es")) {
            binding.radioButtonEspaniol.setChecked(true);
        } else {
            binding.radioButtonIngles.setChecked(true);
        }
    }

    private void idiomaSeleccionado(RadioGroup radioGroup, int i) {
        if (i == R.id.radioButtonIngles) {
            cambiarIdioma("en");
        } else {
            cambiarIdioma("es");
        }
    }

    //Cambia el idioma de la aplicación
    public void cambiarIdioma(String lenguaje) {
        //Cambia el idioma
        Locale locale = new Locale(lenguaje);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        //Tenemos toda la aplicación cambiada al idioma nuevo menos la pantalla en la que nos encontramos
        //Debemos actualizarla

        actualizaPantalla();

        //Guardamos nuestro idioma en nuestro fichero de preferencias privado.

        //Primero creamos el objeto preferencias que apunta a un archivo de preferencias llamado lenguaje y que está en modo privado
        if (getActivity() != null) {
            SharedPreferences preferencias = getActivity().getSharedPreferences("lenguaje", Context.MODE_PRIVATE);
            //Creamos un objeto editor que n os permite editar este archivo
            SharedPreferences.Editor editor = preferencias.edit();
            //Metemos lo que queremos guardar como 'clave' -> 'valor'
            editor.putString("lenguaje", lenguaje);
            //Completar el proceso
            editor.apply();
        }
    }


    private void actualizaPantalla() {
        binding.textLenguaje.setText(R.string.texto_lenguaje);
        binding.radioButtonEspaniol.setText(R.string.text_espa);
        binding.radioButtonIngles.setText(R.string.text_ingles);
        if (getActivity() != null) {
            //Actualizamos el título de la ToolBar
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.ajustes);
        }
        //Ahora vamos a actualizar el menú lateral
        NavigationView navigationView = requireActivity().findViewById(R.id.navview);
        Menu menu = navigationView.getMenu();

        //Aquí el título del menú
        TextView titulo = navigationView.findViewById(R.id.titulo);
        titulo.setText(R.string.app_name);

        //Aquí los dos menuItems
        MenuItem menuItemAjuste = menu.findItem(R.id.op_ajustes);
        MenuItem menuItemHome = menu.findItem(R.id.op_home);
        menuItemAjuste.setTitle(R.string.ajustes);
        menuItemHome.setTitle(R.string.home);

        //Vamos ahora a cambiar el menuitem del texto del menú contextual
        // Accede a la actividad principal
        MainActivity activity = (MainActivity) requireActivity();

        // Obtenemos  el menú inflado a través de un metodo público para obtener el menú
        Menu menuDefault = activity.getToolbarMenu();
        if (menuDefault != null) {
            menuDefault.findItem(R.id.about_id).setTitle(R.string.about);
        }

    }
}