package game.system;
import game.models.Actor;
import game.models.Game;
import game.models.Node;
import game.view.GameView;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public abstract class _Actor implements Actor
{
    _Node location;
    int direction;

    public Node getLocation()
    {
        return location;
    }
    public int getDirection()
    {
        return direction;
    }

    public void addPathTo(Game game, Color color, Node target) {
        if (target != null)
            GameView.addPoints(game, color, getPathTo(target));
    }

    public int getReverse()
    {
        switch(direction)
        {
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            case 3: return 1;
        }
        return 4;
    }

    protected List<Node> getPathTo(Node to, boolean canReverse)
    {
        if (canReverse)
            return location.getPathTo(to);
        else
            return location.getPathTo(to, direction);
    }

    protected List<Integer> getPossibleDirs(boolean canReverse)
    {
        ArrayList<Integer> directions = new ArrayList<Integer>();
        int numNeighbors = location.getNumNeighbors();

        if (numNeighbors == 0)
            return directions;

        List<Node> nodes = location.getNeighbors();

        for (int i = 0; i < nodes.size(); i++)
        {
            if (nodes.get(i) != null)
            {
                if (canReverse || (direction < 0 || direction > 3))
                    directions.add(i);
                else if (i != getReverse())
                    directions.add(i);
            }
        }

        return directions;
    }

    protected List<Node> getPossibleLocations(boolean canReverse)
    {
        List<Node> newLocations = location.getNeighbors();
        if (!canReverse)
            newLocations.set(getReverse(), null);

        return newLocations;
    }

    public Actor getTargetActor(List<? extends Actor> targets, boolean nearest)
    {
        if (nearest) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < targets.size(); i++) {
                int currentDistance = targets.get(i).getLocation().getPathDistance(getLocation());
                if (currentDistance < minDistance && currentDistance > 0) {
                    minDistance = targets.get(i).getLocation().getPathDistance(getLocation());
                    minIndex = i;
                }
            }
            return targets.get(minIndex);
        } else {
            int maxDistance = Integer.MIN_VALUE;
            int maxIndex = 0;
            for (int i = 0; i < targets.size(); i++) {
                int currentDistance = targets.get(i).getLocation().getPathDistance(getLocation());
                if (currentDistance > maxDistance && currentDistance > 0) {
                    maxDistance = targets.get(i).getLocation().getPathDistance(getLocation());
                    maxIndex = i;
                }
            }
            return targets.get(maxIndex);
        }
    }

    //Returns the target closest from this actor's position
    public Node getTargetNode(List<Node> targets, boolean nearest)
    {
        if (nearest) {
            int minDistance = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int i = 0; i < targets.size(); i++) {
                int currentDistance = targets.get(i).getPathDistance(getLocation());
                if (currentDistance < minDistance) {
                    minDistance = targets.get(i).getPathDistance(getLocation());
                    minIndex = i;
                }
            }
            return targets.get(minIndex);
        } else {
            int maxDistance = Integer.MIN_VALUE;
            int maxIndex = 0;
            for (int i = 0; i < targets.size(); i++) {
                int currentDistance = targets.get(i).getPathDistance(getLocation());
                if (currentDistance > maxDistance) {
                    maxDistance = targets.get(i).getPathDistance(getLocation());
                    maxIndex = i;
                }
            }
            return targets.get(maxIndex);
        }
    }



    protected _Actor(_Node _location, int _direction)
    {
        location = _location;
        direction = _direction;
    }

    protected _Actor clone()
    {
        try
        {
            return (_Actor)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return null;
        }
    }


}
