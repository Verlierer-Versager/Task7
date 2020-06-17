package GraphImplementation;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {
        Graph gr = new Graph(5);

        gr.addVertex("a");
        gr.addVertex("b");
        gr.addVertex("c");
        gr.addVertex("d");
        gr.addVertex("e");
        gr.addVertex("f");

        gr.addEdge("a", "b");
        gr.addEdge("a", "d");
        gr.addEdge("e", "a");
        gr.addEdge("e", "c");
        gr.addEdge("f", "a");

        ArrayList<String> list = gr.solution("a");
        for (String st : list) {
            System.out.print(st + " ");
        }
    }
}
