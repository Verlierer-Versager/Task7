package GraphImplementation;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {

    private class Vertex {
        private String name;
        private boolean isReachable = false;

        public Vertex(String name) {
            this.name = name;
        }

        public boolean isReachable() {
            return isReachable;
        }

        public String getName() {
            return name;
        }

        public void setReachable(boolean reachable) {
            isReachable = reachable;
        }

    }

    private int capacity = 10;
    private Vertex[] vArr;
    private boolean[][] adjMatrix;
    private int size = 0;

    public Graph() {
        vArr = new Vertex[capacity];
        adjMatrix = new boolean[capacity][capacity];
    }

    public Graph(int capacity) {
        this.capacity = capacity;
        vArr = new Vertex[capacity];
        adjMatrix = new boolean[capacity][capacity];
    }

    private void increaseAdjMatrix() {
        boolean[][] newM = new boolean[capacity][capacity];
        for (int i = 0; i < size; i++) {
            newM[i] = Arrays.copyOf(adjMatrix[i], capacity);
        }
        this.adjMatrix = newM;
    }

    public boolean addVertex(String name) throws Exception {
        if (isVertexAdded(new Vertex(name))) {
            throw new Exception("Vertex already added");
        }
        if (size == capacity) {
            capacity = (int) (capacity * 1.5);
            vArr = Arrays.copyOf(vArr, capacity);
            increaseAdjMatrix();
        }
        vArr[size] = new Vertex(name);
        size++;
        return true;
    }

    public boolean addEdge(String v1, String v2) {
        if (isVertexAdded(new Vertex(v1)) && isVertexAdded(new Vertex(v2))) {
            int i = getIndex(new Vertex(v1));
            int j = getIndex(new Vertex(v2));
            adjMatrix[i][j] = true;
            return true;
        }
        return false;
    }

    private int getIndex(Vertex v1) {
        if (!isVertexAdded(v1)) {
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (vArr[i].getName().equals(v1.getName())) return i;
        }

        return -1;
    }

    private boolean isVertexAdded(Vertex v1) {
        for (int i = 0; i < size; i++) {
            if (v1.getName().equals(vArr[i].getName())) return true;
        }
        return false;
    }

    public ArrayList<String> solution(String v1) {
        int index = getIndex(new Vertex(v1));
        ArrayList<String> vertex = new ArrayList<>();
        dfs(v1);
        for (int i = 0; i < size; i++) {
            if (!vArr[i].isReachable()) vertex.add(vArr[i].getName());
        }
        return vertex;
    }

    private void dfs(String v1) {
        int index = getIndex(new Vertex(v1));
        vArr[index].setReachable(true);
        for (int i = 0; i < size; i++) {
            if (adjMatrix[index][i] && !vArr[i].isReachable()) {
                dfs(vArr[i].getName());
            }
        }
    }
}
