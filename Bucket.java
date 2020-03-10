
package ceng.ceng351.labdb;

public class Bucket {
    
    private String[] elements;
    private int localDepth;
    private int length;
    private int capacity;   

    
    
    
    
    public Bucket(){
        
        length=0;
        elements=null;
        localDepth=1;
    
    
    }
    
    public Bucket(int lD,int cap)
        {
        localDepth = lD;
        this.elements = new String[cap];
        capacity=cap;
        length=0;
        }
    
    
    public int incrementationdepth(){
           return ++localDepth;
    }
    public int insert(String studentId){
    if(isFull()){
        return 0;
    }
    if(length==capacity){return 0;}
    elements[length] = studentId;
    this.length++;
    return 1;
}
    
    public int search(String studentId){
        int i=0;
        int status=1;
        for(;i<capacity;i++){
            if(this.elements[i]==null)
                return 1;
                
            status=studentId.compareTo(this.elements[i]);
            if(status==0){
                return 0;
            }
        }
        return 1;
    }
    
    
    
    public boolean isFull()
        {           
            if(elements.length+1==capacity)
                {
                    return true;
                }
            
            else{
                    return false;
                }
        }
    public boolean isEmpty()
        {
            if(elements.length==0)
                {
                    return true;
                }else
                    {
                        return false;
                    }
        }

    public void setElemwithIndex(String val,int index)
        {
            elements[index]=val;
            
            
        }
    public String getAllelements(){
        int j=0;
        String s="";
        for(;j<capacity;j++){
            s.concat("<"+elements[j]+">");
        }
        return s;
    }


    public String getElemwithIndex(int index)
        {
            return elements[index];
        }
    public int getLocalDepth()
        {
            return localDepth;
        }
    public void setLocalDepth(int val)
        {
            localDepth = val;
        }
    public int getCapacity(){
        return capacity;
        }
    
    
    
    	public void filter(Bucket b,Bucket b2 ,int index)
	{
            int result=BitUtility.getRightMostBits(index, localDepth);
            int i=0;
            int a;
            int result2;
            for(;i<capacity;i++){
               a=BinaryToInt(indexer(elements[i]));
               result2=BitUtility.getRightMostBits(a, localDepth);
               if(result==result2){
                   b.insert(elements[i]);
               }
               else{
                   b2.insert(elements[i]); 
               }
            }
            
	}
    public int converter(String studentID){
        String[] s=studentID.split("e");
        String s1 = null;
    for(String alphabet : s)
        {
        s1=alphabet;
        }
	int result = Integer.parseInt(s1);			
        return result;
    }
    
    public String hash(int key){
        String binaryString=Integer.toBinaryString(key);
        return binaryString;
    }
    
    public String indexer(String studentID){
        return hash(converter(studentID));
    }
    public int BinaryToInt(String s)
    {
        int value = 0;
        int bitValue = 1;

        for (int i = s.length() - 1; i >= 0; i--)
        {
        if (s.charAt(i) == '1')
            value += bitValue;

        bitValue <<= 1;
        }
    return value;
}
    
}