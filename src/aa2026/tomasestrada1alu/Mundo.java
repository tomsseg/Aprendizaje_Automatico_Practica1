package aa2026.tomasestrada1alu;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import core.game.Observation;
import core.game.StateObservation;
import tools.Vector2d;

public class Mundo {

    private int Bloque;
    private int anchura;
    private int altura;

    private Point pYo;
    private Point dirYo = new Point(0, -1);

    private ArrayList<Point> posMeta;

    private ArrayList<Point> posMuros;

    private int[][] posBloques;
    private char[][] tablero;

    public Mundo(StateObservation stateObs) {
        Bloque = stateObs.getBlockSize();

        Dimension d = stateObs.getWorldDimension();
        anchura = d.width / Bloque;
        altura = d.height / Bloque;

        inicializarListas();
        inicializarTablero();
    }


    public void analizarMundo(StateObservation stateObs) {
        inicializarListas();
        inicializarTablero();

        leerAvatar(stateObs);
        leerObjetos(stateObs);


    }

    private void inicializarListas() {
        posMuros = new ArrayList<Point>();
        posMeta = new ArrayList<Point>();

    }

    private void inicializarTablero() {
        tablero = new char[altura][anchura];

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < anchura; x++) {
                tablero[y][x] = ' ';
            }
        }
    }


    private void leerAvatar(StateObservation stateObs) {
        Vector2d orientacion = stateObs.getAvatarOrientation();

        if (orientacion != null && !(orientacion.x == 0 && orientacion.y == 0)) {
            dirYo = new Point((int) orientacion.x, (int) orientacion.y);
        }

        Vector2d posicion = stateObs.getAvatarPosition();

        if (posicion != null) {
            int x = (int) posicion.x / Bloque;
            int y = (int) posicion.y / Bloque;

            if (dentroDelMapa(x, y)) {
                pYo = new Point(x, y);
                tablero[y][x] = 'A';
            }
        }
    }

    private void leerObjetos(StateObservation stateObs) {

        ArrayList<Observation>[][] grid = stateObs.getObservationGrid();

        for (ArrayList<Observation>[] columna : grid) {
            for (ArrayList<Observation> celda : columna) {
                for (Observation objeto : celda) {

                    int x = (int) objeto.position.x / Bloque;
                    int y = (int) objeto.position.y / Bloque;

                    if (!dentroDelMapa(x, y)) {
                        continue;
                    }

                    switch (objeto.category) {

                        // AVATAR
                        case 0:
                            if (objeto.itype == 1) {
                                tablero[y][x] = 'A';
                            } else {
                                tablero[y][x] = 'X';
                                System.out.println(
                                        "itype no vista en category 0: "
                                                + objeto.itype);
                            }
                            break;

                        // OBJETOS ESTÁTICOS
                        case 4:

                            // Muro / terreno
                            if (objeto.itype == 0) {
                                addSiNoExiste(posMuros, x, y);
                                tablero[y][x] = 'T';

                                // Plataforma de aterrizaje
                            } else if (objeto.itype == 2) {
                                addSiNoExiste(posMeta, x, y);
                                tablero[y][x] = 'M';

                            } else {
                                tablero[y][x] = 'X';
                                System.out.println(
                                        "itype no vista en category 4: "
                                                + objeto.itype);
                            }
                            break;

                        default:
                            tablero[y][x] = 'X';
                            System.out.println(
                                    "category no vista: "
                                            + objeto.category
                                            + " itype: "
                                            + objeto.itype);
                            break;
                    }
                }
            }
        }
    }

    private void addSiNoExiste(ArrayList<Point> lista, int x, int y) {
        for (Point p : lista) {
            if (p.x == x && p.y == y) {
                return;
            }
        }

        lista.add(new Point(x, y));
    }

    public boolean dentroDelMapa(int x, int y) {
        return x >= 0 && x < anchura && y >= 0 && y < altura;
    }

    public String toString() {

        if (tablero == null) {
            return "Mundo todavía no analizado";
        }

        StringBuffer sb = new StringBuffer();

        sb.append("Avatar: ").append(pYo).append("\n");
        sb.append("Direccion: ").append(dirYo).append("\n");
        sb.append("Meta: ").append(posMeta).append("\n");
        sb.append("Muros: ").append(posMuros).append("\n");


        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < anchura; x++) {
                sb.append(tablero[y][x]);
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
