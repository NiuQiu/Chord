import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 10/30/2017.
 */
public class Setting {
    private  static Integer hashSpace = 0;
    private  static Integer numerOfNodes = 0;
    private  static Integer numberOfKey = 0;
    private  static List<Integer> hashNodeID = new ArrayList<>();
    private  static List<Integer> hashKeys = new ArrayList<>();
    private  static Map<Integer, Integer> keyid = new HashMap<>();

    public Integer getHashSpace() {
        return hashSpace;
    }

    public void setHashSpace(Integer hashSpace) {
        this.hashSpace = hashSpace;
    }

    public Integer getNumerOfNodes() {
        return numerOfNodes;
    }

    public void setNumberOfNodes(Integer numerOfNodes) {
        this.numerOfNodes = numerOfNodes;
    }

    public Integer getNumberOfKey() {
        return numberOfKey;
    }

    public void setNumberOfKey(Integer numberOfKey) {
        this.numberOfKey = numberOfKey;
    }

    public List<Integer> getHashNodeID() {
        return hashNodeID;
    }

    public void setHashNodeID(List<Integer> hashNodeID) {
        this.hashNodeID = hashNodeID;
    }

    public List<Integer> getHashKeys() {
        return hashKeys;
    }

    public void setHashKeys(List<Integer> hashKeys) {
        this.hashKeys = hashKeys;
    }

    public Map<Integer, Integer> getKeyid() {
        return keyid;
    }


}
