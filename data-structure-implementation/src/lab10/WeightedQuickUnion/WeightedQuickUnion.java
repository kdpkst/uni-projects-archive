package lab10.WeightedQuickUnion;

public class WeightedQuickUnion {

    private int[] parent;

    /*
     * Returns the parent of element p.
     * If p is the root of a tree, returns the negative size
     * of the tree for which p is the root.
     */
    public int parent(int p) {
        return parent[p];
    }

    /* Prints the parents of the elements, separated by a space */
    public void printParent() {
        for (int element : parent) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    /*
     ***************************
     * DO NOT MODIFY CODE ABOVE
     ***************************
     */


    /*
     ***** HELPER METHODS START *****
     */

    // Add your own helper methods here
	// INCLUDE your helper methods in your submission !
	
	
	

    /*
     ***** HELPER METHODS END *****
     */


    // LAB EXERCISE #10.3  CONSTRUCTOR

    /**
     * Creates a Union Find data structure with n elements,
     * 0 through n-1.
     * Initially, each element is in its own set.
     * @param N the number of elements
     */
    public WeightedQuickUnion(int N) {
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
    }


    // LAB EXERCISE #10.4 VALIDATE

    /**
     * Validates that p is a valid element/index.
     * @throws IllegalArgumentException if p is not a valid index.
     */
//    @SuppressWarnings("unchecked")
    public void validate(int p){
        if ( p < 0 || p >= parent.length){
            throw new IllegalArgumentException();
        }
    }


    // CODING ASSIGNMENT #10.1  SIZE OF

    /**
     * Returns the size of the set element p belongs to.
     * @param p an element
     * @return the size of the set containing p
     */
    public int sizeOf(int p) {
        this.validate(p);
        while (parent(p) >= 0){
            p = parent(p);
        }
        return -parent[p];
    }
	
	
	// CODING ASSIGNMENT #10.2  IS SAME GROUP

    /**
     * Returns true iff elements p is in the same group as q.
     * @param p an element
     * @param q the other element
     * @return true if p and q are in the same group
     *         false otherwise
     * @throws IllegalArgumentException if p or q is not a valid index.
     */
    public boolean isSameGroup(int p, int q) {
        this.validate(p);
        this.validate(q);
        while(parent(p)>=0){
            p=parent(p);
        }

        while(parent(q)>=0){
            q=parent(q);
        }

        if(p==q){
            return true;
        }

        return false;
    }


    // CODING ASSIGNMENT #10.3  UNION

    /**
     * Combines two elements p and q together,
     * by combining the sets containing them.
     * @param p an element
     * @param q the other element
     * @throws IllegalArgumentException if p or q is not a valid index.
     */
    public void union(int p, int q) {

        this.validate(p);
        this.validate(q);
        int sizeP = this.sizeOf(p);
        int sizeQ = this.sizeOf(q);

        while(parent(p) >= 0){
            p = parent(p);
        }
        while(parent(q) >= 0){
            q = parent(q);
        }
        if(p == q){
            return;
        }

        // now, p and q is the root of each set
        if(sizeP > sizeQ){
            parent[q] = p;
            parent[p] = -(sizeP + sizeQ);
        }else{
            parent[p] = q;
            parent[q] = -(sizeP + sizeQ);

        }

    }


    public static void main(String[] args) {
        WeightedQuickUnion uf = new WeightedQuickUnion(4);
        uf.union(1, 0);
        uf.union(3, 2);
        uf.union(3, 1);
        uf.printParent();
    }

}
