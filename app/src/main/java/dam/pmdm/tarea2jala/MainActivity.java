package dam.pmdm.tarea2jala;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import dam.pmdm.tarea2jala.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    /*
    Al haber puesto en el gadle 'viewBinding=true' se han creado las clases de cada uno de nuestros XML,
    de manera que podemos instanciarlas y usar los componentes diseñados en ellas si usar el 'findViewById'
    para cada uno de ellos.
    */

    private ActivityMainBinding binding;
    private NavController navController;
    private boolean snackMostrado = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        //inflamos el binding con los datos del view
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
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
        //Configuramos el navegador de Fragment
        navController = Navigation.findNavController(this, R.id.navegador_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController);
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
        return true;
    }

    /**
     * Definimos las acciones al seleccionar en cada una de las opciones del menú.
     *
     * @param item La opción seleccionada del menú.
     * @return Devuelve un valor boolean. Este valor es true si controlamos la implementación del elemento.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

        Toast.makeText(this, getString(R.string.texto_Toast)+personajeActual.getNombre(), Toast.LENGTH_SHORT).show();
        //Ahora cargamos el detailsfragment pasandole el Bundle

        Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);


    }
}