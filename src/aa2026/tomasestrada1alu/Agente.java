package aa2026.tomasestrada1alu;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types;
import tools.ElapsedCpuTimer;

public class Agente extends AbstractPlayer {


    private Cerebro cerebro;

    public Agente(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        cerebro = new Cerebro(stateObs);
    }

    @Override
    public Types.ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        System.out.print(cerebro.getMundo());
        System.out.println("___________________________________________________________________");
        System.out.println("___________________________________________________________________");
        System.out.println("___________________________________________________________________");
        return cerebro.pensar(stateObs);
    }
}
