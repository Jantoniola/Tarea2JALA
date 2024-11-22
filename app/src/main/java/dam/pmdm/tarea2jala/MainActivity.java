package dam.pmdm.tarea2jala;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import dam.pmdm.tarea2jala.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    /*
    Al haber puesto en el gadle 'viewBinding=true' se han creado las clases de cada uno de nuestros XML,
    de manera que podemos instanciarlas y usar los componentes diseñados en ellas si usar el 'findViewById'
    para cada uno de ellos.
    */

    private ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);

        //inflamos el binding con los datos del view
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);



        //Configuramos el navegador de Fragment
        navController = Navigation.findNavController(this, R.id.navegador_fragment);
        //La navgación del toolbar se va a gestionar a través de los fragment
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController);
    }

    /**
     * Sobreescribimos la creación del menú contextual     *
     * @param menu
     * @return
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
//                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//
//                        }
//                    })
                    .setNegativeButton("Aceptar", new DialogInterface.OnClickListener() {
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
        Bundle bundle=new Bundle();
        //Le pasamos el nombre del personaje
        bundle.putString("nombre", personajeActual.getNombre());
        //Le pasamos la dirección de la imagen
        bundle.putInt("imagen", personajeActual.getId());
        //Le pasamos las habilidades
        bundle.putString("habilidades", personajeActual.getHabilidades());
        //Le pasamso la discripción del personaje
        bundle.putString("descripcion", personajeActual.getDescripcion());

        //Ahora cargamos el detailsfragment pasandole el Bundle

        Navigation.findNavController(view).navigate(R.id.detailsFragment, bundle);



    }
}