package frontend.formattedFigures;

import java.util.ArrayList;
import java.util.List;

public class FormattedFigureList extends ArrayList<FormattedFigure> {

    public FormattedFigureList(){
        super();
    }

    public void addFigure(FormattedFigure figure) {
        if(figure != null) {
            this.add(figure);
        }
    }

    public void unselect(){
        for (FormattedFigure figure:this) {
            figure.unselect();
        }
    }

    public FormattedFigureList selectedFigures(){
        FormattedFigureList sF = new FormattedFigureList();
        for (FormattedFigure f: this) {
            if(f.isSelected()){
                sF.add(f);
            }
        }
        return sF;
    }
}
