import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 10/29/2017.
 */
public class Entry {
    public static int lineCounter = 0;
    public static void main(String args[]){

        List<String> allData = new ArrayList<>();
        Setting setting = new Setting();

        // read input file
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter file name:\t");
        String fileName = reader.nextLine();

        // store value to allData
        try {
            allData = getFileData(fileName);
            System.out.println("create origin conflict 1.....");
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }

        /**
         * extract values to setting
         */
        setting.setHashSpace(new Integer(allData.get(0)));
        setting.setNumberOfNodes(new Integer(allData.get(1)));
        setting.setNumberOfKey(new Integer(allData.get(2)));
        // get hashed node ids
        addHashNodeID(allData, 3, setting);
        // get hashed keys id
        addHashKey(allData, 4, setting);
        // get key and query id
        queryKeyid(allData, setting, lineCounter);

        ChordRing ring = new ChordRing(setting.getHashSpace());
        for(Integer id: setting.getHashNodeID()){
            ChordNode node = ring.getChordRing().get(id);
            ring.join(node, node.getId(), setting.getHashSpace());

        }
        
        System.out.println("create origin conflict 2.....");
        
        for(ChordNode node: ring.getChordRing()){
            if(node.isInit()){
                node.printTable();
            }
            else{
                System.out.println("----------------------");
                System.out.println("node " + node.getId() + " unavailable");
                System.out.println("");
            }
        }

        /**
         * get key
         */
        System.out.println("----------------------");
        System.out.println("Key path:");
        List<Integer> path = ring.find(setting.getKeyid());
        for(int i=0;i<path.size();i++){
            if(i != path.size()-1){
                System.out.print(path.get(i) + " ");
            }else{
                System.out.println(path.get(i));
            }

        }
    }

    public static void queryKeyid(List<String> allData, Setting setting, int counter) {
        if (counter > 6){
           int range = counter - 6;
           for (int i=5; i<5+range; i++){
               String[] currentKeyid = spliteData(allData, i);
               for(int j=0; j<currentKeyid.length-1; j++){
                   setting.getKeyid().put(new Integer(currentKeyid[j]), new Integer(currentKeyid[j+1]));
               }
           }
        }
    }

    public static List<String> getFileData(String file) throws IOException {
        String currentLine = null;
        List<String> data = new ArrayList<>();

        BufferedReader bufferReader = new BufferedReader(new FileReader("../" + file));
        while((currentLine = bufferReader.readLine()) != null){
            data.add(currentLine);
            lineCounter++;
        }
        return data;
    }

    public static String[] spliteData(List<String> dataList, int index){
        return  dataList.get(index).split(",");
    }

    public static void addHashNodeID(List<String> dataList, int index, Setting setting){
        String[] temp = spliteData(dataList,index);
        for(int i=0; i<temp.length; i++){
            setting.getHashNodeID().add(new Integer(temp[i]));
        }
    }

    public static void addHashKey(List<String> dataList, int index, Setting setting){
        String[] temp = spliteData(dataList,index);
        for(int i=0; i<temp.length; i++){
            setting.getHashKeys().add(new Integer(temp[i]));
        }
    }
}



