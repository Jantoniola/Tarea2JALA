package dam.pmdm.tarea2jala;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.snackbar.Snackbar;
import java.util.Locale;
import java.util.Objects;

import dam.pmdm.tarea2jala.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    /*
    Al haber puesto en el gadle 'viewBinding=true' se han creado las clases de cada uno de nuestros XML,
    de manera que podemos instanciarlas y usar los componentes diseñados en ellas si usar el 'findViewById'
    para cada uno de ellos.
    */
    private ActionBarDrawerToggle toggle;
    private ActivityMainBinding binding;
    private NavController navController;
    private boolean snackMostrado = false;
    private Menu toolbarMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cargarPreferencias();

        //inflamos el binding con los datos del view
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Ponemos la barra de navegación
        setSupportActionBar(binding.toolbar);
        //Ponemos el icono en la barra de navegación
        Objects.requireNonNull(getSupportActionBar()).setIcon(R.mipmap.ic_launcher_round);

        //Recuperamos la variable 'snackMostrado' para saber si ya ha sido mostrado, es decir, si cuando ejecutamos el oncreate, es por el inicio
        //de la aplicación o por que se ha ejecutado al girar la pantalla
        if (savedInstanceState != null) {
            snackMostrado = savedInstanceState.getBoolean("SNACK_MOSTRADO");
        }
        //Como la variable está inicializada a false, si es el inicio será false así es que comparamos, mostramos y hacemos true para que no se vuelva a mostrar

        if (!snackMostrado) {
            Snackbar.make(findViewById(android.R.id.content), R.string.texto_Snackbar, Snackbar.LENGTH_SHORT).show();
            snackMostrado = true;
        }


        // Obtener el NavController desde el NavHostFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.navegador_fragment);
        navController = navHostFragment.getNavController();


        //Vamos a configurar un listener del navcontroler para controlar que hacer en cada cambio de fragment
        navController.addOnDestinationChangedListener(this::onChangeView);

        configurarToggleMenu();
        navegacionDrawer();
        //Ponemos el icono de la hamburguesa dependiendo de si viene de un fragment o no. En el caso que venga de  un fragment la variable 'fragmento' será true
        if (getSupportActionBar() != null) {

                getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }

    }

    private void cargarPreferencias() {
        //Acedemos a nuestro fichero de configuración
        SharedPreferences preferencias = getSharedPreferences("lenguaje", Context.MODE_PRIVATE);

        //Leemos el valor almacenado o si no existe, le damos un valor por defecto. En este caso, Español.
        String lenguaje = preferencias.getString("lenguaje", "es");
        //Cambia el idioma
        Locale locale = new Locale(lenguaje);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    private void onChangeView(NavController navController, NavDestination navDestination, Bundle bundle) {
        if (toggle != null) {
            if (navDestination.getId() == R.id.detailsFragment || navDestination.getId() == R.id.ajustesFragment) {
                toggle.setDrawerIndicatorEnabled(false);
            } else {
                toggle.setDrawerIndicatorEnabled(true);
            }
        }
    }

    private void navegacionDrawer() {
        // Manejar la selección de elementos del menú
        binding.navview.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.op_home) {
                navController.navigate(R.id.homeFragment);
            }
            if (item.getItemId() == R.id.op_ajustes) {
                navController.navigate(R.id.ajustesFragment);
            }
            binding.drawerLayout.closeDrawers(); // Cerrar el menú
            return true;
        });


    }

    private void configurarToggleMenu() {
        // Configurar el ActionBarDrawerToggle de forma dinámica
        toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                R.string.open_drawer,
                R.string.close_drawer
        );
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    //Aquí vamos a guardar la variable 'snackMostrado' en el bundle
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("SNACK_MOSTRADO", snackMostrado);
    }

    /**
     * Sobreescribimos la creación del menú contextual
     *
     * @param menu Recurso tipo menú
     * @return Retorna tipo boolean.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_about, menu);
        toolbarMenu=menu;
        return true;
    }

    /**
     * Metodo que devuelve el menú inflado del toolbar, usado para cambiar el texto de idioma en la pantalla de ajustes
     * @return El menú contextual del ToolBar
     */
    public Menu getToolbarMenu() {
        return toolbarMenu;
    }
    /**
     * Definimos las acciones al seleccionar en cada una de las opciones del menú.
     *
     * @param item La opción seleccionada del menú.
     * @return Devuelve un valor boolean. Este valor es true si controlamos la implementación del elemento.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.about_id) {
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setTitle(R.string.about)
                    .setMessage(R.string.dialog)
                    .setNegativeButton(R.string.boton_aceptar, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Usamos el retroceso del NavController
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }


    public void personajeClicked(Personaje personajeActual, View view) {

      /*
        Tenemos que cargar el fragmen de los detalle pasandole los datos desde aquí.
        Para ello crearemos una estructura de datos que la enviaremos al fragmen destino.
        Consiste en una estructura, clave- valor, llamada Bundle.
       */
        Bundle bundle = new Bundle();
        //Le pasamos el nombre del personaje
        bundle.putString("nombre", personajeActual.getNombre());
        //Le pasamos la dirección de la imagen
        bundle.putInt("imagen", personajeActual.getId());
        //Le pasamos las habilidades
        bundle.putString("habilidades", personajeActual.getHabilidades());
        //Le pasamos la descripción del personaje
        bundle.putString("descripcion", personajeActual.getDescripcion());

        //Ponemos el Toast aquí porque su lo ponemos en la carga del fragment de detalles, cada vez que giramos lo vuelve a mostrar ya que lo vuelve a cargar.

        Toast.makeText(this, getString(R.string.texto_Toast) + personajeActual.getNombre(), Toast.LENGTH_SHORT).show();
        //Ahora cargamos el detailsfragment pasandole el Bundle

        Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);


    }
}