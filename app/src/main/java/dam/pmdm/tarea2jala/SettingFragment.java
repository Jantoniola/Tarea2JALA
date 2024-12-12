package dam.pmdm.tarea2jala;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;
import java.util.Locale;


public class SettingFragment extends PreferenceFragmentCompat  {
    ListPreference listaIdiomas;
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.setting,rootKey);

        listaIdiomas=findPreference("idioma");
        listaIdiomas.setOnPreferenceChangeListener((preference, newValue) ->
        {
           String lenguaje=(String)newValue;
            //Cambia el idioma
            Locale locale = new Locale(lenguaje);
            Locale.setDefault(locale);

            Configuration config = new Configuration();
            config.setLocale(locale);
            getResources().updateConfiguration(config, getResources().getDisplayMetrics());
            //Tenemos toda la aplicación cambiada al idioma nuevo menos la pantalla en la que nos encontramos
            //Debemos actualizarla

            actualizaPantalla();


            return true;
        });
    }

    /**
     * Procedimiento para actualizar las partes que están cargadas y por lo tanto hay que cambiar de idioma de forma manual
     */
    private void actualizaPantalla() {

        listaIdiomas.setTitle(R.string.texto_lenguaje);
        listaIdiomas.setSummary(R.string.txt_setting);

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