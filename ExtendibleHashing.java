package ceng.ceng351.labdb;

import javax.swing.table.TableStringConverter;



public class LabDB {

    
    private int globaldepth;
    private Bucket[] table;
    private int size;
    private int capacity;
            
    
    

    public LabDB(int bucketSize) {
        globaldepth=1;
        size=0;
        Bucket b1=new Bucket(1,bucketSize);
        Bucket b2=new Bucket(1,bucketSize);
        capacity=bucketSize;
        
        this.table = new Bucket[2] ;
        table[0]=b1;
        table[1]=b2;

    }

    public void enter(String studentID) {
        int index=BinaryToInt(indexer(studentID));
        int result=BitUtility.getRightMostBits(index, this.globaldepth);
        Bucket b=table[result];
        int status=b.insert(studentID);
        
        if(status==0){
            split(result,studentID);
        }
        
    }
    public void grow (int fullbucketindex){
        int pair_index,pair_index1;
        Bucket[] newtable=new Bucket[(1<<globaldepth)*2];
        Bucket b;
        for(int i=0;i<1<<(globaldepth);i++){
            if(i!=fullbucketindex){
                pair_index1=i^(1<<(table[i].getLocalDepth()));
                newtable[i]=table[i];
                newtable[pair_index1]=table[i];
            }
            else{
                pair_index=i^(1<<(table[i].getLocalDepth()-1));
                b=table[fullbucketindex];
                newtable[fullbucketindex]=new Bucket(table[i].getLocalDepth(),capacity);
                newtable[pair_index]=new Bucket(table[i].getLocalDepth(),capacity);
                b.filter(newtable[fullbucketindex], newtable[pair_index], i);
            }
        }
        table=newtable;
        globaldepth++;
    } 
    
    public void split(int bucketno,String studentId){
        int localdepth=table[bucketno].incrementationdepth();
        int pair_index;
        Bucket b;
        int i;
        int index;
        int lastindex;
        int index_diff,dir_size;
        if(localdepth==globaldepth+1)
        {
            grow(bucketno);
        }
        else{
            
            pair_index=bucketno^(1<<(localdepth-1));
            b=table[bucketno];
            table[bucketno]=new Bucket(localdepth,capacity);
            table[pair_index]=new Bucket(localdepth,capacity);
            b.filter(table[bucketno], table[pair_index], bucketno);
            if(table[pair_index].isEmpty()){
                table[pair_index]=table[bucketno];
            }
        }
        index=BinaryToInt(indexer(studentId));
        lastindex=BitUtility.getRightMostBits(index, localdepth);
        table[lastindex].insert(studentId);
    }
    public void leave(String studentID) {
        
    }

    public String search(String studentID) {
        int index=BinaryToInt(indexer(studentID));
        int result=BitUtility.getRightMostBits(index, this.globaldepth);
        Bucket b;
        int status;
        int numberofbuckets=1<<globaldepth;
        int i=0;
        for(;i<numberofbuckets;i++){
            b=table[i];
            status=b.search(studentID);
            if(status==1) continue;
            return String.format("%"+globaldepth+"s", 
            Integer.toBinaryString(i)).replaceAll(" ", "0");
        }
        return "-1";
    }

    public void printLab() {
        System.out.println("Global Depth : "+globaldepth);
        int numberofbuckets=1<<globaldepth;
        int i=0;
        int j;
        int k;
        Bucket b;
        int rrr;
        for(;i<numberofbuckets;i++){
            k=0;
            b=table[i];
            System.out.print(String.format("%"+globaldepth+"s", 
            Integer.toBinaryString(i)).replaceAll(" ", "0")+" : [Local depth:"+b.getLocalDepth()+"]");
            for(j=0;j<b.getCapacity();j++){
                if(b.getElemwithIndex(j)==null){continue;}
                System.out.print("<"+ b.getElemwithIndex(j) +">");
            }
            System.out.println("");
        }
        
        
    }
    public void add0(int count){
        int i=0;
        while(i<count){
            System.out.print("0");
        }
    }
    
    public int digits(int index){
        int count = 0, num = 3452;
        while(num != 0)
        {
            num /= 10;
            ++count;
        }
    
    return count;
    
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
