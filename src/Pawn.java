public class Pawn extends ChessPiece {
    public Pawn(String color) {
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
            int al = toLine - line, ac = toColumn - column;
            if (column == toColumn) {
                if (((color.equals("White") && (toLine - line == 1 || (toLine - line == 2 && line == 1)))
                        || (color.equals("Black") && (toLine - line == -1 || (toLine - line == -2 && line == 6)))
                ) && chessBoard.board[toLine][toColumn] == null
                ) return super.isPathClear(chessBoard, line, column, toLine, toColumn, true);
                else {
                    chessBoard.setReason("Пешка так не ходит.");
                    return false;
                }
            } else {// поедание по диагонали, ??? нужно учесть битое поле
                if (chessBoard.board[toLine][toColumn] != null
                        && !chessBoard.board[toLine][toColumn].getColor().equals(this.color)
                        && Math.abs(ac) == 1
                        && (al == 1 && this.color.equals("White") || (al == -1 && this.color.equals("Black")))
                ) return true;
                else if (chessBoard.board[toLine][toColumn] == null
                        && chessBoard.isBittenField(toLine,toColumn)) return true;
                else {
                    chessBoard.setReason("Пешка так не ходит.");
                    return false;
                }
            }
        } else return false;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
