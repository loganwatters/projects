/**
 * Class representing a HuffmanNode with numerous operations to easily make a HuffmanCodeTree.
 */

public class HuffmanNode {
    private HuffmanNode one;
    private HuffmanNode zero;
    private Character data;

    /**
     * Constructor. Sets zero and one to be equal to the parameters and data equal to null.
     * @param zero
     * @param one
     */
    public HuffmanNode(HuffmanNode zero, HuffmanNode one){
        data = null;
        this.zero = zero;
        this.one = one;
    }

    /**
     * Constructor. Sets data equal to the parameter and zero and one equal to null.
     * @param data
     */
    public HuffmanNode(char data) {
        this.data = data;
        zero = null;
        one = null;
    }

    /**
     * Returns the zero node.
     * @return
     */
    public HuffmanNode getZero(){ return zero;}

    /**
     * Returns the one node.
     * @return
     */
    public HuffmanNode getOne(){
        return one;
    }

    /**
     * Returns the data.
     * @return
     */
    public Character getData(){
        return data;
    }

    /**
     * Sets the zero node to be equal to the parameter.
     * @param zero
     */
    public void setZero(HuffmanNode zero) {
        this.zero = zero;
    }

    /**
     * Sets the one node to be equal to the parameter.
     * @param one
     */
    public void setOne(HuffmanNode one){
        this.one = one;
    }

    /**
     * Sets data to be equal to the parameter.
     * @param data
     */
    public void setData(Character data){
        this.data = data;
    }

    /**
     * Returns true if data is not null and one and zero are null. False otherwise.
     * @return
     */
    public boolean isLeaf() {
        if(data != null && one == null && zero == null){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Returns true if isLeaf() returns true. Returns true if one and zero are not null and both valid and
     * data is equal to null. Otherwise, false.
     * @return
     */
    public boolean isValid() {
        if(isLeaf()){
            return true;
        }
        else if(zero != null && data == null && one != null && one.isValid() && zero.isValid()){
            return true;
        }
        else {
            return false;
        }
    }
}
