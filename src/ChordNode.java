import java.util.*;

/**
 * Created by Administrator on 10/29/2017.
 */
public class ChordNode {
    private final static double BASE = 2;

    private Map<Integer, List<Integer>> fingerTable;
    private Integer id;
    private ChordNode successor;
    private boolean isInit = false;
    private ChordNode self = this;

    public ChordNode(Integer id) {
        this.id = id;
        fingerTable = new TreeMap<>();
    }

    public ChordNode(Integer id, Boolean isInit) {
        this.id = id;
        this.isInit = isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public boolean isInit() {
        return isInit;
    }

    public ChordNode(Integer id, Map fingerTable, ChordNode successor) {
        this.fingerTable = fingerTable;
        this.id = id;
        this.successor = successor;
    }

    public void setFingerTable() {
        fingerTable = new HashMap<>();
    }

    public Map<Integer, List<Integer>> getFingerTable() {
        return fingerTable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setSuccessor(ChordNode successor) {
        this.successor = successor;
    }

    public ChordNode getSuccessor() {
        return successor;
    }

    public void calculateFirstNode(int exponent, List<ChordNode> list){
        this.successor = self;
        for(int i=0; i< exponent; i++){
            int position = this.id + (int)Math.pow(BASE, i);
            List<Integer> temp = new ArrayList<>();
            temp.add(0, position);
            temp.add(1, this.successor.getId());
            this.fingerTable.put(i, temp);
        }
    }

    public void calculateNode(int exponent, List<ChordNode> list){
        this.successor = findSuccessor(exponent, list, this);
        int length = list.size();
        for(int i=0; i< exponent; i++){
            int position = (this.id + (int)Math.pow(BASE, i)) % length;
            List<Integer> temp = new ArrayList<>();
            temp.add(0, position);
            temp.add(1, this.successor.getId());
            this.fingerTable.put(i, temp);
        }

        for(ChordNode node: list){
            node.successor = findSuccessor(exponent, list, node);
        }

        for(ChordNode node: list){
            if(node.isInit()){
                updateTable(node, list);
            }
        }
    }

    private void updateTable(ChordNode node, List<ChordNode> list){
        if(node.getFingerTable() != null){
            for (Map.Entry<Integer, List<Integer>> entry : node.getFingerTable().entrySet()) {

                    int key = entry.getValue().get(0);
                    if(list.get(key).isInit()){
                        entry.getValue().set(1, key);
                    }else{
                        entry.getValue().set(1, list.get(key).getSuccessor().getId());
                    }


            }
        }

    }

    public ChordNode findSuccessor(int exponent, List<ChordNode> list, ChordNode node){
        int steps = (int)Math.pow(2, exponent)-1;
        int length = list.size();
        int nextNode = node.id + 1;
        for(int i = nextNode; i<(steps + nextNode); i++){
            if(i< length){
                if(list.get(i).isInit()){
                    return list.get(i);
                }
            }else{
                int position = i % length;
                if(list.get(position).isInit()){
                    return list.get(position);
                }
            }
        }
        return self;
    }


    public void printTable(){
        System.out.println("----------------------");
        System.out.println("node id: " + this.getId());
        for (Map.Entry<Integer, List<Integer>> entry : fingerTable.entrySet()) {
            System.out.println(entry.getKey() + " " +entry.getValue().get(0) + " " + entry.getValue().get(1));
        }
        System.out.println("");
    }

}
