public abstract class Graph
{
    private Vertex[] vertices;
    private int[][] edges;
    private int number;

    public Graph(int max)
    {
        vertices = new Vertex[max];
        edges = new int[max][max];
        number = 0;
    }

    public Graph(Vertex[] vertices, int[][] edges){
        this.vertices = vertices;
        this.edges = edges;
        number = vertices.length;
    }

    public int size(){
        return number;
    }

    public void add(Vertex vertex){
        if(number < vertices.length){
            vertices[number] = vertex;
            number ++;
            return;
        }
        System.out.println("out of bounds");
    }

    public void setEdge(int r, int c, int value){
        edges[r][c] = value;
    }

    private int getIdxOf(String id){
        for(int i = 0; i < number; i++){
            if(vertices[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public void printMatrix(){
        System.out.print("\t");
        for(int c = 0; c < number; c++){
            System.out.print(vertices[c].getId() + "\t");
        }
        System.out.println();
        for(int r = 0; r < number; r++){
            System.out.print(vertices[r].getId() + "\t");
            for(int c = 0; c < number; c++){
                System.out.print(edges[r][c]+ "\t");
            }
            System.out.println();
        }
    }

    public Vector2 costAndSteps(String startId, String endId){
        if(!pathExists(startId,endId)){
            System.out.println("no path");
            return new Vector2(-1,-1);
        }
        System.out.println("found path");

        /*
         * DIJKSTRA ALGORITHMUS 
         * implementiert nach
         * https://youtu.be/KiOso3VE-vI
         * durch @Felix
         */

        LinkedList costs = new LinkedList();
        LinkedList predecessor = new LinkedList();
        LinkedList visited = new LinkedList();

        for(int i = 0; i < number; i++){
            costs.add(99999);
            predecessor.add(-1);
        }

        int startIdx = getIdxOf(startId);
        int endIdx = getIdxOf(endId);

        costs.set(startIdx, 0);
        predecessor.set(startIdx, startIdx);

        int curIdx = startIdx;

        while(visited.size() < number){
            for(int i = 0; i < number; i++){
                if(edges[curIdx][i] > 0 && !visited.contains(i)){
                    if((int)costs.get(i) > (int)costs.get(curIdx) + edges[curIdx][i]){
                        costs.set(i, (int)costs.get(curIdx) + edges[curIdx][i]);
                        predecessor.set(i, curIdx);
                    }
                }
            }
            visited.add(curIdx);

            //get vertex with lowest cost & was not visited
            int idx = 0;
            int cost = -1;
            if(visited.size() < number){
                for(int i = 0; i < costs.size(); i++){
                    if(!visited.contains(i) && (cost == -1 || (int)costs.get(i) < cost)){
                        idx = i;
                        cost = (int)costs.get(i);
                    }
                }
            }
            curIdx = idx;
        }

        curIdx = (int)predecessor.get(endIdx);
        int steps = 1;
        while(curIdx != startIdx){
            curIdx = (int)predecessor.get(curIdx);
            steps++;
        }

        System.out.println((int)costs.get(endIdx) + ": " + steps);
        return new Vector2((int)costs.get(endIdx), steps);
    }

    public boolean pathExists(String startId, String endId){
        int startIdx = getIdxOf(startId);
        int endIdx = getIdxOf(endId);

        LinkedList visited = new LinkedList();
        return pathExistsIdx(startIdx, endIdx, visited);
    }

    private boolean pathExistsIdx(int startIdx, int endIdx, LinkedList visited){
        System.out.println("> searching path: " + startIdx + "-" + endIdx);
        visited.add(startIdx);
        System.out.println(edges[startIdx][endIdx]);
        if (startIdx == endIdx){
            return true;
        }
        for (int i = 0; i < number; i++) {
            if(!visited.contains(i) && edges[startIdx][i] > 0){
                boolean path = pathExistsIdx(i, endIdx, visited);
                if(path){
                    return true;
                }
            }
        }
        return false;
    }

    public Vertex get(int idx){
        return vertices[idx];
    }

    public boolean contains(String name){
        return getVertex(name) != null;
    }

    public Vertex getVertex(String name){
        for(int i = 0; i < number; i++){
            if(vertices[i].getName().equals(name)){
                return vertices[i];
            }
        }
        return null;
    }
}
