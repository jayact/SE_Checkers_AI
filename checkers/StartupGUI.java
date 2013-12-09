package checkers;
import Algorithms.*;

/**
*T
* @author mcginl04
*/
public class StartupGUI extends javax.swing.JFrame {

    private static boolean finishSelection = false;
    private static Player p1;
    private static Player p2;
    
    /*
* returns the players chosen by the user to play the game
* @return Player[], two players for the game
*/
    public Player[] getPlayers()
    {
            while(finishSelection == false)
            {
                     try {
                 //do what you want to do before sleeping
                 Thread.currentThread().sleep(1000);//sleep for 1000 ms
                 //do what you want to do after sleeptig
             } catch (InterruptedException ie) {
                 //If this thread was intrrupted by nother thread
             }
            }
            return new Player[]{p1, p2};
    }
    
    /** Creates new form GUI */
    public StartupGUI() {
        initComponents();
    }

    /** This method is called from within the constructor to
* initialize the form.
* WARNING: Do NOT modify this code. The content of this method is
* always regenerated by the Form Editor.
*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        matchStartup = new javax.swing.JButton();
        titleLabel = new javax.swing.JLabel();
        firstPlayer = new javax.swing.JComboBox();
        secondPlayer = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Checkers Setup");

        matchStartup.setText("Begin Match!");
        matchStartup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                matchStartupActionPerformed(evt);
            }
        });

        titleLabel.setText("Checkers Setup");

        firstPlayer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Human Player", "Minimax (AI)", "Minimax with alphabeta (AI)", "Negamax (AI)", "Negascout (AI)", "A* (AI)", "Expectiminimax (AI)" }));

        secondPlayer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Human Player", "Minimax (AI)", "Minimax with alphabeta (AI)", "Negamax (AI)", "Negascout (AI)", "A* (AI)", "Expectiminimax (AI)" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(firstPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addComponent(matchStartup, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(secondPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(373, Short.MAX_VALUE)
                .addComponent(titleLabel)
                .addGap(341, 341, 341))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(secondPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(matchStartup))
                .addContainerGap(151, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>

    /*
* Button to start the BoardGUI with the chosen players
*/
    private void matchStartupActionPerformed(java.awt.event.ActionEvent evt) {
        String p1_str = (String) firstPlayer.getSelectedItem();
        String p2_str = (String) secondPlayer.getSelectedItem();
        setVisible(false);
        p1 = determinePlayer(p1_str, 'B');
        p2 = determinePlayer(p2_str, 'R');
        finishSelection = true;
    }

    /*
* helper method to switch the string returned by the button to a Player
* @param player, the string representation of the player chosen
* @param piece, the piece this player will play as, either 'B' or 'R'
* @return Player, the player determined from the string
*/
    private Player determinePlayer(String player, char piece) {
        Player p;
        switch(player) {
            case "Human Player": p = new User(piece);
                break;
            case "Minimax (AI)": p = new AI(piece, new MiniMax());
                break;
            case "Minimax with alphabeta (AI)": p = new AI(piece, new MiniMaxAlphaBeta());
                break;
            case "Negascout (AI)": p = new AI(piece, new NegaScout());
                break;
            //case "Negamax (AI)": p = new AI(piece, new NegaMax());
            //case "A* (AI)": p = new AI(piece, new A());
            //case "Expectiminimax (AI)": new AI(piece, new ExpectiMiniMax());
            default: p = new User(piece);
                break;
        }
        return p;
    }
    /**
* @param args the command line arguments
*/
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new StartupGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JComboBox firstPlayer;
    private javax.swing.JButton matchStartup;
    private javax.swing.JComboBox secondPlayer;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration
}