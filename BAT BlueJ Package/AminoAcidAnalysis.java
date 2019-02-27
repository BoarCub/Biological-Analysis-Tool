//Can be used to analyze the amino acid sequence of different proteins
//This class contains a variety of methods which can be called
public class AminoAcidAnalysis
{
    
    /**
     * Compares how similar two amino acid sequences are
     * by finding the Levenshtein Distance between two
     * amino acid sequences.
     * 
     * Levenshtein Distance measures
     * the difference between two Strings by finding the minimum
     * number of single-character insertions, deletions, or substitutions
     * needed in order to turn one String into the other.
     * 
     * The greater the value returned by this method is, the more
     * differences there are between the two amino acids. If the
     * value returned is 0, there should be not differences.
     */
    public static int findLevenshteinDistance(String a, String b){
        
        //length of each amino acid sequence
        int aLength = a.length();
        int bLength = b.length();
        
        //special cases
        if (aLength == 0){
            return bLength;
        } else if (bLength == 0){
            return aLength;
        }
        
        //reduces amount of memory used to analyze sequence
        if(aLength > bLength){
            String s = a;
            a = b;
            b = s;
            aLength = bLength;
            bLength = b.length();
        }
        
        //horizontal, cost arrays
        int previousCostArray[] = new int[aLength + 1];
        int costArray[] = new int[aLength + 1];
        int costPlaceholderArray[];
        
        //indexes to iterate through a and b respectively
        int i;
        int j;
        
        char characterAtJ; //character at j index in b
        
        int cost;
        
        for(i=0; i<=aLength; i++){
            previousCostArray[i] = i;
        }
        
        for(j=1; j<=bLength; j++){
            characterAtJ = b.charAt(j-1);
            costArray[0] = j;
            
            for(i=1; i<=aLength; i++){
                cost = a.charAt(i-1)==characterAtJ ? 0 : 1;
                costArray[i] = Math.min(Math.min(costArray[i-1]+1, previousCostArray[i]+1), previousCostArray[i-1]+cost);
            }
            
            //sets previous values to current values
            costPlaceholderArray = previousCostArray;
            previousCostArray = costArray;
            costArray = costPlaceholderArray;
        }
 
        return previousCostArray[aLength];
        
    }
    
}