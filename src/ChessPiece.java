abstract public class ChessPiece {
    protected String color;
    protected boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    abstract public String getColor();

    //����� ��� ���� ������� �����������, �������� � ���������������� �������
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn, boolean isCheckUnderAttack) {
        ChessPiece thisPiece = chessBoard.board[line][column];
        //����������� � �������� �������� ����
        if (thisPiece == null) {
            chessBoard.setReason(String.format("�� ��������� ������ [%s, %s] ����������� ������.", line, column));
            return false;
        } //������ ���� ������ �� ��������� ������
        else if (line == toLine && column == toColumn) {
            chessBoard.setReason(String.format("�������� ������ [%s, %s] �� ������ ���� ������ ��������� [%s,%s].", toColumn, toColumn, line, column));
            return false;
        } //�������� ������ �� ������ ���� ������ ���������
        else if (!(toLine < 8 && toColumn < 8 && toLine >= 0 && toColumn >= 0)) {
            chessBoard.setReason(String.format("�������� ������ [%s, %s] ������ ���� � �������� �����.", toLine, toColumn));
            return false;
        } // �������� ������ ������ ���� �� �����
        else if (!(line < 8 && column < 8 && line >= 0 && column >= 0)) {
            chessBoard.setReason(String.format("��������� ������ [%s, %s] ������ ���� � �������� �����.", toLine, toColumn));
            return false;
        } // ��������� ������ ������ ���� �� �����
        else if (chessBoard.board[toLine][toColumn] != null && chessBoard.board[toLine][toColumn].getColor().equals(thisPiece.getColor())) {
            chessBoard.setReason(String.format("������ [%s, %s] ������.", toLine, toColumn));
            return false;
        } //������ ���������� �� ����� ��� ����� ������ ���� �� �����
        else if (isCheckUnderAttack) {
            King pieceKing = null;
            int l1 = -1, c1 = -1;
            if (thisPiece.getColor().equals("White")) {
                pieceKing = (King) chessBoard.board[chessBoard.getWhiteKingLine()][chessBoard.getWhiteKingColumn()];
                l1 = chessBoard.getWhiteKingLine();
                c1 = chessBoard.getWhiteKingColumn();
            }else {
                pieceKing = (King) chessBoard.board[chessBoard.getBlackKingLine()][chessBoard.getBlackKingColumn()];
                l1 = chessBoard.getBlackKingLine();
                c1 = chessBoard.getBlackKingColumn();
            }
            if (pieceKing != null && pieceKing.isUnderAttack(chessBoard,(thisPiece instanceof King)?toLine:l1, (thisPiece instanceof King)?toColumn:c1)) {
                return false;
            }else return true;
        }
        else return true;
    }
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn){
        return canMoveToPosition(chessBoard, line, column, toLine, toColumn, true);
    }

    //�������� �������� �� ���� ��� �������� �� ������ � �� ���������� ��� ���� ����� ���� � ������
    public boolean isPathClear(ChessBoard chessBoard, int line, int column, int toLine, int toColumn, boolean canMove) {
        int al = toLine - line, ac = toColumn - column;
        ChessPiece thisPiece = chessBoard.board[line][column];
        if (canMove || canMoveToPosition(chessBoard, line, column, toLine, toColumn)) {
            for (int l = (line + Integer.compare(al, 0)), c = (column + Integer.compare(ac, 0)) //���������� ������ ����������� ������ �� ������� �� 1 ������� �� ������ ��� �� ���������
                 ; Math.abs(l - toLine) > 0 || Math.abs(c - toColumn) > 0 // ��������� ������ ��� ��������� � canMoveToPosition
                    ; l += Integer.compare(al, 0), c += Integer.compare(ac, 0)  //l+=(al==0)?0:(al<0)?-1:1, c+=(ac==0)?0:(ac<0)?-1:1
            ) {
                if (chessBoard.board[l][c] != null) {
                    chessBoard.setReason(String.format("�� ���� �� [%s, %s] � [%s, %s] ���� �������� [%s, %s].", line, column, toLine, toColumn, l, c));
                    return false;
                }
            }
            return true;
        } else return false;
    }

    abstract public String getSymbol();
}
