package game.models;
import game.view.GameView;
import java.awt.*;
import java.util.List;

public interface Actor extends Cloneable
{
    Node getLocation();
    int getDirection();
    void addPathTo(Game game, Color color, Node target);
    List<Node> getPathTo(Node to);

    int getNextDir(Node to, boolean approach);

    Actor getTargetActor(List<? extends Actor> targets, boolean nearest);
    Node getTargetNode(List<Node> targets, boolean nearest);

    int getReverse();
}
