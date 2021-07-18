package frontend;

import frontend.formattedFigures.FormattedFigure;

@FunctionalInterface
public interface FigureApply {

    public FormattedFigure apply(FormattedFigure figure);

}
