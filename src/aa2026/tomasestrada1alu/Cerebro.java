package aa2026.tomasestrada1alu;

import core.game.StateObservation;
import ontology.Types.ACTIONS;

public class Cerebro {

    private Mundo mundo;

    public Cerebro(StateObservation stateObs) {
        mundo = new Mundo(stateObs);
    }

    public Mundo getMundo() {
        return this.mundo;
    }

    public ACTIONS pensar(StateObservation stateObs) {
        return ACTIONS.ACTION_NIL;
    }


}
