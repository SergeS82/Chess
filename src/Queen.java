public class Queen extends ChessPiece{
    public Queen(String color) {
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
            if ((l == c && l !=0) || (l > 0 && c == 0) || (c > 0 && l == 0)) return true;
            else return false;
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
