public class Bishop extends ChessPiece{
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        return canMoveToPosition(chessBoard, line, column, toLine, toColumn, true);
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn, boolean isCheckUnderAttack){
        int l, c;
        if (super.canMoveToPosition(chessBoard, line, column, toLine, toColumn, isCheckUnderAttack)) {
            l = Math.abs(line-toLine);
            c = Math.abs(column-toColumn);
            if (l == c && l != 0) return super.isPathClear(chessBoard, line, column, toLine, toColumn, true);
            else {
                chessBoard.setReason("Слон ходит по диагонали.");
                return false;
            }
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
