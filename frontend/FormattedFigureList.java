package frontend;

import backend.model.Figure;
import frontend.formattedFigures.FormattedFigure;

import java.util.ArrayList;

public class FormattedFigureList extends ArrayList<FormattedFigure> {

    public FormattedFigureList(){
        super();
    }

    public void addFigure(FormattedFigure figure) {
        if(figure != null) {
            this.add(figure);
        }
    }



    public Iterable<FormattedFigure> figures() {
        return new ArrayList<>(this);
    }
}
