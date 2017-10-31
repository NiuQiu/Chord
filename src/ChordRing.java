import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 10/30/2017.
 */
public class ChordRing {
    private static List<ChordNode> chordRing;
    private final static int BASE = 2;
    private static boolean isEmpty = false;


    public ChordRing(int space) {
        chordRing = new ArrayList<>((int)Math.pow(BASE, space));
        for(int i = 0; i< (int)Math.pow(BASE, space); i++){
            ChordNode node = new ChordNode(i, null, null);
            chordRing.add(node.getId(), node);
        }
        isEmpty = true;
    }

    public List<ChordNode> getChordRing() {
        return chordRing;
    }

    public void join(ChordNode node, int id, int exponent){
        node.setInit(true);
        node.setFingerTable();
        if(isEmpty){
            node.calculateFirstNode(exponent, chordRing);
            chordRing.set(id, node);
            isEmpty = false;
        }else{

            node.calculateNode(exponent, chordRing);
            chordRing.set(id, node);


        }
    }
}
