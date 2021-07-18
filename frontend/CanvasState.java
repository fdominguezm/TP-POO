package frontend;

import frontend.formattedFigures.FormattedFigure;
import frontend.formattedFigures.FormattedFigureList;

import java.util.ArrayList;
import java.util.List;

public class CanvasState {
    private List<FormattedFigureList> state = new ArrayList<>();
    private int dim = 0;

    CanvasState () {
        state.add(new FormattedFigureList());
    }

    public void undo () {
        state().unselect();
        if (dim>0)
            dim--;
    }
    public void redo () {
        state().unselect();
        if (dim<state.size()-1)
            dim++;
    }

    public void addFigure (FormattedFigure figure) {
        if (figure == null)
            return;
        copyList();
        state().addFigure(figure);
    }

    public void apply (FigureApply apply) {
        FormattedFigureList aux = newState();
        for (FormattedFigure figure : aux) {
            state().addFigure(apply.apply(figure));
        }
    }

    public void toBack () {
        FormattedFigureList aux = newState();
        state().addAll(0, aux);
    }

    public FormattedFigureList state() {
        return state.get(dim);
    }

    private void copyList () {
        for (int i=dim+1; i< state.size(); i++) {
            state.remove(i);
        }
        FormattedFigureList aux = new FormattedFigureList();
        aux.addAll(state());
        state().unselect();
        state.add(aux);
        dim++;
    }

    private FormattedFigureList newState() {
        FormattedFigureList toReturn = new FormattedFigureList();
        toReturn.addAll(state().selectedFigures());
        copyList();
        state().removeAll(toReturn);
        return toReturn;
    }


}
