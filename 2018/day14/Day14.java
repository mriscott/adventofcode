import java.util.*;

public class Day14{

    Elf[] elfs;
    List recipes;
    
    public static void main(String [] args){
	Day14 me = new Day14();
	me.run(760240);
	me.test(9,"5158916779");
	me.test(5,"0124515891");
	me.test(18,"9251071085");
	me.test(2018,"5941429882");
	System.out.println("Part 1:"+me.tenAfter(760221));
    }

    public void run(int max){
	for (int x=0;x<max;x++){
	    createRecipes();
	    moveElves();
	    //System.out.println(this);
	}

    }

    public void test(int after, String target){
	String actual=tenAfter(after);
	if(!target.equals(actual)){
	    throw new RuntimeException("Test failed:"+actual+" isn't "+target);
	}
    }

    

    public Day14(){
	elfs=new Elf[]{ new Elf(0), new Elf(1)};
	recipes=new ArrayList();
	Recipe r1 = new Recipe(3);
	Recipe r2 = new Recipe(7);
	r1.elf=elfs[0];
	r2.elf=elfs[1];
	recipes.add(r1);
	recipes.add(r2);
    }

    public String tenAfter(int i){
	StringBuffer lastten=new StringBuffer();
	for (int x=i;x<i+10;x++){
	    lastten.append(getRecipeVal(x));
	}
	return lastten.toString();
    }

    public void createRecipes(){
	int sum=getRecipeVal(elfs[0].index) +getRecipeVal(elfs[1].index);
	if (sum>=10){
	    recipes.add(new Recipe(sum/10));
	    recipes.add(new Recipe(sum%10));
	}
	else{
	    recipes.add(new Recipe(sum));
	}
    }

    public void moveElves(){
	for(int x=0;x<elfs.length;x++){
	    Elf e = elfs[x];
	    int index=e.index;
	    int recipe=getRecipeVal(index);
	    int newindex=index+recipe+1;
	    newindex%=recipes.size();
	    e.index=newindex;
	    getRecipe(index).elf=null;
	    getRecipe(newindex).elf=e;
	}
    }

    int getRecipeVal(int i){
	return ((Recipe)recipes.get(i)).recipe; 
    }

    Recipe getRecipe(int i){
	return ((Recipe)recipes.get(i)); 
    }
    


    public String toString(){
	StringBuffer str=new StringBuffer();
	for (int x=0;x<recipes.size();x++){
	    str.append(recipes.get(x).toString());	    
	}
	return str.toString();
    }
    
}

class Elf{
    int index;
    public Elf(int i){
	index=i;
    }
    public String format(int i){
	if(index==0) return "("+i+")";
	return "["+i+"]";
	    
    }

}

class Recipe{
    public int recipe;
    public Elf elf;

    public Recipe(int r){
	recipe=r;
    }

    public String toString(){
	if (elf!=null){
	    return elf.format(recipe);
	}
	return " "+recipe+" ";
    }

    public int getRecipe(){
	return recipe;
    }

    
}
