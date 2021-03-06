package view;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import checkers.Board;
import checkers.GUI;
import checkers.Piece;

/**
*This class is the runtime GUI. The user interacts with
*this to select moves.
* @author mcginl04
*/
public class BoardGUI extends javax.swing.JFrame implements GUI{
    private static final String root = "/view/Resources/";
    private static final ImageIcon EMPTY = new ImageIcon(BoardGUI.class.getResource(root + "empty.gif"));
    private static final ImageIcon EMPTY_HIGHLIGHT = new ImageIcon(BoardGUI.class.getResource(root + "emptyLighted.gif"));
    private static final ImageIcon BLACK = new ImageIcon(BoardGUI.class.getResource(root + "black.gif"));
    private static final ImageIcon BLACK_HIGHLIGHT = new ImageIcon(BoardGUI.class.getResource(root + "blackLighted.gif"));
    private static final ImageIcon RED = new ImageIcon(BoardGUI.class.getResource(root + "red.gif"));
    private static final ImageIcon RED_HIGHLIGHT = new ImageIcon(BoardGUI.class.getResource(root + "redLighted.gif"));
    private static final ImageIcon BLACK_KING = new ImageIcon(BoardGUI.class.getResource(root + "blackKing.gif"));
    private static final ImageIcon BLACK_KING_HIGHLIGHT = new ImageIcon(BoardGUI.class.getResource(root + "blackKingLighted.gif"));
    private static final ImageIcon RED_KING = new ImageIcon(BoardGUI.class.getResource(root + "redKing.gif"));
    private static final ImageIcon RED_KING_HIGHLIGHT = new ImageIcon(BoardGUI.class.getResource(root + "redKingLighted.gif"));
    private Board b; //update board after move
    private boolean isPieceSelected = false;
    private char playersTurn = 'b';
    private Piece selectedPiece;

    /** Creates new form BoardGUI */
    public BoardGUI() {
        initComponents();
        b = new Board();
        display(b);
        setVisible(true);
    }
    
    /** This method is called from within the constructor to
* initialize the form.
* WARNING: Do NOT modify this code. The content of this method is
* always regenerated by the Form Editor.
*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        logField = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        boardPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        saveItem = new javax.swing.JMenuItem();
        loadItem = new javax.swing.JMenuItem();
        closeItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        helpItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Checkers");

        boardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                onClicked(evt);
            }
        });
        boardPanel.setLayout(new java.awt.GridLayout(8, 8));

        fileMenu.setText("File");

        saveItem.setText("Save");
        fileMenu.add(saveItem);

        loadItem.setText("Load");
        fileMenu.add(loadItem);

        closeItem.setText("Close");
        closeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeItemActionPerformed(evt);
            }
        });
        fileMenu.add(closeItem);

        menuBar.add(fileMenu);

        helpMenu.setText("Help");
        helpItem.setText("Help");
        helpMenu.add(helpItem);
        helpItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpItemActionPerformed(evt);
            }

            private void helpItemActionPerformed(ActionEvent evt) {
                help();
            }
        });
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
        jScrollPane1.setViewportView(logField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>

    /**
* Closes the window and exits the program.
*/
    private void closeItemActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    /**
* Displays the board in the boardPanel using the board provided.
*
* @param b, the board to display
*/
    public void display(Board b) {
        this.b = b;
        boardPanel.removeAll();
        
        for(int row=0; row<8; row++) {
            for(int col=0; col<8; col++) {
                Piece p = b.getPiece(row, col);
                
                if(p.getColor() == 'B') {
                    if(p.isKing())
                        boardPanel.add(new JLabel(BLACK_KING));
                    else
                        boardPanel.add(new JLabel(BLACK));
                }
                else if(p.getColor() == 'R') {
                    if(p.isKing())
                        boardPanel.add(new JLabel(RED_KING));
                    else
                        boardPanel.add(new JLabel(RED));
                }
                else
                    boardPanel.add(new JLabel(EMPTY));
            }
        }
        pack();
    }
    
    /**
* Interprets the mouse click in boardPanel to find what piece was clicked
*/
    private void onClicked(java.awt.event.MouseEvent evt) {
        int row = (evt.getY()/BLACK.getIconHeight());
        int col = (evt.getX()/BLACK.getIconWidth());
        if(row > 7)
            row = 7;
        if(col > 7)
            col = 7;
        Piece p = b.getPiece(row,col);
        isPieceSelected = true;
        selectedPiece = p;
    }
    
    /**
* Appends the given string to the logField
* @param s, the string to append
*/
    public void append(String s) {
        logField.append(s);
    }

    /**
* Clears the console
*/
    public void clear()
    {
            logField.setText("");
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel boardPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem closeItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem helpItem;
    private javax.swing.JMenuItem loadItem;
    private javax.swing.JTextArea logField;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem saveItem;
    // End of variables declaration

    /**
* displays the board and highlights the moves provided in the boardPanel
*
* @param b, the board to display
* @param moves, the moves to highlight
* @param display, determines between moves('+') or jumps ('*')
*/
    @Override
    public void display(Board b, ArrayList<Piece> moves, char display, Piece orig) {
        this.b = b;
        boardPanel.removeAll();
        
        for(int row=0; row<8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = b.getPiece(row, col);
                boolean found = false;
                for (Piece move : moves) {
                    int[] pos = move.getPosition();
                    if (row == pos[0] && col == pos[1] ) {
                        found = true;
                        if (display == '+') {
                            boardPanel.add(new JLabel(EMPTY_HIGHLIGHT));
                        } else if (display == '*') {
                            switch (p.getColor()) {
                                case 'R':
                                    if (p.isKing()) {
                                        boardPanel.add(new JLabel(RED_KING_HIGHLIGHT));
                                    } else {
                                        boardPanel.add(new JLabel(RED_HIGHLIGHT));
                                    }
                                    break;
                                case 'B':
                                    if (p.isKing()) {
                                        boardPanel.add(new JLabel(BLACK_KING_HIGHLIGHT));
                                    } else {
                                        boardPanel.add(new JLabel(BLACK_HIGHLIGHT));
                                    }
                                    break;
                            }
                        }

                    }
                }
                if(found == false) {
                        if(p.equals(orig))
                        {
                                 switch (p.getColor()) {
                         case 'R':
                             if (p.isKing()) {
                                 boardPanel.add(new JLabel(RED_KING_HIGHLIGHT));
                             } else {
                                 boardPanel.add(new JLabel(RED_HIGHLIGHT));
                             }
                             break;
                         case 'B':
                             if (p.isKing()) {
                                 boardPanel.add(new JLabel(BLACK_KING_HIGHLIGHT));
                             } else {
                                 boardPanel.add(new JLabel(BLACK_HIGHLIGHT));
                             }
                             break;
                     }
                        }
                        else
                        {
         switch (p.getColor()) {
         case 'R':
         if (p.isKing()) {
         boardPanel.add(new JLabel(RED_KING));
         } else {
         boardPanel.add(new JLabel(RED));
         }
         break;
         case 'B':
         if (p.isKing()) {
         boardPanel.add(new JLabel(BLACK_KING));
         } else {
         boardPanel.add(new JLabel(BLACK));
         }
         break;
         case '-':
         boardPanel.add(new JLabel(EMPTY));
         }
                        }
                }
            }
        }
        pack();
    }

    /**
* Uses the selected piece from onClicked to see if it is a valid selection
* If so, it is returned to be used by the User class. Otherwise, it waits
* for another selection by onClicked
*
* @param b, the board to use
* @param color, the piece representing which player's turn it is
* @return Piece, the piece that is selected
*/
    @Override
    public Piece getMove(Board b, char color) throws Exception {
        int[] pos = new int[2];
        boolean valid = false;
        while (valid == false) {
            while (playersTurn != color && isPieceSelected == false) {
                try {
                    //do what you want to do before sleeping
                    Thread.currentThread().sleep(1000);//sleep for 1000 ms
                    //do what you want to do after sleeptig
                } catch (InterruptedException ie) {
                    //If this thread was intrrupted by nother thread
                }
            }
            this.b = b;
            pos = selectedPiece.getPosition();

            if (b.getPiece(pos[0], pos[1]).getColor() != color || b.validMoves(b.getPiece(pos[0], pos[1])).isEmpty()) {
                logField.append("\nThis is not a valid \nchoice.\n");
                isPieceSelected = false;
            } else {
                valid = true;
            }
        }
        if(playersTurn == 'B')
            playersTurn = 'R';
        else if(playersTurn == 'R')
            playersTurn = 'B';
        isPieceSelected = false;
        return b.getPiece(pos[0], pos[1]);
    }

    /**
* Determines which move to make using the move list provided. If a move is
* not valid, it waits for another potential move from onclicked.
*
* @param b, the board to use
* @param moves, the list of moves possible,
* @param color, the piece represention which player's turn it is
* @return Piece, the selected move
*/
    public Piece getMove(Board b, ArrayList<Piece> moves, char color, Piece orig) throws IOException {
        int[] pos = new int[2];
        boolean valid = false;
        while (valid == false) {
            while (playersTurn != color && isPieceSelected == false) {
                try {
                    //do what you want to do before sleeping
                    Thread.currentThread().sleep(1000);//sleep for 1000 ms
                    //do what you want to do after sleeptig
                } catch (InterruptedException ie) {
                    //If this thread was intrrupted by nother thread
                }
            }
            this.b = b;
            pos = selectedPiece.getPosition();

            if(b.getPiece(pos[0], pos[1]).getColor() == color)
                        {
                                for(Piece move : moves)
                                {
                                        if(move.equals(b.getPiece(pos[0], pos[1])))
                                        {
                                                valid = true;
                                        }
                                }
                        }
                        if(valid == false)
                        {
                                if(selectedPiece.equals(orig))//hack for deselect
                                {
                                        isPieceSelected = false;
                                        return null;
                                }
                                logField.append("\nThis is not a valid \nchoice.\n");
                                isPieceSelected = false;
                        }
        }
        if(playersTurn == 'B')
            playersTurn = 'R';
        else if(playersTurn == 'R')
            playersTurn = 'B';
        isPieceSelected = false;
        return b.getPiece(pos[0], pos[1]);
    }
    
    /**
* Appends a victory message to the log field
*/
    public void victory(char winner) {
        append("\n" + winner + " wins!");
    }

    /**
* Appends a help message to the logField
*/
    public void help() {
        logField.append("\nWelcome to \ncheckers! \nHere are the \ninstructions: \n");
        logField.append("\nTo select \na piece or move, \nclick the cell \nof the piece or \nspace you want.\nTo deselect a \npiece, simply \nclick on it!\n");
    }
}