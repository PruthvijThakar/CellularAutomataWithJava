
public class Cellular{


	private final int Height = 100;
	private final int Width = 64;
	private int[] ruleset={0,0,0,1,1,1,0};
	
	
	
	public Cellular() {
		
	}
	
	public int[] generationofAutomata(int prevRow[],int rule){
		
		int[] currentRow = ruleApplication(prevRow,rule);
			
		return currentRow;
	}
	

	public int[] FirstrowGeneration(){
		
		int[] FirstRow = { 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 };
		
		return FirstRow;
	}
		
	public void  rule12(int rule)
	{
		switch(rule)
		{
		case 30: ruleset=new int[]{0,0,0,1,1,1,1,0};
						break;
		case 126: ruleset=new int[]{0,1,1,1,1,1,1,0};
		                break;
		case 54: ruleset=new int[]{0,0,1,1,0,1,1,0};
		                break;
		case 150: ruleset=new int[]{1,0,0,1,0,1,1,0};
		                break;
		case 60: ruleset=new int[]{0,0,0,1,1,1,0,0};
		                break;
		case 158: ruleset=new int[]{1,0,0,1,1,1,1,0};
		                break;
		case 62: ruleset=new int[]{0,0,1,1,1,1,1,0};
		                break;
		case 182: ruleset=new int[]{1,0,1,1,0,1,1,0};
		                break;
		
			
		}
		
		
	}
	
	public int[] ruleApplication(int[] prevRow,int rule){
				
		int[] newrow = new int[Width];
		for(int i=1; i<prevRow.length;i++){
			int leftSide ;
			if(i == 1 ) leftSide = prevRow[prevRow.length - 1];
			else leftSide = prevRow[i-1];
			
			int previousSelf = prevRow[i];
			rule12(rule);
			int rightSide;
			if (i == prevRow.length - 1) rightSide = prevRow[0];
			else rightSide = prevRow[i + 1];
			
				
			if(matchRules(leftSide, previousSelf, rightSide, new int []{1,1,1})) newrow[i] = ruleset[7]; 
			else if(matchRules(leftSide, previousSelf, rightSide, new int []{1,1,0})) newrow[i] =  ruleset[6]; 
			else if(matchRules(leftSide, previousSelf, rightSide, new int []{1,0,1})) newrow[i] =  ruleset[5]; 
			else if(matchRules(leftSide, previousSelf, rightSide, new int []{1,0,0})) newrow[i] =  ruleset[4]; 
			else if(matchRules(leftSide, previousSelf, rightSide, new int []{0,1,1})) newrow[i] =  ruleset[3]; 
			else if(matchRules(leftSide, previousSelf, rightSide, new int []{0,1,0})) newrow[i] =  ruleset[2]; 
			else if(matchRules(leftSide, previousSelf, rightSide, new int []{0,0,1})) newrow[i] =  ruleset[1]; 
			else if(matchRules(leftSide, previousSelf, rightSide, new int []{0,0,0})) newrow[i] =  ruleset[0]; 
			
			
		}
		
		return newrow;
	}
	
	
	public boolean matchRules(int leftSide, int previouSelf, int rightSide, int[] rule){
		if(leftSide == rule[0] && previouSelf == rule[1] && rightSide == rule[2]) return true;
		return false;
		
	}
	
}
