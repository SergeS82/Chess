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
        return canMoveToPosition(chessBoard, line, column, toLine, toColumn, true);
    }
    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn, boolean isCheckUnderAttack) {
        int l, c;
        if (super.canMoveToPosition(chessBoard, line, column, toLine, toColumn, isCheckUnderAttack)) {
            l = Math.abs(line-toLine);
            c = Math.abs(column-toColumn);
            if ((l == c && l == 1) || (l == 1 && c == 0) || (c == 1 && l == 0) /*|| (check && (l == 0 && (toColumn == 1 || toColumn == 6)))*/) return true;
            else {
                chessBoard.setReason("Король так не ходит.");
                return false;
            }
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard chessBoard, int line, int column){
        for (int l = 0; l < 8; l ++){
            for (int c = 0; c < 8; c++){
                if (chessBoard.board[l][c] == null || chessBoard.board[l][c].getColor().equals(this.getColor())) continue;
                else if (chessBoard.board[l][c].canMoveToPosition(chessBoard,l,c,line,column, false)) {
                    chessBoard.setReason("Король под ударом.");
                    return true;
                }
            }
        }
        return false;
    }
}
