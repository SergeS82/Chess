public class King extends ChessPiece{
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int l, c;
        if (super.canMoveToPosition(chessBoard, line, column, toLine, toColumn)) {
            l = Math.abs(line-toLine);
            c = Math.abs(column-toColumn);
            if ((l == c && l == 1) || (l == 1 && c == 0) || (c == 1 && l == 0) /*|| (check && (l == 0 && (toColumn == 1 || toColumn == 6)))*/) return true;
            else return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column){
        return false;
    }
}
