public class DynamicMatrix{
    public int[][] array;
    public int size;

    public DynamicMatrix(){
        this.size = 0;
    }

    public void creat(){
        this.array = new int[1][1];
        this.array[0][0] = 0;
        this.size++;
    }

    public void increaseSize(){
        int n = this.size;
        int[][] newM = new int[2*n+1][];

        for(int i = 0; i < this.array.length; i++){
            newM[i] = this.array[i];
        }

        newM[ 2*n-1 ] = new int[ this.array[ this.array.length-1 ].length ];
        newM[ 2*n ] = new int[ this.array[ this.array.length-1 ].length+1 ];

        for(int i = 0; i < newM[ 2*n-1 ].length; i++){
            newM[ 2*n-1 ][i] = 0;
        }
        
        for(int i = 0; i < newM[ 2*n ].length; i++){
            newM[ 2*n ][i] = 0;
        }
        this.array = newM;
        this.size++;
    }

    public String toString(){
        for(int i = 1; i <= this.size; i++){
            for(int j = 1; j <= this.size; j++){
                System.out.print(this.get(i,j) + " ");
            }
            System.out.print("\n");
        }

        return "";
    }

    public void test(){
        System.out.println("Test: ");
        for(int i = 0; i < this.array.length; i++){
            for(int j = 0; j < this.array[i].length; j++){
            System.out.print(this.array[i][j]);
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    public void set(int i, int j, int value){
        if( j >= i){
            this.array[2*(j-1)][i-1] = value;
        }else{
            this.array[2*i - 3][j-1] = value;
        }
    }

    public int get(int i, int j){
        // System.out.println("row " + i + " column " +j);
        if( j >= i){
            return this.array[2*(j-1)][i-1];
        }else{
            // System.out.println("The index is "+(2*i - 3));
            return this.array[2*i - 3][j-1];
        }
    }

    public static void main(String[] args){
        DynamicMatrix d = new DynamicMatrix();
        d.creat();
        
        d.set(1,1, 5);
        d.increaseSize();
        d.test();
        System.out.println(d);
        
        d.increaseSize();
        d.test();
        System.out.println(d);
        
        
        d.increaseSize();
        d.test();
        d.set(2,4, 5);
        System.out.println(d);

        d.increaseSize();
        d.test();
        System.out.println(d);

        d.increaseSize();
        d.test();
        System.out.println(d);

        d.increaseSize();
        d.test();
        System.out.println(d);
    }
}