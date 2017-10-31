import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public List<Integer> find(Map<Integer, Integer> query){
        List<Integer> path = new ArrayList();

        for (Map.Entry<Integer, Integer> entry : query.entrySet()) {
            int key = entry.getKey();
            int nodeId = entry.getValue();
            jump(path, nodeId, key);
       }
       return path;
    }

    private void jump(List<Integer> path, int nodeId, int key){
        path.add(nodeId);
        int max = 0;
        ChordNode startNode = chordRing.get(nodeId);
        for (Map.Entry<Integer, List<Integer>> row : startNode.getFingerTable().entrySet()) {
            if(key == row.getValue().get(0)){
                path.add(row.getValue().get(1));
                return;
            }else if( key > row.getValue().get(0)){
                if(max < row.getValue().get(0)){
                    max = row.getValue().get(0);
                }
            }else{
                return;
            }
        }

        // jump to succesor of max
        for (Map.Entry<Integer, List<Integer>> row : startNode.getFingerTable().entrySet()) {
            if(max == row.getValue().get(0)){
                max = 0;
                jump(path, row.getValue().get(1), key);
            }
        }
    }
}
