package dam.pmdm.tarea2jala;

import java.util.ArrayList;
/**
 *
 * En estga clase cargaremos los datos de la aplicación desde cualquier fuente de datos.
 * En nuestro caso cargaremos los datos manualmente pero podrá ser, en un futuro, desde una
 * base de datos a cualquier otra fuente de datos.
 */
public class CargarDatos {

    //Los datos lo hemos sacado de la página oficial de Nintendo. "https://mario.nintendo.com/es/characters/"

    /**
     * Procedimiento donde cargamos los datos de los personajes de la aplicación
     * @return Devuelve un ArrayList de tipo Personaje
     */
    public static ArrayList<Personaje> loadDatos() {
        ArrayList<Personaje> personajes = new ArrayList<>();

        personajes.add(new Personaje("Mario",
                "El héroe principal del Reino Champiñón. Mario es muy positivo y siempre está alegre.",
                "Mario se destaca en deportes como tenis, golf, béisbol, fútbol y hasta en las carreras de karts. " +
                        "¡Es bueno en todos los deportes! Es plomero de profesión, pero la verdad es que es un experto en todos los oficios.\n" +
                        "\n" +
                        "Mario utiliza su poderosa habilidad para saltar y una gran cantidad de mejoras para enfrentarse a su archienemigo, Bowser.",
                true, R.drawable.mario));
        personajes.add(new Personaje("Luigi",
                "Hermano de Mario y héroe del Reino Champiñón.",
                "Luigi es amable, pero un poco de nervioso, especialmente si hay fantasmas por ahí. Sin embargo, sus habilidades igualan las habilidades de Mario, " +
                        "así que cuando estos hermanos se unen, no hay nada que no puedan lograr.\n" +
                        "\n" + "Luigi es más alto y puede saltar más alto que Mario. Si pones atención, también podrás notar que la forma de su bigote también " +
                        "es un poco diferente.",
                true, R.drawable.luigi));
        personajes.add(new Personaje("Bowser",
                "El rey de los Koopas. Bowser es el archienemigo de Mario y siempre está causando estragos en el Reino Champiñón.",
                "Bowser cuenta con muchos secuaces que incluyen a los Koopas, Goombas, Bill Bala y Shy Guys. Cada vez que le pone la mirada al Reino Champiñón sus planes se ven " +
                        "frustrados por Mario y sus amigos.\n" +
                        "\n" +
                        "Este poderoso enemigo posee una gran fuerza y hasta puede escupir fuego.",
                false, R.drawable.bowser));
        personajes.add(new Personaje("Princesa Peach",
                "La querida princesa del Reino Champiñón.",
                "La princesa Peach siempre está lista para participar en una gran variedad de deportes, y también le encanta hornear y cocinar.\n" +
                        "\n" +
                        "Ella y Mario son muy buenos amigos y siempre están dispuestos a ayudarse mutuamente cuando pueden.",
                true, R.drawable.peach));
        personajes.add(new Personaje("Wario",
                "El autoproclamado archienemigo de Mario.",
                "Wario y Mario se conocen desde que eran bebés. Posee una personalidad escandalosa y no se preocupa por las pequeñas cosas. Le encanta el ajo y dinero.",
                false, R.drawable.wario));
        personajes.add(new Personaje("Waluigi",
                "Amigo y cómplice de Wario. Waluigi es el autoproclamado rival de Luigi.",
                "Waluigi está dispuesto a esforzarse para derrotar a Mario y a Luigi, aunque solo sea para molestarlos." +
                        " Sus largos brazos y piernas lo ayudan a mantenerse competitivo en los deportes.",
                false, R.drawable.waluigi));
        personajes.add(new Personaje("Toad",
                "Residente del Reino Champiñón, trabaja al servicio de la Princesa Peach.",
                "Toad es muy alegre y leal. Hace todo lo posible por ayudar a Mario y a Luigi en sus esfuerzos por proteger el Reino Champiñón de Bowser, incluso si" +
                        " eso significa enfrentarse al peligro durante en el proceso.",
                true, R.drawable.toad));

        personajes.add(new Personaje("Yoshi",
                "El confiable compañero de Mario originario de la Isla de Yoshi.",
                "Yoshi es amable y tranquilo. Utiliza su larga lengua para comerse las frutas y a los enemigos, los cuales puede convertir en huevos que podrá arrojar.",
                true, R.drawable.yoshi));

        personajes.add(new Personaje("Daisy",
                "La princesa de Sarasaland.",
                "Daisy es alegre, enérgica y con un corazón muy valiente. Disfruta de jugar diferentes deportes con Mario y sus amigos.",
                true, R.drawable.daisy));

        personajes.add(new Personaje("Bowser jr.",
                "Hijo único de Bowser, el rey de los Koopas.",
                "Bowser Jr. es pequeño pero ha heredado la gran fuerza de su padre.\n" +
                        "\n" + "Se vuelve berrinchudo si las cosas no salen como él quiere. Es conocido por ser muy problemático y puede ser un poco egoísta.",
                false, R.drawable.bowserjr));
        personajes.add(new Personaje("Boo",

                "Unos traviesos fantasmas que suelen deambular en lugares oscuros y abandonados.",
                "Puede que estos fantasmas sean espeluznantes, pero también son increíblemente tímidos. " +
                        "Se congelarán en su lugar y se cubrirán los ojos si alguien los mira directamente.",
                false, R.drawable.boo));
        personajes.add(new Personaje("Donkey Kong",
                "El rey de la jungla.",
                "Donkey Kong puede lanzar gigantescos barriles con la mayor facilidad, y su fuerza es tan grande que el suelo tiembla cada vez que lo golpea.\n" +
                        "\n" + "Le encantan los plátanos y siempre guarda con una gran cantidad en su hogar.",
                true, R.drawable.donkeykong));

        return personajes;
    }
}
