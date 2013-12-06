package checkers.checkers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextGUI implements GUI
{
	/**
	 * Work in progress.
	 */
	private static void clearConsole()
	{
	    try
	    {
	        String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (Exception exception)
	    {
	        //  Handle exception.
	    }
	}
	
	/**
	 * This just displays the board as is.
	 * @Override
	 */
	public void display(Board b) 
	{
		System.out.print("  ");
		for(int i = 0; i < 8; i++)
		{
			System.out.print (i+ " ");
		}
		System.out.println();
		for(int row = 0; row < 8; row++)
		{
			System.out.print(row + "|");
			for(int col = 0; col < 8; col++)
			{
				Piece p = b.getPiece(row, col);
				if(p.getColor() != '-' && p.isKing() == false)
				{
					System.out.print(Character.toLowerCase(p.getColor()) + " ");
				}
				else
					System.out.print(p.getColor() + " ");
			}
			System.out.println();
		}
	}
	/**
	 * This displays the board and the moves for the selected piece.
	 * @Override
	 */
	public void display(Board b, ArrayList<Piece> moves, char display)
	{
		System.out.print("  ");
		for(int i = 0; i < 8; i++)
		{
			System.out.print (i+ " ");
		}
		System.out.println();
		for(int row = 0; row < 8; row++)
		{
			System.out.print(row + "|");
			for(int col = 0; col < 8; col++)
			{
				boolean found = false;
				for(Piece move : moves)
				{
					int[] pos = move.getPosition();
					if(row == pos[0] && col == pos[1])
					{
						found = true;
						System.out.print(display + " ");
					}
				}
				if(found == false)
				{
					Piece p = b.getPiece(row, col);
					if(p.getColor() != '-' && p.isKing() == false)
					{
						System.out.print(Character.toLowerCase(p.getColor()) + " ");
					}
					else
						System.out.print(p.getColor() + " ");
				}
			}
			System.out.println();
		}
	}

	/**
	 * This selects a piece to move.
	 * @Override
	 */
	public Piece getMove(Board b, char piece) throws IOException 
	{
		boolean valid = false;
		int row, col;
		do
		{
			boolean input = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String in = br.readLine();
			while(input == false)
			{
				if(in.length() == 3 && in.charAt(1) == ',')
					input = true;
				else if(in.equals("help"))
				{
					help();
					in = br.readLine();
				}
				else
				{
					System.out.println("Invalid input. Please separate row and column by ',' ex 3,4");
					in = br.readLine();
				}
				
			}
			String[] pos = in.split(",");
			row = Integer.valueOf(pos[0]);
			col = Integer.valueOf(pos[1]);
			
			if(b.getPiece(row, col).getColor() != piece)
			{
				System.out.println("This is not a valid choice.");
			}
			else
			{
				valid = true;
			}
		}while(!valid);
		return b.getPiece(row, col);
	}
	/**
	 * This returns a move for the selected piece.
	 * @Override
	 */
	public Piece getMove(Board b, ArrayList<Piece> moves, char piece) throws IOException 
	{
		boolean valid = false;
		int row, col;
		do
		{
			boolean input = false;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String in = br.readLine();
			while(input == false)
			{
				
				if(in.equals("exit"))
					return null;
				else if(in.equals("help"))
				{
					help();
					in = br.readLine();
				}
				else if(in.length() == 3 && in.charAt(1) == ',')
					input = true;
				else
				{
					System.out.println("Invalid input. Please separate row and column by ',' ex 3,4 or type exit to deselect");
					in = br.readLine();
				}
				
			}
			String[] pos = in.split(",");
			row = Integer.valueOf(pos[0]);
			col = Integer.valueOf(pos[1]);
			
			if(b.getPiece(row, col).getColor() == piece)
			{
				for(Piece move : moves)
				{
					if(move.equals(b.getPiece(row, col)))
					{
						valid = true;
					}
				}
			}
			if(valid == false)
			{
				System.out.println("This is not a valid choice.");
			}
		}while(valid == false);
		return b.getPiece(row, col);
	}
	
	/**
	 * Standard help command
	 * @Override
	 */
	public void help()
	{
		System.out.println("Welcome to checkers! Here are the instructions:");
		System.out.println("To select a piece or move, enter it as follows: row, col. Substitute row and col for the selected values");
	}
}
