/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai_firstproject;

import java.util.Set;
import java.util.Vector;

/**
 *
 * @author hayamikkawi
 */
public class Node {
        char g[][]=new char[6][7];
        Vector <Node> children ;
//        Vector vectors[]=new Vector[children.size()];
        int move = -1; 
        int test = -1; 
        public Node(char g[][]){
            this.g = g; 
            children = new Vector();
        }
        public void copyChildFromParent(char ch[][]){
            for (int i=0; i<6; i++){
                for(int j=0; j<7; j++){
                    ch[i][j] = g[i][j];
                }
            }
        }

        void makeChildren(char c) {
          
            for (int i =0; i<6; i++){
                for(int j =0; j<7; j++){
                    if (i==5 && g[i][j]== 'n'){
                        char ch[][]=new char[6][7];
                        copyChildFromParent(ch); 
                        ch[i][j] = c;
                        Node child = new Node (ch); 
                        child.move = j;
                        children.add(child); 
                        
                    }
                    else if (i !=0 && (g[i][j]=='r' || g[i][j] == 'b') && g[i-1][j]=='n' ){
                        char ch[][]=new char[6][7];
                        copyChildFromParent(ch);
                        ch[i-1][j] =c;
                        Node child = new Node (ch); 
                        child.move = j;
                        children.add(child); 
                    }
                }
            }
    }

    int chooseBestChild(String level) {
        int max = -10000; 
        Node bestChild= null;
        if (level.equalsIgnoreCase("easy")){
            for (Node c : children){
           int e =  c.evaluateFunctionEasy(); 
           if (e>max){
               max = e;
               bestChild= c;
           }
       }
        }
        else if(level.equalsIgnoreCase("medium")){
        for (Node c : children){
           int e =  c.evaluateFunction(); 
           if (e>max){
               max = e;
               bestChild= c;
           }
       }
      }
//        else if (level.equalsIgnoreCase("hard")){
//          this.alphabeta(this, 2, -100000, 100000, true);
//        }
           return bestChild.move;
    }
    
    int evaluateFunctionEasy() {
       int countMe = 0;
       int countOther = 0;
        for(int j=5; j>=0;j--){
           for(int i=0; i<7;i++){
             
                if(g[j][i]=='n'){
                  continue;
              }
                 if(i<=3){
                  char tmp=g[j][i];
                  if(tmp=='r'){
                       if(g[j][i+1]!='b'&&g[j][i+2]!='b'&&g[j][i+3]!='b'){
                          countMe++;
                  }
                  }
                  else if (tmp =='b'){
                      if(g[j][i+1]!='r'&&g[j][i+2]!='r'&&g[j][i+3]!='r'){
                      countOther++;
                  }
                      
                  }
              }
                 if(j>2){
                  char tmp=g[j][i];
                  if(tmp=='r'){
                      
                  if(g[j-1][i]!='b' && g[j-2][i]!='b' && g[j-3][i]!='b'){
                      countMe++;
                    
                  }
                  
                }
                  else if(tmp =='b'){
                      if(g[j-1][i]!='r' && g[j-2][i]!='r' && g[j-3][i]!='r'){
                      countOther++;
                      
                  }
                  }
            }
                 
                 
                 
                 if(j>2&&i>=3){
                  char tmp=g[j][i];
                  
                   if(tmp=='r'){
                      
                  if(g[j-1][i-1]!='b' &&g[j-2][i-2]!='b'&& g[j-3][i-3]!='b'){
                      countMe++;
                     
                  }
                 
                }
                   else if (tmp =='b'){
                      if(g[j-1][i-1]!='r' &&g[j-2][i-2]!='r'&& g[j-3][i-3]!='r'){
                      countOther++;
                  }
                   }
              }
                  if(j>=3&&i<=3){
                  char tmp=g[j][i];
                  
                 
                  if(tmp=='r'){
                     if(g[j-1][i+1]!='b'&&g[j-2][i+2]!='b'&&g[j-3][i+3]!='b'){
                          countMe++;
                         
                     }
                 }
                  else if (tmp =='b'){
                      if(g[j-1][i+1]!='r'&&g[j-2][i+2]!='r'&&g[j-3][i+3]!='r'){
                         countOther++;     
                     }
                  }
              }
           }
        }
        return countMe - countOther; 
    }
    int evaluateFunction() {
       int countMe = 0;
       int countOther = 0; 
       for(int j=5; j>=0;j--){
           for(int i=0; i<7;i++){
             
                if(g[j][i]=='n'){
                  continue;
              }
                 if(i<=3){
                  char tmp=g[j][i];
                  
                  if(tmp=='r'){
                     
                  
                  if(g[j][i+1]!='b'&&g[j][i+2]!='b'&&g[j][i+3]!='b'){
                      countMe++;
                       if(g[j][i+1]==tmp){
                      countMe++;
                  }
                  if(g[j][i+2]==tmp){
                      countMe++;
                  }
                  if(g[j][i+3]==tmp){
                      countMe++;
                  }
                   if (g[j][i+1]==tmp&&g[j][i+2]==tmp&&g[j][i+3]==tmp){
                      countMe+=1000;
                  }
                  }
                 
                 
                  }
                  else if (tmp =='b'){
                      if(g[j][i+1]!='r'&&g[j][i+2]!='r'&&g[j][i+3]!='r'){
                      countOther++;
                       if(g[j][i+1]==tmp){
                      countOther++;
                  }
                  if(g[j][i+2]==tmp){
                     countOther++;
                  }
                  if(g[j][i+3]==tmp){
                     countOther++;
                  }
                   if ((g[j][i+1]==tmp&&g[j][i+2]==tmp) ||(g[j][i+1]==tmp &&g[j][i+3]==tmp) || (g[j][i+2]==tmp&&g[j][i+3]==tmp)){
                       System.out.println("danger");
                      countOther+=100;
                  }
                  }
                      
                  }
              }
                 if(i>=3){
                  char tmp=g[j][i];
                  
                  if(tmp=='r'){
                     
                  
                  if(g[j][i-1]!='b'&&g[j][i-2]!='b'&&g[j][i-3]!='b'){
                      countMe++;
                       if(g[j][i-1]==tmp){
                      countMe++;
                  }
                  if(g[j][i-2]==tmp){
                      countMe++;
                  }
                  if(g[j][i-3]==tmp){
                      countMe++;
                  }
                   if (g[j][i-1]==tmp&&g[j][i-2]==tmp &&g[j][i-3]==tmp){
                      countMe+=1000;
                  }
                  }
                 
                 
                  }
                  else if (tmp =='b'){
                      if(g[j][i-1]!='r'&&g[j][i-2]!='r'&&g[j][i-3]!='r'){
                      countOther++;
                       if(g[j][i-1]==tmp){
                      countOther++;
                  }
                  if(g[j][i-2]==tmp){
                     countOther++;
                  }
                  if(g[j][i-3]==tmp){
                     countOther++;
                  }
                   if ((g[j][i-1]==tmp&&g[j][i-2]==tmp) ||(g[j][i-1]==tmp &&g[j][i-3]==tmp) || (g[j][i-2]==tmp&&g[j][i-3]==tmp)){
                       System.out.println("danger");
                      countOther+=1000;
                  }
                  }
                      
                  }
              }
                 if(j>2){
                  char tmp=g[j][i];
                  
                  if(tmp=='r'){
                      
                  if(g[j-1][i]!='b' && g[j-2][i]!='b' && g[j-3][i]!='b'){
                      countMe++;
                      if(g[j-1][i]==tmp){
                     countMe++;
                  }
                  if(g[j-2][i]==tmp){
                     countMe++;
                  }
                  if(g[j-3][i]==tmp){
                     countMe++;
                  }
                  if (g[j-1][i]==tmp&&g[j-2][i]==tmp&&g[j-3][i]==tmp){
                      countMe+=1000;
                  }
                  }
                  
                }
                  else if(tmp =='b'){
                      if(g[j-1][i]!='r' && g[j-2][i]!='r' && g[j-3][i]!='r'){
                      countOther++;
                      if(g[j-1][i]==tmp){
                    countOther++;
                  }
                  if(g[j-2][i]==tmp){
                   countOther++;
                  }
                  if(g[j-3][i]==tmp){
                  countOther++;
                  }
                   if (g[j-1][i]==tmp&&g[j-2][i]==tmp){
                      countOther+=100;
                      System.out.println("dangerrrr");
                  }
                  }
                  }
            }
                 
                 
                 
                 if(j>2&&i>=3){
                  char tmp=g[j][i];
                  
                   if(tmp=='r'){
                      
                  if(g[j-1][i-1]!='b' &&g[j-2][i-2]!='b'&& g[j-3][i-3]!='b'){
                      countMe++;
                       if(g[j-1][i-1]==tmp){
                      countMe++;
                  }
                  if(g[j-2][i-2]==tmp){
                      countMe++;
                  }
                   if(g[j-3][i-3]==tmp){
                      countMe++;
                  }
                   if((g[j-1][i-1]==tmp&&g[j-2][i-2]==tmp) &&g[j-3][i-3]==tmp){
                       countMe+=1000;
                   }
                  }
                 
                }
                   else if (tmp =='b'){
                      if(g[j-1][i-1]!='r' &&g[j-2][i-2]!='r'&& g[j-3][i-3]!='r'){
                      countOther++;
                       if(g[j-1][i-1]==tmp){
                      countOther++;
                  }
                  if(g[j-2][i-2]==tmp){
                      countOther++;
                  }
                   if(g[j-3][i-3]==tmp){
                     countOther++;
                  }
                   if((g[j-1][i-1]==tmp&&g[j-2][i-2]==tmp) || (g[j-1][i-1]==tmp&&g[j-3][i-3]==tmp)||(g[j-2][i-2]==tmp&&g[j-3][i-3]==tmp)){
                       countOther+=100;
                   }
                  }
                   }
              }
                  if(j>=3&&i<=3){
                  char tmp=g[j][i];
                  
                 
                  if(tmp=='r'){
                     if(g[j-1][i+1]!='b'&&g[j-2][i+2]!='b'&&g[j-3][i+3]!='b'){
                          countMe++;
                          if(g[j-1][i+1]==tmp){
                          countMe++;
                     }
                      if(g[j-2][i+2]==tmp){
                         countMe++;
                     }
                       if(g[j-3][i+3]==tmp){
                         countMe++;
                     }
                       if(g[j-1][i+1]==tmp&&g[j-2][i+2]==tmp&&g[j-3][i+3]==tmp){
                       countMe+=1000;
                   }
                     }
                 }
                  else if (tmp =='b'){
                      if(g[j-1][i+1]!='r'&&g[j-2][i+2]!='r'&&g[j-3][i+3]!='r'){
                         countOther++; 
                          if(g[j-1][i+1]==tmp){
                         countOther++;
                     }
                      if(g[j-2][i+2]==tmp){
                         countOther++;
                     }
                       if(g[j-3][i+3]==tmp){
                          countOther++;
                     }
                     if((g[j-1][i+1]==tmp&&g[j-2][i+2]==tmp) || (g[j-1][i+1]==tmp&&g[j-3][i+3]==tmp)||(g[j-2][i+2]==tmp&&g[j-3][i+3]==tmp)){
                       countOther+=100;
                   } 
                     }
                  }
              }
                  
                  
                  
                  
                  
             }
           }
  

  
      
   // System.out.println(countMe - countOther);
    return countMe - countOther; 
    
           }
     int evaluateFunctionHard() {
       int countMe = 0;
       int countOther = 0; 
       for(int j=5; j>=0;j--){
           for(int i=0; i<7;i++){
             
                if(g[j][i]=='n'){
                  continue;
              }
                 if(i<=3){
                  char tmp=g[j][i];
                  
                  if(tmp=='r'){
                     
                  
                  if(g[j][i+1]!='b'&&g[j][i+2]!='b'&&g[j][i+3]!='b'){
                      countMe++;
                       if(g[j][i+1]==tmp){
                      countMe++;
                  }
                  if(g[j][i+2]==tmp){
                      countMe++;
                  }
                  if(g[j][i+3]==tmp){
                      countMe++;
                  }
                   if (g[j][i+1]==tmp&&g[j][i+2]==tmp&&g[j][i+3]==tmp){
                      countMe+=1000;
                  }
                  }
                 
                 
                  }
                  else if (tmp =='b'){
                      if(g[j][i+1]!='r'&&g[j][i+2]!='r'&&g[j][i+3]!='r'){
                      countOther++;
                       if(g[j][i+1]==tmp){
                      countOther++;
                  }
                  if(g[j][i+2]==tmp){
                     countOther++;
                  }
                  if(g[j][i+3]==tmp){
                     countOther++;
                  }
                   if (g[j][i+1]==tmp&&g[j][i+2]==tmp&&g[j][i+3]==tmp){
                      countOther+=1000;
                  }
                  }
                      
                  }
              }
                 if(i>=3){
                  char tmp=g[j][i];
                  
                  if(tmp=='r'){
                     
                  
                  if(g[j][i-1]!='b'&&g[j][i-2]!='b'&&g[j][i-3]!='b'){
                      countMe++;
                       if(g[j][i-1]==tmp){
                      countMe++;
                  }
                  if(g[j][i-2]==tmp){
                      countMe++;
                  }
                  if(g[j][i-3]==tmp){
                      countMe++;
                  }
                   if (g[j][i-1]==tmp&&g[j][i-2]==tmp &&g[j][i-3]==tmp){
                      countMe+=1000;
                  }
                  }
                 
                 
                  }
                  else if (tmp =='b'){
                      if(g[j][i-1]!='r'&&g[j][i-2]!='r'&&g[j][i-3]!='r'){
                      countOther++;
                       if(g[j][i-1]==tmp){
                      countOther++;
                  }
                  if(g[j][i-2]==tmp){
                     countOther++;
                  }
                  if(g[j][i-3]==tmp){
                     countOther++;
                  }
                   if (g[j][i-1]==tmp&&g[j][i-2]==tmp &&g[j][i-3]==tmp){
                      countOther+=1000;
                  }
                  }
                      
                  }
              }
                 if(j>2){
                  char tmp=g[j][i];
                  
                  if(tmp=='r'){
                      
                  if(g[j-1][i]!='b' && g[j-2][i]!='b' && g[j-3][i]!='b'){
                      countMe++;
                      if(g[j-1][i]==tmp){
                     countMe++;
                  }
                  if(g[j-2][i]==tmp){
                     countMe++;
                  }
                  if(g[j-3][i]==tmp){
                     countMe++;
                  }
                  if (g[j-1][i]==tmp&&g[j-2][i]==tmp&&g[j-3][i]==tmp){
                      countMe+=1000;
                  }
                  }
                  
                }
                  else if(tmp =='b'){
                      if(g[j-1][i]!='r' && g[j-2][i]!='r' && g[j-3][i]!='r'){
                      countOther++;
                      if(g[j-1][i]==tmp){
                    countOther++;
                  }
                  if(g[j-2][i]==tmp){
                   countOther++;
                  }
                  if(g[j-3][i]==tmp){
                  countOther++;
                  }
                   if (g[j-1][i]==tmp&&g[j-2][i]==tmp&&g[j-3][i]==tmp){
                      countOther+=1000;
                  }
                  }
                  }
            }
                 
            if(j>2&&i>=3){
                  char tmp=g[j][i];
                  
                   if(tmp=='r'){
                      
                  if(g[j-1][i-1]!='b' &&g[j-2][i-2]!='b'&& g[j-3][i-3]!='b'){
                      countMe++;
                       if(g[j-1][i-1]==tmp){
                      countMe++;
                  }
                  if(g[j-2][i-2]==tmp){
                      countMe++;
                  }
                   if(g[j-3][i-3]==tmp){
                      countMe++;
                  }
                   if((g[j-1][i-1]==tmp&&g[j-2][i-2]==tmp) &&g[j-3][i-3]==tmp){
                       System.out.println("About to win");
                       countMe+=1000;
                   }
                  }
                 
                }
                   else if (tmp =='b'){
                      if(g[j-1][i-1]!='r' &&g[j-2][i-2]!='r'&& g[j-3][i-3]!='r'){
                      countOther++;
                       if(g[j-1][i-1]==tmp){
                      countOther++;
                  }
                  if(g[j-2][i-2]==tmp){
                      countOther++;
                  }
                   if(g[j-3][i-3]==tmp){
                     countOther++;
                  }
                   if((g[j-1][i-1]==tmp&&g[j-2][i-2]==tmp) &&g[j-3][i-3]==tmp){
                       countOther+=1000;
                   }
                  }
                   }
              }
                  if(j>=3&&i<=3){
                  char tmp=g[j][i];
                  
                 
                  if(tmp=='r'){
                     if(g[j-1][i+1]!='b'&&g[j-2][i+2]!='b'&&g[j-3][i+3]!='b'){
                          countMe++;
                          if(g[j-1][i+1]==tmp){
                          countMe++;
                     }
                      if(g[j-2][i+2]==tmp){
                         countMe++;
                     }
                       if(g[j-3][i+3]==tmp){
                         countMe++;
                     }
                       if(g[j-1][i+1]==tmp&&g[j-2][i+2]==tmp&&g[j-3][i+3]==tmp){
                       countMe+=1000;
                   }
                     }
                 }
                  else if (tmp =='b'){
                      if(g[j-1][i+1]!='r'&&g[j-2][i+2]!='r'&&g[j-3][i+3]!='r'){
                         countOther++; 
                          if(g[j-1][i+1]==tmp){
                         countOther++;
                     }
                      if(g[j-2][i+2]==tmp){
                         countOther++;
                     }
                       if(g[j-3][i+3]==tmp){
                          countOther++;
                     }
                      if(g[j-1][i+1]==tmp&&g[j-2][i+2]==tmp&&g[j-3][i+3]==tmp){
                       countOther+=1000;
                   } 
                     }
                  }
              }
                  
                  
                  
                  
                  
             }
           }
    return countMe - countOther; 
    
           }


     

    boolean isTerminalNode() {
         for (int i =0; i<6; i++){
                for(int j =0; j<7; j++){
                    if (g[i][j] == 'n')
                        return false;
                }
    }
         return true; 
}
}


