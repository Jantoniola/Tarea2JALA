package dam.pmdm.tarea2jala;

import android.graphics.drawable.Drawable;

/**
 * En esta clase definiremos el tipo de datos de un personaje con todos sus atributos.
 */
public class Personaje {


    // Establecemos todos los atributos de cada personaje.
    private String nombre;
    private String descripcion;
    private String habilidades;
    private boolean amigo;
    private int id;


    /**
     * Constructor con todos los atributos para la creación de un personaje
     *
     * @param nombre      Establece el nombre del personaje.
     * @param descripcion Una breve descripción del personaje.
     * @param habilidades Una descripción más detallada, con habilidades especiales y características.
     * @param amigo       Un boolean que determina si es amigo de Mario o enemigo.
     * @param id          El id de la imagen almacenada en Recursos.
     */

    public Personaje(String nombre,  String descripcion, String habilidades, boolean amigo,int id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.habilidades = habilidades;
        this.amigo = amigo;
        this.id=id;
    }

    //Vamos a crear solo los getter de los atributos ya que no necesitamos modificarlos una vez creados a traves del constructor

    public int getId() { return id; }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public boolean isAmigo() {
        return amigo;
    }
}
