package com.company;

import com.company.entities.Cat;
import com.company.entities.Fish;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            File myObj = new File("voorbeeld_invoer.txt");
            Scanner myReader = new Scanner(myObj);
            int nbExamples=Integer.parseInt(myReader.nextLine());
            for(int n=0;n<nbExamples;n++){
                int fish=0;
                ArrayList<Fish>fishes=new ArrayList<>();
                Cat garfield=new Cat();
                String firstRule=myReader.nextLine();
                int col=Integer.parseInt(firstRule.split(" ")[0]);
                int row=Integer.parseInt(firstRule.split(" ")[1]);
                int maxTime=Integer.parseInt(firstRule.split(" ")[2]);
                for(int i=0;i<row;i++){
                    String rowLine=myReader.nextLine();
                    for(int j=0;j<col;j++){
                        char c=rowLine.charAt(j);
                        if(c =='E'){
                            fishes.add(new Fish(fish,j,i));
                            fish++;
                        }
                        else if (c=='G'){
                            garfield.x=j;
                            garfield.y=i;
                            garfield.maxTime=maxTime;
                        }
                    }
                }
                solve(n+1,garfield,fishes);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void solve(int n, Cat garfield, ArrayList<Fish> fishes) {
        Fish homeGarfield=new Fish(-1,garfield.x, garfield.y);
        Stack<Integer> stack = new Stack<>();
        recursion(garfield,fishes,-1,0,0,homeGarfield,stack);
        System.out.println(n+" "+garfield.maxFishes);
    }

    private static void recursion(Cat garfield, ArrayList<Fish> fishes, int level, int time, int fishesEated, Fish previousLocation, Stack<Integer> stack) {
        level++;
        if(level==fishes.size()){
            return;
        }
        for(int i=0;i<fishes.size();i++){
            if(!stack.contains(i)){
                Fish currentLocation=fishes.get(i);
                if(time + calculateDistance(previousLocation,currentLocation) + calculateDistanceToHome(garfield,currentLocation)+1<=garfield.maxTime){
                    fishesEated++;
                    stack.add(i);
                    if(fishesEated > garfield.maxFishes){
                        garfield.maxFishes=fishesEated;
                    }
                    recursion(garfield,fishes,level,time + calculateDistance(previousLocation,currentLocation)+1,fishesEated, currentLocation, stack);
                    fishesEated--;
                    stack.pop();
                }
            }

        }

    }

    public static int calculateDistance(Fish f1, Fish f2){
        return Math.abs(f1.x-f2.x)+Math.abs(f1.y-f2.y);
    }
    public static int calculateDistanceToHome(Cat c, Fish f){
        return Math.abs(c.x-f.x)+Math.abs(c.y-f.y);
    }


}
