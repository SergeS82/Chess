public class Horse extends ChessPiece{
    public Horse(String color) {
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
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn, boolean isCheckUnderAttack) {
        if (super.canMoveToPosition(chessBoard, line, column, toLine, toColumn, isCheckUnderAttack)) {
            if ((Math.abs(line - toLine)==2 && Math.abs(column - toColumn) ==1)
                || (Math.abs(line - toLine)==1 && Math.abs(column - toColumn) ==2)
               ) return true;
            else {
                chessBoard.setReason("Конь ходит буквой Г.");
                return false;
            }
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
