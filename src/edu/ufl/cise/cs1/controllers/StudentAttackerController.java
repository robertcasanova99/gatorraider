package edu.ufl.cise.cs1.controllers;
import game.controllers.AttackerController;
import game.models.*;
import java.awt.*;
import java.util.List;

public final class StudentAttackerController implements AttackerController
{
	public void init(Game game) { }

	public void shutdown(Game game) { }

	public int update(Game game,long timeDue)
	{
		int action;

		//An example (which should not be in your final submission) of some syntax that randomly chooses a direction for the attacker to move
		List<Integer> possibleDirs = game.getAttacker().getPossibleDirs(true);
		if (possibleDirs.size() != 0)
			action = possibleDirs.get(Game.rng.nextInt(possibleDirs.size()));
		else
			action = -1;

		//An example (which should not be in your final submission) of some syntax to use the visual debugging method, addPathTo, to the top left power pill.
		List<Node> powerPills = game.getPowerPillList();
		if (powerPills.size() != 0) {
			game.getAttacker().addPathTo(game, Color.BLUE, powerPills.get(0));
		}

		return action;
	}
}