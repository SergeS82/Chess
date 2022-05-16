public class Pawn extends ChessPiece{
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (super.canMoveToPosition(chessBoard, line, column, toLine, toColumn)) {
            if (column == toColumn) {
                if ((color.equals("White") && (toLine - line == 1 || (toLine - line == 2 && line == 1)))
                        || (color.equals("Black")  && (toLine - line == -1 || (toLine - line == -2 && line == 6)))
                ) return true;
                else return false;
            } else return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
