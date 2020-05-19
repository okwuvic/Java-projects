/*
 * This is a Tape class that represents Turing machine tapes
 * 
 * @author Victor Nwankpa
 * 
 * @since 27/04/2020
 *
 * 
 */

package assignmentUNit3;

public class Tape {

	protected Cell currentCell; // Current cell pointer

	Tape() { // constructor to create the new cell

		Cell new_Cell = new Cell();

		new_Cell.content = 0;

		new_Cell.prev = null;

		new_Cell.next = null;

		currentCell = new_Cell;

	}

	public Cell getCurrentCell() { // The pointer to current cell.

		return currentCell;

	}

	public char getContent() { // The content of current cell.

		return currentCell.content;

	}

	public void setContent(char ch) { // The character to be set into the current cell.

		currentCell.content = ch;

	}

	public void moveLeft() { // Moves the current cell one position to the left of the tape.

		if (currentCell.prev == null) {

			Cell new_Cell = new Cell();

			new_Cell.content = 0;

			new_Cell.prev = null;

			new_Cell.next = currentCell;

			currentCell.prev = new_Cell;

		}

		currentCell = currentCell.prev;

	}

	public void moveRight() { // Moves the current cell one position to the right along the tape.

		if (currentCell.next == null) {

			Cell now2Cell = new Cell();

			now2Cell.content = 0;

			now2Cell.next = null;

			now2Cell.prev = currentCell;

			currentCell.next = now2Cell;

		}

		currentCell = currentCell.next;

	}

	public String getTapeContents() { // Returns a String consisting of the chars from all the cells .

		Cell point = currentCell;

		if (point.prev != null)

			point = point.prev;

		String strContent = null;

		if (point != null) {

			strContent += point.content;

			point = point.next;

		}

		strContent = strContent.trim(); // Returns a copy of the string, with leading and trailing white space omitted.

		return strContent;

	}

}